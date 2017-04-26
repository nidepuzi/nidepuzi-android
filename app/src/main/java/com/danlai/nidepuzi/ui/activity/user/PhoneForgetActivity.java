package com.danlai.nidepuzi.ui.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.SeekBar;

import com.danlai.library.rx.RxCountDown;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityPhoneForgetBinding;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.service.ServiceResponse;

public class PhoneForgetActivity extends BaseMVVMActivity<ActivityPhoneForgetBinding>
    implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, TextWatcher {
    private String mobile, invalid_code;

    @Override
    protected void setListener() {
        b.btnSendCode.setOnClickListener(this);
        b.btnNext.setOnClickListener(this);
        b.sb.setOnSeekBarChangeListener(this);
        b.etPhone.addTextChangedListener(this);
        b.viewFirst.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_phone_forget;
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

    public boolean checkInput(String mobile, String checkCode) {
        if (checkMobileInput(mobile)) {
            if (checkCode == null || checkCode.trim().equals("")) {
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
            case R.id.btn_send_code:
                mobile = b.etPhone.getText().toString().trim();
                if (checkMobileInput(mobile)) {
                    if (b.sb.getProgress() == b.sb.getMax()) {
                        RxCountDown.countdown(60).doOnSubscribe(disposable -> {
                            addDisposable(disposable);
                            b.btnSendCode.setClickable(false);
                            b.btnSendCode.setBackgroundColor(Color.parseColor("#f3f3f4"));
                            BaseApp.getUserInteractor(PhoneForgetActivity.this)
                                .getCodeBean(mobile, "find_pwd", new ServiceResponse<CodeBean>(mBaseActivity) {
                                    @Override
                                    public void onNext(CodeBean codeBean) {
                                        JUtils.Toast(codeBean.getMsg());
                                    }
                                });
                        }).subscribe(new ServiceResponse<Integer>(mBaseActivity) {
                            @Override
                            public void onComplete() {
                                b.btnSendCode.setText("获取验证码");
                                b.btnSendCode.setClickable(true);
                                b.btnSendCode.setBackgroundResource(R.drawable.btn_common_white);
                            }

                            @Override
                            public void onNext(Integer integer) {
                                b.btnSendCode.setText(integer + "s后重新获取");
                            }
                        });
                    } else {
                        JUtils.Toast("请先拖动滑块验证");
                    }
                }
                break;
            case R.id.btn_next:
                mobile = b.etPhone.getText().toString().trim();
                invalid_code = b.etCode.getText().toString().trim();
                if (checkInput(mobile, invalid_code)) {
                    BaseApp.getUserInteractor(PhoneForgetActivity.this)
                        .verifyCode(mobile, "find_pwd", invalid_code, new ServiceResponse<CodeBean>(mBaseActivity) {
                            @Override
                            public void onNext(CodeBean codeBean) {
                                int result = codeBean.getRcode();
                                if (result == 0) {
                                    Intent intent = new Intent(PhoneForgetActivity.this,
                                        EditPasswordActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("username", mobile);
                                    bundle.putString("valid_code", invalid_code);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    JUtils.Toast(codeBean.getMsg());
                                }
                            }
                        });
                }
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        b.tv.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        seekBar.setThumbOffset(0);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mobile = b.etPhone.getText().toString().trim();
        if (seekBar.getProgress() != seekBar.getMax()) {
            seekBar.setProgress(0);
            b.tv.setVisibility(View.VISIBLE);
            b.tv.setTextColor(Color.GRAY);
            b.tv.setText("向右滑动验证");
        } else if (!checkMobileInput(mobile)) {
            seekBar.setProgress(0);
            b.tv.setVisibility(View.VISIBLE);
            b.tv.setTextColor(Color.GRAY);
            b.tv.setText("向右滑动验证");
        } else {
            b.tv.setVisibility(View.VISIBLE);
            b.tv.setTextColor(Color.WHITE);
            b.tv.setText("完成验证");
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() < 11) {
            b.frameLayout.setVisibility(View.GONE);
            b.sb.setProgress(0);
            b.tv.setVisibility(View.VISIBLE);
            b.tv.setTextColor(Color.GRAY);
            b.tv.setText("向右滑动验证");
        } else {
            b.frameLayout.setVisibility(View.VISIBLE);
        }
    }
}
