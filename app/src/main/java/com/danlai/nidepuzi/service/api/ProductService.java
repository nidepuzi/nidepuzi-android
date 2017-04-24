package com.danlai.nidepuzi.service.api;


import com.danlai.nidepuzi.entity.ProductDetailBean;
import com.danlai.nidepuzi.entity.ProductListBean;
import com.danlai.nidepuzi.entity.SearchHistoryBean;
import com.danlai.nidepuzi.entity.ShareModelBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wisdom on 16/11/23.
 */

public interface ProductService {

    @GET("/rest/v2/modelproducts/{id}")
    Observable<ProductDetailBean> getProductDetail(
        @Path("id") int id);

    @GET("/rest/v1/share/model")
    Observable<ShareModelBean> getShareModel(
        @Query("model_id") int model_id);

    @GET("/rest/v2/modelproducts")
    Observable<ProductListBean> getCategoryProductList(
        @Query("cid") String cid,
        @Query("page") int page,
        @Query("order_by") String order_by);

    @GET("/rest/v2/modelproducts")
    Observable<ProductListBean> getCategoryProductList(
        @Query("cid") String cid,
        @Query("page") int page);

    @GET("/rest/v2/modelproducts/search_by_name")
    Observable<ProductListBean> searchProduct(
        @Query("name") String name,
        @Query("page") int page);

    @GET("/rest/v2/searchhistory/product_search_history")
    Observable<SearchHistoryBean> getSearchHistory();


    @FormUrlEncoded
    @POST("/rest/v2/searchhistory/clear_search_history")
    Observable<Object> clearSearch(
        @Field("target") String target);
}
