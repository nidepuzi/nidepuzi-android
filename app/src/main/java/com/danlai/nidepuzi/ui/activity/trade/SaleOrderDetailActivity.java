package com.danlai.nidepuzi.ui.activity.trade;

import android.os.Bundle;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.OrderGoodsListAdapter;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityOrderDetailBinding;
import com.danlai.nidepuzi.entity.OrderDetailBean;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;

/**
 * @author wisdom
 * @date 2017年06月05日 下午5:32
 */

public class SaleOrderDetailActivity extends BaseMVVMActivity<ActivityOrderDetailBinding>
    implements View.OnClickListener {
    int order_id = -1;
    String tid;

    @Override
    protected void setListener() {
        b.layoutService.setOnClickListener(this);
        b.tvOrderId.setOnClickListener(this);
    }

    private void init() {
        if (order_id != -1) {
            showIndeterminateProgressDialog(false);
            BaseApp.getTradeInteractor(this)
                .getOrderDetail(order_id, new ServiceResponse<OrderDetailBean>(mBaseActivity) {
                    @Override
                    public void onNext(OrderDetailBean orderDetailBean) {
                        fillDataToView(orderDetailBean);
                        b.scrollView.scrollTo(0, 0);
                        hideIndeterminateProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        JUtils.Log(e.getMessage());
                        JUtils.Toast("订单详情获取失败!");
                        hideIndeterminateProgressDialog();
                    }
                });
        }
    }

    private void fillDataToView(OrderDetailBean orderDetailBean) {
        tid = orderDetailBean.getTid();
        showService(orderDetailBean);
        b.rightFlag.setVisibility(View.GONE);
        OrderDetailBean.UserAddressBean user_address = orderDetailBean.getUser_adress();
        b.tvOrderId.setText("订单编号:" + orderDetailBean.getTid());
        b.tvCustomName.setText("收货人:" + user_address.getReceiver_name());
        b.tvCustomMobile.setText(user_address.getReceiver_mobile());
        b.tvCustomAddress.setText("收货地址:" + user_address.getReceiver_state()
            + user_address.getReceiver_city()
            + user_address.getReceiver_district()
            + user_address.getReceiver_address());
        b.tvTotalFee.setText("¥" + JUtils.formatDouble(orderDetailBean.getTotal_fee()));
        b.tvOrderDiscount.setText("-¥" + JUtils.formatDouble(orderDetailBean.getDiscount_fee()));
        b.tvPostFee.setText("¥" + JUtils.formatDouble(orderDetailBean.getPost_fee()));
        b.tvOrderPayment.setText("¥" + JUtils.formatDouble(orderDetailBean.getPay_cash()));
        String format = JUtils.formatDouble(orderDetailBean.getPayment() - orderDetailBean.getPay_cash());
        b.tvWalletPay.setText("¥" + format);
        b.tvCreateTime.setText("下单时间:" + orderDetailBean.getCreated().replace("T", " ").substring(0, 19));
        String channel = orderDetailBean.getChannel();
        if (channel.contains("alipay")) {
            b.tvPayType.setText("付款类型:支付宝支付");
        } else if (channel.contains("wx")) {
            b.tvPayType.setText("付款类型:微信支付");
        } else if (channel.equals("budget")) {
            b.tvPayType.setText("付款类型:余额支付");
        } else {
            b.tvPayType.setVisibility(View.GONE);
        }
        String payTime = orderDetailBean.getPay_time();
        if (payTime != null) {
            b.tvPayTime.setText("付款时间:" + payTime.replace("T", " ").substring(0, 19));
        } else {
            b.tvPayTime.setVisibility(View.GONE);
        }
        OrderGoodsListAdapter mGoodsAdapter = new OrderGoodsListAdapter(this, orderDetailBean, false);
        mGoodsAdapter.setSale(true);
        b.lvGoods.setAdapter(mGoodsAdapter);
        ViewUtils.setListViewHeightBasedOnChildren(b.lvGoods);
    }

    private void showService(OrderDetailBean orderDetailBean) {
        int state = orderDetailBean.getStatus();
        switch (state) {
            case BaseConst.ORDER_STATE_PAYED:
            case BaseConst.ORDER_STATE_SENDED:
            case BaseConst.ORDER_STATE_CONFIRM_RECEIVE:
            case BaseConst.ORDER_STATE_TRADE_SUCCESS:
                b.layoutService.setVisibility(View.VISIBLE);
                break;
            default:
                b.layoutService.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    @Override
    public View getLoadingView() {
        return b.scrollView;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        order_id = extras.getInt("order_id");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_service:
                BaseApp.getMainInteractor(mBaseActivity)
                    .getProfile(new ServiceResponse<UserInfoBean>(mBaseActivity) {
                        @Override
                        public void onNext(UserInfoBean userInfoBean) {
                            String data = "[ " +
                                "{\"key\":\"real_name\", \"value\":\"" + userInfoBean.getNick() + "\"}, " +
                                "{\"key\":\"mobile_phone\", \"value\":\"" + userInfoBean.getMobile() + "\"}, " +
                                "{\"key\":\"avatar\", \"value\": \"" + userInfoBean.getThumbnail() + "\"}]";
                            String title = "铺子客服";
                            YSFUserInfo ysfUserInfo = new YSFUserInfo();
                            ysfUserInfo.userId = userInfoBean.getUser_id();
                            ysfUserInfo.data = data;
                            Unicorn.setUserInfo(ysfUserInfo);
                            ConsultSource source = new ConsultSource("https://m.nidepuzi.com", "Android客户端", "Android客户端");
                            Unicorn.openServiceActivity(mBaseActivity, title, source);
                        }
                    });
                break;
            case R.id.tv_order_id:
                JUtils.copyToClipboard(tid);
                JUtils.Toast("已复制订单号到粘贴板!");
                break;
        }
    }
}
