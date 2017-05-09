package com.danlai.nidepuzi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.AllOrdersBean.ResultsEntity.OrdersEntity;

import java.util.List;

/**
 * Created by wisdom on 16/5/28.
 */
public class GoodsListAdapter extends BaseAdapter {

    private List<OrdersEntity> packageBeanList;
    private Context context;

    public GoodsListAdapter(List<OrdersEntity> packageBeanList, Context context) {
        this.packageBeanList = packageBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return packageBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return packageBeanList;
    }

    @Override
    public long getItemId(int position) {
        return position;
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
        OrdersEntity bean = packageBeanList.get(position);
        ViewUtils.loadImgToImgView(context, holder.imageView, bean.getPic_path());
        holder.name.setText(bean.getTitle());
        holder.price.setText("¥" + JUtils.formatDouble(bean.getTotal_fee()));
        holder.num.setText("x" + bean.getNum());
        holder.size.setText(bean.getSku_name());
        return convertView;
    }

    private class ViewHolder {
        TextView name, price, num, size;
        ImageView imageView;


        public ViewHolder(View itemView) {
            imageView = ((ImageView) itemView.findViewById(R.id.img_good));
            name = ((TextView) itemView.findViewById(R.id.tx_good_name));
            price = ((TextView) itemView.findViewById(R.id.tx_good_price));
            num = ((TextView) itemView.findViewById(R.id.tx_good_num));
            size = ((TextView) itemView.findViewById(R.id.tx_good_size));
        }
    }
}
