package com.danlai.nidepuzi.adapter;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemShareHistoryBinding;
import com.danlai.nidepuzi.entity.FansBean.ResultsBean;

/**
 * @author wisdom
 * @date 2017年05月26日 下午2:31
 */

public class ShareHistoryAdapter extends BaseRecyclerViewAdapter<ItemShareHistoryBinding, ResultsBean> {
    public ShareHistoryAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_share_history;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemShareHistoryBinding> holder, int position) {
        ResultsBean bean = data.get(position);
        ItemShareHistoryBinding b = holder.b;
        b.tvMobile.setText(bean.getInvitee_mobile());
        b.tvName.setText(bean.getNick());
        b.tvDate.setText(bean.getCharge_time().replace("T", " ").substring(0, 10));
        if (bean.getReferal_type() == 365 && "effect".equals(bean.getInvitee_status())) {
            b.tvStatus.setText("已支付");
        } else if ("effect".equals(bean.getInvitee_status())) {
            b.tvStatus.setText("未支付");
        } else {
            b.tvStatus.setText("已冻结");
        }
    }
}
