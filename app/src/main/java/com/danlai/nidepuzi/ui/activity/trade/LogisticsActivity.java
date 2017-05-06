package com.danlai.nidepuzi.ui.activity.trade;

import android.os.Bundle;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.library.widget.LogImageView;
import com.danlai.library.widget.LogMsgView;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.GoodsListAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityLogisticsBinding;
import com.danlai.nidepuzi.entity.AllOrdersBean;
import com.danlai.nidepuzi.entity.LogisticsBean;
import com.danlai.nidepuzi.entity.OrderDetailBean;
import com.danlai.nidepuzi.service.ServiceResponse;

import java.util.ArrayList;
import java.util.List;

public class LogisticsActivity extends BaseMVVMActivity<ActivityLogisticsBinding> implements View.OnClickListener {
    private int id;
    private String orderPacketId;
    OrderDetailBean.PackageOrdersBean packageOrdersBean;
    private List<AllOrdersBean.ResultsEntity.OrdersEntity> data;

    @Override
    protected void setListener() {
        b.orderLayout.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        data = new ArrayList<>();
        if (packageOrdersBean.getLogistics_company() != null) {
            b.tvCompany.setText(packageOrdersBean.getLogistics_company().getName());
            String packetid = packageOrdersBean.getOut_sid();
            String company_code = packageOrdersBean.getLogistics_company().getCode();
            if (packetid != null && company_code != null &&
                !"".equals(packetid) && !"".equals(company_code)) {
                b.tvOrder.setText(packetid);
                orderPacketId = packetid;
                BaseApp.getTradeInteractor(this)
                    .getLogisticsByPacketId(packetid, company_code, new ServiceResponse<LogisticsBean>(mBaseActivity) {
                        @Override
                        public void onNext(LogisticsBean logisticsBean) {
                            fillDataToView(logisticsBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            fillDataToView(null);
                            JUtils.Toast("暂无物流进展!");
                        }
                    });
            } else {
                b.tvOrder.setText("未揽件");
                fillDataToView(null);
            }
        } else {
            b.tvCompany.setText("铺子推荐");
            b.tvOrder.setText("未揽件");
            fillDataToView(null);
        }
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    private void fillDataToView(LogisticsBean logisticsBean) {
        BaseApp.getTradeInteractor(this)
            .getOrderDetail(id, new ServiceResponse<OrderDetailBean>(mBaseActivity) {
                @Override
                public void onNext(OrderDetailBean orderDetailBean) {
                    ArrayList<AllOrdersBean.ResultsEntity.OrdersEntity> orders = orderDetailBean.getOrders();
                    for (int i = 0; i < orders.size(); i++) {
                        if (orders.get(i).getPackage_order_id().equals(packageOrdersBean.getId())) {
                            data.add(orders.get(i));
                        }
                    }
                    b.lv.setAdapter(new GoodsListAdapter(data, LogisticsActivity.this));
                    ViewUtils.setListViewHeightBasedOnChildren(b.lv);
                    hideIndeterminateProgressDialog();
                }
            });
        if (logisticsBean != null) {
            if ((logisticsBean.getData() != null) && (logisticsBean.getData().size() != 0)) {
                List<LogisticsBean.Msg> data1 = logisticsBean.getData();
                for (int i = 0; i < data1.size(); i++) {
                    if (i == 0) {
                        if (packageOrdersBean.getCancel_time() != null) {
                            b.tvOrderLastState.setText("取消订单");
                            b.tvOrderLastTime.setText(packageOrdersBean.getCancel_time().replace("T", " "));
                            b.logImageLayout.addView(new LogImageView(this));
                            LogMsgView logMsgView = new LogMsgView(this);
                            logMsgView.setMsg(data1.get(0).getContent());
                            logMsgView.setTime(data1.get(0).getTime().replace("T", " "));
                            b.logMsgLayout.addView(logMsgView);
                        } else {
                            b.tvOrderLastState.setText(data1.get(0).getContent());
                            b.tvOrderLastTime.setText(data1.get(0).getTime().replace("T", " "));
                        }
                    } else {
                        b.logImageLayout.addView(new LogImageView(this));
                        LogMsgView logMsgView = new LogMsgView(this);
                        logMsgView.setMsg(data1.get(i).getContent());
                        logMsgView.setTime(data1.get(i).getTime().replace("T", " "));
                        b.logMsgLayout.addView(logMsgView);
                    }
                }
                addFinishTime();
            } else {
                fillStatusView();
            }
        } else {
            fillStatusView();
        }
    }

    private void fillStatusView() {
        if (packageOrdersBean.getCancel_time() != null) {
            b.tvOrderLastState.setText("取消订单");
            b.tvOrderLastTime.setText(packageOrdersBean.getCancel_time().replace("T", " "));
            addFinishTime();
        } else if (packageOrdersBean.getWeight_time() != null) {
            b.tvOrderLastState.setText("产品发货中");
            b.tvOrderLastTime.setText(packageOrdersBean.getWeight_time().replace("T", " "));
            addAssignTime();
        } else if (packageOrdersBean.getAssign_time() != null) {
            b.tvOrderLastState.setText("仓库质检");
            b.tvOrderLastTime.setText(packageOrdersBean.getAssign_time().replace("T", " "));
            addBookTime();
        } else if (packageOrdersBean.getBook_time() != null) {
            b.tvOrderLastState.setText("订货中,订单暂时无法取消");
            b.tvOrderLastTime.setText(packageOrdersBean.getBook_time().replace("T", " "));
            addPaytime();
        } else if (packageOrdersBean.getPay_time() != null) {
            b.tvOrderLastState.setText("支付成功");
            b.tvOrderLastTime.setText(packageOrdersBean.getPay_time().replace("T", " "));
        } else {
            b.tvOrderLastState.setText("订单已成功创建!");
        }
    }

    private void addFinishTime() {
        if (packageOrdersBean.getWeight_time() != null) {
            b.logImageLayout.addView(new LogImageView(this));
            LogMsgView logMsgView = new LogMsgView(this);
            logMsgView.setMsg("产品发货中");
            logMsgView.setTime(packageOrdersBean.getWeight_time().replace("T", " "));
            b.logMsgLayout.addView(logMsgView);
        }
        addAssignTime();
    }

    private void addAssignTime() {
        if (packageOrdersBean.getAssign_time() != null) {
            b.logImageLayout.addView(new LogImageView(this));
            LogMsgView logMsgView = new LogMsgView(this);
            logMsgView.setMsg("仓库质检");
            logMsgView.setTime(packageOrdersBean.getAssign_time().replace("T", " "));
            b.logMsgLayout.addView(logMsgView);
        }
        addBookTime();
    }

    private void addBookTime() {
        if (packageOrdersBean.getBook_time() != null) {
            b.logImageLayout.addView(new LogImageView(this));
            LogMsgView logMsgView = new LogMsgView(this);
            logMsgView.setMsg("订货中,订单暂时无法取消");
            logMsgView.setTime(packageOrdersBean.getBook_time().replace("T", " "));
            b.logMsgLayout.addView(logMsgView);
        }
        addPaytime();
    }

    private void addPaytime() {
        if (packageOrdersBean.getPay_time() != null) {
            b.logImageLayout.addView(new LogImageView(this));
            LogMsgView logMsgView = new LogMsgView(this);
            logMsgView.setMsg("支付成功");
            logMsgView.setTime(packageOrdersBean.getPay_time().replace("T", " "));
            b.logMsgLayout.addView(logMsgView);
        }
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        id = extras.getInt("id");
        packageOrdersBean = ((OrderDetailBean.PackageOrdersBean) extras.getSerializable("packageOrdersBean"));
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_logistics;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_layout:
                if (orderPacketId != null && !orderPacketId.equals("")) {
                    JUtils.copyToClipboard(orderPacketId);
                    JUtils.Toast("单号已复制到粘贴板，可以到相应快递官网核对物流信息。");
                }
                break;
        }
    }
}
