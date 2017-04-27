package com.danlai.nidepuzi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.LogisticCompany;

import java.util.List;


/**
 * Created by wisdom on 16/7/13.
 */
public class CompanyAdapter extends BaseAdapter {
    private List<LogisticCompany> logisticCompanies;
    private Context context;

    public CompanyAdapter(List<LogisticCompany> logisticCompanies, Context context) {
        this.logisticCompanies = logisticCompanies;
        this.context = context;
    }

    @Override
    public int getCount() {
        return logisticCompanies.size();
    }

    @Override
    public Object getItem(int position) {
        return logisticCompanies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_logistics, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String name = logisticCompanies.get(position).getName();
        holder.nameTv.setText(name);
        return convertView;
    }

    private class ViewHolder {
        TextView nameTv;

        public ViewHolder(View itemView) {
            nameTv = ((TextView) itemView.findViewById(R.id.name));
        }
    }
}