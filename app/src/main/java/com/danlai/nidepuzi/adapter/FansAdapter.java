package com.danlai.nidepuzi.adapter;

import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemFansBinding;
import com.danlai.nidepuzi.entity.FansBean.ResultsEntity;

/**
 * @author wisdom
 * @date 2016年10月5日 上午11:33
 */

public class FansAdapter extends BaseRecyclerViewAdapter<ItemFansBinding, ResultsEntity> {
    public FansAdapter(BaseActivity mActivity) {
        super(mActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fans;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemFansBinding> holder, int position) {
        ResultsEntity entity = data.get(position);
        holder.b.tvName.setText("微信名:" + entity.getFansNick());
        holder.b.tvTime.setText("注册时间:" + entity.getCreated().substring(0, 10));
        if (TextUtils.isEmpty(entity.getFansThumbnail())) {
            Glide.with(mActivity).load(R.drawable.img_user_head).into(holder.b.ivHead);
        } else {
            ViewUtils.loadImgToImgView(mActivity, holder.b.ivHead, entity.getFansThumbnail());
        }
    }
}

