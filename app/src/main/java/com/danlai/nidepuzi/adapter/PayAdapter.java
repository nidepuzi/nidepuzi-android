package com.danlai.nidepuzi.adapter;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseListViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemPayBinding;
import com.danlai.nidepuzi.entity.OrderDetailBean.ExtrasBean.ChannelsBean;

import java.util.List;

/**
 * @author wisdom
 * @date 2016年07月16日 下午4:17
 */
public class PayAdapter extends BaseListViewAdapter<ItemPayBinding, ChannelsBean> {

    public PayAdapter(List<ChannelsBean> data, BaseActivity mActivity) {
        super(mActivity);
        this.data = data;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_pay;
    }

    @Override
    protected void fillData(ChannelsBean channelsBean, BaseViewHolder<ItemPayBinding> holder, int position) {
        holder.b.name.setText(channelsBean.getName());
        String id = channelsBean.getId();
        if (id.contains("wx")) {
            holder.b.icon.setImageResource(R.drawable.wx);
        } else if (id.contains("alipay")) {
            holder.b.icon.setImageResource(R.drawable.alipay);
        }
    }
}