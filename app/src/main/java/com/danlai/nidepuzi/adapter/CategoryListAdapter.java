package com.danlai.nidepuzi.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemCategoryProductBinding;
import com.danlai.nidepuzi.entity.ProductListBean.ResultsBean;
import com.danlai.nidepuzi.ui.activity.product.ProductDetailActivity;

/**
 * @author wisdom
 * @date 2017年05月18日 下午3:09
 */

public class CategoryListAdapter extends BaseRecyclerViewAdapter<ItemCategoryProductBinding, ResultsBean> {

    public CategoryListAdapter(BaseActivity mActivity) {
        super(mActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_category_product;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemCategoryProductBinding> holder, int position) {
        ResultsBean resultsBean = data.get(position);
        String sale_state = resultsBean.getSale_state();
        if ("will".equals(sale_state)) {
            holder.b.saleStatus.setText("即将开售");
            holder.b.saleStatus.setVisibility(View.VISIBLE);
            holder.b.saleLayout.setVisibility(View.GONE);
        } else if ("off".equals(sale_state)) {
            holder.b.saleStatus.setText("已下架");
            holder.b.saleLayout.setVisibility(View.GONE);
            holder.b.saleStatus.setVisibility(View.VISIBLE);
        } else if ("on".equals(sale_state) && resultsBean.isIs_saleout()) {
            holder.b.saleStatus.setText("已抢光");
            holder.b.saleStatus.setVisibility(View.VISIBLE);
            holder.b.saleLayout.setVisibility(View.GONE);
        } else {
            holder.b.saleStatus.setVisibility(View.GONE);
            holder.b.saleLayout.setVisibility(View.VISIBLE);
        }
        ViewUtils.loadImgToImgViewWithWaterMark(mActivity, holder.b.image, resultsBean.getHead_img(),
            resultsBean.getWatermark_op());
        holder.b.name.setText(resultsBean.getName());
        holder.b.saleNum.setText("在售人数" + resultsBean.getSelling_num());
        holder.b.stock.setText("库存" + resultsBean.getStock());
        holder.b.agentPrice.setText("¥" + JUtils.formatDouble(resultsBean.getLowest_agent_price()));
        holder.b.profit.setText("赚" + JUtils.formatDouble(resultsBean.getProfit().getMin()));
        holder.itemView.setOnClickListener(v -> {
            int modelId = resultsBean.getId();
            Intent intent = new Intent(mActivity, ProductDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("model_id", modelId);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        });
    }
}
