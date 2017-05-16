package com.danlai.nidepuzi.adapter;

import android.os.Bundle;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemMainProductBinding;
import com.danlai.nidepuzi.entity.ActivityBean;
import com.danlai.nidepuzi.entity.MainTodayBean;
import com.danlai.nidepuzi.entity.MainTodayBean.ItemsBean;
import com.danlai.nidepuzi.entity.ShareModelBean;
import com.danlai.nidepuzi.entity.ShopBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.product.ProductDetailActivity;
import com.danlai.nidepuzi.util.ShareUtils;

/**
 * @author wisdom
 * @date 2017年05月15日 下午4:16
 */

public class MainProductAdapter extends BaseRecyclerViewAdapter<ItemMainProductBinding, ItemsBean> {

    public MainProductAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_main_product;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemMainProductBinding> holder, int position) {
        ItemsBean bean = data.get(position);
        ViewUtils.loadImgToImgViewWithPlaceholder(mActivity, holder.b.img, bean.getPic());
        holder.b.setData(bean);
        holder.b.productLayout.setOnClickListener(v -> jumpToProduct(bean));
        holder.b.layoutProductDesc.setOnClickListener(v -> jumpToProduct(bean));
        holder.b.desc.setText("库存500件/在售人数1200");
        holder.b.layoutShareProduct.setOnClickListener(v -> {
            mActivity.showIndeterminateProgressDialog(false);
            BaseApp.getProductInteractor(mActivity)
                .getShareModel(bean.getModel_id(), new ServiceResponse<ShareModelBean>(mActivity) {
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

    private void jumpToProduct(MainTodayBean.ItemsBean bean) {
        Bundle bundle = new Bundle();
        bundle.putInt("model_id", bean.getModel_id());
        mActivity.readyGo(ProductDetailActivity.class, bundle);
    }
}
