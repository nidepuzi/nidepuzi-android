package com.danlai.nidepuzi.adapter;

import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemCarryBinding;
import com.danlai.nidepuzi.entity.CarryListBean.ResultsBean;

/**
 * Created by wisdom on 17/3/21.
 */

public class CarryAdapter extends BaseRecyclerViewAdapter<ItemCarryBinding, ResultsBean> {

    public CarryAdapter(BaseActivity mActivity) {
        super(mActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_carry;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemCarryBinding> holder, int position) {
        ResultsBean bean = data.get(position);
        holder.b.status.setText(bean.getStatus_display());
        holder.b.amount.setText(JUtils.formatDouble(bean.getCarry_value()));
        holder.b.desc.setText(bean.getCarry_description());
        holder.b.timeDetail.setText(bean.getCreated().substring(11, 19));
        String time_data = bean.getCreated().substring(0, 10);
        if (position == 0) {
            holder.b.layoutTitle.setVisibility(View.VISIBLE);
        } else if (time_data.equals(data.get(position - 1).getCreated().substring(0, 10))) {
            holder.b.layoutTitle.setVisibility(View.GONE);
        } else {
            holder.b.layoutTitle.setVisibility(View.VISIBLE);
        }
        holder.b.time.setText(time_data);
        holder.b.income.setText("总收入:" + JUtils.formatDouble(bean.getToday_carry()));
    }
}
