package com.danlai.nidepuzi.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemItemCategoryBinding;
import com.danlai.nidepuzi.entity.CategoryBean;
import com.danlai.nidepuzi.ui.activity.product.CategoryProductActivity;

import java.util.ArrayList;

/**
 * Created by wisdom on 17/2/16.
 */

public class CategoryItemAdapter extends BaseRecyclerViewAdapter<ItemItemCategoryBinding, CategoryBean> {

    public CategoryItemAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_item_category;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemItemCategoryBinding> holder, int position) {
        CategoryBean bean = data.get(position);
        holder.b.itemName.setText(bean.getName());
        holder.b.itemName.setOnClickListener(view -> {
            Intent intent = new Intent(mActivity, CategoryProductActivity.class);
            Bundle bundle = new Bundle();
            ArrayList<String> nameList = new ArrayList<>();
            ArrayList<String> cidList = new ArrayList<>();
            nameList.add(bean.getName());
            cidList.add(bean.getCid());
            for (int i = 0; i < bean.getChilds().size(); i++) {
                nameList.add(bean.getChilds().get(i).getName());
                cidList.add(bean.getChilds().get(i).getCid());
            }
            bundle.putStringArrayList("name", nameList);
            bundle.putStringArrayList("cid", cidList);
            bundle.putInt("position", 0);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        });
        GridLayoutManager manager = new GridLayoutManager(mActivity, 3);
        holder.b.itemXrv.setLayoutManager(manager);
        holder.b.itemXrv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        holder.b.itemXrv.setPullRefreshEnabled(false);
        holder.b.itemXrv.setLoadingMoreEnabled(false);
        CategoryAdapter adapter = new CategoryAdapter(mActivity, bean.getName());
        adapter.updateWithClear(bean.getChilds());
        holder.b.itemXrv.setAdapter(adapter);
    }

}
