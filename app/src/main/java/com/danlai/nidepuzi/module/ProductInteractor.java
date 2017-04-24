package com.danlai.nidepuzi.module;


import com.danlai.nidepuzi.entity.ProductDetailBean;
import com.danlai.nidepuzi.entity.ProductListBean;
import com.danlai.nidepuzi.entity.SearchHistoryBean;
import com.danlai.nidepuzi.entity.ShareModelBean;
import com.danlai.nidepuzi.service.ServiceResponse;

/**
 * Created by wisdom on 17/2/24.
 */

public interface ProductInteractor {

    void getProductDetail(int id, ServiceResponse<ProductDetailBean> response);

    void getShareModel(int model_id, ServiceResponse<ShareModelBean> response);

    void getCategoryProductList(String cid, int page, String order_by, ServiceResponse<ProductListBean> response);

    void searchProduct(String name, int page, ServiceResponse<ProductListBean> response);

    void getSearchHistory(ServiceResponse<SearchHistoryBean> response);

    void clearSearch(ServiceResponse<Object> response);
}
