package com.danlai.nidepuzi.adapter;

/**
 * Created by wulei on 2016/1/24.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.LogisticsCompanyInfo;

import java.util.List;

public class LogisticCompanyAdapter extends BaseAdapter {

    private List<LogisticsCompanyInfo> list = null;
    private Context mContext;

    public LogisticCompanyAdapter(Context mContext, List<LogisticsCompanyInfo> list) {
        this.mContext = mContext;
        this.list = list;

    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_logistics_company, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final LogisticsCompanyInfo mContent = list.get(position);
        if (position == 0) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.getLetter());
        } else {
            String lastCatalog = list.get(position - 1).getLetter();
            if (mContent.getLetter().equals(lastCatalog)) {
                viewHolder.tvLetter.setVisibility(View.GONE);
            } else {
                viewHolder.tvLetter.setVisibility(View.VISIBLE);
                viewHolder.tvLetter.setText(mContent.getLetter());
            }
        }
        viewHolder.tvTitle.setText(this.list.get(position).getName());
        return view;
    }

    final static class ViewHolder {
        TextView tvTitle;
        TextView tvLetter;

        public ViewHolder(View itemView) {
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvLetter = (TextView) itemView.findViewById(R.id.tv_catalog);
        }
    }

    public int getPositionForSection(int section) {
        LogisticsCompanyInfo mContent;
        String l;
        if (section == '!') {
            return 0;
        } else {
            for (int i = 0; i < getCount(); i++) {
                mContent = list.get(i);
                l = mContent.getLetter();
                char firstChar = l.toUpperCase().charAt(0);
                if (firstChar == section) {
                    return i + 1;
                }

            }
        }
        return -1;
    }
}
