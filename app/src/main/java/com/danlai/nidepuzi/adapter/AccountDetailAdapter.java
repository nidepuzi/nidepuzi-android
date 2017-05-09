package com.danlai.nidepuzi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.BudgetDetailBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AccountDetailAdapter extends RecyclerView.Adapter<AccountDetailAdapter.UserWalletVH> {

    private List<BudgetDetailBean.ResultsBean> mList;
    private Context mContext;

    public AccountDetailAdapter(Context context) {
        this.mContext = context;
        mList = new ArrayList<>();
    }

    public void updateWithClear(List<BudgetDetailBean.ResultsBean> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void update(List<BudgetDetailBean.ResultsBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public UserWalletVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_userwallet, parent, false);
        return new UserWalletVH(view);
    }

    @Override
    public void onBindViewHolder(UserWalletVH holder, int position) {
        BudgetDetailBean.ResultsBean bean = mList.get(position);
        holder.tvTitle.setText(bean.getBudget_log_type());
        holder.tvTime.setText(bean.getBudget_date());
        holder.tvDesc.setText(bean.getDesc());
        holder.tvStatus.setText(bean.getGet_status_display());
        if (0 == bean.getBudget_type()) {
            holder.tvMoneyChange.setText("+ " + JUtils.formatDouble(bean.getBudeget_detail_cash()) + "元  ");
            holder.tvMoneyChange.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        } else if (1 == bean.getBudget_type()) {
            holder.tvMoneyChange.setText("- " + JUtils.formatDouble(bean.getBudeget_detail_cash()) + "元  ");
            holder.tvMoneyChange.setTextColor(mContext.getResources().getColor(R.color.color_33));
        }
        bindOnClickListener(holder.itemView, position);
    }

    private void bindOnClickListener(View itemView, int position) {
        BudgetDetailBean.ResultsBean entity = mList.get(position);
        itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(mContext, WalletDetailActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("entity", entity);
//            intent.putExtras(bundle);
//            mContext.startActivity(intent);
            // TODO: 17/5/9
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    static class UserWalletVH extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_desc)
        TextView tvDesc;
        @Bind(R.id.tv_money_change)
        TextView tvMoneyChange;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_status)
        TextView tvStatus;

        UserWalletVH(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
