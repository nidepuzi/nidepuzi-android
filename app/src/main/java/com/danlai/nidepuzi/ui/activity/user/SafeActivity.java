package com.danlai.nidepuzi.ui.activity.user;

import android.support.v7.app.AlertDialog;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivitySafeBinding;
import com.danlai.nidepuzi.entity.LogoutBean;
import com.danlai.nidepuzi.entity.event.LogoutEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.util.LoginUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * @author wisdom
 * @date 2017年04月28日 上午9:23
 */

public class SafeActivity extends BaseMVVMActivity<ActivitySafeBinding> implements View.OnClickListener {

    @Override
    protected void setListener() {
        b.tvLogout.setOnClickListener(this);
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_safe;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
}
