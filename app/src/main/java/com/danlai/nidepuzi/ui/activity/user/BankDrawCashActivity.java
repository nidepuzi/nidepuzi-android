package com.danlai.nidepuzi.ui.activity.user;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityBankDrawCashBinding;
import com.danlai.nidepuzi.entity.ResultEntity;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.UserWithDrawResult;
import com.danlai.nidepuzi.service.ServiceResponse;

/**
 * @author wisdom
 * @date 2017年05月19日 下午4:06
 */

public class BankDrawCashActivity extends BaseMVVMActivity<ActivityBankDrawCashBinding> implements View.OnClickListener, TextWatcher {
    private CountDownTimer mCountDownTimer;
    private double money, minMoney;
    private double drawMoney = 0;
    private int id;

    @Override
    protected void setListener() {
        b.btnCode.setOnClickListener(this);
        b.btn.setOnClickListener(this);
        b.etMoney.addTextChangedListener(this);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        id = extras.getInt("id");
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        BaseApp.getMainInteractor(this)
            .getProfile(new ServiceResponse<UserInfoBean>(mBaseActivity) {
                @Override
                public void onNext(UserInfoBean userNewBean) {
                    hideIndeterminateProgressDialog();
                    if (userNewBean.getUser_budget() != null) {
                        money = userNewBean.getUser_budget().getBudget_cash();
                        minMoney = userNewBean.getUser_budget().getCash_out_limit();
                    }
                    b.tvMoney.setText("当前可提现最大金额:" + JUtils.formatDouble(money));
                }

                @Override
                public void onError(Throwable e) {
                    JUtils.Toast("获取零钱数目失败!");
                    hideIndeterminateProgressDialog();
                }
            });
        mCountDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                b.btnCode.setText(millisUntilFinished / 1000 + "S");
            }

            @Override
            public void onFinish() {
                b.btnCode.setClickable(true);
                b.btnCode.setText("获取验证码");
            }
        };
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_bank_draw_cash;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                if (drawMoney == 0) {
                    JUtils.Toast("提现金额不能为空,并且要大于0哦~~");
                } else if (b.etCode.getText().length() < 4) {
                    JUtils.Toast("验证码有误");
                } else {
                    drawCash();
                }
                break;
            case R.id.btn_code:
                b.btnCode.setClickable(false);
                mCountDownTimer.start();
                BaseApp.getUserInteractor(this)
                    .getVerifyCode(new ServiceResponse<ResultEntity>(mBaseActivity) {
                        @Override
                        public void onNext(ResultEntity resultEntity) {
                            JUtils.Toast(resultEntity.getInfo());
                            b.etMoney.setFocusable(false);
                            b.etCode.setFocusable(true);
                        }

                        @Override
                        public void onError(Throwable e) {
                            JUtils.Toast("获取验证码失败");
                        }
                    });
                break;
        }
    }

    private void drawCash() {
        b.btn.setClickable(false);
        BaseApp.getUserInteractor(this)
            .userWithDrawCash(JUtils.formatDouble(drawMoney), b.etCode.getText().toString(), id,
                new ServiceResponse<UserWithDrawResult>(mBaseActivity) {
                    @Override
                    public void onNext(UserWithDrawResult resp) {
                        new AlertDialog.Builder(mBaseActivity)
                            .setTitle("提示")
                            .setMessage(resp.getMessage())
                            .setPositiveButton("确定", (dialog, which) -> {
                                dialog.dismiss();
                                finish();
                            })
                            .show();
                    }
                });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                s = s.toString().subSequence(0, s.toString().indexOf(".") + 3);
                b.etMoney.setText(s);
                b.etMoney.setSelection(s.length());
            }
        }
        if (s.toString().equals(".")) {
            s = "0" + s;
            b.etMoney.setText(s);
            b.etMoney.setSelection(2);
        }

        if (s.toString().startsWith("0") && s.toString().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                b.etMoney.setText(s.subSequence(0, 1));
                b.etMoney.setSelection(1);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 0) {
            b.etMoney.setTextSize(14);
            drawMoney = 0;
        } else {
            b.etMoney.setTextSize(32);
            if (!s.toString().startsWith(".")) {
                drawMoney = Double.parseDouble(s.toString());
            }
        }
        setBtn();
    }

    private void setBtn() {
        if (money < minMoney) {
            b.tvMsg.setText("您的零钱未满" + minMoney + "元，不满足提现条件");
            b.tvMsg.setVisibility(View.VISIBLE);
            setBtnUnClick();
        } else {
            if (drawMoney < minMoney) {
                if (b.etMoney.getText().length() > 0) {
                    b.tvMsg.setText("提现最低金额需要" + minMoney + "元哦");
                    b.tvMsg.setVisibility(View.VISIBLE);
                } else {
                    b.tvMsg.setVisibility(View.GONE);
                }
                setBtnUnClick();
            } else if (drawMoney > money) {
                b.tvMsg.setText("提现零钱超过账户可提额度");
                b.tvMsg.setVisibility(View.VISIBLE);
                setBtnUnClick();
            } else {
                b.tvMsg.setText("");
                b.tvMsg.setVisibility(View.GONE);
                setBtnClick();
            }
        }
    }

    private void setBtnClick() {
        b.btn.setClickable(true);
        b.btn.setEnabled(true);
    }

    private void setBtnUnClick() {
        b.btn.setClickable(false);
        b.btn.setEnabled(false);
    }
}
