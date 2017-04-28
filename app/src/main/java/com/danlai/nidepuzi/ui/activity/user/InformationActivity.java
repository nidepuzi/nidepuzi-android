package com.danlai.nidepuzi.ui.activity.user;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityInformationBinding;

/**
 * @author wisdom
 * @date 2017年04月28日 上午11:20
 */

public class InformationActivity extends BaseMVVMActivity<ActivityInformationBinding> {

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_information;
    }
}
