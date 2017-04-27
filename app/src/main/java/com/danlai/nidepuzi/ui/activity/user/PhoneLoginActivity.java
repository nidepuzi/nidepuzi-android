package com.danlai.nidepuzi.ui.activity.user;

import android.support.v7.app.AlertDialog;
import android.view.View;

import com.danlai.library.rx.RxCountDown;
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

public class PhoneLoginActivity extends BaseMVVMActivity<ActivityPhoneLoginBinding>
    implements View.OnClickListener {
    private String phone;

    @Override
    protected void setListener() {
        b.tvCode.setOnClickListener(this);
        b.tvVip.setOnClickListener(this);
        b.serviceLayout.setOnClickListener(this);
        b.btnLogin.setOnClickListener(this);
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_phone_login;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_vip:
                JumpUtils.jumpToWebViewWithCookies(mBaseActivity, "https://m.nidepuzi.com/mall/boutiqueinvite",
                    -1, BaseWebViewActivity.class, false, false);
                break;
            case R.id.service_layout:
                new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("你的铺子微店用户服务协议")
                    .setMessage("协议内容")
                    .setPositiveButton("同意", (dialog, which) -> dialog.dismiss())
                    .show();
                break;
            case R.id.tv_code:
                phone = b.etPhone.getText().toString();
                if (checkMobileInput(phone)) {
                    RxCountDown.countdown(60).doOnSubscribe(disposable -> {
                        addDisposable(disposable);
                        b.tvCode.setClickable(false);
                        BaseApp.getUserInteractor(mBaseActivity)
                            .getCodeBean(phone, "sms_login", new ServiceResponse<CodeBean>(mBaseActivity) {
                                @Override
                                public void onNext(CodeBean codeBean) {
                                    JUtils.Toast(codeBean.getMsg());
                                }
                            });
                    }).subscribe(new ServiceResponse<Integer>(mBaseActivity) {
                        @Override
                        public void onComplete() {
                            if (b.tvCode != null) {
                                b.tvCode.setText("获取验证码");
                                b.tvCode.setClickable(true);
                            }
                        }

                        @Override
                        public void onNext(Integer integer) {
                            if (b.tvCode != null) {
                                b.tvCode.setText(integer + "S");
                            }
                        }
                    });
                }
                break;
            case R.id.btn_login:
                phone = b.etPhone.getText().toString();
                String code = b.etCode.getText().toString();
                if (checkInput(phone, code)) {
                    BaseApp.getUserInteractor(this)
                        .verifyCode(phone, "sms_login", code, new ServiceResponse<CodeBean>(mBaseActivity) {
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
        }
    }

    public boolean checkMobileInput(String mobile) {
        if (mobile == null || mobile.trim().trim().equals("")) {
            JUtils.Toast("请输入手机号");
        } else {
            if (mobile.length() != 11) {
                JUtils.Toast("请输入正确的手机号");
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean checkInput(String mobile, String code) {
        if (checkMobileInput(mobile)) {
            if (code == null || code.trim().equals("")) {
                JUtils.Toast("验证码不能为空");
            } else {
                return true;
            }
        }
        return false;
    }
}
