package com.danlai.nidepuzi.ui.activity.user;

import android.view.View;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityMessageBinding;

/**
 * @author wisdom
 * @date 2017年04月22日 下午2:21
 */

public class MessageActivity extends BaseMVVMActivity<ActivityMessageBinding> {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_message;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }
}
