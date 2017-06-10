package com.danlai.nidepuzi.adapter;

import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemFansBinding;
import com.danlai.nidepuzi.entity.FansBean.ResultsBean;

/**
 * @author wisdom
 * @date 2016年10月5日 上午11:33
 */

public class FansAdapter extends BaseRecyclerViewAdapter<ItemFansBinding, ResultsBean> {
    public FansAdapter(BaseActivity mActivity) {
        super(mActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fans;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemFansBinding> holder, int position) {
        ResultsBean bean = data.get(position);
        holder.b.tvName.setText("微信名:" + bean.getNick());
        holder.b.tvTime.setText("注册时间:" + bean.getCharge_time().substring(0, 10));
        if (TextUtils.isEmpty(bean.getThumbnail())) {
            Glide.with(mActivity).load(R.drawable.img_user_head).into(holder.b.ivHead);
        } else {
            ViewUtils.loadImgToImgView(mActivity, holder.b.ivHead, bean.getThumbnail());
        }
    }
}

