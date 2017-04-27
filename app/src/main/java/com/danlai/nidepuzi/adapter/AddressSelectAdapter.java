package com.danlai.nidepuzi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.AddressBean;
import com.danlai.nidepuzi.ui.activity.user.ChangeAddressActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author wisdom
 * @date 2017年04月13日 下午5:54
 */
public class AddressSelectAdapter extends RecyclerView.Adapter<AddressSelectAdapter.ViewHolder> {

    private List<AddressBean> mList;
    private Activity context;
    private String addressId;
    private int needLevel;

    public AddressSelectAdapter(Activity context, String addressId) {
        this.context = context;
        this.addressId = addressId;
        mList = new ArrayList<>();
        needLevel = 1;
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
    public AddressSelectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_address_select, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddressSelectAdapter.ViewHolder holder, int position) {
        AddressBean addressBean = mList.get(position);
        if (addressBean.isDefaultX()) {
            holder.tvDefault.setVisibility(View.VISIBLE);
        } else {
            holder.tvDefault.setVisibility(View.GONE);
        }
        holder.mCheckBox.setClickable(false);
        if (addressId != null && addressId.equals(addressBean.getId() + "")) {
            holder.mCheckBox.setChecked(true);
        } else {
            holder.mCheckBox.setChecked(false);
        }
        holder.receiverMobile.setText(addressBean.getReceiver_mobile());
        holder.receiverAddress.setText(addressBean.getReceiver_state() + "" + addressBean.getReceiver_city()
            + "" + addressBean.getReceiver_district() + "" + addressBean.getReceiver_address());
        holder.receiverName.setText(addressBean.getReceiver_name());
        holder.card.setOnClickListener(v -> setBundle(addressBean));
        holder.change.setOnClickListener(v -> setBundle1(addressBean));
    }

    private void setBundle(AddressBean addressBean) {
        Intent intent = new Intent();
        intent.putExtra("name", addressBean.getReceiver_name());
        intent.putExtra("phone", addressBean.getReceiver_mobile());
        intent.putExtra("id_no", addressBean.getIdentification_no());
        intent.putExtra("state", addressBean.getReceiver_state());
        intent.putExtra("city", addressBean.getReceiver_city());
        intent.putExtra("district", addressBean.getReceiver_district());
        intent.putExtra("address", addressBean.getReceiver_address());
        intent.putExtra("isDefault", addressBean.isDefaultX());
        intent.putExtra("addressDetails", addressBean.getReceiver_state()
            + addressBean.getReceiver_city()
            + addressBean.getReceiver_district()
            + addressBean.getReceiver_address());
        intent.putExtra("addressId", addressBean.getId() + "");
        intent.putExtra("level", addressBean.getPersonalinfo_level());
        intent.putExtra("face", addressBean.getIdcard().getFace());
        intent.putExtra("back", addressBean.getIdcard().getBack());
        context.setResult(Activity.RESULT_OK, intent);
        context.finish();
    }

    private void setBundle1(AddressBean addressBean) {
        Intent intent = new Intent(context, ChangeAddressActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("receiver_state", addressBean.getReceiver_state());
        bundle.putString("receiver_district", addressBean.getReceiver_district());
        bundle.putString("receiver_city", addressBean.getReceiver_city());
        bundle.putInt("needLevel", needLevel);
        bundle.putString("receiver_name", addressBean.getReceiver_name());
        bundle.putString("id", addressBean.getId() + "");
        bundle.putString("mobile", addressBean.getReceiver_mobile());
        bundle.putString("address1", addressBean.getReceiver_state()
            + addressBean.getReceiver_city()
            + addressBean.getReceiver_district());
        bundle.putString("address2", addressBean.getReceiver_address());
        bundle.putString("idNo", addressBean.getIdentification_no());
        bundle.putBoolean("isDefaultX", addressBean.isDefaultX());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setNeedLevel(int needLevel) {
        this.needLevel = needLevel;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.address_default)
        TextView tvDefault;
        @Bind(R.id.receiver_name)
        TextView receiverName;
        @Bind(R.id.receiver_mobile)
        TextView receiverMobile;
        @Bind(R.id.receiver_address)
        TextView receiverAddress;
        @Bind(R.id.change)
        Button change;
        @Bind(R.id.check_box)
        CheckBox mCheckBox;
        View card;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView;
            AutoUtils.auto(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
