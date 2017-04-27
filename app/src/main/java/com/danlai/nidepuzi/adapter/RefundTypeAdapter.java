package com.danlai.nidepuzi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.OrderDetailBean;

import java.util.List;


/**
 * Created by wisdom on 16/6/30.
 */
public class RefundTypeAdapter extends BaseAdapter {

    private Context context;
    private List<OrderDetailBean.ExtrasBean.RefundChoicesBean> refund_choices;
    private boolean[] flag;

    public RefundTypeAdapter(Context context, List<OrderDetailBean.ExtrasBean.RefundChoicesBean> refund_choices) {
        this.context = context;
        this.refund_choices = refund_choices;
        flag = new boolean[refund_choices.size()];
        for (int i = 0; i < flag.length; i++) {
            flag[i] = false;
        }
    }

    @Override
    public int getCount() {
        return refund_choices.size();
    }

    @Override
    public OrderDetailBean.ExtrasBean.RefundChoicesBean getItem(int position) {
        return refund_choices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_refund_type, parent, false);
        ImageView icon = (ImageView) convertView.findViewById(R.id.refund_iv);
        TextView name = (TextView) convertView.findViewById(R.id.refund_tv);
        TextView desc = (TextView) convertView.findViewById(R.id.refund_tv_desc);
        ((RadioButton) convertView.findViewById(R.id.refund_rb)).setChecked(flag[position]);
        name.setText(refund_choices.get(position).getName());
        desc.setText(refund_choices.get(position).getDesc());
        if ("budget".equals(refund_choices.get(position).getRefund_channel())) {
            icon.setImageResource(R.drawable.icon_fast_return);
        } else {
            icon.setImageResource(R.drawable.icon_return);
        }
        convertView.setOnClickListener(v -> {
            for (int i = 0; i < flag.length; i++) {
                if (position == i) {
                    flag[i] = true;
                } else {
                    flag[i] = false;
                }
            }
            RefundTypeAdapter.this.notifyDataSetChanged();
        });
        return convertView;
    }

    public int getSelect() {
        for (int i = 0; i < flag.length; i++) {
            if (flag[i]) {
                return i;
            }
        }
        return -1;
    }
}
