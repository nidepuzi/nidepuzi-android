package com.danlai.nidepuzi.adapter;

import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemCarryAwardBinding;
import com.danlai.nidepuzi.entity.AwardCarryBean.ResultsEntity;

/**
 * @author wisdom
 * @date 2017年05月24日 上午11:36
 */

public class CarryAwardAdapter extends BaseRecyclerViewAdapter<ItemCarryAwardBinding, ResultsEntity> {
    private boolean isToday;

    public CarryAwardAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_carry_award;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemCarryAwardBinding> holder, int position) {
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
        holder.b.income.setText("总收益:" + JUtils.formatDouble(bean.getTodayCarry()));
        holder.b.status.setText(bean.getStatusDisplay());
        holder.b.amount.setText("+" + JUtils.formatDouble(bean.getCarryNum()));
        holder.b.desc.setText(bean.getmCarryDescription());
        holder.b.timeDetail.setText(bean.getCreated().substring(11, 19));
    }

    public void setToday(boolean today) {
        isToday = today;
    }
}
