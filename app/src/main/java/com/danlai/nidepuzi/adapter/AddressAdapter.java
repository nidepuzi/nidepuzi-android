package com.danlai.nidepuzi.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.entity.AddressBean;
import com.danlai.nidepuzi.entity.AddressResultBean;
import com.danlai.nidepuzi.entity.event.AddressChangeEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.user.ChangeAddressActivity;
import com.zhy.autolayout.utils.AutoUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author wisdom
 * @date 2017年04月28日 下午2:38
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
        AddressBean bean = mList.get(position);
        holder.receiverMobile.setText(bean.getReceiver_mobile());
        holder.receiverAddress.setText(bean.getReceiver_state() + "" + bean.getReceiver_city()
            + "" + bean.getReceiver_district() + "" + bean.getReceiver_address());
        holder.receiverName.setText(bean.getReceiver_name());
        String idno = bean.getIdentification_no();
        if (idno != null && idno.length() == 18) {
            holder.receiverIdno.setText("身份证:" + idno.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1**********$2"));
        } else {
            holder.receiverIdno.setText("身份证:未填写");
        }
        holder.layoutEdit.setOnClickListener(v -> setBundle(bean));
        holder.layoutDelete.setOnClickListener(v -> new AlertDialog.Builder(context)
            .setTitle("删除地址")
            .setMessage("您确定要删除吗？")
            .setPositiveButton("确定", (dialog, which) -> {
                BaseApp.getAddressInteractor(context)
                    .delete_address(bean.getId() + "", new ServiceResponse<AddressResultBean>(context) {
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
            holder.ivDeafult.setImageResource(R.drawable.icon_address_select);
            holder.ivDeafult.setOnClickListener(null);
        } else {
            holder.ivDeafult.setImageResource(R.drawable.icon_address_un);
            holder.ivDeafult.setOnClickListener(v ->
                BaseApp.getAddressInteractor(context)
                    .update_addressWithId(bean.getId() + "", bean.getReceiver_state(), bean.getReceiver_city(),
                        bean.getReceiver_district(), bean.getReceiver_address(), bean.getReceiver_name(),
                        bean.getReceiver_mobile(), "true", bean.getIdentification_no(), bean.getIdcard().getFace(),
                        bean.getIdcard().getBack(), new ServiceResponse<AddressResultBean>(context) {
                            @Override
                            public void onNext(AddressResultBean addressResultBean) {
                                EventBus.getDefault().post(new AddressChangeEvent());
                                if (addressResultBean != null) {
                                    if (addressResultBean.getCode() == 0) {
                                        JUtils.Toast("设置成功");
                                    }
                                }
                            }
                        }));
        }
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
        @Bind(R.id.receiver_name)
        TextView receiverName;
        @Bind(R.id.receiver_mobile)
        TextView receiverMobile;
        @Bind(R.id.receiver_address)
        TextView receiverAddress;
        @Bind(R.id.receiver_idno)
        TextView receiverIdno;
        @Bind(R.id.layout_default)
        LinearLayout layoutDefault;
        @Bind(R.id.layout_edit)
        LinearLayout layoutEdit;
        @Bind(R.id.layout_delete)
        LinearLayout layoutDelete;
        @Bind(R.id.iv_default)
        ImageView ivDeafult;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
