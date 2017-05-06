package com.danlai.nidepuzi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.ProductDetailBean.SkuInfoBean.SkuItemsBean;
import com.danlai.nidepuzi.ui.activity.product.ProductDetailActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wisdom on 16/8/11.
 */
public class SkuSizeAdapter extends RecyclerView.Adapter<SkuSizeAdapter.SkuSizeViewHolder> {

    private List<SkuItemsBean> data;
    private ProductDetailActivity activity;
    private int num;

    public SkuSizeAdapter(ProductDetailActivity activity) {
        data = new ArrayList<>();
        this.activity = activity;
        num = 0;
    }

    public void updateWithClear(List<SkuItemsBean> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void update(List<SkuItemsBean> list) {
        data.addAll(list);
        num = 0;
        notifyDataSetChanged();
    }

    @Override
    public SkuSizeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_sku, parent, false);
        return new SkuSizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SkuSizeViewHolder holder, int position) {
        SkuItemsBean skuItemsBean = data.get(position);
        holder.name.setText(skuItemsBean.getName());
        if (skuItemsBean.getFree_num() == 0) {
            holder.rl.setBackgroundResource(R.drawable.sku_item_bg_unselect);
            holder.name.setTextColor(activity.getResources().getColor(R.color.color_99));
            if (num == position) {
//                num++;
                activity.refreshSkuId(skuItemsBean);
            }
        } else {
            if (num == position) {
                holder.rl.setBackgroundResource(R.drawable.sku_item_bg_select);
                holder.name.setTextColor(activity.getResources().getColor(R.color.colorAccent));
                activity.refreshSkuId(skuItemsBean);
            } else {
                holder.rl.setBackgroundResource(R.drawable.sku_item_bg_unselect);
                holder.name.setTextColor(activity.getResources().getColor(R.color.color_33));
            }
            holder.view.setOnClickListener(v -> {
                num = position;
                notifyDataSetChanged();
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SkuSizeViewHolder extends RecyclerView.ViewHolder {
        View view;
        @Bind(R.id.rl)
        RelativeLayout rl;
        @Bind(R.id.tag_name)
        TextView name;

        public SkuSizeViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            AutoUtils.autoSize(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
