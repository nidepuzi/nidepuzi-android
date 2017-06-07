package com.danlai.nidepuzi.adapter;

/**
 * Created by wulei on 15-12-17.
 * 商品订单数据适配
 */

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.entity.AllOrdersBean;
import com.danlai.nidepuzi.entity.LogisticCompany;
import com.danlai.nidepuzi.entity.OrderDetailBean;
import com.danlai.nidepuzi.entity.UserBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.product.ProductDetailActivity;
import com.danlai.nidepuzi.ui.activity.trade.ApplyRefundActivity;
import com.danlai.nidepuzi.ui.activity.trade.ApplyReturnGoodsActivity;
import com.danlai.nidepuzi.ui.activity.trade.LogisticsActivity;
import com.danlai.nidepuzi.ui.activity.trade.RefundDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class OrderGoodsListAdapter extends BaseAdapter {
    private BaseActivity context;
    private List<AllOrdersBean.ResultsBean.OrdersBean> data;

    private OrderDetailBean orderDetailEntity;
    private int count;
    private boolean can_refund;
    private boolean isSale;

    public OrderGoodsListAdapter(BaseActivity context, OrderDetailBean orderDetailEntity, boolean can_refund) {
        this.orderDetailEntity = orderDetailEntity;
        this.can_refund = can_refund;
        data = new ArrayList<>();
        data.addAll(orderDetailEntity.getOrders());
        count = 0;
        isSale = false;
        this.context = context;
    }

    public void setData(OrderDetailBean orderDetailBean) {
        orderDetailEntity = orderDetailBean;
        data.addAll(orderDetailBean.getOrders());
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int state = data.get(position).getStatus();
        int refund_state = data.get(position).getRefund_status();
        convertView = LayoutInflater.from(context).inflate(R.layout.item_order_detail_include_proc, null);
        if (((state == BaseConst.ORDER_STATE_PAYED) ||
            (state == BaseConst.ORDER_STATE_SENDED) ||
            (state == BaseConst.ORDER_STATE_CONFIRM_RECEIVE)) && !isSale) {
            setBtnInfo(convertView, state, refund_state,
                data.get(position).isKill_title(), data.get(position));
            setBtnListener(convertView, state, refund_state, data.get(position).getId(), position);
        } else {
            convertView.findViewById(R.id.rl_info).setVisibility(View.GONE);
        }
        View divider = convertView.findViewById(R.id.divider);
        View logisticsLayout = convertView.findViewById(R.id.logistics_layout);
        View textLogistic = convertView.findViewById(R.id.text_logistic);
        if (position == 0) {
            count = 0;
            divider.setVisibility(View.VISIBLE);
            if (!"".equals(data.get(position).getPackage_order_id())) {
                logisticsLayout.setVisibility(View.VISIBLE);
                ((TextView) convertView.findViewById(R.id.tv_order_package)).setText("包裹" + BaseConst.numberToWord(++count) + ":");
            } else {
                logisticsLayout.setVisibility(View.GONE);
            }
        } else if (!data.get(position).getPackage_order_id().equals(data.get(position - 1).getPackage_order_id())) {
            divider.setVisibility(View.VISIBLE);
            logisticsLayout.setVisibility(View.VISIBLE);
            ((TextView) convertView.findViewById(R.id.tv_order_package)).setText("包裹" + BaseConst.numberToWord(++count) + ":");
        } else {
            divider.setVisibility(View.GONE);
            logisticsLayout.setVisibility(View.GONE);
        }

        for (int i = 0; i < orderDetailEntity.getPackage_orders().size(); i++) {
            if (data.get(position).getPackage_order_id().equals(orderDetailEntity.getPackage_orders().get(i).getId())) {
                LogisticCompany company = orderDetailEntity.getPackage_orders().get(i).getLogistics_company();
                if (company != null) {
                    ((TextView) convertView.findViewById(R.id.tv_order_log)).setText(company.getName());
                }
                String assign_status_display = orderDetailEntity.getPackage_orders().get(i).getAssign_status_display();
                if (assign_status_display != null) {
                    ((TextView) convertView.findViewById(R.id.tx_order_crtstate)).setText(assign_status_display);
                }
                final int finalI = i;
                textLogistic.setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.ll_item).setOnClickListener(v -> {
                    Intent intent = new Intent(context, LogisticsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", orderDetailEntity.getId());
                    bundle.putSerializable("packageOrdersBean", orderDetailEntity.getPackage_orders().get(finalI));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                });
                break;
            }
        }
        ((TextView) convertView.findViewById(R.id.tx_good_name)).setText(data.get(position).getTitle());
        double price = (double) (Math.round((data.get(position).getTotal_fee() / data.get(position).getNum()) * 100)) / 100;
        ((TextView) convertView.findViewById(R.id.tx_good_price)).setText("¥" + price);
        ((TextView) convertView.findViewById(R.id.tx_good_size)).setText(data.get(position).getSku_name());
        ((TextView) convertView.findViewById(R.id.tx_good_num)).setText("x" + data.get(position).getNum());
        ImageView img_goods = (ImageView) convertView.findViewById(R.id.img_good);
        ViewUtils.loadImgToImgViewWithPlaceholder(context, img_goods, data.get(position).getPic_path());
        img_goods.setOnClickListener(v -> {
            int model_id = data.get(position).getModel_id();
            if (model_id != 0) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("model_id", model_id);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private void setBtnInfo(View convertView, int state, int refund_state, boolean kill_title
        , AllOrdersBean.ResultsBean.OrdersBean entity) {
        Button btn = (Button) convertView.findViewById(R.id.btn_order_proc);
        Button tv_order_state = (Button) convertView.findViewById(R.id.tv_order_state);
        switch (state) {
            case BaseConst.ORDER_STATE_PAYED:
            case BaseConst.ORDER_STATE_CONFIRM_RECEIVE:
                if (kill_title) {
                    convertView.findViewById(R.id.rl_info).setVisibility(View.GONE);
                } else {
                    if (refund_state != BaseConst.REFUND_STATE_NO_REFUND) {
                        btn.setVisibility(View.INVISIBLE);
                        tv_order_state.setVisibility(View.VISIBLE);
                        switch (refund_state) {
                            case BaseConst.REFUND_STATE_SELLER_REJECTED:
                                tv_order_state.setText("拒绝退款");
                                break;
                            case BaseConst.REFUND_STATE_REFUND_CLOSE:
                                tv_order_state.setText("退款关闭");
                                break;
                            case BaseConst.REFUND_STATE_REFUND_SUCCESS:
                                tv_order_state.setText("退款成功");
                                break;
                            default:
                                break;
                        }
                        tv_order_state.setOnClickListener(v -> {
                            Intent intent = new Intent(context, RefundDetailActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("goods_id", entity.getRefund_id());
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        });
                    } else {
                        if (state == BaseConst.ORDER_STATE_PAYED) {
                            btn.setText("申请退款");
                            if (!can_refund) {
                                btn.setVisibility(View.INVISIBLE);
                            }
                        } else {
                            btn.setText("申请退货");
                        }
                        tv_order_state.setVisibility(View.INVISIBLE);
                    }
                }
                break;
            case BaseConst.ORDER_STATE_SENDED: {
                btn.setText("确认收货");
                tv_order_state.setVisibility(View.INVISIBLE);
                break;
            }
            default:
                break;
        }
    }

    private void setBtnListener(View convertView, int state, int refund_state, int goods_id, int position) {
        Button btn = (Button) convertView.findViewById(R.id.btn_order_proc);
        switch (state) {
            case BaseConst.ORDER_STATE_PAYED: {
                switch (refund_state) {
                    case BaseConst.REFUND_STATE_NO_REFUND: {
                        btn.setOnClickListener(v -> {
                            List<OrderDetailBean.ExtrasBean.RefundChoicesBean> choices = orderDetailEntity.getExtras().getRefund_choices();
                            if (choices.size() > 1) {
                                Dialog dialog = new Dialog(context, R.style.CustomDialog);
                                dialog.setContentView(R.layout.pop_refund_layout);
                                dialog.setCancelable(true);
                                Window window = dialog.getWindow();
                                WindowManager.LayoutParams wlp = window.getAttributes();
                                wlp.gravity = Gravity.BOTTOM;
                                wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
                                window.setAttributes(wlp);
                                window.setWindowAnimations(R.style.AnimBottom);
                                View closeIv = dialog.findViewById(R.id.close_iv);
                                View sure = dialog.findViewById(R.id.sure);
                                ListView listView = (ListView) dialog.findViewById(R.id.lv_refund);
                                closeIv.setOnClickListener(v1 -> dialog.dismiss());
                                RefundTypeAdapter adapter = new RefundTypeAdapter(context, choices);
                                listView.setAdapter(adapter);
                                sure.setOnClickListener(v12 -> {
                                    if (adapter.getSelect() == -1) {
                                        JUtils.Toast("请选择退款方式");
                                    } else {
                                        Intent intent = new Intent(context, ApplyRefundActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("id", orderDetailEntity.getId());
                                        bundle.putInt("position", position);
                                        bundle.putString("refund_channel", adapter.getItem(adapter.getSelect()).getRefund_channel());
                                        bundle.putString("name", adapter.getItem(adapter.getSelect()).getName());
                                        bundle.putString("desc", adapter.getItem(adapter.getSelect()).getDesc());
                                        intent.putExtras(bundle);
                                        context.startActivity(intent);
                                        dialog.dismiss();
                                        context.finish();
                                    }
                                });
                                dialog.show();
                            } else if (choices.size() == 1) {
                                Intent intent = new Intent(context, ApplyRefundActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("id", orderDetailEntity.getId());
                                bundle.putInt("position", position);
                                bundle.putString("refund_channel", choices.get(0).getRefund_channel());
                                bundle.putString("name", choices.get(0).getName());
                                bundle.putString("desc", choices.get(0).getDesc());
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                                context.finish();
                            }
                        });
                        break;
                    }
                }
                break;
            }
            case BaseConst.ORDER_STATE_SENDED: {
                btn.setOnClickListener(v -> {
                    new AlertDialog.Builder(context)
                        .setTitle("提示")
                        .setMessage("是否确认签收产品？")
                        .setPositiveButton("确认", (dialog, which) -> {
                            receive_goods(goods_id);
                            dialog.dismiss();
                        })
                        .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                        .show();
                });
                break;
            }
            case BaseConst.ORDER_STATE_CONFIRM_RECEIVE: {
                switch (refund_state) {
                    case BaseConst.REFUND_STATE_NO_REFUND: {
                        btn.setOnClickListener(v -> {
                            Intent intent = new Intent(context, ApplyReturnGoodsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("id", orderDetailEntity.getId());
                            bundle.putInt("position", position);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                            context.finish();
                        });
                        break;
                    }
                }
            }
            break;
        }
    }

    private void receive_goods(int id) {
        BaseApp.getTradeInteractor(context)
            .receiveGoods(id, new ServiceResponse<UserBean>(context) {
                @Override
                public void onNext(UserBean userBean) {
                    context.finish();
                }
            });
    }

    public void setSale(boolean sale) {
        isSale = sale;
    }
}

