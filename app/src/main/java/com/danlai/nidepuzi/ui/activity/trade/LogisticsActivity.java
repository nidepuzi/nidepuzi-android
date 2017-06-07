package com.danlai.nidepuzi.ui.activity.trade;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.GoodsListAdapter;
import com.danlai.nidepuzi.adapter.LogisticAdapter;
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
    private List<AllOrdersBean.ResultsBean.OrdersBean> data;
    private LogisticAdapter adapter;
    private GoodsListAdapter goodAdapter;


    @Override
    protected void setListener() {
        b.orderLayout.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        b.rv.setLayoutManager(new LinearLayoutManager(mBaseActivity));
        b.rv.setNestedScrollingEnabled(false);
        adapter = new LogisticAdapter(mBaseActivity);
        b.rv.setAdapter(adapter);

        b.rvGood.setLayoutManager(new LinearLayoutManager(mBaseActivity));
        b.rvGood.setNestedScrollingEnabled(false);
        goodAdapter = new GoodsListAdapter(mBaseActivity);
        b.rvGood.setAdapter(goodAdapter);
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
        if (logisticsBean != null) {
            if ((logisticsBean.getData() != null) && (logisticsBean.getData().size() != 0)) {
                List<LogisticsBean.Msg> data1 = logisticsBean.getData();
                adapter.updateWithClear(data1);
            }
        }
        BaseApp.getTradeInteractor(this)
            .getOrderDetail(id, new ServiceResponse<OrderDetailBean>(mBaseActivity) {
                @Override
                public void onNext(OrderDetailBean orderDetailBean) {
                    ArrayList<AllOrdersBean.ResultsBean.OrdersBean> orders = orderDetailBean.getOrders();
                    for (int i = 0; i < orders.size(); i++) {
                        if (orders.get(i).getPackage_order_id().equals(packageOrdersBean.getId())) {
                            data.add(orders.get(i));
                        }
                    }
                    goodAdapter.updateWithClear(data);
                    hideIndeterminateProgressDialog();
                }
            });
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
