package com.danlai.nidepuzi.adapter;

import com.bumptech.glide.Glide;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.base.BaseWebViewActivity;
import com.danlai.nidepuzi.databinding.ItemEduBinding;
import com.danlai.nidepuzi.entity.EduBean.ResultsBean;
import com.danlai.nidepuzi.util.JumpUtils;

/**
 * @author wisdom
 * @date 2017年05月09日 下午4:26
 */

public class EduAdapter extends BaseRecyclerViewAdapter<ItemEduBinding, ResultsBean> {

    public EduAdapter(BaseActivity mActivity) {
        super(mActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_edu;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemEduBinding> holder, int position) {
        ResultsBean bean = data.get(position);
        holder.b.title.setText(bean.getTitle());
        holder.b.desc.setText(bean.getDescription());
        holder.b.num.setText("参与人数" + bean.getNum_attender());
        Glide.with(mActivity).load(bean.getCover_image()).into(holder.b.img);
        holder.itemView.setOnClickListener(v -> JumpUtils.jumpToWebViewWithCookies(mActivity, bean.getContent_link(), -1, BaseWebViewActivity.class,
            bean.getTitle(), false));
    }
}
