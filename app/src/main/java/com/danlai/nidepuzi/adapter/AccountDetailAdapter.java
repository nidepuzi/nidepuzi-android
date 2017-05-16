package com.danlai.nidepuzi.adapter;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemUserWalletBinding;
import com.danlai.nidepuzi.entity.BudgetDetailBean.ResultsBean;

public class AccountDetailAdapter extends BaseRecyclerViewAdapter<ItemUserWalletBinding, ResultsBean> {

    public AccountDetailAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_user_wallet;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemUserWalletBinding> holder, int position) {
        ResultsBean bean = data.get(position);
        holder.b.setData(bean);
        if (0 == bean.getBudget_type()) {
            holder.b.tvMoneyChange.setText("+ " + JUtils.formatDouble(bean.getBudeget_detail_cash()) + "元  ");
            holder.b.tvMoneyChange.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
        } else if (1 == bean.getBudget_type()) {
            holder.b.tvMoneyChange.setText("- " + JUtils.formatDouble(bean.getBudeget_detail_cash()) + "元  ");
            holder.b.tvMoneyChange.setTextColor(mActivity.getResources().getColor(R.color.color_33));
        }
        // TODO: 17/5/15
//        bindOnClickListener(holder.itemView, position);
    }

//    private void bindOnClickListener(View itemView, int position) {
//        ResultsBean entity = data.get(position);
//        itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(mActivity, WalletDetailActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("entity", entity);
//            intent.putExtras(bundle);
//            mActivity.startActivity(intent);
//        });
//    }
}
