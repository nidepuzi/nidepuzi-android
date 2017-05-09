package com.danlai.nidepuzi.ui.activity.trade;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.library.widget.CountDownView;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.OrderGoodsListAdapter;
import com.danlai.nidepuzi.adapter.PayAdapter;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityOrderDetailBinding;
import com.danlai.nidepuzi.entity.OrderDetailBean;
import com.danlai.nidepuzi.entity.PayInfoBean;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.event.RefreshOrderListEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.util.pay.PayUtils;
import com.google.gson.Gson;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;

public class OrderDetailActivity extends BaseMVVMActivity<ActivityOrderDetailBinding>
    implements View.OnClickListener, AdapterView.OnItemClickListener {
    ListView payListView;
    int order_id = -1;
    OrderDetailBean orderDetail;
    String tid;
    private Dialog dialog;

    @Override
    protected void setListener() {
        b.btnOrderPay.setOnClickListener(this);
        b.btnOrderCancel.setOnClickListener(this);
        payListView.setOnItemClickListener(this);
        b.layoutService.setOnClickListener(this);
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
    public void getIntentUrl(Uri uri) {
        order_id = Integer.valueOf(uri.getQueryParameter("trade_id"));
    }

    @Override
    protected void initViews() {
        View view = getLayoutInflater().inflate(R.layout.pop_layout, null);
        dialog = new Dialog(this, R.style.CustomDialog);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        window.setWindowAnimations(R.style.BottomDialogAnim);
        View closeIv = view.findViewById(R.id.close_iv);
        payListView = (ListView) view.findViewById(R.id.lv);
        ((TextView) view.findViewById(R.id.title)).setText("选择支付方式");
        closeIv.setOnClickListener(this);
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

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void fillDataToView(OrderDetailBean orderDetailBean) {
        tid = orderDetailBean.getTid();
        showPayBtn(orderDetailBean);
        orderDetail = orderDetailBean;
        if (orderDetailBean.isCan_change_address()) {
            b.address.setOnClickListener(OrderDetailActivity.this);
        } else {
            b.rightFlag.setVisibility(View.GONE);
        }
        PayAdapter payAdapter = new PayAdapter(orderDetailBean.getExtras().getChannels(), this);
        payListView.setAdapter(payAdapter);
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
        if (format.startsWith(".")) {
            b.tvWalletPay.setText("¥0" + format);
        } else {
            b.tvWalletPay.setText("¥" + format);
        }
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
        OrderGoodsListAdapter mGoodsAdapter = new OrderGoodsListAdapter(this, orderDetailBean, orderDetailBean.isCan_refund());
        b.lvGoods.setAdapter(mGoodsAdapter);
        ViewUtils.setListViewHeightBasedOnChildren(b.lvGoods);
    }

    private void showPayBtn(OrderDetailBean orderDetailBean) {
        try {
            int state = orderDetailBean.getStatus();
            switch (state) {
                case BaseConst.ORDER_STATE_WAITPAY:
                    b.layoutPay.setVisibility(View.VISIBLE);
                    b.tvPayType.setVisibility(View.GONE);
                    b.countView.start(calcLeftTime(orderDetailBean.getCreated()), CountDownView.TYPE_MINUTE);
                    b.countView.setOnEndListener(view -> {
                        b.btnOrderPay.setClickable(false);
                        b.btnOrderPay.setBackgroundResource(R.drawable.btn_common_grey);

                    });
                    if (calcLeftTime(orderDetailBean.getCreated()) <= 0) {
                        b.btnOrderPay.setClickable(false);
                        b.btnOrderPay.setBackgroundResource(R.drawable.btn_common_grey);
                    }
                    break;
                case BaseConst.ORDER_STATE_PAYED:
                case BaseConst.ORDER_STATE_SENDED:
                case BaseConst.ORDER_STATE_CONFIRM_RECEIVE:
                case BaseConst.ORDER_STATE_TRADE_SUCCESS:
                    b.layoutPay.setVisibility(View.GONE);
                    b.layoutService.setVisibility(View.VISIBLE);
                    break;
                default:
                    b.layoutPay.setVisibility(View.GONE);
                    b.layoutService.setVisibility(View.GONE);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long calcLeftTime(String crtTime) {
        long left = 0;
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            crtTime = crtTime.replace("T", " ");
            Date date = format.parse(crtTime);
            if (date.getTime() + 20 * 60 * 1000 - now.getTime() > 0) {
                left = date.getTime() + 20 * 60 * 1000 - now.getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return left;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_order_pay:
                if (orderDetail != null && orderDetail.getStatus() == BaseConst.ORDER_STATE_WAITPAY) {
                    dialog.show();
                }
                break;
            case R.id.btn_order_cancel:
                cancel_order();
                break;
            case R.id.address:
                if (orderDetail != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("receiver_name", orderDetail.getUser_adress().getReceiver_name());
                    bundle.putString("mobile", orderDetail.getUser_adress().getReceiver_mobile());
                    bundle.putString("address1", orderDetail.getUser_adress().getReceiver_state()
                        + orderDetail.getUser_adress().getReceiver_city()
                        + orderDetail.getUser_adress().getReceiver_district());
                    bundle.putString("receiver_address", orderDetail.getUser_adress().getReceiver_address());
                    bundle.putString("receiver_state", orderDetail.getUser_adress().getReceiver_state());
                    bundle.putString("receiver_city", orderDetail.getUser_adress().getReceiver_city());
                    bundle.putString("receiver_district", orderDetail.getUser_adress().getReceiver_district());
                    bundle.putString("address_id", orderDetail.getUser_adress().getId() + "");
                    bundle.putString("referal_trade_id", orderDetail.getId() + "");
                    bundle.putString("idNo", orderDetail.getUser_adress().getIdentification_no());
                    readyGo(WaitSendAddressActivity.class, bundle);
                }
                break;
            case R.id.close_iv:
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                break;
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
                            ConsultSource source = new ConsultSource("http://m.nidepuzi.com", "Android客户端", "Android客户端");
                            Unicorn.openServiceActivity(mBaseActivity, title, source);
                        }
                    });
                break;
        }
    }

    private void payNow(String channel) {
        showIndeterminateProgressDialog(false);
        BaseApp.getTradeInteractor(this)
            .orderPayWithChannel(order_id, channel, new ServiceResponse<PayInfoBean>(mBaseActivity) {
                @Override
                public void onNext(PayInfoBean payInfoBean) {
                    PayUtils.createPayment(OrderDetailActivity.this, new Gson().toJson(payInfoBean.getCharge()));
                }

                @Override
                public void onError(Throwable e) {
                    JUtils.Toast("支付请求失败!");
                    hideIndeterminateProgressDialog();
                    super.onError(e);
                }
            });
    }

    private void cancel_order() {
        showIndeterminateProgressDialog(false);
        setDialogContent("订单取消中!");
        BaseApp.getTradeInteractor(this)
            .delRefund(order_id, new ServiceResponse<ResponseBody>(mBaseActivity) {
                @Override
                public void onNext(ResponseBody responseBody) {
                    hideIndeterminateProgressDialog();
                    try {
                        finish();
                        EventBus.getDefault().post(new RefreshOrderListEvent());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    hideIndeterminateProgressDialog();
                    JUtils.Toast("取消失败");
                }
            });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PayUtils.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                EventBus.getDefault().post(new RefreshOrderListEvent());
                hideIndeterminateProgressDialog();
                String result = data.getExtras().getString("pay_result");
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                if (result != null) {
                    switch (result) {
                        case "cancel":
                            JUtils.Toast("已取消支付!");
                            break;
                        case "success":
                            JUtils.Toast("支付成功！");
                            Bundle bundle = new Bundle();
                            bundle.putInt("fragment", 3);
                            readyGoThenKill(AllOrderActivity.class, bundle);
                            break;
                        default:
                            showMsg(result, errorMsg, extraMsg);
                            break;
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        b.countView.cancel();
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String channel = orderDetail.getExtras().getChannels().get(position).getId();
        dialog.dismiss();
        payNow(channel);
    }

}
