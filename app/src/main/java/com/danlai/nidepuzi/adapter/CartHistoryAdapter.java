package com.danlai.nidepuzi.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.CartsHisBean;
import com.danlai.nidepuzi.entity.CartsInfoBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.product.ProductDetailActivity;
import com.danlai.nidepuzi.ui.activity.trade.CartActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wisdom on 16/9/3.
 */
public class CartHistoryAdapter extends RecyclerView.Adapter<CartHistoryAdapter.ViewHolder> {
    private List<CartsInfoBean> mList;
    private CartActivity mActivity;

    public CartHistoryAdapter(CartActivity mActivity, List<CartsInfoBean> mList) {
        this.mList = mList;
        this.mActivity = mActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hiscarts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CartsInfoBean cartsInfoBean = mList.get(position);
        holder.title.setText(cartsInfoBean.getTitle());
        holder.skuName.setText("尺码:" + cartsInfoBean.getSku_name());
        holder.price1.setText("¥" + (float) (Math.round(cartsInfoBean.getPrice() * 100)) / 100);
        holder.price2.setText("/¥" + (float) (Math.round(cartsInfoBean.getStd_sale_price() * 100)) / 100);
        ViewUtils.loadImgToImgViewWithPlaceholder(mActivity, holder.cartImage, cartsInfoBean.getPic_path());
        holder.cartImage.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, ProductDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("model_id", cartsInfoBean.getModel_id());
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        });
        holder.rebuy.setOnClickListener(v -> {
            mActivity.showIndeterminateProgressDialog(false);
            BaseApp.getCartsInteractor(mActivity)
                .rebuy(0, cartsInfoBean.getItem_id(), cartsInfoBean.getSku_id(),
                    cartsInfoBean.getId() + "", new ServiceResponse<CartsHisBean>(mActivity) {
                        @Override
                        public void onNext(CartsHisBean cartsHisBean) {
                            if (null != cartsHisBean) {
                                if (cartsHisBean.getCode() == 0) {
                                    mList.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemChanged(position, mList.size());
                                    mActivity.refreshCartList();
                                } else {
                                    mActivity.hideIndeterminateProgressDialog();
                                    JUtils.Toast(cartsHisBean.getInfo());
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            JUtils.Toast("重新购买失败,商品可能已下架");
                            mActivity.hideIndeterminateProgressDialog();
                        }
                    });
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        int id = R.layout.item_hiscarts;
        @Bind(R.id.cart_image)
        ImageView cartImage;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.sku_name)
        TextView skuName;
        @Bind(R.id.price1)
        TextView price1;
        @Bind(R.id.price2)
        TextView price2;
        @Bind(R.id.rebuy)
        TextView rebuy;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
