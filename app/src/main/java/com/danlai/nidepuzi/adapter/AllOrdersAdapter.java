package com.danlai.nidepuzi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danlai.library.widget.NestedListView;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.AllOrdersBean;
import com.danlai.nidepuzi.ui.activity.trade.OrderDetailActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wisdom on 16/12/7.
 */

public class AllOrdersAdapter extends RecyclerView.Adapter<AllOrdersAdapter.ViewHolder> {

    private Activity context;
    private List<AllOrdersBean.ResultsEntity> mList;

    public AllOrdersAdapter(Activity context) {
        mList = new ArrayList<>();
        this.context = context;
    }

    public void updateWithClear(List<AllOrdersBean.ResultsEntity> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void update(List<AllOrdersBean.ResultsEntity> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final AllOrdersBean.ResultsEntity entity = mList.get(position);
        holder.linearLayout.setOnClickListener(v->{
                    Intent intent = new Intent(context, OrderDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("orderinfo", entity.getId());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                });
        OrderListAdapter adapter = new OrderListAdapter(context, entity.getOrders());
        adapter.setId(entity.getId());
        holder.listView.setAdapter(adapter);
        holder.statusTv.setText(entity.getStatusDisplay());
        holder.timeTv.setText(entity.getCreated().substring(0, 10));
        String payment = new DecimalFormat("0.00").format(entity.getPayment());
        holder.paymentTv.setText(payment);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ll)
        LinearLayout linearLayout;
        @Bind(R.id.status)
        TextView statusTv;
        @Bind(R.id.payment)
        TextView paymentTv;
        @Bind(R.id.time)
        TextView timeTv;
        @Bind(R.id.lv)
        NestedListView listView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
