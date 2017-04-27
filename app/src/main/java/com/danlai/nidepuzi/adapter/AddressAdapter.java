package com.danlai.nidepuzi.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.entity.AddressBean;
import com.danlai.nidepuzi.entity.AddressResultBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.user.ChangeAddressActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by itxuye(www.itxuye.com) on 2016/01/18.
 * <p>
 * Copyright 2015年 上海己美. All rights reserved.
 */
public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private List<AddressBean> mList;
    private BaseActivity context;

    public AddressAdapter(BaseActivity context) {
        this.context = context;
        mList = new ArrayList<>();
    }

    public void update(List<AddressBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void updateWithClear(List<AddressBean> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_address, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddressAdapter.ViewHolder holder, int position) {
        AddressBean addressBean = mList.get(position);
        if (addressBean.isDefaultX()) {
            holder.addressDefault.setVisibility(View.VISIBLE);
        } else {
            holder.addressDefault.setVisibility(View.GONE);
        }
        holder.receiverMobile.setText(addressBean.getReceiver_mobile());
        holder.receiverAddress.setText(addressBean.getReceiver_state() + "" + addressBean.getReceiver_city()
            + "" + addressBean.getReceiver_district() + "" + addressBean.getReceiver_address());
        holder.receiverName.setText(addressBean.getReceiver_name());
        holder.card.setOnClickListener(v -> setBundle(addressBean));
        holder.card.setOnLongClickListener(v -> {
            new AlertDialog.Builder(context)
                .setTitle("删除地址")
                .setMessage("您确定要删除吗？")
                .setPositiveButton("确定", (dialog, which) -> {
                    BaseApp.getAddressInteractor(context)
                        .delete_address(addressBean.getId() + "", new ServiceResponse<AddressResultBean>(context) {
                            @Override
                            public void onNext(AddressResultBean addressResultBean) {
                                if (addressResultBean != null && addressResultBean.isRet()) {
                                    removeAt(position);
                                }
                            }
                        });
                    dialog.dismiss();
                }).
                setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                .show();
            return false;
        });
    }

    private void setBundle(AddressBean addressBean) {
        Intent intent = new Intent(context, ChangeAddressActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("receiver_state", addressBean.getReceiver_state());
        bundle.putString("receiver_district", addressBean.getReceiver_district());
        bundle.putString("receiver_city", addressBean.getReceiver_city());

        bundle.putString("receiver_name", addressBean.getReceiver_name());
        bundle.putString("id", addressBean.getId() + "");
        bundle.putString("mobile", addressBean.getReceiver_mobile());
        bundle.putString("address1", addressBean.getReceiver_state()
            + addressBean.getReceiver_city()
            + addressBean.getReceiver_district());
        bundle.putString("address2", addressBean.getReceiver_address());
        bundle.putBoolean("isDefaultX", addressBean.isDefaultX());
        bundle.putString("idNo", addressBean.getIdentification_no());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private void removeAt(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mList.size());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.address_default)
        TextView addressDefault;
        @Bind(R.id.receiver_name)
        TextView receiverName;
        @Bind(R.id.receiver_mobile)
        TextView receiverMobile;
        @Bind(R.id.receiver_address)
        TextView receiverAddress;
        View card;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView;
            AutoUtils.auto(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
