package com.danlai.nidepuzi.ui.activity.main;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityAboutCompanyBinding;

/**
 * @author wisdom
 * @date 2017年05月09日 上午10:49
 */

public class AboutCompanyActivity extends BaseMVVMActivity<ActivityAboutCompanyBinding> {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_about_company;
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }
}
