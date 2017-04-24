package com.danlai.nidepuzi.ui.activity.user;

import android.view.View;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityInviteBinding;

/**
 * @author wisdom
 * @date 2017年04月22日 下午5:05
 */

public class InviteActivity extends BaseMVVMActivity<ActivityInviteBinding> {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_invite;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }
}
