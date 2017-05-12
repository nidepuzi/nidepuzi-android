package com.danlai.nidepuzi.service.api;


import com.danlai.nidepuzi.entity.CategoryBean;
import com.danlai.nidepuzi.entity.ChooseListBean;
import com.danlai.nidepuzi.entity.MMCarryBean;
import com.danlai.nidepuzi.entity.ShopBean;
import com.danlai.nidepuzi.entity.MMVisitorsBean;
import com.danlai.nidepuzi.entity.FansBean;
import com.danlai.nidepuzi.entity.MamaFortune;
import com.danlai.nidepuzi.entity.MamaSelfListBean;
import com.danlai.nidepuzi.entity.MamaUrl;
import com.danlai.nidepuzi.entity.NinePicBean;
import com.danlai.nidepuzi.entity.OderCarryBean;
import com.danlai.nidepuzi.entity.ProductNinePicBean;
import com.danlai.nidepuzi.entity.RecentCarryBean;
import com.danlai.nidepuzi.entity.SaveTimeBean;
import com.danlai.nidepuzi.entity.WxQrcode;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wisdom on 16/11/23.
 */

public interface VipService {

    //获取粉丝列表
    @GET("/rest/v2/mama/fans")
    Observable<FansBean> getFans(
        @Query("page") int page);

    //获取访客列表
    @GET("/rest/v2/mama/visitor")
    Observable<MMVisitorsBean> getMamaVisitor(
        @Query("recent") int recent,
        @Query("page") int page);

    @GET("/rest/v2/mama/fortune")
    Observable<MamaFortune> getMamaFortune();

    @GET("/rest/v2/categorys")
    Observable<List<CategoryBean>> getCategory();

    @GET("/rest/v2/modelproducts/product_choice")
    Observable<ChooseListBean> getChooseList(
        @Query("page") int page);

    @GET("/rest/v2/modelproducts/product_choice")
    Observable<ChooseListBean> getChooseListBySort(
        @Query("page") int page,
        @Query("sort_field") String sort_field,
        @Query("reverse") int reverse);

    @GET("/rest/v2/modelproducts/product_choice")
    Observable<ChooseListBean> getChooseListByCid(
        @Query("page") int page,
        @Query("cid") String cid);

    @GET("/rest/v2/modelproducts/product_choice")
    Observable<ChooseListBean> getChooseList(
        @Query("page") int page,
        @Query("sort_field") String sort_field,
        @Query("cid") String cid,
        @Query("reverse") int reverse);

    @GET("/rest/v1/pmt/cushop/customer_shop")
    Observable<ShopBean> getShop();

    @GET("/rest/v1/mmwebviewconfig")
    Observable<MamaUrl> getMamaUrl(
        @Query("version") String version);

    @GET("/rest/v2/mama/message/self_list")
    Observable<MamaSelfListBean> getMaMaSelfList();

    @GET("/rest/v2/mama/dailystats")
    Observable<RecentCarryBean> getRecentCarry(
        @Query("from") String from,
        @Query("days") String days);

    @GET("/rest/v1/pmt/ninepic")
    Observable<List<NinePicBean>> getNinePic();

    @GET("/rest/v1/pmt/ninepic")
    Observable<List<NinePicBean>> getNinePic(
        @Query("sale_category") int sale_category);

    @GET("/rest/v1/pmt/ninepic/page_list")
    Observable<ProductNinePicBean> getNinePicByModelId(
        @Query("model_id") int model_id,
        @Query("page") int page);

    @GET("/rest/v2/mama/ordercarry")
    Observable<OderCarryBean> getMamaAllOder(
        @Query("carry_type") String carry_type,
        @Query("page") int page);

    @GET("/rest/v2/qrcode/get_wxpub_qrcode")
    Observable<WxQrcode> getWxCode();

    @FormUrlEncoded
    @PATCH("/rest/v1/pmt/ninepic/{id}")
    Observable<SaveTimeBean> saveTime(
        @Path("id") int id,
        @Field("save_times") int save_times);

    @GET("/rest/v2/mmcarry")
    Observable<MMCarryBean> getCarryBean(
        @Query("page") int page);
}
