package com.danlai.nidepuzi.ui.activity.shop;

import android.os.Bundle;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityAllIncomeBinding;
import com.danlai.nidepuzi.entity.MamaFortune;
import com.danlai.nidepuzi.service.ServiceResponse;

/**
 * @author wisdom
 * @date 2017年05月26日 下午5:59
 */

public class AllIncomeActivity extends BaseMVVMActivity<ActivityAllIncomeBinding> implements View.OnClickListener {
    private Bundle bundle = new Bundle();
    private String allCarryValue, weekCarryValue, monthCarryValue;

    @Override
    protected void setListener() {
        b.layoutAll.setOnClickListener(this);
        b.layoutWeek.setOnClickListener(this);
        b.layoutMonth.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        BaseApp.getVipInteractor(mBaseActivity)
            .getMamaFortune()
            .subscribe(new ServiceResponse<MamaFortune>(mBaseActivity) {
                @Override
                public void onNext(MamaFortune mamaFortune) {
                    MamaFortune.MamaFortuneBean fortune = mamaFortune.getMama_fortune();
                    allCarryValue = JUtils.formatDouble(fortune.getCarry_value());
                    weekCarryValue = JUtils.formatDouble(fortune.getExtra_figures().getWeek_duration_total());
                    monthCarryValue = JUtils.formatDouble(fortune.getExtra_figures().getMonth_duration_total());
                    b.tvAll.setText("¥" + allCarryValue);
                    b.tvWeek.setText("¥" + weekCarryValue);
                    b.tvMonth.setText("¥" + monthCarryValue);
                }
            });
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_all_income;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    public void onClick(View v) {
        bundle.clear();
        switch (v.getId()) {
            case R.id.layout_all:
                bundle.putString("carryValue", allCarryValue);
                readyGo(IncomeActivity.class, bundle);
                break;
            case R.id.layout_week:
                bundle.putInt("days", 7);
                bundle.putString("carryValue", weekCarryValue);
                readyGo(IncomeActivity.class, bundle);
                break;
            case R.id.layout_month:
                bundle.putInt("days", 30);
                bundle.putString("carryValue", monthCarryValue);
                readyGo(IncomeActivity.class, bundle);
                break;
        }
    }
}
