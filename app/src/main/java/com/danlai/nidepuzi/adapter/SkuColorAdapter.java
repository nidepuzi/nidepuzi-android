package com.danlai.nidepuzi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.ProductDetailBean.SkuInfoBean;
import com.danlai.nidepuzi.ui.activity.product.ProductDetailActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wisdom on 16/8/11.
 */
public class SkuColorAdapter extends RecyclerView.Adapter<SkuColorAdapter.SkuColorViewHolder> {
    private List<SkuInfoBean> data;
    private ProductDetailActivity activity;
    private int num;

    public SkuColorAdapter(List<SkuInfoBean> data, ProductDetailActivity activity) {
        this.data = data;
        this.activity = activity;
        num = 0;
    }

    @Override
    public SkuColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_sku, parent, false);
        return new SkuColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SkuColorViewHolder holder, int position) {
        SkuInfoBean skuInfoBean = data.get(position);
        holder.name.setText(skuInfoBean.getName());
        boolean flag = true;
        for (int i = 0; i < skuInfoBean.getSku_items().size(); i++) {
            if (!skuInfoBean.getSku_items().get(i).isIs_saleout()) {
                flag = false;
                break;
            }
        }
        if (flag) {
            holder.rl.setBackgroundResource(R.drawable.sku_item_bg_unselect);
            holder.name.setTextColor(activity.getResources().getColor(R.color.color_88));
            if (num == position) {
                num++;
            }
        } else {
            if (num == position) {
                holder.rl.setBackgroundResource(R.drawable.sku_item_bg_select);
                holder.name.setTextColor(activity.getResources().getColor(R.color.colorAccent));
            } else {
                holder.rl.setBackgroundResource(R.drawable.sku_item_bg_unselect);
                holder.name.setTextColor(activity.getResources().getColor(R.color.color_44));
            }
            holder.view.setOnClickListener(v -> {
                activity.refreshSku(position);
                num = position;
                notifyDataSetChanged();
            });
        }
        if (position == (data.size() - 1)) {
            activity.refreshSku(num);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SkuColorViewHolder extends RecyclerView.ViewHolder {
        View view;
        @Bind(R.id.rl)
        RelativeLayout rl;
        @Bind(R.id.tag_name)
        TextView name;

        public SkuColorViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            AutoUtils.autoSize(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
