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
import com.danlai.nidepuzi.databinding.ItemTodayListBinding;
import com.danlai.nidepuzi.entity.ProductListBean.ResultsBean;
import com.danlai.nidepuzi.ui.activity.product.ProductDetailActivity;


/**
 * Created by wisdom on 16/8/6.
 */
public class ProductListAdapter extends BaseRecyclerViewAdapter<ItemTodayListBinding, ResultsBean> {

    public ProductListAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_today_list;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemTodayListBinding> holder, int position) {
        ResultsBean resultsBean = data.get(position);
        String sale_state = resultsBean.getSale_state();
        if ("will".equals(sale_state)) {
            holder.b.saleStatus.setText("即将开售");
            holder.b.saleStatus.setVisibility(View.VISIBLE);
        } else if ("off".equals(sale_state)) {
            holder.b.saleStatus.setText("已下架");
            holder.b.saleStatus.setVisibility(View.VISIBLE);
        } else if ("on".equals(sale_state) && resultsBean.isIs_saleout()) {
            holder.b.saleStatus.setText("已抢光");
            holder.b.saleStatus.setVisibility(View.VISIBLE);
        } else {
            holder.b.saleStatus.setVisibility(View.GONE);
        }
        ViewUtils.loadImgToImgViewWithPlaceholder(mActivity, holder.b.image, resultsBean.getHead_img());
        holder.b.name.setText(resultsBean.getName());
        holder.b.agentPrice.setText("¥" + JUtils.formatDouble(resultsBean.getLowest_agent_price()));
        holder.b.stdSalePrice.setText("/¥" + JUtils.formatDouble(resultsBean.getLowest_std_sale_price()));
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
