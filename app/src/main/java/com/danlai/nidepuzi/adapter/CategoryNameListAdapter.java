package com.danlai.nidepuzi.adapter;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseListViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemCategoryItemBinding;
import com.danlai.nidepuzi.entity.CategoryBean;


/**
 * @author wisdom
 * @date 2016年09月23日 上午11:50
 */

public class CategoryNameListAdapter extends BaseListViewAdapter<ItemCategoryItemBinding, CategoryBean> {
    private String cid;

    public CategoryNameListAdapter(BaseActivity mActivity) {
        super(mActivity);
    }

    public void setCid(String cid) {
        this.cid = cid;
        notifyDataSetChanged();
    }

    protected int getLayoutId() {
        return R.layout.item_category_item;
    }

    @Override
    protected void fillData(CategoryBean categoryBean, BaseViewHolder<ItemCategoryItemBinding> holder, int position) {
        holder.b.tv.setText(categoryBean.getName());
        if (cid != null && !"".equals(cid)) {
            if (cid.equals(categoryBean.getCid())) {
                holder.b.tv.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
                holder.b.selectedView.setBackgroundColor(mActivity.getResources().getColor(R.color.colorAccent));
                holder.b.tv.setBackgroundColor(mActivity.getResources().getColor(R.color.bg_grey));
            } else {
                holder.b.tv.setTextColor(mActivity.getResources().getColor(R.color.color_33));
                holder.b.selectedView.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
                holder.b.tv.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
            }
        } else if (position == 0) {
            holder.b.tv.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
            holder.b.selectedView.setBackgroundColor(mActivity.getResources().getColor(R.color.colorAccent));
            holder.b.tv.setBackgroundColor(mActivity.getResources().getColor(R.color.bg_grey));
        } else {
            holder.b.tv.setTextColor(mActivity.getResources().getColor(R.color.color_33));
            holder.b.selectedView.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
            holder.b.tv.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
        }
    }
}
