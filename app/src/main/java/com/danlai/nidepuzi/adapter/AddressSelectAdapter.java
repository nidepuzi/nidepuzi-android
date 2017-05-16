package com.danlai.nidepuzi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemAddressSelectBinding;
import com.danlai.nidepuzi.entity.AddressBean;
import com.danlai.nidepuzi.ui.activity.user.ChangeAddressActivity;

/**
 * @author wisdom
 * @date 2017年04月13日 下午5:54
 */
public class AddressSelectAdapter extends BaseRecyclerViewAdapter<ItemAddressSelectBinding, AddressBean> {
    private String addressId;
    private int needLevel;

    public AddressSelectAdapter(BaseActivity activity, String addressId) {
        super(activity);
        this.addressId = addressId;
        needLevel = 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_address_select;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemAddressSelectBinding> holder, int position) {
        AddressBean addressBean = data.get(position);
        if (addressBean.isDefaultX()) {
            holder.b.addressDefault.setVisibility(View.VISIBLE);
        } else {
            holder.b.addressDefault.setVisibility(View.GONE);
        }
        holder.b.checkBox.setClickable(false);
        if (addressId != null && addressId.equals(addressBean.getId() + "")) {
            holder.b.checkBox.setChecked(true);
        } else {
            holder.b.checkBox.setChecked(false);
        }
        holder.b.receiverMobile.setText(addressBean.getReceiver_mobile());
        holder.b.receiverAddress.setText(addressBean.getReceiver_state() + "" + addressBean.getReceiver_city()
            + "" + addressBean.getReceiver_district() + "" + addressBean.getReceiver_address());
        holder.b.receiverName.setText(addressBean.getReceiver_name());
        holder.itemView.setOnClickListener(v -> setBundle(addressBean));
        holder.b.change.setOnClickListener(v -> setBundle1(addressBean));
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
        mActivity.setResult(Activity.RESULT_OK, intent);
        mActivity.finish();
    }

    private void setBundle1(AddressBean addressBean) {
        Intent intent = new Intent(mActivity, ChangeAddressActivity.class);
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
        mActivity.startActivity(intent);
    }

    public void setNeedLevel(int needLevel) {
        this.needLevel = needLevel;
    }
}
