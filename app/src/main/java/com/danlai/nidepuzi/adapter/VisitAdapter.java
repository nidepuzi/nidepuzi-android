package com.danlai.nidepuzi.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.entity.MMVisitorsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author wisdom
 * @date 2017年05月10日 上午11:48
 */

public class VisitAdapter extends RecyclerView.Adapter<VisitAdapter.ViewHolder> {
    private BaseActivity context;
    private List<MMVisitorsBean.ResultsEntity> mList;

    public VisitAdapter(BaseActivity context) {
        mList = new ArrayList<>();
        this.context = context;
    }

    public void updateWithClear(List<MMVisitorsBean.ResultsEntity> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void update(List<MMVisitorsBean.ResultsEntity> list) {

        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_visit, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MMVisitorsBean.ResultsEntity entity = mList.get(position);
        String created = entity.getCreated().replace("T", " ");
        String time = created.substring(0, 10);
        holder.timeTv.setText(time);
        if (position == 0) {
            holder.timeLayout.setVisibility(View.VISIBLE);
        } else {
            if (time.equals(mList.get(position - 1).getCreated().substring(0, 10))) {
                holder.timeLayout.setVisibility(View.GONE);
            } else {
                holder.timeLayout.setVisibility(View.VISIBLE);
            }
        }
        holder.tvFans.setText(entity.getVisitorNick());
        holder.tvInfo.setText(entity.getVisitorDescription());
        holder.tvTime.setText(created.substring(5, 19));
        if (TextUtils.isEmpty(entity.getVisitorImg())) {
            Glide.with(context).load(R.drawable.img_user_head).into(holder.imgFans);
        } else {
            Glide.with(context).load(entity.getVisitorImg()).into(holder.imgFans);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_view_time)
        TextView timeTv;
        @Bind(R.id.ll_layout_time)
        LinearLayout timeLayout;
        @Bind(R.id.img_fans)
        ImageView imgFans;
        @Bind(R.id.tv_fans)
        TextView tvFans;
        @Bind(R.id.tv_info)
        TextView tvInfo;
        @Bind(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

