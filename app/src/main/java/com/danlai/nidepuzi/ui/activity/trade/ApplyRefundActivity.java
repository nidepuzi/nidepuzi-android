package com.danlai.nidepuzi.ui.activity.trade;

import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityApplyRefundBinding;
import com.danlai.nidepuzi.entity.AllOrdersBean;
import com.danlai.nidepuzi.entity.OrderDetailBean;
import com.danlai.nidepuzi.entity.RefundMsgBean;
import com.danlai.nidepuzi.service.ServiceResponse;

public class ApplyRefundActivity extends BaseMVVMActivity<ActivityApplyRefundBinding>
    implements View.OnClickListener {
    String select_reason[] = new String[]{"七天无理由退换", "缺货", "错拍", "没有发货", "与描述不符", "其他"};

    AllOrdersBean.ResultsEntity.OrdersEntity goods_info;
    String reason = "";
    int num = 0;
    double apply_fee = 0;
    String desc = "";
    String proof_pic = "";
    private int id;
    private int position;
    private String refund_channel;
    private String name;
    private String typeDesc;

    @Override
    protected void setListener() {
        b.btnCommit.setOnClickListener(this);
        b.add.setOnClickListener(this);
        b.delete.setOnClickListener(this);
        b.etRefundInfo.setOnClickListener(this);
        b.etRefundReason.setOnTouchListener((v, event) -> {
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                chooseReason();
            }
            return false;
        });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        id = extras.getInt("id");
        position = extras.getInt("position");
        refund_channel = extras.getString("refund_channel");
        name = extras.getString("name");
        typeDesc = extras.getString("desc");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_apply_refund;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        BaseApp.getTradeInteractor(this)
            .getOrderDetail(id, new ServiceResponse<OrderDetailBean>(mBaseActivity) {
                @Override
                public void onNext(OrderDetailBean orderDetailBean) {
                    goods_info = orderDetailBean.getOrders().get(position);
                    fillDataIntoView();
                }

                @Override
                public void onError(Throwable e) {
                    hideIndeterminateProgressDialog();
                    JUtils.Toast("加载失败");
                }
            });
    }

    private void fillDataIntoView() {
        if (goods_info != null) {
            fillDataToView(goods_info);
        } else {
            hideIndeterminateProgressDialog();
            Bundle bundle = new Bundle();
            bundle.putInt("fragment", 1);
            readyGo(AllOrderActivity.class, bundle);
        }
    }

    private void fillDataToView(AllOrdersBean.ResultsEntity.OrdersEntity goods) {
        ViewUtils.loadImgToImgView(this, b.imgGood, goods.getPic_path());

        b.txGoodName.setText(goods.getTitle());
        b.txGoodPrice.setText("￥" + goods.getTotal_fee());

        b.txGoodSize.setText(goods.getSku_name());
        b.txGoodNum.setText("×" + goods.getNum());

        num = goods.getNum();
        b.txRefundNum.setText(Integer.toString(num));
        b.txRefundfee.setText("￥" + goods.getPayment());
        b.refundTv.setText(name);
        b.refundTvDesc.setText(typeDesc);
        if ("budget".equals(refund_channel)) {
            b.refundIv.setImageResource(R.drawable.icon_fast_return);
        } else {
            b.refundIv.setImageResource(R.drawable.icon_return);
        }
        hideIndeterminateProgressDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                desc = b.etRefundInfo.getText().toString().trim();
                if (reason.equals("")) {
                    JUtils.Toast("请选择退货原因！");
                } else {
                    AlertDialog dialog = new AlertDialog.Builder(this)
                        .setCancelable(true)
                        .setTitle("小鹿急速退款说明")
                        .setMessage("退款立即退到小鹿零钱账户，该退款可以用于重新购买商品或者提现。")
                        .setPositiveButton("同意", (dialog1, which) -> {
                            dialog1.dismiss();
                            commit_apply();
                        })
                        .setNegativeButton("取消", (dialog1, which) -> dialog1.dismiss())
                        .create();
                    if ("budget".equals(refund_channel)) {
                        dialog.show();
                    } else {
                        commit_apply();
                    }
                }
                break;
            case R.id.et_refund_info:
                b.etRefundInfo.setCursorVisible(true);
                b.etRefundInfo.setFocusable(true);
                b.etRefundInfo.setFocusableInTouchMode(true);
                b.etRefundInfo.requestFocus();
                break;
            case R.id.add:
                if (goods_info.getNum() > num) {
                    num++;
                }
                b.txRefundNum.setText(Integer.toString(num));
                break;
            case R.id.delete:
                if (num > 1) {
                    num--;
                }
                b.txRefundNum.setText(Integer.toString(num));
                break;
        }
    }

    private void commit_apply() {
        showIndeterminateProgressDialog(false);
        BaseApp.getTradeInteractor(this)
            .refundCreate(goods_info.getId(), BaseConst.get_reason_num(reason), num,
                apply_fee, desc, proof_pic, refund_channel, new ServiceResponse<RefundMsgBean>(mBaseActivity) {
                    @Override
                    public void onNext(RefundMsgBean resp) {
                        new AlertDialog.Builder(mBaseActivity)
                            .setMessage(resp.getInfo())
                            .setPositiveButton("确定", (dialog, which) -> dialog.dismiss())
                            .show();
                        hideIndeterminateProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideIndeterminateProgressDialog();
                        JUtils.Toast("提交失败,请重新提交");
                        super.onError(e);
                    }
                });
    }

    private void chooseReason() {
        new AlertDialog.Builder(this).setTitle("")
            .setItems(select_reason, (dialog, which) -> {
                reason = select_reason[which];
                b.etRefundReason.setText(reason);
                dialog.dismiss();
            })
            .setNegativeButton("取消", null)
            .show();
    }
}
