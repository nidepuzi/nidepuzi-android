package com.danlai.nidepuzi.ui.activity.user;

import android.os.Bundle;
import android.view.View;

import com.danlai.library.rx.RxCountDown;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityVerifyPhoneBinding;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.service.ServiceResponse;

public class VerifyPhoneForgetActivity extends BaseMVVMActivity<ActivityVerifyPhoneBinding>
    implements View.OnClickListener {
    private String mobile;

    @Override
    protected void setListener() {
        b.tvCode.setOnClickListener(this);
        b.btnNext.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_verify_phone;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    public boolean checkMobileInput(String mobile) {
        if (mobile == null || mobile.trim().equals("")) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_code:
                mobile = b.etPhone.getText().toString().trim();
                if (checkMobileInput(mobile)) {
                    RxCountDown.countdown(60).doOnSubscribe(disposable -> {
                        addDisposable(disposable);
                        b.tvCode.setClickable(false);
                        BaseApp.getUserInteractor(mBaseActivity)
                            .getCodeBean(mobile, "find_pwd", new ServiceResponse<CodeBean>(mBaseActivity) {
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
                            b.tvCode.setText("获取验证码");
                            b.tvCode.setClickable(true);
                        }

                        @Override
                        public void onNext(Integer integer) {
                            b.tvCode.setText(integer + "S");
                        }
                    });
                }
                break;
            case R.id.btn_next:
                mobile = b.etPhone.getText().toString().trim();
                String code = b.etCode.getText().toString().trim();
                if (checkInput(mobile, code)) {
                    BaseApp.getUserInteractor(mBaseActivity)
                        .verifyCode(mobile, "find_pwd", code, new ServiceResponse<CodeBean>(mBaseActivity) {
                            @Override
                            public void onNext(CodeBean codeBean) {
                                if (codeBean.getRcode() == 0) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("mobile", mobile);
                                    bundle.putString("code", code);
                                    readyGoThenKill(SetPasswordActivity.class, bundle);
                                } else {
                                    JUtils.Toast(codeBean.getMsg());
                                }
                            }
                        });
                }
                break;
        }
    }
}
