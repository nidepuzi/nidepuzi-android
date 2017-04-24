package com.danlai.nidepuzi.module;

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
import com.danlai.nidepuzi.service.ServiceResponse;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by wisdom on 17/3/1.
 */

public interface TradeInteractor {

    void getOrderList(int type, int page, ServiceResponse<AllOrdersBean> response);

    void getAllOrderBean(int page, ServiceResponse<AllOrdersBean> response);

    void shoppingCartCreateV2(String cart_ids, String addr_id, String channel,
                              String payment, String post_fee, String discount_fee,
                              String total_fee, String uuid, String pay_extras, String code,
                              boolean isteam, ServiceResponse<PayInfoBean> response);

    void orderPayWithChannel(int order_id, String channel, ServiceResponse<PayInfoBean> response);

    void getOrderDetail(int order_id, ServiceResponse<OrderDetailBean> response);

    void getRefunds(int page, ServiceResponse<AllRefundsBean> response);

    void getWaitPayOrdersBean(int page, ServiceResponse<AllOrdersBean> response);

    void getWaitSendOrdersBean(int page, ServiceResponse<AllOrdersBean> response);

    void receiveGoods(int id, ServiceResponse<UserBean> response);

    void getRefundDetailBean(int order_id, ServiceResponse<AllRefundsBean.ResultsEntity> response);

    void delRefund(int order_id, ServiceResponse<ResponseBody> response);

    void refundCreate(int goods_id, int reason, int num, double sum_price, String description,
                      String proof_pic, String refund_channel, ServiceResponse<RefundMsgBean> response);

    void refundCreate(int goods_id, int reason, int num, double sum_price, String description,
                      String proof_pic, ServiceResponse<RefundMsgBean> response);

    void getQiniuToken(ServiceResponse<QiniuTokenBean> response);

    void commitLogisticsInfo(int goods_id, String company, String logistics_number,
                             ServiceResponse<ResponseBody> response);

    void getLogisticsByPacketId(String packetid, String company_code,
                                ServiceResponse<LogisticsBean> response);

    void getRefundLogistic(int rid, String packetid, String company_name,
                           ServiceResponse<LogisticsBean> response);

    void getLogisticCompany(int referal_trade_id, ServiceResponse<List<LogisticCompany>> response);

    void changeLogisticCompany(int address_id, String referal_trade_id, String logistic_company_code,
                               ServiceResponse<ResultBean> response);

    void getTeamBuyBean(String tid, ServiceResponse<TeamBuyBean> response);
}
