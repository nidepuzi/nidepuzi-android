package com.danlai.nidepuzi.service.api;


import com.danlai.nidepuzi.entity.CartsHisBean;
import com.danlai.nidepuzi.entity.CartsInfoBean;
import com.danlai.nidepuzi.entity.CartsPayInfoBean;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.entity.ResultEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wisdom on 16/11/23.
 */

public interface CartsService {

    //获取购物车信息
    @GET("/rest/v2/carts")
    Observable<List<CartsInfoBean>> getCartsList(
        @Query("type") int type);

    //获取历史购物车信息
    @GET("/rest/v2/carts/show_carts_history")
    Observable<List<CartsInfoBean>> getCartsHisList(
        @Query("type") int type);

    //获取购物信息列表
    @GET("/rest/v1/carts/carts_payinfo")
    Observable<CartsPayInfoBean> getCartsPayInfoList(
        @Query("cart_ids") String cart_ids);

    //获取购物信息列表
    @GET("/rest/v2/carts/carts_payinfo")
    Observable<CartsPayInfoBean> getCartsPayInfoListV2(
        @Query("cart_ids") String cart_ids,
        @Query("device") String app);

    //购物车增加一件
    @POST("/rest/v2/carts/{id}/plus_product_carts")
    Observable<Response<CodeBean>> plus_product_carts(
        @Path("id") String id);

    //购物车删除一件
    @POST("/rest/v2/carts/{id}/minus_product_carts")
    Observable<Response<CodeBean>> minus_product_carts(
        @Path("id") String id);

    //删除一列
    @POST("/rest/v2/carts/{id}/delete_carts")
    Observable<Response<CodeBean>> delete_carts(
        @Path("id") String id);

    //重新购买商品
    @FormUrlEncoded
    @POST("/rest/v2/carts")
    Observable<CartsHisBean> rebuy(
        @Field("type") int type,
        @Field("item_id") String item_id,
        @Field("sku_id") String sku_id,
        @Field("cart_id") String cart_id);

    @FormUrlEncoded
    @POST("/rest/v2/carts")
    Observable<ResultEntity> addToCart(
        @Field("item_id") int item_id,
        @Field("sku_id") int sku_id,
        @Field("num") int num);

    @FormUrlEncoded
    @POST("/rest/v2/carts")
    Observable<ResultEntity> addToCart(
        @Field("item_id") int item_id,
        @Field("sku_id") int sku_id,
        @Field("num") int num,
        @Field("type") int type);

}
