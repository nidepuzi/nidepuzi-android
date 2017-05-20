package com.danlai.nidepuzi.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityDrawCashInfoBinding;
import com.danlai.nidepuzi.entity.BankCardEntity;
import com.danlai.nidepuzi.entity.BankListEntity;
import com.danlai.nidepuzi.entity.BankResultEntity;
import com.danlai.nidepuzi.service.ServiceResponse;

import java.util.List;

/**
 * @author wisdom
 * @date 2017年05月19日 上午11:25
 */

public class DrawCashInfoActivity extends BaseMVVMActivity<ActivityDrawCashInfoBinding> implements View.OnClickListener {
    private String default_no, default_name, default_bank_name, bank_name;
    private int id;
    private static final int REQUEST_BANK = 666;

    @Override
    protected void setListener() {
        b.layoutBank.setOnClickListener(this);
        b.btn.setOnClickListener(this);
        b.tvNum.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        BaseApp.getUserInteractor(mBaseActivity)
            .getDefaultCard(new ServiceResponse<BankCardEntity>(mBaseActivity) {
                @Override
                public void onNext(BankCardEntity bankCardEntity) {
                    if (bankCardEntity != null) {
                        id = bankCardEntity.getId();
                        default_name = bankCardEntity.getAccount_name();
                        if (TextUtils.isEmpty(default_name)) {
                            b.etName.setVisibility(View.VISIBLE);
                            b.layoutName.setVisibility(View.GONE);
                        } else {
                            b.etName.setVisibility(View.GONE);
                            b.etName.setText(default_name);
                            b.layoutName.setVisibility(View.VISIBLE);
                            b.tvName.setText(default_name);
                        }
                        default_no = bankCardEntity.getAccount_no();
                        if (TextUtils.isEmpty(default_no)) {
                            b.etNum.setVisibility(View.VISIBLE);
                            b.tvNum.setVisibility(View.GONE);
                        } else {
                            b.etNum.setText(default_no);
                            b.tvNum.setText(default_no);
                            b.etNum.setSelection(default_no.length());
                            b.etNum.setVisibility(View.GONE);
                            b.tvNum.setVisibility(View.VISIBLE);
                        }
                        default_bank_name = bankCardEntity.getBank_name();
                        if (TextUtils.isEmpty(bankCardEntity.getBank_img())) {
                            BaseApp.getUserInteractor(mBaseActivity)
                                .getBankList(new ServiceResponse<BankListEntity>(mBaseActivity) {
                                    @Override
                                    public void onNext(BankListEntity bankListEntity) {
                                        List<BankListEntity.BanksBean> banks = bankListEntity.getBanks();
                                        if (banks.size() > 0) {
                                            String bank_img = banks.get(0).getBank_img();
                                            Glide.with(mBaseActivity).load(bank_img).into(b.imgBank);
                                            bank_name = banks.get(0).getBank_name();
                                        }
                                    }
                                });
                        } else {
                            bank_name = default_bank_name;
                            Glide.with(mBaseActivity).load(bankCardEntity.getBank_img()).into(b.imgBank);
                        }
                    }
                }
            });


    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_draw_cash_info;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_bank:
                readyGoForResult(ChooseBankActivity.class, REQUEST_BANK);
                break;
            case R.id.btn:
                String name = b.etName.getText().toString().trim();
                String no = b.etNum.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(no)) {
                    JUtils.Toast("信息填写未完整!");
                } else {
                    if (TextUtils.equals(name, default_name) && TextUtils.equals(no, default_no) &&
                        TextUtils.equals(bank_name, default_bank_name)) {
                        jumpToDrawCash();
                    } else {
                        BaseApp.getUserInteractor(mBaseActivity)
                            .createBankCard(no, name, bank_name, new ServiceResponse<BankResultEntity>(mBaseActivity) {
                                @Override
                                public void onNext(BankResultEntity resultEntity) {
                                    if (resultEntity.getCode() == 0) {
                                        id = resultEntity.getCard().getId();
                                        jumpToDrawCash();

                                    } else {
                                        JUtils.Toast(resultEntity.getInfo());
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                }
                            });
                    }
                }

                break;
            case R.id.tv_num:
                b.etNum.setVisibility(View.VISIBLE);
                b.tvNum.setVisibility(View.GONE);
                break;
        }
    }

    private void jumpToDrawCash() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        readyGo(BankDrawCashActivity.class, bundle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_BANK) {
            bank_name = data.getStringExtra("name");
            Glide.with(mBaseActivity).load(data.getStringExtra("img")).into(b.imgBank);
        }
    }
}

