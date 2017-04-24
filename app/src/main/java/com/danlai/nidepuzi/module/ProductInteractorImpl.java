package com.danlai.nidepuzi.module;


import com.danlai.library.rx.DefaultTransform;
import com.danlai.nidepuzi.entity.ProductDetailBean;
import com.danlai.nidepuzi.entity.ProductListBean;
import com.danlai.nidepuzi.entity.SearchHistoryBean;
import com.danlai.nidepuzi.entity.ShareModelBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.service.api.ProductService;

import javax.inject.Inject;

/**
 * Created by wisdom on 17/2/24.
 */

public class ProductInteractorImpl implements ProductInteractor {
    private final ProductService service;

    @Inject
    public ProductInteractorImpl(ProductService service) {
        this.service = service;
    }

    @Override
    public void getProductDetail(int id, ServiceResponse<ProductDetailBean> response) {
        service.getProductDetail(id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getShareModel(int model_id, ServiceResponse<ShareModelBean> response) {
        service.getShareModel(model_id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getCategoryProductList(String cid, int page, String order_by, ServiceResponse<ProductListBean> response) {
        if (order_by != null && !"".equals(order_by)) {
            service.getCategoryProductList(cid, page, order_by)
                .compose(new DefaultTransform<>())
                .subscribe(response);
        } else {
            service.getCategoryProductList(cid, page)
                .compose(new DefaultTransform<>())
                .subscribe(response);
        }
    }

    @Override
    public void searchProduct(String name, int page, ServiceResponse<ProductListBean> response) {
        service.searchProduct(name, page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getSearchHistory(ServiceResponse<SearchHistoryBean> response) {
        service.getSearchHistory()
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void clearSearch(ServiceResponse<Object> response) {
        service.clearSearch("ModelProduct")
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }
}
