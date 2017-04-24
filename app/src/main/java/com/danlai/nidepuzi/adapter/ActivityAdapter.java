package com.danlai.nidepuzi.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.danlai.library.widget.ActivityImageView;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.PortalBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wisdom on 17/2/28.
 */

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {
    private List<PortalBean.ActivitysBean> data;
    private Activity context;

    public ActivityAdapter(Activity context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public void update(List<PortalBean.ActivitysBean> list) {
        data.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PortalBean.ActivitysBean bean = data.get(position);
        Glide.with(context).load(bean.getAct_img()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ActivityImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ActivityImageView) itemView.findViewById(R.id.img);
        }
    }
}
