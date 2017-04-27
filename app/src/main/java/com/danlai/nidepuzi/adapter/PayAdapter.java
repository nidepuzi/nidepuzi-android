package com.danlai.nidepuzi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.OrderDetailBean.ExtrasBean.ChannelsBean;

import java.util.List;

/**
 * Created by wisdom on 16/7/16.
 */
public class PayAdapter extends BaseAdapter {
    private List<ChannelsBean> channelsBeanList;
    private Context context;

    public PayAdapter(List<ChannelsBean> channelsBeanList, Context context) {
        this.channelsBeanList = channelsBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return channelsBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return channelsBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pay, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameTv.setText(channelsBeanList.get(position).getName());
        String id = channelsBeanList.get(position).getId();
        if (id.contains("wx")) {
            holder.iconImg.setImageResource(R.drawable.wx);
        } else if (id.contains("alipay")) {
            holder.iconImg.setImageResource(R.drawable.alipay);
        }
        return convertView;
    }

    private class ViewHolder {
        TextView nameTv;
        ImageView iconImg;

        public ViewHolder(View itemView) {
            nameTv = ((TextView) itemView.findViewById(R.id.name));
            iconImg = ((ImageView) itemView.findViewById(R.id.icon));
        }
    }
}