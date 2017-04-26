package com.danlai.nidepuzi.ui.activity.user;

import android.os.Bundle;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityEditPasswordBinding;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.danlai.nidepuzi.util.LoginUtils;


public class EditPasswordActivity extends BaseMVVMActivity<ActivityEditPasswordBinding>
    implements View.OnClickListener {
    String username;
    String valid_code;

    @Override
    protected void setListener() {
        b.btnCommit.setOnClickListener(this);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        username = extras.getString("username");
        valid_code = extras.getString("valid_code");
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_edit_password;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                String password1 = b.setPassword.getText().toString().trim();
                String password2 = b.resetPassword.getText().toString().trim();
                if (checkInput(password1) && checkInput(password2)) {
                    if (checkInputSame(password1, password2)) {
                        changePassword(username, valid_code, password1, password2);
                    }
                }
                break;
        }
    }

    private boolean checkInput(String name) {
        if (name.length() < 4 || name.length() > 20) {
            JUtils.Toast("请输入6-16位密码");
            return false;
        }
        return true;
    }

    private void changePassword(String username, String valid_code, String password1,
                                String password2) {
        BaseApp.getUserInteractor(this)
            .resetPassword(username, password1, password2, valid_code, new ServiceResponse<CodeBean>(mBaseActivity) {
                @Override
                public void onNext(CodeBean codeBean) {
                    if (codeBean.getRcode() == 0) {
                        BaseApp.getUserInteractor(EditPasswordActivity.this)
                            .passwordLogin(username, password1, null, new ServiceResponse<CodeBean>(mBaseActivity) {
                                @Override
                                public void onNext(CodeBean codeBean1) {
                                    if (codeBean1.getRcode() == 0) {
                                        LoginUtils.saveLoginInfo(true, mBaseActivity, username,
                                            password1);
                                        JUtils.Toast("密码重置成功,登录成功");
                                        readyGoThenKill(TabActivity.class);
                                    } else {
                                        JUtils.Toast(codeBean1.getMsg());
                                    }
                                }
                            });
                    } else {
                        JUtils.Toast("修改失败");
                    }
                }
            });
    }

    private boolean checkInputSame(String pass1, String pass2) {
        if (!pass1.equals(pass2)) {
            JUtils.Toast("两次密码不一致");
            return false;
        }
        return true;
    }

}
