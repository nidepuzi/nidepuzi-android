package com.danlai.nidepuzi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.CategoryBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wisdom on 16/9/23.
 */

public class CategoryNameListAdapter extends BaseAdapter {
    private List<CategoryBean> data;
    private Context mContext;
    private String cid;

    public CategoryNameListAdapter(Context context) {
        mContext = context;
        data = new ArrayList<>();
    }

    public void update(List<CategoryBean> list) {
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public void updateWithClear(List<CategoryBean> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void setCid(String cid) {
        this.cid = cid;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.category_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(data.get(position).getName());
        if (cid != null && !"".equals(cid)) {
            if (cid.equals(data.get(position).getCid())) {
                holder.tv.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                holder.selectedView.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
                holder.tv.setBackgroundColor(mContext.getResources().getColor(R.color.bg_grey));
            } else {
                holder.tv.setTextColor(mContext.getResources().getColor(R.color.color_33));
                holder.selectedView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                holder.tv.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            }
        } else if (position == 0) {
            holder.tv.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            holder.selectedView.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
            holder.tv.setBackgroundColor(mContext.getResources().getColor(R.color.bg_grey));
        } else {
            holder.tv.setTextColor(mContext.getResources().getColor(R.color.color_33));
            holder.selectedView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            holder.tv.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv;
        View selectedView;

        public ViewHolder(View itemView) {
            tv = ((TextView) itemView.findViewById(R.id.tv));
            selectedView = itemView.findViewById(R.id.selected_view);
        }
    }
}
