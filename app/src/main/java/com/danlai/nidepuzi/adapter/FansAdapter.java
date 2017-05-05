package com.danlai.nidepuzi.adapter;

/**
 * Created by wulei on 15-12-17.
 * 商品订单数据适配
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.danlai.library.utils.ViewUtils;
import com.danlai.library.widget.CircleImageView;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.FansBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FansAdapter extends RecyclerView.Adapter<FansAdapter.ViewHolder> {
    private Activity context;
    private List<FansBean.ResultsEntity> mList;

    public FansAdapter(Activity context) {
        mList = new ArrayList<>();
        this.context = context;
    }

    public void update(List<FansBean.ResultsEntity> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fans, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FansBean.ResultsEntity entity = mList.get(position);
        holder.tvName.setText("微信名:" + entity.getFansNick());
        holder.tvTime.setText("注册时间:" + entity.getCreated().substring(0, 10));
        if (TextUtils.isEmpty(entity.getFansThumbnail())) {
            Glide.with(context).load(R.drawable.img_user_head).into(holder.ivHead);
        } else {
            ViewUtils.loadImgToImgView(context, holder.ivHead, entity.getFansThumbnail());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_head)
        CircleImageView ivHead;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

