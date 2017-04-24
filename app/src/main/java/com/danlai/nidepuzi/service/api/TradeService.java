package com.danlai.nidepuzi.service.api;

import com.danlai.nidepuzi.entity.AllOrdersBean;
import com.danlai.nidepuzi.entity.AllRefundsBean;
import com.danlai.nidepuzi.entity.LogisticCompany;
import com.danlai.nidepuzi.entity.LogisticsBean;
import com.danlai.nidepuzi.entity.OrderDetailBean;
import com.danlai.nidepuzi.entity.PayInfoBean;
import com.danlai.nidepuzi.entity.QiniuTokenBean;
import com.danlai.nidepuzi.entity.RefundMsgBean;
import com.danlai.nidepuzi.entity.ResultBean;
import com.danlai.nidepuzi.entity.TeamBuyBean;
import com.danlai.nidepuzi.entity.UserBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wisdom on 16/11/23.
 */

public interface TradeService {

    //获取所有订单
    @GET("/rest/v2/trades")
    Observable<AllOrdersBean> getAllOrdersList(
        @Query("page") int page);

    @FormUrlEncoded
    @POST("/rest/v2/trades/shoppingcart_create")
    Observable<PayInfoBean> shoppingCartCreateV2(
        @Field("cart_ids") String cart_ids,
        @Field("addr_id") String addr_id,
        @Field("channel") String channel,
        @Field("payment") String payment,
        @Field("post_fee") String post_fee,
        @Field("discount_fee") String discount_fee,
        @Field("total_fee") String total_fee,
        @Field("uuid") String uuid,
        @Field("pay_extras") String pay_extras,
        @Field("logistics_company_id") String code,
        @Field("order_type") String type);

    @FormUrlEncoded
    @POST("/rest/v2/trades/shoppingcart_create")
    Observable<PayInfoBean> shoppingCartCreateV2(
        @Field("cart_ids") String cart_ids,
        @Field("addr_id") String addr_id,
        @Field("channel") String channel,
        @Field("payment") String payment,
        @Field("post_fee") String post_fee,
        @Field("discount_fee") String discount_fee,
        @Field("total_fee") String total_fee,
        @Field("uuid") String uuid,
        @Field("pay_extras") String pay_extras,
        @Field("logistics_company_id") String code);

    //立即支付订单接口
    @FormUrlEncoded
    @POST("/rest/v2/trades/{pk}/charge")
    Observable<PayInfoBean> orderPayWithChannel(
        @Path("pk") int order_id,
        @Field("channel") String channel);

    //获得订单数据
    @GET("/rest/v2/trades/{pk}")
    Observable<OrderDetailBean> getOrderDetail(
        @Path("pk") int order_id,
        @Query("device") String device);

    //获取所有退货订单
    @GET("/rest/v1/refunds")
    Observable<AllRefundsBean> getAllRefundsList(
        @Query("page") int page);

    //获取所有待支付订单
    @GET("/rest/v2/trades/waitpay")
    Observable<AllOrdersBean> getWaitPayOrdersBean(
        @Query("page") int page);

    //获取所有待发货订单
    @GET("/rest/v2/trades/waitsend")
    Observable<AllOrdersBean> getWaitSendOrdersBean(
        @Query("page") int page);

    //确认签收
    @POST("/rest/v1/order/{id}/confirm_sign")
    Observable<UserBean> receiveGoods(
        @Path("id") int id);

    //获得详细退款单数据
    @GET("/rest/v1/refunds/{pk}")
    Observable<AllRefundsBean.ResultsEntity> getRefundDetailBean(
        @Path("pk") int order_id);

    //删除订单数据
    @DELETE("/rest/v2/trades/{pk}")
    Observable<ResponseBody> delRefund(
        @Path("pk") int order_id);

    //创建退款单接口
    @FormUrlEncoded
    @POST("/rest/v1/refunds")
    Observable<RefundMsgBean> refundCreate(
        @Field("id") int goods_id,
        @Field("reason") int reason,
        @Field("num") int num,
        @Field("sum_price") double sum_price,
        @Field("description") String description,
        @Field("proof_pic") String proof_pic,
        @Field("refund_channel") String refund_channel);

    //创建退款单接口
    @FormUrlEncoded
    @POST("/rest/v1/refunds")
    Observable<RefundMsgBean> refundCreate(
        @Field("id") int goods_id,
        @Field("reason") int reason,
        @Field("num") int num,
        @Field("sum_price") double sum_price,
        @Field("description") String description,
        @Field("proof_pic") String proof_pic);

    @GET("/rest/v1/refunds/qiniu_token")
    Observable<QiniuTokenBean> getQiniuToken();

    //添加退款物流信息
    @FormUrlEncoded
    @POST("/rest/v1/refunds")
    Observable<ResponseBody> commitLogisticsInfo(
        @Field("id") int goods_id,
        @Field("modify") int type,
        @Field("company") String company,
        @Field("sid") String sid);

    //获取物流信息
    @GET("/rest/v1/wuliu/get_wuliu_by_packetid")
    Observable<LogisticsBean> getLogisticsByPacketId(
        @Query("packetid") String packetid,
        @Query("company_code") String company_code);

    //获取退货物流信息
    @GET("/rest/v1/rtnwuliu/get_wuliu_by_packetid")
    Observable<LogisticsBean> getRefundLogistic(
        @Query("rid") int rid,
        @Query("packetid") String packetid,
        @Query("company_name") String company_name);

    @GET("/rest/v1/address/get_logistic_companys")
    Observable<List<LogisticCompany>> getLogisticCompany(
        @Query("referal_trade_id") int referal_trade_id);

    @FormUrlEncoded
    @POST("/rest/v1/address/{address_id}/change_company_code")
    Observable<ResultBean> changeLogisticCompany(
        @Path("address_id") int address_id,
        @Field("referal_trade_id") String referal_trade_id,
        @Field("logistic_company_code") String logistic_company_code);

    @GET("/rest/v2/teambuy/{tid}/team_info")
    Observable<TeamBuyBean> getTeamBuyBean(
        @Path("tid") String tid);

}
