package com.danlai.nidepuzi.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdom
 * @date 2017年05月15日 下午4:07
 */

public abstract class BaseRecyclerViewAdapter<B extends ViewDataBinding, D> extends RecyclerView.Adapter<BaseViewHolder<B>> {
    protected List<D> data;
    protected BaseActivity mActivity;

    public BaseRecyclerViewAdapter(BaseActivity activity) {
        mActivity = activity;
        data = new ArrayList<>();
    }

    public void addOneData(D d) {
        data.add(d);
        notifyDataSetChanged();
    }

    public void removeOneData(D d) {
        data.remove(d);
        notifyDataSetChanged();
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
    public BaseViewHolder<B> onCreateViewHolder(ViewGroup parent, int viewType) {
        B b = DataBindingUtil.inflate(LayoutInflater.from(mActivity), getLayoutId(), parent, false);
        return new BaseViewHolder<>(b);
    }

    protected abstract int getLayoutId();

    @Override
    public int getItemCount() {
        return data.size();
    }
}
