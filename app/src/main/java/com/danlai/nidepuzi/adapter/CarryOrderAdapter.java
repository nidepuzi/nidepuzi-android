package com.danlai.nidepuzi.adapter;

import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemCarryOrderBinding;
import com.danlai.nidepuzi.entity.OrderCarryBean.ResultsEntity;

/**
 * @author wisdom
 * @date 2017年05月24日 上午11:35
 */

public class CarryOrderAdapter extends BaseRecyclerViewAdapter<ItemCarryOrderBinding, ResultsEntity> {
    private boolean isToday;

    public CarryOrderAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_carry_order;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemCarryOrderBinding> holder, int position) {
        ResultsEntity bean = data.get(position);
        String time_data = bean.getCreated().substring(0, 10);
        if (isToday) {
            holder.b.layoutTitle.setVisibility(View.GONE);
        } else if (position == 0) {
            holder.b.layoutTitle.setVisibility(View.VISIBLE);
        } else if (time_data.equals(data.get(position - 1).getCreated().substring(0, 10))) {
            holder.b.layoutTitle.setVisibility(View.GONE);
        } else {
            holder.b.layoutTitle.setVisibility(View.VISIBLE);
        }
        holder.b.time.setText(time_data);
        holder.b.income.setText("总收益:" + JUtils.formatDouble(bean.getToday_carry()));
        holder.b.status.setText(bean.getStatus_display());
        holder.b.amount.setText("+" + JUtils.formatDouble(bean.getCarry_value()));
        holder.b.desc.setText(bean.getCarry_description());
        holder.b.timeDetail.setText(bean.getCreated().substring(11, 19));
    }

    public void setToday(boolean today) {
        isToday = today;
    }
}
