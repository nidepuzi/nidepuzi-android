package com.danlai.nidepuzi.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemCartsBinding;
import com.danlai.nidepuzi.entity.CartsInfoBean;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.product.ProductDetailActivity;
import com.danlai.nidepuzi.ui.activity.trade.CartActivity;

import retrofit2.Response;

import static com.danlai.nidepuzi.BaseApp.getCartsInteractor;

/**
 * Created by wisdom on 16/9/3.
 */
public class CartListAdapter extends BaseRecyclerViewAdapter<ItemCartsBinding, CartsInfoBean> {
    private CartActivity mActivity;

    public CartListAdapter(CartActivity mActivity) {
        super(mActivity);
        this.mActivity = mActivity;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_carts;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemCartsBinding> holder, int position) {
        CartsInfoBean cartsInfoBean = data.get(position);
        holder.b.title.setText(cartsInfoBean.getTitle());
        holder.b.skuName.setText("尺码:" + cartsInfoBean.getSku_name());
        holder.b.price1.setText("¥" + (float) (Math.round(cartsInfoBean.getPrice() * 100)) / 100);
        holder.b.price2.setText("/¥" + (float) (Math.round(cartsInfoBean.getStd_sale_price() * 100)) / 100);
        holder.b.count.setText(cartsInfoBean.getNum() + "");
        ViewUtils.loadImgToImgViewWithPlaceholder(mActivity, holder.b.cartImage, cartsInfoBean.getPic_path());
        holder.b.cartImage.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, ProductDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("model_id", cartsInfoBean.getModel_id());
            intent.putExtras(bundle);
            mActivity.startActivity(intent);

        });
        holder.b.delete.setOnClickListener(v -> {
            if (cartsInfoBean.getNum() == 1) {
                new AlertDialog.Builder(mActivity)
                    .setTitle("删除商品")
                    .setMessage("您确定要删除吗？")
                    .setPositiveButton("确定", (dialog, which) -> {
                        dialog.dismiss();
                        mActivity.showIndeterminateProgressDialog(false);
                        getCartsInteractor(mActivity)
                            .deleteCarts(cartsInfoBean.getId() + "", new ServiceResponse<Response<CodeBean>>(mActivity) {
                                @Override
                                public void onNext(Response<CodeBean> codeBeanResponse) {
                                    if (codeBeanResponse != null) {
                                        if (codeBeanResponse.isSuccessful()) {
                                            mActivity.showLine(cartsInfoBean);
                                            mActivity.removeCartList(cartsInfoBean);
                                        } else {
                                            JUtils.Toast(codeBeanResponse.body().getInfo());
                                        }
                                    } else {
                                        JUtils.Toast("操作未成功，请重新尝试");
                                    }
                                    mActivity.hideIndeterminateProgressDialog();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    mActivity.hideIndeterminateProgressDialog();
                                }
                            });

                    })
                    .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                    .show();
            } else {
                mActivity.showIndeterminateProgressDialog(false);
                BaseApp.getCartsInteractor(mActivity)
                    .minusProductCarts(cartsInfoBean.getId() + "", new ServiceResponse<Response<CodeBean>>(mActivity) {
                        @Override
                        public void onNext(Response<CodeBean> codeBeanResponse) {
                            if (codeBeanResponse != null && codeBeanResponse.isSuccessful()) {
                                CodeBean body = codeBeanResponse.body();
                                if (body != null && body.getCode() == 0) {
                                    mActivity.setPriceText();
                                    cartsInfoBean.setNum(cartsInfoBean.getNum() - 1);
                                    holder.b.count.setText(cartsInfoBean.getNum() + "");
                                    notifyDataSetChanged();
                                } else {
                                    JUtils.Toast(body != null ? body.getInfo() : "操作失败");
                                }
                            } else {
                                JUtils.Toast("操作未成功，请重新尝试");
                            }
                            mActivity.hideIndeterminateProgressDialog();
                        }

                        @Override
                        public void onError(Throwable e) {
                            mActivity.hideIndeterminateProgressDialog();
                        }
                    });
            }
        });
        holder.b.add.setOnClickListener(v -> {
            mActivity.showIndeterminateProgressDialog(false);
            getCartsInteractor(mActivity)
                .plusProductCarts(cartsInfoBean.getId() + "", new ServiceResponse<Response<CodeBean>>(mActivity) {
                    @Override
                    public void onNext(Response<CodeBean> codeBeanResponse) {
                        if (null != codeBeanResponse) {
                            CodeBean body = codeBeanResponse.body();
                            if (body != null && body.getCode() == 0) {
                                mActivity.setPriceText();
                                cartsInfoBean.setNum(cartsInfoBean.getNum() + 1);
                                holder.b.count.setText(cartsInfoBean.getNum() + "");
                                notifyDataSetChanged();
                            } else {
                                JUtils.Toast(body != null ? body.getInfo() : "操作失败");
                            }
                        } else {
                            JUtils.Toast("操作未成功，请重新尝试");
                        }
                        mActivity.hideIndeterminateProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mActivity.hideIndeterminateProgressDialog();
                    }
                });
        });
    }
}
