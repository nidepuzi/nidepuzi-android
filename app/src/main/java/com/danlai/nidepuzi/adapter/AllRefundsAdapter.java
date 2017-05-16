package com.danlai.nidepuzi.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemRefundsListBinding;
import com.danlai.nidepuzi.entity.AllRefundsBean.ResultsEntity;
import com.danlai.nidepuzi.ui.activity.trade.RefundDetailActivity;

/**
 * @author wisdom
 * @date 2016年12月16日 上午9:52
 */

public class AllRefundsAdapter extends BaseRecyclerViewAdapter<ItemRefundsListBinding, ResultsEntity> {

    public AllRefundsAdapter(BaseActivity mActivity) {
        super(mActivity);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_refunds_list;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemRefundsListBinding> holder, int position) {
        ResultsEntity entity = data.get(position);
        holder.b.state.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
        holder.b.warn.setVisibility(View.GONE);
        if (entity.isHas_good_return()) {
            holder.b.flag.setImageResource(R.drawable.icon_return_goods_mini);
            holder.b.type.setText("退货退款");
        } else if ("".equals(entity.getRefund_channel())
            || "budget".equals(entity.getRefund_channel())) {
            holder.b.flag.setImageResource(R.drawable.icon_fast_return_mini);
            holder.b.type.setText("极速退款");
        } else {
            holder.b.flag.setImageResource(R.drawable.icon_return_mini);
            holder.b.type.setText("退款");
        }
        if (entity.getStatus() == 4 && entity.isHas_good_return()) {
            holder.b.state.setText("请寄回商品");
            holder.b.state.setTextColor(mActivity.getResources().getColor(R.color.coupon_red));
            holder.b.warn.setVisibility(View.VISIBLE);
        } else {
            holder.b.state.setText(entity.getStatus_display());
        }
        ViewUtils.loadImgToImgViewWithPlaceholder(mActivity, holder.b.good, entity.getPic_path());
        holder.b.name.setText(entity.getTitle());
        holder.b.size.setText("规格:" + entity.getSku_name());
        holder.b.payment.setText("交易金额:" + JUtils.formatDouble(entity.getPayment()) +
            "x" + entity.getRefund_num());
        holder.b.refund.setText("退款金额:" + JUtils.formatDouble(entity.getRefund_fee()) +
            "x" + entity.getRefund_num());
        holder.b.layout.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, RefundDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("goods_id", entity.getId());
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        });
    }
}
