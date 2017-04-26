package com.danlai.nidepuzi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.MainTodayBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wisdom on 17/2/14.
 */

public abstract class MainTabAdapter extends RecyclerView.Adapter<MainTabAdapter.ViewHolder> {

    private List<MainTodayBean> data;
    private Context context;
    private int currentPosition;

    protected MainTabAdapter(Context context) {
        this.context = context;
        currentPosition = 2;
        data = new ArrayList<>();
    }

    public void updateWithClear(List<MainTodayBean> list) {
        data.clear();
        currentPosition = 2;
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void update(List<MainTodayBean> list) {
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public void setCurrentPosition(int position) {
        if (currentPosition != position) {
            currentPosition = position;
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_tab, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int width = JUtils.getScreenWidth() / 5;
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
        holder.itemView.setLayoutParams(params);
        if (currentPosition == position) {
            holder.time.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        } else {
            holder.time.setTextColor(context.getResources().getColor(R.color.color_66));
            holder.textView.setTextColor(context.getResources().getColor(R.color.color_66));
        }
        if (position >= 2 && position < (data.size() + 2)) {
            MainTodayBean bean = data.get(position - 2);
            if (bean.getHour() < 10) {
                holder.time.setText("0" + bean.getHour() + ":00");
            } else {
                holder.time.setText(bean.getHour() + ":00");
            }
            holder.textView.setText("热卖中");
            final int cur = position;
            holder.itemView.setOnClickListener(v -> {
                itemClick(cur - currentPosition, position - 2);
                setCurrentPosition(cur);
            });
        } else {
            holder.textView.setText("");
            holder.time.setText("");
            holder.itemView.setOnClickListener(v -> {
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 4;
    }

    public abstract void itemClick(int count, int position);


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView, time;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }
}
