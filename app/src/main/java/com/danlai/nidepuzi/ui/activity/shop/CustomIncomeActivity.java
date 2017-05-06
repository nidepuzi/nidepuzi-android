package com.danlai.nidepuzi.ui.activity.shop;

import android.view.View;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityCustomIncomeBinding;

/**
 * @author wisdom
 * @date 2017年05月06日 上午11:47
 */

public class CustomIncomeActivity extends BaseMVVMActivity<ActivityCustomIncomeBinding> {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_custom_income;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }
}
