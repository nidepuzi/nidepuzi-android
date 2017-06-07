package com.danlai.nidepuzi.service.api;


import com.danlai.nidepuzi.entity.BankCardEntity;
import com.danlai.nidepuzi.entity.BankListEntity;
import com.danlai.nidepuzi.entity.BankResultEntity;
import com.danlai.nidepuzi.entity.BudgetDetailBean;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.entity.CouponPagingBean;
import com.danlai.nidepuzi.entity.DrawCashBean;
import com.danlai.nidepuzi.entity.DrawCashListBean;
import com.danlai.nidepuzi.entity.LogoutBean;
import com.danlai.nidepuzi.entity.ResultBean;
import com.danlai.nidepuzi.entity.ResultEntity;
import com.danlai.nidepuzi.entity.UserAccountBean;
import com.danlai.nidepuzi.entity.UserWithDrawResult;
import com.danlai.nidepuzi.entity.VersionBean;

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

public interface UserService {

    @FormUrlEncoded
    @POST("/rest/v1/users/update_profile")
    Observable<ResultBean> updateProfile(
        @Field("id") int id,
        @Field("sex") int sex,
        @Field("nick") String nick,
        @Field("birthday") String birthday,
        @Field("province") String province,
        @Field("city") String city,
        @Field("district") String district);

    @POST("/rest/v1/users/customer_logout")
    Observable<LogoutBean> customerLogout();

    //获取优惠券
    @GET("/rest/v1/usercoupons/get_user_coupons")
    Observable<CouponPagingBean> getCouponPaging(
        @Query("status") int status,
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
        @Field("noncestr") String noncestr,
        @Field("timestamp") String timestamp,
        @Field("sign") String sign,
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

    //创建提款信息
    @FormUrlEncoded
    @POST("/rest/v1/users/budget_cash_out")
    Observable<UserWithDrawResult> userWithDrawCash(
        @Field("cashout_amount") String amount,
        @Field("verify_code") String verify_code);

    //创建提款信息
    @FormUrlEncoded
    @POST("/rest/v2/redenvelope/budget_cash_out")
    Observable<UserWithDrawResult> userWithDrawCash(
        @Field("cashout_amount") String amount,
        @Field("verify_code") String verify_code,
        @Field("channel") String channel,
        @Field("card_id") int card_id);

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

    //验证码验证
    @FormUrlEncoded
    @POST("/rest/v2/verify_code")
    Observable<CodeBean> verifyCode(
        @Field("mobile") String mobile,
        @Field("action") String action,
        @Field("verify_code") String code,
        @Field("devtype") String devtype,
        @Field("nickname") String nickname);

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

    @GET("/rest/v2/bankcards/preferances")
    Observable<BankListEntity> getBankList();

    @FormUrlEncoded
    @POST("/rest/v2/bankcards")
    Observable<BankResultEntity> createBankCard(
        @Field("account_no") String account_no,
        @Field("account_name") String account_name,
        @Field("bank_name") String bank_name,
        @Field("default") boolean isDefault);

    @GET("/rest/v2/bankcards/get_default")
    Observable<BankCardEntity> getDefaultCard();

    @GET("/rest/v2/redenvelope")
    Observable<DrawCashListBean> getDrawCashList(
        @Query("page") int page);

    @GET("/rest/v2/redenvelope/{id}")
    Observable<DrawCashBean> getDrawCashDetail(
        @Path("id") int id);
}
