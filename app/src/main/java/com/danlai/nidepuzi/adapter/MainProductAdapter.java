package com.danlai.nidepuzi.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.entity.MainTodayBean;
import com.danlai.nidepuzi.ui.activity.product.ProductDetailActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wisdom on 17/2/14.
 */

public class MainProductAdapter extends RecyclerView.Adapter<MainProductAdapter.ViewHolder> {
    private List<MainTodayBean.ItemsBean> data;
    private BaseActivity context;

    public MainProductAdapter(BaseActivity context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void updateWithClear(List<MainTodayBean.ItemsBean> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainTodayBean.ItemsBean bean = data.get(position);
        ViewUtils.loadImgToImgView(context, holder.image, bean.getPic());
        holder.name.setText(bean.getName());
        String price = new DecimalFormat("0.0").format(bean.getPrice());
        holder.price.setText("售价: ¥" + price);
        String min = new DecimalFormat("0.0").format(bean.getProfit().getMin());
        String max = new DecimalFormat("0.0").format(bean.getProfit().getMax());
        if (bean.getProfit().getMin() == 0) {
            min = "0";
        }
        if (bean.getProfit().getMax() == 0) {
            max = "0";
        }
        holder.profit.setText("利润: ¥" + min + " ~ ¥" + max);
        holder.productLayout.setOnClickListener(v -> {
            try {
                int modelId = bean.getModel_id();
                Intent intent = new Intent(context, ProductDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("model_id", modelId);
                intent.putExtras(bundle);
                context.startActivity(intent);
            } catch (Exception ignored) {
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price, profit;
        LinearLayout productLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            productLayout = (LinearLayout) itemView.findViewById(R.id.product_layout);
            image = (ImageView) itemView.findViewById(R.id.img);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            profit = (TextView) itemView.findViewById(R.id.profit);
        }
    }

}
