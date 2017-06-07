package com.danlai.nidepuzi.ui.activity.user;

import android.view.View;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityDrawCashBinding;

/**
 * @author wisdom
 * @date 2017年05月19日 上午10:36
 */

public class DrawCashActivity extends BaseMVVMActivity<ActivityDrawCashBinding> implements View.OnClickListener {

    @Override
    protected void setListener() {
        b.layoutHistory.setOnClickListener(this);
        b.layoutDraw.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_draw_cash;
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_history:
                readyGo(DrawCashHistoryActivity.class);
                break;
            case R.id.layout_draw:
                readyGoThenKill(DrawCashInfoActivity.class);
                break;
        }
    }
}
