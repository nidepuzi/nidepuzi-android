package com.danlai.nidepuzi.ui.activity.user;

import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.SeekBar;

import com.danlai.library.rx.RxCountDown;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.base.BaseWebViewActivity;
import com.danlai.nidepuzi.databinding.ActivitySmsLoginBinding;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.danlai.nidepuzi.util.JumpUtils;
import com.danlai.nidepuzi.util.LoginUtils;

public class SmsLoginActivity extends BaseMVVMActivity<ActivitySmsLoginBinding>
    implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, TextWatcher {

    private String mobile;

    @Override
    protected void setListener() {
        b.getCheckCode.setOnClickListener(this);
        b.confirm.setOnClickListener(this);
        b.sb.setOnSeekBarChangeListener(this);
        b.registerName.addTextChangedListener(this);
        b.viewFirst.setOnClickListener(this);
        b.tvVip.setOnClickListener(this);
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected void initViews() {
        b.tvVip.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_sms_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_check_code:
                mobile = b.registerName.getText().toString().trim();
                if (checkMobileInput(mobile)) {
                    if (b.sb.getProgress() == b.sb.getMax()) {
                        RxCountDown.countdown(60).doOnSubscribe(disposable -> {
                            addDisposable(disposable);
                            b.getCheckCode.setClickable(false);
                            b.getCheckCode.setBackgroundColor(Color.parseColor("#f3f3f4"));
                            BaseApp.getUserInteractor(SmsLoginActivity.this)
                                .getCodeBean(mobile, "sms_login", new ServiceResponse<CodeBean>(mBaseActivity) {
                                    @Override
                                    public void onNext(CodeBean codeBean) {
                                        JUtils.Toast(codeBean.getMsg());
                                    }
                                });
                        }).subscribe(new ServiceResponse<Integer>(mBaseActivity) {
                            @Override
                            public void onComplete() {
                                if (b.getCheckCode != null) {
                                    b.getCheckCode.setText("获取验证码");
                                    b.getCheckCode.setClickable(true);
                                    b.getCheckCode.setBackgroundResource(R.drawable.btn_common_white);
                                }
                            }

                            @Override
                            public void onNext(Integer integer) {
                                if (b.getCheckCode != null) {
                                    b.getCheckCode.setText(integer + "s后重新获取");
                                }
                            }
                        });
                    } else {
                        JUtils.Toast("请先拖动滑块验证");
                    }
                }
                break;
            case R.id.confirm:
                mobile = b.registerName.getText().toString().trim();
                String invalid_code = b.checkCode.getText().toString().trim();
                if (checkInput(mobile, invalid_code)) {
                    BaseApp.getUserInteractor(this)
                        .verifyCode(mobile, "sms_login", invalid_code, new ServiceResponse<CodeBean>(mBaseActivity) {
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
            case R.id.tv_vip:
                JumpUtils.jumpToWebViewWithCookies(mBaseActivity, "https://m.xiaolumeimei.com/mall/boutiqueinvite",
                    -1, BaseWebViewActivity.class, false, false);
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

    public boolean checkInput(String mobile, String checkcode) {
        if (mobile == null || mobile.trim().trim().equals("")) {
            JUtils.Toast("请输入手机号");
        } else {
            if (mobile.length() != 11) {
                JUtils.Toast("请输入正确的手机号");
            } else if (checkcode == null || checkcode.trim().trim().equals("")) {
                JUtils.Toast("验证码不能为空");
            } else {
                return true;
            }
        }
        return false;
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
        mobile = b.registerName.getText().toString().trim();
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
