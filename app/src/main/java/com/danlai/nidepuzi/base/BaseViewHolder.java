package com.danlai.nidepuzi.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * @author wisdom
 * @date 2017年05月15日 下午4:04
 */

public class BaseViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public B b;

    BaseViewHolder(B b) {
        super(b.getRoot());
        this.b = b;
        AutoUtils.auto(itemView);
    }
}
