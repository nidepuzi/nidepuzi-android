package com.danlai.nidepuzi.ui.activity.user;

import android.support.v7.app.AlertDialog;
import android.view.View;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityDrawCashBinding;

/**
 * @author wisdom
 * @date 2017年05月19日 上午10:36
 */

public class DrawCashActivity extends BaseMVVMActivity<ActivityDrawCashBinding> implements View.OnClickListener {
    private AlertDialog mRuleDialog;

    @Override
    protected void initViews() {
        mRuleDialog = new AlertDialog.Builder(mBaseActivity)
            .setTitle("提现小知识")
            .setMessage(getResources().getString(R.string.draw_rule))
            .setPositiveButton("同意", (dialog, which) -> dialog.dismiss())
            .create();
    }

    @Override
    protected void setListener() {
        b.layoutProblem.setOnClickListener(this);
        b.layoutDraw.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_draw_cash;
    }

    @Override
    public View getLoadingView() {
        return b.xrv;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_problem:
                mRuleDialog.show();
                break;
            case R.id.layout_draw:
                readyGo(DrawCashInfoActivity.class);
                break;
        }
    }
}
