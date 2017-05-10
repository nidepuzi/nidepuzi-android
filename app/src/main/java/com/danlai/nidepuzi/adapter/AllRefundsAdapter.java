package com.danlai.nidepuzi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.AllRefundsBean;
import com.danlai.nidepuzi.ui.activity.trade.RefundDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wisdom on 16/12/8.
 */

public class AllRefundsAdapter extends RecyclerView.Adapter<AllRefundsAdapter.ViewHolder> {
    private Activity context;
    private List<AllRefundsBean.ResultsEntity> datas;

    public AllRefundsAdapter(Activity context) {
        this.context = context;
        this.datas = new ArrayList<>();
    }

    public void update(List<AllRefundsBean.ResultsEntity> list) {
        datas.addAll(list);
        notifyDataSetChanged();
    }

    public void updateWithClear(List<AllRefundsBean.ResultsEntity> list) {
        datas.clear();
        datas.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.refunds_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AllRefundsBean.ResultsEntity entity = datas.get(position);
        holder.state.setTextColor(context.getResources().getColor(R.color.colorAccent));
        holder.warn.setVisibility(View.GONE);
        if (entity.isHas_good_return()) {
            holder.flag.setImageResource(R.drawable.icon_return_goods_mini);
            holder.type.setText("退货退款");
        } else if ("".equals(entity.getRefund_channel())
            || "budget".equals(entity.getRefund_channel())) {
            holder.flag.setImageResource(R.drawable.icon_fast_return_mini);
            holder.type.setText("极速退款");
        } else {
            holder.flag.setImageResource(R.drawable.icon_return_mini);
            holder.type.setText("退款");
        }
        if (entity.getStatus() == 4 && entity.isHas_good_return()) {
            holder.state.setText("请寄回商品");
            holder.state.setTextColor(context.getResources().getColor(R.color.coupon_red));
            holder.warn.setVisibility(View.VISIBLE);
        } else {
            holder.state.setText(entity.getStatus_display());
        }
        ViewUtils.loadImgToImgViewWithPlaceholder(context, holder.good, entity.getPic_path());
        holder.name.setText(entity.getTitle());
        holder.size.setText("规格:" + entity.getSku_name());
        holder.payment.setText("交易金额:" + JUtils.formatDouble(entity.getPayment()) +
            "x" + entity.getRefund_num());
        holder.refund.setText("退款金额:" + JUtils.formatDouble(entity.getRefund_fee()) +
            "x" + entity.getRefund_num());
        holder.layout.setOnClickListener(v -> {
            Intent intent = new Intent(context, RefundDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("goods_id", entity.getId());
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.flag)
        ImageView flag;
        @Bind(R.id.good)
        ImageView good;
        @Bind(R.id.warn)
        ImageView warn;
        @Bind(R.id.type)
        TextView type;
        @Bind(R.id.state)
        TextView state;
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.size)
        TextView size;
        @Bind(R.id.payment)
        TextView payment;
        @Bind(R.id.refund)
        TextView refund;
        @Bind(R.id.layout)
        LinearLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
