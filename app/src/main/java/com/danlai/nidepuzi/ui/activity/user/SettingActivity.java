package com.danlai.nidepuzi.ui.activity.user;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.danlai.library.utils.DataClearManager;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivitySettingBinding;
import com.danlai.nidepuzi.entity.LogoutBean;
import com.danlai.nidepuzi.entity.VersionBean;
import com.danlai.nidepuzi.entity.event.LogoutEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.service.UpdateService;
import com.danlai.nidepuzi.ui.activity.main.AboutCompanyActivity;
import com.danlai.nidepuzi.util.LoginUtils;
import com.danlai.nidepuzi.util.VersionManager;

import org.greenrobot.eventbus.EventBus;

/**
 * @author wisdom
 * @date 2017年04月24日 下午5:53
 */

public class SettingActivity extends BaseMVVMActivity<ActivitySettingBinding> implements View.OnClickListener {

    @Override
    protected void setListener() {
        b.clear.setOnClickListener(this);
        b.version.setOnClickListener(this);
        b.tvLogout.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        updateCache();
        b.version.setSummary(JUtils.getAppVersionName());
        b.user.bindActivity(mBaseActivity, InformationActivity.class, null);
        b.about.bindActivity(mBaseActivity, AboutCompanyActivity.class, null);
        b.bindPhone.bindActivity(mBaseActivity, LoginBindPhoneActivity.class, null);
        b.password.bindActivity(mBaseActivity, VerifyPhoneForgetActivity.class, null);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear:
                DataClearManager.cleanApplicationData(BaseApp.getInstance());
                Snackbar snackbar = Snackbar.make(getWindow().getDecorView(), "缓存已清理", Snackbar.LENGTH_SHORT);
                View view = snackbar.getView();
                ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.colorAccent));
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.white));
                snackbar.show();
                updateCache();
                break;
            case R.id.version:
                BaseApp.getUserInteractor(this)
                    .getVersion(new ServiceResponse<VersionBean>(mBaseActivity) {
                        @Override
                        public void onNext(VersionBean versionBean) {
                            VersionManager versionManager = VersionManager.newInstance(versionBean.getVersion_code(),
                                "最新版本:" + versionBean.getVersion() + "\n\n更新内容:\n" + versionBean.getMemo(), true);
                            versionManager.setPositiveListener(v -> {
                                Intent intent = new Intent(SettingActivity.this, UpdateService.class);
                                intent.putExtra(UpdateService.EXTRAS_DOWNLOAD_URL, versionBean.getDownload_link());
                                intent.putExtra(UpdateService.VERSION_CODE, versionBean.getVersion_code());
                                SettingActivity.this.startService(intent);
                                versionManager.getDialog().dismiss();
                                JUtils.Toast("应用正在后台下载!");
                            });
                            versionManager.checkVersion(SettingActivity.this, versionBean.getVersion_code());
                        }
                    });
                break;
            case R.id.tv_logout:
                new AlertDialog.Builder(this)
                    .setTitle("注销登录")
                    .setMessage("您确定要退出登录吗？")
                    .setPositiveButton("注销", (dialog, which) -> {
                        BaseApp.getUserInteractor(mBaseActivity)
                            .customerLogout(new ServiceResponse<LogoutBean>(mBaseActivity) {
                                @Override
                                public void onNext(LogoutBean responseBody) {
                                    if (responseBody.getCode() == 0) {
                                        JUtils.Toast("退出成功");
                                        EventBus.getDefault().post(new LogoutEvent());
                                        LoginUtils.delLoginInfo(getApplicationContext());
                                        readyGoThenKill(LoginActivity.class);
                                    }
                                }
                            });
                        dialog.dismiss();
                    })
                    .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                    .show();
                break;
        }

    }

    private void updateCache() {
        b.clear.setSummary(DataClearManager.getApplicationDataSize(BaseApp.getInstance()));
    }
}
