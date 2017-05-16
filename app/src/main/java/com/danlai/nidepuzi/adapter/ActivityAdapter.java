package com.danlai.nidepuzi.adapter;

import com.bumptech.glide.Glide;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.base.BaseWebViewActivity;
import com.danlai.nidepuzi.databinding.ItemActivityBinding;
import com.danlai.nidepuzi.entity.PortalBean.ActivitysBean;
import com.danlai.nidepuzi.util.JumpUtils;

/**
 * Created by wisdom on 17/2/28.
 */

public class ActivityAdapter extends BaseRecyclerViewAdapter<ItemActivityBinding, ActivitysBean> {

    public ActivityAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemActivityBinding> holder, int position) {
        ActivitysBean bean = data.get(position);
        Glide.with(mActivity).load(bean.getAct_img()).into(holder.b.img);
        holder.b.img.setOnClickListener(v -> {
            String actLink = bean.getAct_link();
            JumpUtils.jumpToWebViewWithCookies(mActivity, actLink, bean.getId(), BaseWebViewActivity.class,
                bean.getTitle());
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_activity;
    }
}
