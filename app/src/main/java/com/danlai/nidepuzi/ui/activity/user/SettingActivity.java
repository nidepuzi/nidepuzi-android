package com.danlai.nidepuzi.ui.activity.user;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.danlai.library.utils.DataClearManager;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivitySettingBinding;
import com.danlai.nidepuzi.entity.VersionBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.service.UpdateService;
import com.danlai.nidepuzi.util.VersionManager;

/**
 * @author wisdom
 * @date 2017年04月24日 下午5:53
 */

public class SettingActivity extends BaseMVVMActivity<ActivitySettingBinding> implements View.OnClickListener {

    @Override
    protected void setListener() {
        b.stop.setSwitchListener((buttonView, isChecked) -> {
            if (isChecked) {
                JUtils.Toast("已关闭广告推送!");
            } else {
                JUtils.Toast("广告推送已开启!");
            }
        });
        b.mode.setSwitchListener((buttonView, isChecked) -> {
            if (isChecked) {
                JUtils.Toast("兼容模式已开启!");
            } else {
                JUtils.Toast("兼容模式已关闭!");
            }
        });
        b.clear.setOnClickListener(this);
        b.version.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        updateCache();
        b.version.setSummary(JUtils.getAppVersionName());
        b.user.bindActivity(mBaseActivity, InformationActivity.class, null);
        b.safe.bindActivity(mBaseActivity, SafeActivity.class, null);
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
        }

    }

    private void updateCache() {
        b.clear.setSummary(DataClearManager.getApplicationDataSize(BaseApp.getInstance()));
    }
}
