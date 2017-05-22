package com.danlai.nidepuzi.ui.activity.main;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.danlai.library.utils.FileUtils;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseAppManager;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.base.BaseWebViewActivity;
import com.danlai.nidepuzi.databinding.ActivityTabBinding;
import com.danlai.nidepuzi.entity.AddressDownloadResultBean;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.VersionBean;
import com.danlai.nidepuzi.entity.event.LoginEvent;
import com.danlai.nidepuzi.receiver.UpdateBroadReceiver;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.service.UpdateService;
import com.danlai.nidepuzi.ui.activity.user.LoginActivity;
import com.danlai.nidepuzi.ui.fragment.main.EduTabFragment;
import com.danlai.nidepuzi.ui.fragment.main.MainTabFragment;
import com.danlai.nidepuzi.ui.fragment.main.ServiceTabFragment;
import com.danlai.nidepuzi.ui.fragment.main.ShopTabFragment;
import com.danlai.nidepuzi.util.FragmentTabUtils;
import com.danlai.nidepuzi.util.JumpUtils;
import com.danlai.nidepuzi.util.LoginUtils;
import com.danlai.nidepuzi.util.VersionManager;
import com.qiyukf.nimlib.sdk.NimIntent;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import retrofit2.HttpException;


public class TabActivity extends BaseActivity {
    protected ActivityTabBinding b;
    private long firstTime = 0;
    private UpdateBroadReceiver mUpdateBroadReceiver;
    private FragmentTabUtils mFragmentTabUtils;
    private List<BaseFragment> fragments;
    public Handler mHandler;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.hasExtra(NimIntent.EXTRA_NOTIFY_CONTENT)) {
            // 打开客服窗口
            Unicorn.openServiceActivity(this, "铺子客服", new ConsultSource("https://m.nidepuzi.com", "Android客户端", "Android客户端"));
            // 最好将intent清掉，以免从堆栈恢复时又打开客服窗口
            setIntent(new Intent());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.main_in, R.anim.main_out);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View getLoadingView() {
        return b.container;
    }

    @Override
    public void initContentView() {
        b = DataBindingUtil.setContentView(this, getContentViewLayoutID());
    }

    @Override
    protected void initData() {
        mFragmentTabUtils = new FragmentTabUtils(getSupportFragmentManager(), b.radioGroup, fragments, R.id.container, this);
        downLoadAddress();
        checkVersion();
        initService();
        LoginUtils.registerMiPush(mBaseActivity);
        LoginUtils.clearCacheEveryWeek(mBaseActivity);
    }

    private void initService() {
        BaseApp.getMainInteractor(mBaseActivity)
            .getProfile(new ServiceResponse<UserInfoBean>(mBaseActivity) {
                @Override
                public void onNext(UserInfoBean userInfoBean) {
                    String data = "[ " +
                        "{\"key\":\"real_name\", \"value\":\"" + userInfoBean.getNick() + "\"}, " +
                        "{\"key\":\"mobile_phone\", \"value\":\"" + userInfoBean.getMobile() + "\"}, " +
                        "{\"key\":\"avatar\", \"value\": \"" + userInfoBean.getThumbnail() + "\"}]";
                    mFragmentTabUtils.setUserInfo(data, userInfoBean.getUser_id());
                    UserInfoBean.XiaolummBean bean = userInfoBean.getXiaolumm();
                    if (bean != null && "effect".equals(bean.getStatus())) {
                        if (bean.getLast_renew_type() == 15) {
                            String renewTime = bean.getRenew_time().replace("T", " ");
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                Date date = sdf.parse(renewTime);
                                long time = date.getTime() - new Date().getTime();
                                int day = (int) (time / 1000 / 60 / 60 / 24);
                                if (time / 1000 / 60 / 60 / 24 > 0) {
                                    day += 1;
                                }
                                Dialog dialog = new Dialog(mBaseActivity, R.style.CustomDialog);
                                dialog.setContentView(R.layout.pop_vip_msg);
                                dialog.setCancelable(true);
                                ((TextView) dialog.findViewById(R.id.tv_day)).setText("" + day);
                                dialog.findViewById(R.id.tv_join).setOnClickListener(v -> {
                                    dialog.dismiss();
                                    JumpUtils.jumpToWebViewWithCookies(mBaseActivity,
                                        "https://m.nidepuzi.com/mall/boutiqueinvite2", -1, BaseWebViewActivity.class);
                                });
                                dialog.findViewById(R.id.iv_cancel).setOnClickListener(v -> dialog.dismiss());
                                dialog.show();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ((BaseApp) getApplication()).setShow(true);
                        }
                    } else {
                        LoginUtils.delLoginInfo(mBaseActivity);
                        readyGoThenKill(LoginActivity.class);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    if (e instanceof HttpException && ((HttpException) e).code() == 403) {
                        LoginUtils.delLoginInfo(mBaseActivity);
                        readyGoThenKill(LoginActivity.class);
                    } else {
                        mVaryViewHelperController.showNetworkError(view -> {
                            refreshView();
                            showNetworkError();
                        });
                        JUtils.Toast("信息获取失败,请点击重试!");
                    }
                }
            });
    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
        setSwipeBackEnable(false);
        fragments = new ArrayList<>();
//        fragments.add(TodayNewFragment.newInstance("每日焦点"));
        fragments.add(MainTabFragment.newInstance());
        fragments.add(EduTabFragment.newInstance());
        fragments.add(ShopTabFragment.newInstance());
        fragments.add(ServiceTabFragment.newInstance());
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.main_in, R.anim.main_out);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_tab;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 1000) {
                firstTime = secondTime;
                JUtils.Toast("再按一次退出程序!");
                return true;
            } else {
                BaseAppManager.getInstance().clear();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void checkVersion() {
        BaseApp.getMainInteractor(this)
            .getVersion(new ServiceResponse<VersionBean>(mBaseActivity) {
                @Override
                public void onNext(VersionBean versionBean) {
                    if (versionBean != null) {
                        checkVersion(versionBean);
                    }
                }
            });
    }

    private void checkVersion(VersionBean versionBean) {
        VersionManager versionManager = VersionManager.newInstance(versionBean.getVersion_code(),
            ("最新版本:" + versionBean.getVersion() + "\n\n更新内容:\n" + versionBean.getMemo()), false);
        if (versionBean.isAuto_update()) {
            versionManager.setPositiveListener(v -> {
                Intent intent = new Intent(TabActivity.this, UpdateService.class);
                intent.putExtra(UpdateService.EXTRAS_DOWNLOAD_URL, versionBean.getDownload_link());
                intent.putExtra(UpdateService.VERSION_CODE, versionBean.getVersion_code());
                startService(intent);
                JUtils.Toast("应用正在后台下载!");
            });
            versionManager.setNegativeListener(v -> {
                versionManager.getDialog().dismiss();
                finish();
            });
            versionManager.setIgnoreStr("退出应用");
            versionManager.checkVersion(TabActivity.this, versionBean.getVersion_code());
            versionBean.getVersion_code();
        }
    }

    private void downLoadAddress() {
        BaseApp.getMainInteractor(this)
            .getAddressVersionAndUrl(new ServiceResponse<AddressDownloadResultBean>(mBaseActivity) {
                @Override
                public void onNext(AddressDownloadResultBean addressDownloadResultBean) {
                    if (addressDownloadResultBean != null) {
                        String downloadUrl = addressDownloadResultBean.getDownloadUrl();
                        String hash = addressDownloadResultBean.getHash();
                        if (!FileUtils.isAddressFileHashSame(getApplicationContext(), hash) ||
                            !FileUtils.isFileExist(BaseConst.BASE_DIR + "areas.json")) {
                            OkHttpUtils.get().url(downloadUrl).build()
                                .execute(new FileCallBack(BaseConst.BASE_DIR, "areas.json") {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        if (FileUtils.isFolderExist(BaseConst.BASE_DIR + "areas.json")) {
                                            FileUtils.deleteFile(BaseConst.BASE_DIR + "areas.json");
                                        }
                                    }

                                    @Override
                                    public void onResponse(File response, int id) {
                                        FileUtils.saveAddressFile(getApplicationContext(), hash);
                                    }
                                });
                        }
                    }
                }
            });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(UpdateBroadReceiver.ACTION_RETRY_DOWNLOAD);
        mUpdateBroadReceiver = new UpdateBroadReceiver();
        registerReceiver(mUpdateBroadReceiver, filter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshLogin(LoginEvent event) {
        initService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mUpdateBroadReceiver);
    }
}
