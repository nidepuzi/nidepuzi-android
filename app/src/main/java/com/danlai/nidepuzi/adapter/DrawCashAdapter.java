package com.danlai.nidepuzi.adapter;

import android.os.Bundle;
import android.text.TextUtils;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemDrawCashBinding;
import com.danlai.nidepuzi.entity.DrawCashBean;
import com.danlai.nidepuzi.ui.activity.user.DrawCashDetailActivity;

/**
 * @author wisdom
 * @date 2017年05月22日 上午11:06
 */

public class DrawCashAdapter extends BaseRecyclerViewAdapter<ItemDrawCashBinding, DrawCashBean> {
    public DrawCashAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_draw_cash;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemDrawCashBinding> holder, int position) {
        DrawCashBean bean = data.get(position);
        String state = bean.getState();
        if ("fail".equals(state)) {
            holder.b.status.setText("提现失败");
        } else if ("success".equals(state)) {
            holder.b.status.setText("提现成功");
        } else {
            holder.b.status.setText("提现中");
        }
        if (TextUtils.isEmpty(bean.getSuccess_time())) {
            holder.b.time.setText(bean.getCreated().substring(0, 10));
        } else {
            holder.b.time.setText(bean.getSuccess_time().substring(0, 10));
        }
        holder.b.amount.setText(JUtils.formatDouble(bean.getAmount()));
        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", bean.getId());
            mActivity.readyGo(DrawCashDetailActivity.class, bundle);
        });
    }
}
