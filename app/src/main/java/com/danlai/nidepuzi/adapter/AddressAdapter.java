package com.danlai.nidepuzi.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemAddressBinding;
import com.danlai.nidepuzi.entity.AddressBean;
import com.danlai.nidepuzi.entity.AddressResultBean;
import com.danlai.nidepuzi.entity.event.AddressChangeEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.user.ChangeAddressActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * @author wisdom
 * @date 2017年04月28日 下午2:38
 */
public class AddressAdapter extends BaseRecyclerViewAdapter<ItemAddressBinding, AddressBean> {

    public AddressAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemAddressBinding> holder, int position) {
        AddressBean bean = data.get(position);
        holder.b.setData(bean);
        String idno = bean.getIdentification_no();
        if (idno != null && idno.length() == 18) {
            holder.b.receiverIdno.setText("身份证:" + idno.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1**********$2"));
        } else {
            holder.b.receiverIdno.setText("身份证:未填写");
        }
        holder.b.layoutEdit.setOnClickListener(v -> setBundle(bean));
        holder.b.layoutDelete.setOnClickListener(v -> new AlertDialog.Builder(mActivity)
            .setTitle("删除地址")
            .setMessage("您确定要删除吗？")
            .setPositiveButton("确定", (dialog, which) -> {
                BaseApp.getAddressInteractor(mActivity)
                    .delete_address(bean.getId() + "", new ServiceResponse<AddressResultBean>(mActivity) {
                        @Override
                        public void onNext(AddressResultBean addressResultBean) {
                            if (addressResultBean != null && addressResultBean.isRet()) {
                                removeAt(position);
                            }
                        }
                    });
                dialog.dismiss();
            })
            .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
            .show());
        if (bean.isDefaultX()) {
            holder.b.ivDefault.setImageResource(R.drawable.icon_address_select);
            holder.b.layoutDefault.setOnClickListener(null);
        } else {
            holder.b.ivDefault.setImageResource(R.drawable.icon_address_un);
            holder.b.layoutDefault.setOnClickListener(v -> new AlertDialog.Builder(mActivity)
                .setTitle("提示")
                .setMessage("是否设置为默认地址?")
                .setPositiveButton("确定", (dialog, which) -> {
                    BaseApp.getAddressInteractor(mActivity)
                        .update_addressWithId(bean.getId() + "", bean.getReceiver_state(), bean.getReceiver_city(),
                            bean.getReceiver_district(), bean.getReceiver_address(), bean.getReceiver_name(),
                            bean.getReceiver_mobile(), "true", bean.getIdentification_no(), bean.getIdcard().getFace(),
                            bean.getIdcard().getBack(), new ServiceResponse<AddressResultBean>(mActivity) {
                                @Override
                                public void onNext(AddressResultBean addressResultBean) {
                                    EventBus.getDefault().post(new AddressChangeEvent());
                                    if (addressResultBean != null) {
                                        if (addressResultBean.getCode() == 0) {
                                            JUtils.Toast("设置成功");
                                        }
                                    }
                                }
                            });
                    dialog.dismiss();
                })
                .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                .show());
        }
    }

    private void setBundle(AddressBean addressBean) {
        Intent intent = new Intent(mActivity, ChangeAddressActivity.class);
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
        mActivity.startActivity(intent);
    }

    private void removeAt(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_address;
    }
}
