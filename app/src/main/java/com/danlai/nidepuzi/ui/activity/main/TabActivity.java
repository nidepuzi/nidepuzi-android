package com.danlai.nidepuzi.ui.activity.main;

import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.danlai.library.utils.FileUtils;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseAppManager;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.ActivityTabBinding;
import com.danlai.nidepuzi.entity.AddressDownloadResultBean;
import com.danlai.nidepuzi.entity.VersionBean;
import com.danlai.nidepuzi.entity.event.LogoutEvent;
import com.danlai.nidepuzi.receiver.UpdateBroadReceiver;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.service.UpdateService;
import com.danlai.nidepuzi.ui.fragment.main.MainTabFragment;
import com.danlai.nidepuzi.ui.fragment.main.ShopTabFragment;
import com.danlai.nidepuzi.util.FragmentTabUtils;
import com.danlai.nidepuzi.util.VersionManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;


public class TabActivity extends BaseActivity {
    protected ActivityTabBinding b;
    private long firstTime = 0;
    private UpdateBroadReceiver mUpdateBroadReceiver;
    public Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.main_in, R.anim.main_out);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    public void initContentView() {
        b = DataBindingUtil.setContentView(this, getContentViewLayoutID());
    }

    @Override
    protected void initData() {
        downLoadAddress();
//        checkVersion();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
        setSwipeBackEnable(false);
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(MainTabFragment.newInstance());
        fragments.add(MainTabFragment.newInstance());
        fragments.add(ShopTabFragment.newInstance());
        fragments.add(MainTabFragment.newInstance());
        new FragmentTabUtils(getSupportFragmentManager(), b.radioGroup, fragments, R.id.container, this);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void initLogOutInfo(LogoutEvent event) {
        b.radioGroup.check(R.id.rb_main);
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

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mUpdateBroadReceiver);
    }
}
