package com.danlai.nidepuzi.ui.activity.user;

import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;

import com.danlai.library.rx.RxCountDown;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.base.BaseWebViewActivity;
import com.danlai.nidepuzi.databinding.ActivityPhoneLoginBinding;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.event.LoginEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.danlai.nidepuzi.util.JumpUtils;
import com.danlai.nidepuzi.util.LoginUtils;

import org.greenrobot.eventbus.EventBus;

public class PhoneLoginActivity extends BaseMVVMActivity<ActivityPhoneLoginBinding>
    implements View.OnClickListener {
    private String phone;
    private AlertDialog mRuleDialog;

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
    protected void initViews() {
        mRuleDialog = new AlertDialog.Builder(this)
            .setTitle("注册必读")
            .setMessage(getResources().getString(R.string.login_rule))
            .setPositiveButton("同意", (dialog, which) -> dialog.dismiss())
            .create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_vip:
                JumpUtils.jumpToWebViewWithCookies(mBaseActivity, "https://m.nidepuzi.com/mall/boutiqueinvite",
                    -1, BaseWebViewActivity.class, false, false);
                break;
            case R.id.service_layout:
                mRuleDialog.show();
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
                                    b.etPhone.setFocusable(false);
                                    b.etCode.setFocusable(true);
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
                                if (codeBean.getRcode() == 0) {
                                    BaseApp.getMainInteractor(mBaseActivity)
                                        .getProfile(new ServiceResponse<UserInfoBean>(mBaseActivity) {
                                            @Override
                                            public void onNext(UserInfoBean userInfoBean) {
                                                hideIndeterminateProgressDialog();
                                                UserInfoBean.XiaolummBean bean = userInfoBean.getXiaolumm();
                                                if (bean != null && "effect".equals(bean.getStatus())) {
                                                    if (TextUtils.isEmpty(userInfoBean.getMobile())) {
                                                        JUtils.Toast("绑定手机后才可以登录!");
                                                        readyGo(LoginBindPhoneActivity.class);
                                                    } else {
                                                        LoginUtils.saveLoginSuccess(true, mBaseActivity);
                                                        EventBus.getDefault().post(new LoginEvent());
                                                        JUtils.Toast("登录成功!");
                                                        readyGoThenKill(TabActivity.class);
                                                    }
                                                } else {
                                                    JUtils.Toast("您不是会员暂时无法登录!");
                                                }
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                loginFail("登录失败,请重试!");
                                            }
                                        });
                                } else {
                                    loginFail(codeBean.getMsg());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                loginFail("登录失败,请重试!");
                            }
                        });
                }
                break;
        }
    }

    private void loginFail(String msg) {
        hideIndeterminateProgressDialog();
        JUtils.Toast(msg);
        LoginUtils.delLoginInfo(mBaseActivity);
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
