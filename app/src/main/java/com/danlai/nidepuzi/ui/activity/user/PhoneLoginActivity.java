package com.danlai.nidepuzi.ui.activity.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.base.BaseWebViewActivity;
import com.danlai.nidepuzi.databinding.ActivityPhoneLoginBinding;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.danlai.nidepuzi.util.JumpUtils;
import com.danlai.nidepuzi.util.LoginUtils;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.wechat.friends.Wechat;

public class PhoneLoginActivity extends BaseMVVMActivity<ActivityPhoneLoginBinding>
    implements View.OnClickListener, TextWatcher {
    String login_name_value;//登录名
    String login_pass_value;//登录密码

    @Override
    protected void setListener() {
        b.setLoginButton.setOnClickListener(this);
        b.forgetTextView.setOnClickListener(this);
        b.setLoginName.addTextChangedListener(this);
        b.tvVip.setOnClickListener(this);
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected void initData() {
        if (!LoginUtils.checkLoginState(getApplicationContext())) {
            removeWX(new Wechat(this));
        }
        String[] loginInfo = LoginUtils.getLoginInfo(getApplicationContext());
        b.setLoginName.setText(loginInfo[0]);
        b.setLoginPassword.setText(loginInfo[1]);
    }

    public void removeWX(Platform platform) {
        if (platform != null) {
            platform.removeAccount(true);
            platform.removeAccount();
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_phone_login;
    }

    @Override
    protected void initViews() {
        b.forgetTextView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        b.tvVip.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_login_button:
                login_name_value = b.setLoginName.getText().toString().trim();
                login_pass_value = b.setLoginPassword.getText().toString().trim();
                if (checkInput(login_name_value, login_pass_value)) {
                    showIndeterminateProgressDialog(false);
                    SharedPreferences sharedPreferences =
                        getSharedPreferences("password", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (b.cbPwd.isChecked()) {
                        editor.putString(login_name_value, login_pass_value);
                    } else {
                        editor.remove(login_name_value);
                    }
                    editor.apply();
                    BaseApp.getUserInteractor(this)
                        .passwordLogin(login_name_value, login_pass_value, null, new ServiceResponse<CodeBean>(mBaseActivity) {
                            @Override
                            public void onNext(CodeBean codeBean) {
                                hideIndeterminateProgressDialog();
                                JUtils.Toast(codeBean.getMsg());
                                if (codeBean.getRcode() == 0) {
                                    LoginUtils.saveLoginSuccess(true, mBaseActivity);
                                    readyGoThenKill(TabActivity.class);
                                } else {
                                    LoginUtils.delLoginInfo(mBaseActivity);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                JUtils.Toast("登录失败");
                                hideIndeterminateProgressDialog();
                            }
                        });
                }
                break;
            case R.id.forget_text_view:
                startActivity(new Intent(this, PhoneForgetActivity.class));
                finish();
                break;
            case R.id.tv_vip:
                JumpUtils.jumpToWebViewWithCookies(mBaseActivity, "https://m.xiaolumeimei.com/mall/boutiqueinvite",
                    -1, BaseWebViewActivity.class, false, false);
                break;
        }
    }

    public boolean checkInput(String mobile, String password) {
        if (mobile == null || mobile.trim().trim().equals("")) {
            JUtils.Toast("请输入手机号");
        } else {
            if (mobile.length() != 11) {
                JUtils.Toast("请输入正确的手机号");
            } else if (password == null || password.trim().trim().equals("")) {
                JUtils.Toast("密码不能为空");
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String phone = s.toString();
        SharedPreferences sharedPreferences =
            getSharedPreferences("password", Context.MODE_PRIVATE);
        String password = sharedPreferences.getString(phone, "");
        if (!password.equals("")) {
            b.setLoginPassword.setText(password);
            b.cbPwd.setChecked(true);
        } else {
            b.setLoginPassword.setText("");
            b.cbPwd.setChecked(false);
        }
    }
}
