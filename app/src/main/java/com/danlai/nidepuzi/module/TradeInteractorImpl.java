package com.danlai.nidepuzi.module;

import com.danlai.library.rx.DefaultTransform;
import com.danlai.nidepuzi.base.BaseConst;
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
import com.danlai.nidepuzi.service.api.TradeService;

import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;

/**
 * Created by wisdom on 17/3/1.
 */

public class TradeInteractorImpl implements TradeInteractor {

    private final TradeService service;

    @Inject
    public TradeInteractorImpl(TradeService service) {
        this.service = service;
    }

    @Override
    public void getOrderList(int type, int page, ServiceResponse<AllOrdersBean> response) {
        if (type == BaseConst.WAIT_SEND) {
            getWaitSendOrdersBean(page, response);
        } else if (type == BaseConst.WAIT_PAY) {
            getWaitPayOrdersBean(page, response);
        } else {
            getAllOrderBean(page, response);
        }
    }

    @Override
    public void getAllOrderBean(int page, ServiceResponse<AllOrdersBean> response) {
        service.getAllOrdersList(page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void shoppingCartCreateV2(String cart_ids, String addr_id, String channel,
                                     String payment, String post_fee, String discount_fee,
                                     String total_fee, String uuid, String pay_extras,
                                     String code, ServiceResponse<PayInfoBean> response) {

            service.shoppingCartCreateV2(cart_ids, addr_id, channel, payment, post_fee,
                discount_fee, total_fee, uuid, pay_extras, code)
                .compose(new DefaultTransform<>())
                .subscribe(response);

    }

    @Override
    public void orderPayWithChannel(int order_id, String channel, ServiceResponse<PayInfoBean> response) {
        service.orderPayWithChannel(order_id, channel)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getOrderDetail(int order_id, ServiceResponse<OrderDetailBean> response) {
        service.getOrderDetail(order_id, "app")
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getRefunds(int page, ServiceResponse<AllRefundsBean> response) {
        service.getAllRefundsList(page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getWaitPayOrdersBean(int page, ServiceResponse<AllOrdersBean> response) {
        service.getWaitPayOrdersBean(page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getWaitSendOrdersBean(int page, ServiceResponse<AllOrdersBean> response) {
        service.getWaitSendOrdersBean(page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void receiveGoods(int id, ServiceResponse<UserBean> response) {
        service.receiveGoods(id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getRefundDetailBean(int order_id, ServiceResponse<AllRefundsBean.ResultsEntity> response) {
        service.getRefundDetailBean(order_id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void delRefund(int order_id, ServiceResponse<ResponseBody> response) {
        service.delRefund(order_id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void refundCreate(int goods_id, int reason, int num, double sum_price, String description,
                             String proof_pic, String refund_channel, ServiceResponse<RefundMsgBean> response) {
        service.refundCreate(goods_id, reason, num, sum_price, description, proof_pic, refund_channel)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void refundCreate(int goods_id, int reason, int num, double sum_price,
                             String description, String proof_pic, ServiceResponse<RefundMsgBean> response) {
        service.refundCreate(goods_id, reason, num, sum_price, description, proof_pic)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getQiniuToken(ServiceResponse<QiniuTokenBean> response) {
        service.getQiniuToken()
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void commitLogisticsInfo(int goods_id, String company, String logistics_number,
                                    ServiceResponse<ResponseBody> response) {
        service.commitLogisticsInfo(goods_id, 2, company, logistics_number)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getLogisticsByPacketId(String packetid, String company_code,
                                       ServiceResponse<LogisticsBean> response) {
        service.getLogisticsByPacketId(packetid, company_code)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getRefundLogistic(int rid, String packetid, String company_name,
                                  ServiceResponse<LogisticsBean> response) {
        service.getRefundLogistic(rid, packetid, company_name)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getLogisticCompany(int referal_trade_id, ServiceResponse<List<LogisticCompany>> response) {
        service.getLogisticCompany(referal_trade_id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void changeLogisticCompany(int address_id, String referal_trade_id, String logistic_company_code,
                                      ServiceResponse<ResultBean> response) {
        service.changeLogisticCompany(address_id, referal_trade_id, logistic_company_code)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getTeamBuyBean(String tid, ServiceResponse<TeamBuyBean> response) {
        service.getTeamBuyBean(tid)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }
}
