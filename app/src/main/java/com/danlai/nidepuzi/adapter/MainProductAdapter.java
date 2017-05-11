package com.danlai.nidepuzi.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.entity.ActivityBean;
import com.danlai.nidepuzi.entity.MainTodayBean;
import com.danlai.nidepuzi.entity.ShareModelBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.product.ProductDetailActivity;
import com.danlai.nidepuzi.util.ShareUtils;

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
        ViewUtils.loadImgToImgViewWithPlaceholder(context, holder.image, bean.getPic());
        holder.name.setText(bean.getName());
        String price = JUtils.formatDouble(bean.getPrice());
        holder.price.setText("售价: ¥" + price);
        String min = JUtils.formatDouble(bean.getProfit().getMin());
        holder.profit.setText("利润: ¥" + min);
        holder.productLayout.setOnClickListener(v -> jumpToProduct(bean));
        holder.productDescLayout.setOnClickListener(v -> jumpToProduct(bean));
        holder.shareProductLayout.setOnClickListener(v -> {
            context.showIndeterminateProgressDialog(false);
            BaseApp.getProductInteractor(context)
                .getShareModel(bean.getModel_id(), new ServiceResponse<ShareModelBean>(context) {
                    @Override
                    public void onNext(ShareModelBean shareModel) {
                        context.hideIndeterminateProgressDialog();
                        ShareUtils.shareWithModel(shareModel, context);
                    }

                    @Override
                    public void onError(Throwable e) {
                        context.hideIndeterminateProgressDialog();
                        JUtils.Toast("分享失败,请点击重试!");
                    }
                });
        });
        holder.shareShopLayout.setOnClickListener(v -> {
            context.showIndeterminateProgressDialog(false);
            BaseApp.getActivityInteractor(context)
                .getActivityBean("8", new ServiceResponse<ActivityBean>(context) {
                    @Override
                    public void onNext(ActivityBean activityBean) {
                        context.hideIndeterminateProgressDialog();
                        ShareUtils.shareShop(activityBean, context);
                    }

                    @Override
                    public void onError(Throwable e) {
                        context.hideIndeterminateProgressDialog();
                        JUtils.Toast("分享失败,请点击重试!");
                    }
                });
        });
    }

    private void jumpToProduct(MainTodayBean.ItemsBean bean) {
        Bundle bundle = new Bundle();
        bundle.putInt("model_id", bean.getModel_id());
        context.readyGo(ProductDetailActivity.class, bundle);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price, profit;
        LinearLayout productLayout, productDescLayout, shareProductLayout, shareShopLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            productLayout = (LinearLayout) itemView.findViewById(R.id.product_layout);
            productDescLayout = (LinearLayout) itemView.findViewById(R.id.layout_product_desc);
            shareProductLayout = (LinearLayout) itemView.findViewById(R.id.layout_share_product);
            shareShopLayout = (LinearLayout) itemView.findViewById(R.id.layout_share_shop);
            image = (ImageView) itemView.findViewById(R.id.img);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            profit = (TextView) itemView.findViewById(R.id.profit);
        }
    }

}
