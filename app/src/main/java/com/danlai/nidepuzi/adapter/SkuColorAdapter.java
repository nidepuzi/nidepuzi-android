package com.danlai.nidepuzi.adapter;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemSkuBinding;
import com.danlai.nidepuzi.entity.ProductDetailBean.SkuInfoBean;
import com.danlai.nidepuzi.ui.activity.product.ProductDetailActivity;

import java.util.List;

/**
 * Created by wisdom on 16/8/11.
 */
public class SkuColorAdapter extends BaseRecyclerViewAdapter<ItemSkuBinding, SkuInfoBean> {
    private ProductDetailActivity activity;
    private int num;

    public SkuColorAdapter(List<SkuInfoBean> data, ProductDetailActivity mActivity) {
        super(mActivity);
        this.data = data;
        this.activity = mActivity;
        num = 0;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_sku;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemSkuBinding> holder, int position) {
        SkuInfoBean skuInfoBean = data.get(position);
        holder.b.tagName.setText(skuInfoBean.getName());
        boolean flag = true;
        for (int i = 0; i < skuInfoBean.getSku_items().size(); i++) {
            if (!skuInfoBean.getSku_items().get(i).isIs_saleout()) {
                flag = false;
                break;
            }
        }
        if (flag) {
            holder.b.rl.setBackgroundResource(R.drawable.sku_item_bg_unselect);
            holder.b.tagName.setTextColor(mActivity.getResources().getColor(R.color.color_99));
            if (num == position) {
                num++;
            }
        } else {
            if (num == position) {
                holder.b.rl.setBackgroundResource(R.drawable.sku_item_bg_select);
                holder.b.tagName.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
            } else {
                holder.b.rl.setBackgroundResource(R.drawable.sku_item_bg_unselect);
                holder.b.tagName.setTextColor(mActivity.getResources().getColor(R.color.color_33));
            }
            holder.itemView.setOnClickListener(v -> {
                activity.refreshSku(position);
                num = position;
                notifyDataSetChanged();
            });
        }
        if (position == (data.size() - 1)) {
            activity.refreshSku(num);
        }
    }
}
