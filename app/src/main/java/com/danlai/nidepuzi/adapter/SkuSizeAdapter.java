package com.danlai.nidepuzi.adapter;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemSkuBinding;
import com.danlai.nidepuzi.entity.ProductDetailBean.SkuInfoBean.SkuItemsBean;
import com.danlai.nidepuzi.ui.activity.product.ProductDetailActivity;

/**
 * Created by wisdom on 16/8/11.
 */
public class SkuSizeAdapter extends BaseRecyclerViewAdapter<ItemSkuBinding, SkuItemsBean> {
    private ProductDetailActivity activity;
    private int num;

    public SkuSizeAdapter(ProductDetailActivity mActivity) {
        super(mActivity);
        this.activity = mActivity;
        num = 0;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_sku;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemSkuBinding> holder, int position) {
        SkuItemsBean skuItemsBean = data.get(position);
        holder.b.tagName.setText(skuItemsBean.getName());
        if (skuItemsBean.getFree_num() == 0) {
            holder.b.rl.setBackgroundResource(R.drawable.sku_item_bg_unselect);
            holder.b.tagName.setTextColor(activity.getResources().getColor(R.color.color_99));
            if (num == position) {
                activity.refreshSkuId(skuItemsBean);
            }
        } else {
            if (num == position) {
                holder.b.rl.setBackgroundResource(R.drawable.sku_item_bg_select);
                holder.b.tagName.setTextColor(activity.getResources().getColor(R.color.colorAccent));
                activity.refreshSkuId(skuItemsBean);
            } else {
                holder.b.rl.setBackgroundResource(R.drawable.sku_item_bg_unselect);
                holder.b.tagName.setTextColor(activity.getResources().getColor(R.color.color_33));
            }
            holder.itemView.setOnClickListener(v -> {
                num = position;
                notifyDataSetChanged();
            });
        }
    }
}
