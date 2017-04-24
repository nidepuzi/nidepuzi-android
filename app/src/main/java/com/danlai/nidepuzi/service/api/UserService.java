package com.danlai.nidepuzi.service.api;


import com.danlai.nidepuzi.entity.BudgetDetailBean;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.entity.CoinHistoryListBean;
import com.danlai.nidepuzi.entity.CouponPagingBean;
import com.danlai.nidepuzi.entity.LogoutBean;
import com.danlai.nidepuzi.entity.NicknameBean;
import com.danlai.nidepuzi.entity.ResultEntity;
import com.danlai.nidepuzi.entity.UserAccountBean;
import com.danlai.nidepuzi.entity.UserBean;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.UserWithDrawResult;
import com.danlai.nidepuzi.entity.VersionBean;
import com.danlai.nidepuzi.entity.WxPubAuthInfo;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wisdom on 16/11/23.
 */

public interface UserService {
    //获取用户信息
    @GET("/rest/v1/users/profile")
    Observable<UserInfoBean> getUserInfo();

    //设置用户昵称
    @PATCH("/rest/v1/users/{id}")
    Observable<UserBean> setNickName(
        @Path("id") int id,
        @Body NicknameBean nickname);

    @POST("/rest/v1/users/customer_logout")
    Observable<LogoutBean> customerLogout();

    //获取用户积分记录信息
    @GET("/rest/v2/xiaolucoin/history")
    Observable<CoinHistoryListBean> getCoinHisList(
        @Query("page") int page);

    //获取优惠券
    @GET("/rest/v1/usercoupons/get_user_coupons")
    Observable<CouponPagingBean> getCouponPaging(
        @Query("status") int status,
        @Query("paging") int paging,
        @Query("page") int page);

    //获取优惠券
    @GET("/rest/v1/usercoupons/get_user_coupons")
    Observable<CouponPagingBean> getCouponPaging(
        @Query("status") int status,
        @Query("coupon_type") int coupon_type,
        @Query("paging") int paging,
        @Query("page") int page);

    //购物车选择优惠券
    @GET("/rest/v1/usercoupons/coupon_able")
    Observable<CouponPagingBean> getCouponSelectEntity(
        @Query("cart_ids") String cart_ids,
        @Query("type") String type,
        @Query("page") int page);

    @FormUrlEncoded
    @POST("/rest/v2/weixinapplogin")
    Observable<CodeBean> wxappLogin(
        @Query("noncestr") String noncestr,
        @Query("timestamp") String timestamp,
        @Query("sign") String sign,
        @Field("headimgurl") String headimgurl,
        @Field("nickname") String nickname,
        @Field("openid") String openid,
        @Field("unionid") String unionid,
        @Field("devtype") String devtype);

    //get push useraccount
    @FormUrlEncoded
    @POST("/rest/v1/push/set_device")
    Observable<UserAccountBean> getUserAccount(
        @Field("platform") String platform,
        @Field("regid") String regid,
        @Field("device_id") String device_id);

    @GET("/rest/v1/users/get_wxpub_authinfo")
    Observable<WxPubAuthInfo> getWxPubAuthInfo();

    //创建提款信息
    @FormUrlEncoded
    @POST("/rest/v1/users/budget_cash_out")
    Observable<UserWithDrawResult> userWithDrawCash(
        @Field("cashout_amount") String amount,
        @Field("verify_code") String verify_code);

    @POST("/rest/v2/request_cashout_verify_code")
    Observable<ResultEntity> getVerifyCode();

    //发送验证码
    @FormUrlEncoded
    @POST("/rest/v2/send_code")
    Observable<CodeBean> getCodeBean(
        @Field("mobile") String mobile,
        @Field("action") String action);

    //验证码验证
    @FormUrlEncoded
    @POST("/rest/v2/verify_code")
    Observable<CodeBean> verifyCode(
        @Field("mobile") String mobile,
        @Field("action") String action,
        @Field("verify_code") String code,
        @Field("devtype") String devtype);

    //设置账号密码
    @FormUrlEncoded
    @POST("/rest/v2/reset_password")
    Observable<CodeBean> resetPassword(
        @Field("mobile") String mobile,
        @Field("password1") String password1,
        @Field("password2") String password2,
        @Field("verify_code") String code);

    //用户账号密码
    @FormUrlEncoded
    @POST("/rest/v2/passwordlogin")
    Observable<CodeBean> passwordLogin(
        @Field("username") String username,
        @Field("password") String password,
        @Field("next") String next,
        @Field("devtype") String devtype);

    @FormUrlEncoded
    @POST("/rest/v1/users/open_debug_for_app")
    Observable<CodeBean> openDebug(
        @Field("debug_secret") String debug_secret);


    @GET("/rest/v1/users/get_budget_detail")
    Observable<BudgetDetailBean> budgetDetailBean(
        @Query("page") int page);

    @GET("/sale/apprelease/newversion")
    Observable<VersionBean> getVersion();
}
