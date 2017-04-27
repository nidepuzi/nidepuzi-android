package com.danlai.nidepuzi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.danlai.library.utils.ViewUtils;
import com.danlai.library.widget.RoundCornerImageView;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.AllOrdersBean.ResultsEntity.OrdersEntity;
import com.danlai.nidepuzi.ui.activity.trade.OrderDetailActivity;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by wisdom on 16/7/15.
 */
public class OrderListAdapter extends BaseAdapter {
    private Context context;
    private List<OrdersEntity> mList;
    private int id;

    public OrderListAdapter(Context context, List<OrdersEntity> mList) {
        this.context = context;
        this.mList = mList;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.one_order_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, OrderDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("orderinfo", id);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
        OrdersEntity entity = mList.get(position);
//        ViewUtils.loadImgToImgView(context, holder.img, entity.getPic_path());
        ViewUtils.loadImgToImgViewWithPlaceholder(context,holder.img,entity.getPic_path());
        holder.name.setText(entity.getTitle());
        holder.price.setText("¥" + new DecimalFormat("0.00").format(entity.getTotal_fee()/entity.getNum()));
        holder.num.setText("x" + entity.getNum());
        holder.size.setText(entity.getSku_name());
        return convertView;
    }

    public void setId(int id) {
        this.id = id;
    }

    private class ViewHolder {
        RoundCornerImageView img;
        TextView name, price, num, size;

        public ViewHolder(View itemView) {
            img = ((RoundCornerImageView) itemView.findViewById(R.id.img_good));
            name = ((TextView) itemView.findViewById(R.id.tx_good_name));
            price = ((TextView) itemView.findViewById(R.id.tx_good_price));
            num = ((TextView) itemView.findViewById(R.id.tx_good_num));
            size = ((TextView) itemView.findViewById(R.id.tx_good_size));
        }
    }

}
