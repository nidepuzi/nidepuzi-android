package com.danlai.nidepuzi.adapter;

import android.view.View;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemLogisticBinding;
import com.danlai.nidepuzi.entity.LogisticsBean.Msg;

/**
 * @author wisdom
 * @date 2017年05月25日 上午10:47
 */

public class LogisticAdapter extends BaseRecyclerViewAdapter<ItemLogisticBinding, Msg> {
    public LogisticAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_logistic;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemLogisticBinding> holder, int position) {
        Msg msg = data.get(position);
        if (position == (data.size() - 1)) {
            holder.b.line.setVisibility(View.GONE);
        } else {
            holder.b.line.setVisibility(View.VISIBLE);
        }
        if (position == 0) {
            holder.b.status.setImageResource(R.drawable.icon_logistic_in);
            holder.b.topLine.setBackgroundResource(R.color.white);
        } else {
            holder.b.status.setImageResource(R.drawable.icon_logistic_out);
            holder.b.topLine.setBackgroundResource(R.color.color_AA);
        }
        holder.b.time.setText(msg.getTime().substring(0, 19).replaceAll("T", " "));
        holder.b.content.setText(msg.getContent());
    }
}
