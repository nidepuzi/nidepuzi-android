package com.danlai.nidepuzi.ui.activity.trade;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.CountDownView;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.CompanyAdapter;
import com.danlai.nidepuzi.adapter.OrderGoodsListAdapter;
import com.danlai.nidepuzi.adapter.PayAdapter;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.base.BaseWebViewActivity;
import com.danlai.nidepuzi.databinding.ActivityOrderDetailBinding;
import com.danlai.nidepuzi.entity.LogisticCompany;
import com.danlai.nidepuzi.entity.OrderDetailBean;
import com.danlai.nidepuzi.entity.PayInfoBean;
import com.danlai.nidepuzi.entity.ResultBean;
import com.danlai.nidepuzi.entity.TeamBuyBean;
import com.danlai.nidepuzi.entity.event.RefreshOrderListEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.util.JumpUtils;
import com.danlai.nidepuzi.util.pay.PayUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;

public class OrderDetailActivity extends BaseMVVMActivity<ActivityOrderDetailBinding>
    implements View.OnClickListener, AdapterView.OnItemClickListener {

    ListView listView;
    ListView listView2;

    int order_id = 0;
    OrderDetailBean orderDetail;
    String tid;
    private Dialog dialog;
    private Dialog dialog2;

    @Override
    protected void setListener() {
        b.btnOrderPay.setOnClickListener(this);
        b.btnOrderCancel.setOnClickListener(this);
        b.logisticsLayout.setOnClickListener(this);
        b.teamBuy.setOnClickListener(this);
        listView2.setOnItemClickListener(this);
    }

    @Override
    public View getLoadingView() {
        return b.scrollView;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        order_id = extras.getInt("orderinfo");
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
        listView = (ListView) view.findViewById(R.id.lv);
        ((TextView) view.findViewById(R.id.title)).setText("选择物流");
        closeIv.setOnClickListener(this);

        View view2 = getLayoutInflater().inflate(R.layout.pop_layout, null);
        dialog2 = new Dialog(this, R.style.CustomDialog);
        dialog2.setContentView(view2);
        dialog2.setCancelable(true);
        Window window2 = dialog2.getWindow();
        WindowManager.LayoutParams wlp2 = window2.getAttributes();
        wlp2.gravity = Gravity.BOTTOM;
        wlp2.width = WindowManager.LayoutParams.MATCH_PARENT;
        window2.setAttributes(wlp);
        window2.setWindowAnimations(R.style.BottomDialogAnim);
        View closeIv2 = view2.findViewById(R.id.close_iv);
        listView2 = (ListView) view2.findViewById(R.id.lv);
        ((TextView) view.findViewById(R.id.title)).setText("选择支付方式");
        closeIv2.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (order_id != -1) {
            showIndeterminateProgressDialog(false);
            BaseApp.getTradeInteractor(this)
                .getOrderDetail(order_id, new ServiceResponse<OrderDetailBean>(mBaseActivity) {
                    @Override
                    public void onNext(OrderDetailBean orderDetailBean) {
                        tid = orderDetailBean.getTid();
                        orderDetail = orderDetailBean;
                        fillDataToView(orderDetailBean);
                        showProcBtn(orderDetailBean);
                        b.scrollView.scrollTo(0, 0);
                        if (orderDetailBean.isCan_change_address()) {
                            b.address.setOnClickListener(OrderDetailActivity.this);
                        } else {
                            b.rightFlag.setVisibility(View.GONE);
                            b.logisticsRight.setVisibility(View.GONE);
                        }
                        BaseApp.getTradeInteractor(OrderDetailActivity.this)
                            .getLogisticCompany(order_id, new ServiceResponse<List<LogisticCompany>>(mBaseActivity) {
                                @Override
                                public void onNext(List<LogisticCompany> logisticCompanies) {
                                    CompanyAdapter adapter = new CompanyAdapter(logisticCompanies, getApplicationContext());
                                    listView.setAdapter(adapter);
                                    listView.setOnItemClickListener((parent, view, position, id) -> {
                                        String code = logisticCompanies.get(position).getCode();
                                        BaseApp.getTradeInteractor(OrderDetailActivity.this)
                                            .changeLogisticCompany(orderDetail.getUser_adress().getId(), order_id + "", code,
                                                new ServiceResponse<ResultBean>(mBaseActivity) {
                                                    @Override
                                                    public void onNext(ResultBean resultBean) {
                                                        switch (resultBean.getCode()) {
                                                            case 0:
                                                                b.logistics.setText(logisticCompanies.get(position).getName());
                                                                break;
                                                        }
                                                        JUtils.Toast(resultBean.getInfo());
                                                        dialog.dismiss();
                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {
                                                        JUtils.Toast(e.getMessage());
                                                        dialog.dismiss();
                                                    }
                                                });
                                    });
                                }
                            });
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
        PayAdapter payAdapter = new PayAdapter(orderDetailBean.getExtras().getChannels(), this);
        listView2.setAdapter(payAdapter);
        OrderDetailBean.UserAdressBean user_adress = orderDetailBean.getUser_adress();
        int status = orderDetailBean.getStatus();
        if (!"退款中".equals(orderDetailBean.getStatus_display())
            && !"退货中".equals(orderDetailBean.getStatus_display())) {
            if (status == 2 || status == 3 || status == 4 || status == 5) {
                if (orderDetailBean.getOrder_type() == 3) {
                    BaseApp.getTradeInteractor(this)
                        .getTeamBuyBean(orderDetailBean.getTid(), new ServiceResponse<TeamBuyBean>(mBaseActivity) {
                            @Override
                            public void onNext(TeamBuyBean teamBuyBean) {
                                if (teamBuyBean.getStatus() != 2) {
                                    setStatusView(status);
                                }
                                b.teamBuy.setVisibility(View.VISIBLE);
                            }
                        });
                } else {
                    setStatusView(status);
                }
            } else {
                setStatusView(status);
            }
        }
        b.txOrderId.setText(orderDetailBean.getTid());
        b.txCustomName.setText(user_adress.getReceiver_name());
        b.txCustomMobile.setText(user_adress.getReceiver_mobile());
        b.txCustomPhone.setText(user_adress.getReceiver_phone());
        b.txCustomAddress.setText(user_adress.getReceiver_state()
            + user_adress.getReceiver_city()
            + user_adress.getReceiver_district()
            + user_adress.getReceiver_address());
        b.txOrderTotalfee.setText("¥" + orderDetailBean.getTotal_fee());
        b.txOrderDiscountfee.setText("-¥" + orderDetailBean.getDiscount_fee());
        b.txOrderPostfee.setText("¥" + orderDetailBean.getPost_fee());
        b.txOrderPayment.setText("¥" + orderDetailBean.getPay_cash());
        String format = new DecimalFormat("0.00").format(orderDetailBean.getPayment() - orderDetailBean.getPay_cash());
        if (format.startsWith(".")) {
            b.txOrderPayment2.setText("¥0" + format);
        } else {
            b.txOrderPayment2.setText("¥" + format);
        }
        b.time.setText(orderDetailBean.getCreated().replace("T", " "));
        if (orderDetailBean.getLogistics_company() != null) {
            b.logistics.setText(orderDetailBean.getLogistics_company().getName());
        }
        String channel = orderDetailBean.getChannel();
        if (channel.contains("alipay")) {
            b.ivPay.setImageResource(R.drawable.alipay);
        } else if (channel.contains("wx")) {
            b.ivPay.setImageResource(R.drawable.wx);
        } else {
            b.rlPay.setVisibility(View.GONE);
        }
        OrderGoodsListAdapter mGoodsAdapter = new OrderGoodsListAdapter(this, orderDetailBean, orderDetailBean.isCan_refund());
        b.lvGoods.setAdapter(mGoodsAdapter);
        setListViewHeightBasedOnChildren(b.lvGoods);
    }

    private void setStatusView(int status) {
        switch (status) {
            case 5:
                setView5();
                break;
            case 4:
                setView4();
                break;
            case 3:
                setView3();
                break;
            case 2:
                setView2();
                break;
            case 0:
            case 1:
                b.hsv.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setView2() {
        b.tv1.setTextColor(getResources().getColor(R.color.color_33));
        b.tv2.setTextColor(getResources().getColor(R.color.colorAccent));
        b.iv1.setImageResource(R.drawable.status_black);
        b.iv2.setImageResource(R.drawable.state_in);
        b.line2.setBackgroundColor(getResources().getColor(R.color.color_33));
        b.hsv.setVisibility(View.VISIBLE);
    }

    private void setView3() {
        setView2();
        b.tv2.setTextColor(getResources().getColor(R.color.color_33));
        b.tv3.setTextColor(getResources().getColor(R.color.colorAccent));
        b.iv2.setImageResource(R.drawable.status_black);
        b.iv3.setImageResource(R.drawable.state_in);
        b.line3.setBackgroundColor(getResources().getColor(R.color.color_33));
    }

    private void setView4() {
        setView3();
        b.tv3.setTextColor(getResources().getColor(R.color.color_33));
        b.tv4.setTextColor(getResources().getColor(R.color.colorAccent));
        b.iv3.setImageResource(R.drawable.status_black);
        b.iv4.setImageResource(R.drawable.state_in);
        b.line4.setBackgroundColor(getResources().getColor(R.color.color_33));
    }

    private void setView5() {
        setView4();
        b.tv4.setTextColor(getResources().getColor(R.color.color_33));
        b.tv5.setTextColor(getResources().getColor(R.color.colorAccent));
        b.iv4.setImageResource(R.drawable.status_black);
        b.iv5.setImageResource(R.drawable.state_in);
        b.line5.setBackgroundColor(getResources().getColor(R.color.color_33));
        b.line6.setBackgroundColor(getResources().getColor(R.color.color_33));
    }

    private void showProcBtn(OrderDetailBean orderDetailBean) {
        try {
            int state = orderDetailBean.getStatus();
            switch (state) {
                case BaseConst.ORDER_STATE_WAITPAY:
                    b.rlayoutOrderLefttime.setVisibility(View.VISIBLE);
                    b.rlPay.setVisibility(View.GONE);
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
            Date crtdate = format.parse(crtTime);
            if (crtdate.getTime() + 20 * 60 * 1000 - now.getTime() > 0) {
                left = crtdate.getTime() + 20 * 60 * 1000 - now.getTime();
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
                    dialog2.show();
                }
                break;
            case R.id.team_buy:
                SharedPreferences preferences = BaseApp.getInstance().getSharedPreferences("APICLIENT", Context.MODE_PRIVATE);
                String baseUrl = "http://m.nidepuzi.com/mall/order/spell/group/" + tid + "?from_page=order_detail";
                if (!TextUtils.isEmpty(preferences.getString("BASE_URL", ""))) {
                    baseUrl = "http://" + preferences.getString("BASE_URL", "") + "/mall/order/spell/group/" + tid + "?from_page=order_detail";
                }
                JumpUtils.jumpToWebViewWithCookies(mBaseActivity,
                    baseUrl, -1, BaseWebViewActivity.class, "查看拼团详情", false);
                break;
            case R.id.btn_order_cancel:
                cancel_order();
                break;
            case R.id.address:
                if (orderDetail != null) {
                    Intent intent = new Intent(this, WaitSendAddressActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("receiver_name", orderDetail.getUser_adress().getReceiver_name());
                    bundle.putString("mobile", orderDetail.getUser_adress().getReceiver_mobile());
                    bundle.putString("address1", orderDetail.getUser_adress().getReceiver_state()
                        + orderDetail.getUser_adress().getReceiver_city()
                        + orderDetail.getUser_adress().getReceiver_district());
                    bundle.putString("address2", orderDetail.getUser_adress().getReceiver_address());
                    bundle.putString("receiver_state", orderDetail.getUser_adress().getReceiver_state());
                    bundle.putString("receiver_city", orderDetail.getUser_adress().getReceiver_city());
                    bundle.putString("receiver_district", orderDetail.getUser_adress().getReceiver_district());
                    bundle.putString("address_id", orderDetail.getUser_adress().getId() + "");
                    bundle.putString("referal_trade_id", orderDetail.getId() + "");
                    bundle.putString("idNo", orderDetail.getUser_adress().getIdentification_no());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.logistics_layout:
                if (orderDetail != null && "已付款".equals(orderDetail.getStatus_display())) {
                    new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("非服装类商品是由供应商直接发货，只能尽量满足您选择的快递公司，" +
                            "如需确认能否满足您的快递需求，请联系客服。")
                        .setPositiveButton("确认", (dialog1, which) -> {
                            dialog1.dismiss();
                            dialog.show();
                        })
                        .show();
                }
                break;
            case R.id.close_iv:
                if (dialog2.isShowing()) {
                    dialog2.dismiss();
                }
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
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
                            Intent intent = new Intent(this, AllOrderActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("fragment", 3);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                            break;
                        default:
                            showMsg(result, errorMsg, extraMsg);
                            break;
                    }
                }
            }
        }
    }

    public void showMsg(String title, String msg1, String msg2) {
        String str = title;
        if (null != msg1 && msg1.length() != 0) {
            str += "\n" + msg1;
        }
        if (null != msg2 && msg2.length() != 0) {
            str += "\n" + msg2;
        }
        if (title.equals("fail")) {
            str = "支付失败，请重试！";
        } else if (title.equals("invalid")) {
            str = "支付失败，支付软件未安装完整！";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailActivity.this);
        builder.setMessage(str);
        builder.setTitle("提示");
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
            + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    protected void onDestroy() {
        b.countView.cancel();
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String channel = orderDetail.getExtras().getChannels().get(position).getId();
        dialog2.dismiss();
        payNow(channel);
    }

}
