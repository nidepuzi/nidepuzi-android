package com.danlai.nidepuzi.adapter;

import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemVisitBinding;
import com.danlai.nidepuzi.entity.MMVisitorsBean.ResultsEntity;

/**
 * @author wisdom
 * @date 2017年05月10日 上午11:48
 */

public class VisitAdapter extends BaseRecyclerViewAdapter<ItemVisitBinding, ResultsEntity> {
    private boolean isToday;

    public VisitAdapter(BaseActivity activity) {
        super(activity);
        isToday = false;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemVisitBinding> holder, int position) {
        ResultsEntity entity = data.get(position);
        String created = entity.getCreated().replace("T", " ");
        String time = created.substring(0, 10);
        holder.b.timeTv.setText(time);
        if (isToday) {
            holder.b.timeLayout.setVisibility(View.GONE);
        } else if (position == 0) {
            holder.b.timeLayout.setVisibility(View.VISIBLE);
        } else {
            if (time.equals(data.get(position - 1).getCreated().substring(0, 10))) {
                holder.b.timeLayout.setVisibility(View.GONE);
            } else {
                holder.b.timeLayout.setVisibility(View.VISIBLE);
            }
        }
        holder.b.tvFans.setText(entity.getVisitorNick());
        holder.b.tvInfo.setText(entity.getVisitorDescription());
        holder.b.tvTime.setText(created.substring(5, 19));
        if (TextUtils.isEmpty(entity.getVisitorImg())) {
            Glide.with(mActivity).load(R.drawable.img_user_head).into(holder.b.imgFans);
        } else {
            Glide.with(mActivity).load(entity.getVisitorImg()).into(holder.b.imgFans);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_visit;
    }

    public void setToday(boolean today) {
        isToday = today;
    }
}

