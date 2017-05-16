package com.danlai.nidepuzi.adapter;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseListViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemRefundTypeBinding;
import com.danlai.nidepuzi.entity.OrderDetailBean.ExtrasBean.RefundChoicesBean;

import java.util.List;


/**
 * Created by wisdom on 16/6/30.
 */
public class RefundTypeAdapter extends BaseListViewAdapter<ItemRefundTypeBinding, RefundChoicesBean> {
    private boolean[] flag;

    public RefundTypeAdapter(BaseActivity mActivity, List<RefundChoicesBean> data) {
        super(mActivity);
        this.data = data;
        flag = new boolean[data.size()];
        for (int i = 0; i < flag.length; i++) {
            flag[i] = false;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_refund_type;
    }

    @Override
    protected void fillData(RefundChoicesBean refundChoicesBean, BaseViewHolder<ItemRefundTypeBinding> holder, int position) {
        holder.b.refundRb.setChecked(flag[position]);
        holder.b.refundName.setText(refundChoicesBean.getName());
        holder.b.refundDesc.setText(refundChoicesBean.getDesc());
        if ("budget".equals(refundChoicesBean.getRefund_channel())) {
            holder.b.refundIcon.setImageResource(R.drawable.icon_fast_return);
        } else {
            holder.b.refundIcon.setImageResource(R.drawable.icon_return);
        }
        holder.itemView.setOnClickListener(v -> {
            for (int i = 0; i < flag.length; i++) {
                if (position == i) {
                    flag[i] = true;
                } else {
                    flag[i] = false;
                }
            }
            RefundTypeAdapter.this.notifyDataSetChanged();
        });
    }

    public int getSelect() {
        for (int i = 0; i < flag.length; i++) {
            if (flag[i]) {
                return i;
            }
        }
        return -1;
    }
}
