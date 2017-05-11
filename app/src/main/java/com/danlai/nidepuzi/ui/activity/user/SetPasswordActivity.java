package com.danlai.nidepuzi.ui.activity.user;

import android.os.Bundle;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivitySetPasswordBinding;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.util.LoginUtils;

/**
 * @author wisdom
 * @date 2017年05月11日 下午1:54
 */

public class SetPasswordActivity extends BaseMVVMActivity<ActivitySetPasswordBinding>
    implements View.OnClickListener {
    private String mobile, code;

    @Override
    protected void setListener() {
        b.btnSetPassword.setOnClickListener(this);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mobile = extras.getString("mobile");
        code = extras.getString("code");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_set_password;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    private boolean checkInput(String name) {
        if (name.length() < 6 || name.length() > 16) {
            JUtils.Toast("请输入6-16位密码");
            return false;
        }
        return true;
    }

    private boolean checkInputSame(String pass1, String pass2) {
        if (!pass1.equals(pass2)) {
            JUtils.Toast("两次密码不一致");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set_password:
                String password1 = b.etPassword1.getText().toString().trim();
                String password2 = b.etPassword2.getText().toString().trim();
                if (checkInput(password1) && checkInput(password2)) {
                    if (checkInputSame(password1, password2)) {
                        changePassword(mobile, code, password1, password2);
                    }
                }
                break;
        }
    }

    private void changePassword(String mobile, String code, String password1, String password2) {
        BaseApp.getUserInteractor(this)
            .resetPassword(mobile, password1, password2, code, new ServiceResponse<CodeBean>(mBaseActivity) {
                @Override
                public void onNext(CodeBean bean) {
                    if (bean.getRcode() == 0) {
                        LoginUtils.delLoginInfo(mBaseActivity);
                        JUtils.Toast("密码重置成功,请重新登录!");
                        readyGoThenKill(LoginActivity.class);
                    } else {
                        JUtils.Toast(bean.getMsg());
                    }
                }
            });
    }
}
