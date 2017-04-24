package com.danlai.nidepuzi.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.entity.CategoryBean;
import com.danlai.nidepuzi.ui.activity.product.CategoryProductActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wisdom on 17/2/16.
 */

public class CategoryItemAdapter extends XRecyclerView.Adapter<CategoryItemAdapter.ViewHolder> {

    private List<CategoryBean> mData;
    private BaseActivity mContext;

    public CategoryItemAdapter(BaseActivity mActivity) {
        this.mContext = mActivity;
        mData = new ArrayList<>();
    }

    public void update(List<CategoryBean> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public void updateWithClear(List<CategoryBean> list) {
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CategoryBean bean = mData.get(position);
        holder.name.setText(bean.getName());
        holder.name.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, CategoryProductActivity.class);
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
            mContext.startActivity(intent);
        });
        GridLayoutManager manager = new GridLayoutManager(mContext, 3);
        holder.xrv.setLayoutManager(manager);
        holder.xrv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        holder.xrv.setPullRefreshEnabled(false);
        holder.xrv.setLoadingMoreEnabled(false);
        CategoryAdapter adapter = new CategoryAdapter(mContext, bean.getName());
        adapter.updateWithClear(bean.getChilds());
        holder.xrv.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends XRecyclerView.ViewHolder {
        View item;
        @Bind(R.id.item_name)
        TextView name;
        @Bind(R.id.item_xrv)
        XRecyclerView xrv;

        public ViewHolder(View itemView) {
            super(itemView);
            item = itemView;
            AutoUtils.autoSize(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
