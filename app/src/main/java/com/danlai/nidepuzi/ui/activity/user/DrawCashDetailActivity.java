package com.danlai.nidepuzi.ui.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityDrawCashDetailBinding;
import com.danlai.nidepuzi.entity.DrawCashBean;
import com.danlai.nidepuzi.service.ServiceResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wisdom
 * @date 2017年05月22日 上午11:45
 */

public class DrawCashDetailActivity extends BaseMVVMActivity<ActivityDrawCashDetailBinding> {

    private int id;

    @Override
    protected void getBundleExtras(Bundle extras) {
        id = extras.getInt("id");
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        BaseApp.getUserInteractor(mBaseActivity)
            .getDrawCashDetail(id, new ServiceResponse<DrawCashBean>(mBaseActivity) {
                @Override
                public void onNext(DrawCashBean drawCashBean) {
                    hideIndeterminateProgressDialog();
                    fillData(drawCashBean);
                }

                @Override
                public void onError(Throwable e) {
                    initDataError();
                }
            });
    }

    private void fillData(DrawCashBean bean) {
        b.tvAmount.setText(JUtils.formatDouble(bean.getAmount()));
        String state = bean.getState();
        if ("fail".equals(state)) {
            b.ivStatus.setImageResource(R.drawable.icon_draw_fail);
            b.tvStatus.setText("提现失败");
            b.tvError.setText(bean.getFail_msg());
            b.tvError.setVisibility(View.VISIBLE);
            b.tvErrorTitle.setVisibility(View.VISIBLE);
        } else if ("success".equals(state)) {
            b.ivStatus.setImageResource(R.drawable.icon_draw_success);
        }
        String success_time = bean.getSuccess_time();
        String created_time = bean.getCreated();
        if (TextUtils.isEmpty(success_time)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(created_time);
                long time = date.getTime() + 3 * 24 * 60 * 60 * 1000;
                String format = sdf.format(new Date(time));
                b.tvSuccessTime.setText(format);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            b.tvSuccessTime.setText(success_time.substring(0, 10));
        }

        b.tvCreateTime.setText(created_time.substring(0, 10));
        b.tvInTime.setText(created_time.substring(0, 10));
        b.tvType.setText(bean.getPlatform_name());
        b.tvServiceFee.setText(JUtils.formatDouble(bean.getService_fee()));
        b.tvCreated.setText(created_time.substring(0, 10));
        DrawCashBean.BankCardBean bank_card = bean.getBank_card();
        if (bank_card != null) {
            b.tvName.setText(bank_card.getAccount_name());
            b.tvBank.setText(bank_card.getBank_name());
            String accountNo = bank_card.getAccount_no();
            b.tvCard.setText(accountNo.replaceAll("(\\d{4})\\d{" + (accountNo.length() - 8) + "}(\\w{4})", "$1**********$2"));
        } else {
            b.tvNameTitle.setVisibility(View.GONE);
            b.tvName.setVisibility(View.GONE);
            b.tvCardTitle.setVisibility(View.GONE);
            b.tvCard.setVisibility(View.GONE);
            b.tvBankTitle.setVisibility(View.GONE);
            b.tvBank.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_draw_cash_detail;
    }
}
