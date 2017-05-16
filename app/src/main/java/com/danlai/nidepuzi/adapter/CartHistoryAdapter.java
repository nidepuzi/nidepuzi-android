package com.danlai.nidepuzi.adapter;

import android.content.Intent;
import android.os.Bundle;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemHiscartsBinding;
import com.danlai.nidepuzi.entity.CartsHisBean;
import com.danlai.nidepuzi.entity.CartsInfoBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.product.ProductDetailActivity;
import com.danlai.nidepuzi.ui.activity.trade.CartActivity;

/**
 * Created by wisdom on 16/9/3.
 */
public class CartHistoryAdapter extends BaseRecyclerViewAdapter<ItemHiscartsBinding, CartsInfoBean> {
    private CartActivity mActivity;

    public CartHistoryAdapter(CartActivity mActivity) {
        super(mActivity);
        this.mActivity = mActivity;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_hiscarts;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemHiscartsBinding> holder, int position) {
        CartsInfoBean cartsInfoBean = data.get(position);
        holder.b.title.setText(cartsInfoBean.getTitle());
        holder.b.skuName.setText("尺码:" + cartsInfoBean.getSku_name());
        holder.b.price1.setText("¥" + (float) (Math.round(cartsInfoBean.getPrice() * 100)) / 100);
        holder.b.price2.setText("/¥" + (float) (Math.round(cartsInfoBean.getStd_sale_price() * 100)) / 100);
        ViewUtils.loadImgToImgViewWithPlaceholder(mActivity, holder.b.cartImage, cartsInfoBean.getPic_path());
        holder.b.cartImage.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, ProductDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("model_id", cartsInfoBean.getModel_id());
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        });
        holder.b.rebuy.setOnClickListener(v -> {
            mActivity.showIndeterminateProgressDialog(false);
            BaseApp.getCartsInteractor(mActivity)
                .rebuy(0, cartsInfoBean.getItem_id(), cartsInfoBean.getSku_id(),
                    cartsInfoBean.getId() + "", new ServiceResponse<CartsHisBean>(mActivity) {
                        @Override
                        public void onNext(CartsHisBean cartsHisBean) {
                            if (null != cartsHisBean) {
                                if (cartsHisBean.getCode() == 0) {
                                    data.remove(position);
                                    notifyDataSetChanged();
                                    mActivity.hideLine();
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
}
