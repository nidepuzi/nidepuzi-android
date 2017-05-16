package com.danlai.nidepuzi.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdom
 * @date 2017年05月16日 上午9:57
 */

public abstract class BaseListViewAdapter<B extends ViewDataBinding, D> extends BaseAdapter {
    protected List<D> data;
    protected BaseActivity mActivity;

    public BaseListViewAdapter(BaseActivity activity) {
        mActivity = activity;
        data = new ArrayList<>();
    }

    public void update(List<D> list) {
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void updateWithClear(List<D> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public D getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected abstract int getLayoutId();

    @Override
    @SuppressWarnings("unchecked")
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder<B> holder;
        if (convertView == null) {
            B b = DataBindingUtil.inflate(LayoutInflater.from(mActivity), getLayoutId(), parent, false);
            convertView = b.getRoot();
            holder = new BaseViewHolder<>(b);
            convertView.setTag(holder);
        } else {
            holder = (BaseViewHolder<B>) convertView.getTag();
        }
        fillData(data.get(position), holder, position);
        return convertView;
    }

    protected abstract void fillData(D d, BaseViewHolder<B> holder, int position);
}
