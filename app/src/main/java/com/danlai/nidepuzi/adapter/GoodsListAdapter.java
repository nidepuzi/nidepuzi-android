package com.danlai.nidepuzi.adapter;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseListViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemOneOrderBinding;
import com.danlai.nidepuzi.entity.AllOrdersBean.ResultsEntity.OrdersEntity;

import java.util.List;

/**
 * @author wisdom
 * @date 2016年05月28日 下午3:18
 */
public class GoodsListAdapter extends BaseListViewAdapter<ItemOneOrderBinding, OrdersEntity> {

    public GoodsListAdapter(List<OrdersEntity> data, BaseActivity mActivity) {
        super(mActivity);
        this.data = data;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_one_order;
    }

    @Override
    protected void fillData(OrdersEntity ordersEntity, BaseViewHolder<ItemOneOrderBinding> holder, int position) {
        ViewUtils.loadImgToImgViewWithPlaceholder(mActivity, holder.b.imgGood, ordersEntity.getPic_path());
        holder.b.txGoodName.setText(ordersEntity.getTitle());
        holder.b.txGoodPrice.setText("¥" + JUtils.formatDouble(ordersEntity.getTotal_fee()));
        holder.b.txGoodNum.setText("x" + ordersEntity.getNum());
        holder.b.txGoodSize.setText(ordersEntity.getSku_name());
    }
}
