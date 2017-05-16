package com.danlai.nidepuzi.adapter;

/**
 * Created by wulei on 2016/1/24.
 */

import android.view.View;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseListViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemLogisticsCompanyBinding;
import com.danlai.nidepuzi.entity.LogisticsCompanyInfo;

import java.util.List;

public class LogisticCompanyAdapter extends BaseListViewAdapter<ItemLogisticsCompanyBinding, LogisticsCompanyInfo> {

    public LogisticCompanyAdapter(BaseActivity mActivity, List<LogisticsCompanyInfo> data) {
        super(mActivity);
        this.data = data;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_logistics_company;
    }

    @Override
    protected void fillData(LogisticsCompanyInfo logisticsCompanyInfo, BaseViewHolder<ItemLogisticsCompanyBinding> holder, int position) {
        if (position == 0) {
            holder.b.tvCategory.setVisibility(View.VISIBLE);
            holder.b.tvCategory.setText(logisticsCompanyInfo.getLetter());
        } else {
            String lastCatalog = data.get(position - 1).getLetter();
            if (logisticsCompanyInfo.getLetter().equals(lastCatalog)) {
                holder.b.tvCategory.setVisibility(View.GONE);
            } else {
                holder.b.tvCategory.setVisibility(View.VISIBLE);
                holder.b.tvCategory.setText(logisticsCompanyInfo.getLetter());
            }
        }
        holder.b.tvTitle.setText(logisticsCompanyInfo.getName());
    }

    public int getPositionForSection(int section) {
        LogisticsCompanyInfo mContent;
        String l;
        if (section == '!') {
            return 0;
        } else {
            for (int i = 0; i < getCount(); i++) {
                mContent = data.get(i);
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
