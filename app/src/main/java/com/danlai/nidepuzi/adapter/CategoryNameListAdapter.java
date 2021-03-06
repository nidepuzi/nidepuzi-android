package com.danlai.nidepuzi.adapter;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseListViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemCategoryItemBinding;
import com.danlai.nidepuzi.entity.PortalBean.CategorysBean;

/**
 * @author wisdom
 * @date 2017年05月18日 下午3:20
 */

public class CategoryNameListAdapter extends BaseListViewAdapter<ItemCategoryItemBinding, CategorysBean> {
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
    protected void fillData(CategorysBean categoryBean, BaseViewHolder<ItemCategoryItemBinding> holder, int position) {
        holder.b.tv.setText(categoryBean.getName());
        if (cid != null && !"".equals(cid)) {
            if (cid.equals(categoryBean.getId())) {
                holder.b.tv.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
                holder.b.selectedView.setBackgroundColor(mActivity.getResources().getColor(R.color.colorAccent));
            } else {
                holder.b.tv.setTextColor(mActivity.getResources().getColor(R.color.color_33));
                holder.b.selectedView.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
            }
        } else if (position == 0) {
            holder.b.tv.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
            holder.b.selectedView.setBackgroundColor(mActivity.getResources().getColor(R.color.colorAccent));
        } else {
            holder.b.tv.setTextColor(mActivity.getResources().getColor(R.color.color_33));
            holder.b.selectedView.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
        }
    }
}
