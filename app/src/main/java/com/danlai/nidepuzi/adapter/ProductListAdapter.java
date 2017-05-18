package com.danlai.nidepuzi.adapter;

import android.os.Bundle;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemProductBinding;
import com.danlai.nidepuzi.entity.ActivityBean;
import com.danlai.nidepuzi.entity.ProductListBean.ResultsBean;
import com.danlai.nidepuzi.entity.ShareModelBean;
import com.danlai.nidepuzi.entity.ShopBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.product.ProductDetailActivity;
import com.danlai.nidepuzi.util.ShareUtils;


/**
 * Created by wisdom on 16/8/6.
 */
public class ProductListAdapter extends BaseRecyclerViewAdapter<ItemProductBinding, ResultsBean> {

    public ProductListAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_product;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemProductBinding> holder, int position) {
        ResultsBean bean = data.get(position);
        ViewUtils.loadImgToImgViewWithPlaceholder(mActivity, holder.b.img, bean.getHead_img());
        holder.b.name.setText(bean.getName());
        holder.b.price.setText("¥" + JUtils.formatDouble(bean.getLowest_agent_price()));
        holder.b.profit.setText("赚" + JUtils.formatDouble(bean.getProfit().getMin()));
        holder.b.productLayout.setOnClickListener(v -> jumpToProduct(bean));
        holder.b.layoutProductDesc.setOnClickListener(v -> jumpToProduct(bean));
        holder.b.sellingNum.setText("在售人数" + bean.getSelling_num());
        holder.b.stock.setText("库存" + bean.getStock());
        holder.b.layoutShareProduct.setOnClickListener(v -> {
            mActivity.showIndeterminateProgressDialog(false);
            BaseApp.getProductInteractor(mActivity)
                .getShareModel(bean.getId(), new ServiceResponse<ShareModelBean>(mActivity) {
                    @Override
                    public void onNext(ShareModelBean shareModel) {
                        mActivity.hideIndeterminateProgressDialog();
                        ShareUtils.shareWithModel(shareModel, mActivity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mActivity.hideIndeterminateProgressDialog();
                        JUtils.Toast("分享失败,请点击重试!");
                    }
                });
        });
        holder.b.layoutShareShop.setOnClickListener(v -> {
            mActivity.showIndeterminateProgressDialog(false);
            BaseApp.getVipInteractor(mActivity)
                .getShopBean(new ServiceResponse<ShopBean>(mActivity) {
                    @Override
                    public void onNext(ShopBean shopBean) {
                        mActivity.hideIndeterminateProgressDialog();
                        ShopBean.ShopInfoBean info = shopBean.getShop_info();
                        ActivityBean activityBean = new ActivityBean(info.getDesc(), info.getName(),
                            info.getShop_link(), info.getThumbnail());
                        ShareUtils.shareShop(activityBean, mActivity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mActivity.hideIndeterminateProgressDialog();
                        JUtils.Toast("分享失败,请点击重试!");
                    }
                });
        });
    }

    private void jumpToProduct(ResultsBean bean) {
        Bundle bundle = new Bundle();
        bundle.putInt("model_id", bean.getId());
        mActivity.readyGo(ProductDetailActivity.class, bundle);
    }
}
