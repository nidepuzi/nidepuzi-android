package com.danlai.nidepuzi.ui.activity.user;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivitySettingBinding;

/**
 * @author wisdom
 * @date 2017年04月24日 下午5:53
 */

public class SettingActivity extends BaseMVVMActivity<ActivitySettingBinding> {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }
}
