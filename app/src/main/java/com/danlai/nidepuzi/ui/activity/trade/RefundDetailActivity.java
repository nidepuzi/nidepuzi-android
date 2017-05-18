package com.danlai.nidepuzi.ui.activity.trade;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityRefundDetailBinding;
import com.danlai.nidepuzi.entity.AllRefundsBean.ResultsEntity;
import com.danlai.nidepuzi.entity.AllRefundsBean.ResultsEntity.StatusShaftBean;
import com.danlai.nidepuzi.service.ServiceResponse;

import java.util.List;


public class RefundDetailActivity extends BaseMVVMActivity<ActivityRefundDetailBinding>
    implements View.OnClickListener {
    ResultsEntity refundDetail;
    private int goods_id;
    private boolean isWrited;

    @Override
    protected void setListener() {
        b.btnWrite.setOnClickListener(this);
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        BaseApp.getTradeInteractor(this)
            .getRefundDetailBean(goods_id, new ServiceResponse<ResultsEntity>(mBaseActivity) {
                @Override
                public void onNext(ResultsEntity refundDetailBean) {
                    refundDetail = refundDetailBean;
                    fillDataToView(refundDetailBean);
                    hideIndeterminateProgressDialog();
                }

                @Override
                public void onError(Throwable e) {
                    hideIndeterminateProgressDialog();
                    JUtils.Toast("数据加载失败!");
                    mVaryViewHelperController.showNetworkError(view -> {
                        refreshView();
                        showNetworkError();
                    });
                }
            });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        goods_id = extras.getInt("goods_id");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_refund_detail;
    }

    private void fillDataToView(ResultsEntity refundDetailBean) {
        if (refundDetailBean.isHas_good_return()) {
            switch (refundDetailBean.getStatus()) {
                case BaseConst.REFUND_STATE_SELLER_AGREED:
                    b.llReturn.setVisibility(View.VISIBLE);
                    b.btnWrite.setText("填写快递单");
                    isWrited = false;
                    break;
                case BaseConst.REFUND_STATE_WAIT_RETURN_FEE:
                case BaseConst.REFUND_STATE_REFUND_SUCCESS:
                    b.llReturn.setVisibility(View.VISIBLE);
                    b.btnWrite.setText("已验收");
                    isWrited = true;
                    break;
                case BaseConst.REFUND_STATE_BUYER_RETURNED_GOODS:
                    b.llReturn.setVisibility(View.VISIBLE);
                    b.btnWrite.setText("查看进度");
                    isWrited = true;
                    break;
                default:
                    b.llReturn.setVisibility(View.GONE);
                    isWrited = true;
                    break;
            }
        } else {
            b.llReturn.setVisibility(View.GONE);
        }
        b.tvOrderId.setText(refundDetailBean.getRefund_no());
        b.tvStatus.setText(refundDetailBean.getStatus_display());
        b.address.setText(refundDetailBean.getReturn_address());
        b.name.setText(refundDetailBean.getReturn_contact());
        b.phone.setText(refundDetailBean.getReturn_mobile());
        ViewUtils.loadImgToImgViewWithPlaceholder(mBaseActivity, b.sdv, refundDetailBean.getPic_path());
        b.tvGoodName.setText(refundDetailBean.getTitle());
        b.tvGoodPrice.setText("¥" + refundDetailBean.getTotal_fee() + "x" + refundDetailBean.getRefund_num());
        b.tvGoodSize.setText("尺码：" + refundDetailBean.getSku_name());
        b.time.setText("申请时间:" + refundDetailBean.getCreated().replace("T", " "));
        b.num.setText(Integer.toString(refundDetailBean.getRefund_num()));
        b.price.setText("¥" + refundDetailBean.getRefund_fee());
        b.reason.setText(refundDetailBean.getReason());
        ImageView[] images = {b.roundImage, b.roundImage1, b.roundImage2, b.roundImage3};
        RelativeLayout[] rls = {b.rlImg, b.rlImg1, b.rlImg2, b.rlImg3};
        if (refundDetailBean.getProof_pic() != null && refundDetailBean.getProof_pic().size() > 0) {
            for (int i = 0; i < refundDetailBean.getProof_pic().size(); i++) {
                ViewUtils.loadImgToImgViewWithPlaceholder(mBaseActivity, images[i],
                    refundDetailBean.getProof_pic().get(i));
                rls[i].setVisibility(View.VISIBLE);
            }
            b.imageLayout.setVisibility(View.VISIBLE);
        }
        String desc = refundDetailBean.getAmount_flow().getDesc();
        b.refundType.setText(desc);
        if (!"".equals(desc)) {
            b.refundLayout.setVisibility(View.VISIBLE);
        } else {
            b.refundLayout.setVisibility(View.GONE);
        }
        if ("拒绝退款".equals(refundDetailBean.getStatus_display()) || "没有退款".equals(refundDetailBean.getStatus_display()) ||
            "退款关闭".equals(refundDetailBean.getStatus_display())) {
            b.statusLayout.setVisibility(View.GONE);
        } else {
            showRefundStatus(refundDetailBean);
        }
    }

    private void showRefundStatus(ResultsEntity refundDetailBean) {
        List<StatusShaftBean> status_shaft = refundDetailBean.getStatus_shaft();
        if (refundDetailBean.isHas_good_return()) {
            b.tv3.setVisibility(View.VISIBLE);
            b.line3.setVisibility(View.VISIBLE);
            b.iv3.setVisibility(View.VISIBLE);
            b.iv.setVisibility(View.VISIBLE);
            b.line.setVisibility(View.VISIBLE);
            b.tv.setVisibility(View.VISIBLE);
        } else {
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER;
            b.statusLayout.setLayoutParams(lp);
            b.driver.setVisibility(View.GONE);
        }
        if (status_shaft.size() > 1) {
            String display = status_shaft.get(status_shaft.size() - 1).getStatus_display();
            if ("同意申请".equals(display)) {
                setView1();
            } else if ("退货待收".equals(display)) {
                setView2();
            } else if ("等待返款".equals(display)) {
                setView3();
            } else if ("退款成功".equals(display)) {
                setView4();
            }
        }
    }

    private void setView2() {
        setView1();
        b.tv2.setTextColor(getResources().getColor(R.color.color_33));
        b.tv2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        b.tv.setTextColor(getResources().getColor(R.color.colorAccent));
        b.tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        b.iv2.setImageResource(R.drawable.status_black);
        b.iv.setImageResource(R.drawable.state_in);
        b.line.setBackgroundColor(getResources().getColor(R.color.color_33));
    }

    private void setView4() {
        setView3();
        b.tv4.setTextColor(getResources().getColor(R.color.color_33));
        b.tv4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        b.tv5.setTextColor(getResources().getColor(R.color.colorAccent));
        b.tv5.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        b.iv4.setImageResource(R.drawable.status_black);
        b.iv5.setImageResource(R.drawable.state_in);
        b.line5.setBackgroundColor(getResources().getColor(R.color.color_33));
        b.line6.setBackgroundColor(getResources().getColor(R.color.color_33));
    }

    private void setView3() {
        setView2();
        b.tv.setTextColor(getResources().getColor(R.color.color_33));
        b.tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        b.tv3.setTextColor(getResources().getColor(R.color.color_33));
        b.tv3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        b.tv4.setTextColor(getResources().getColor(R.color.colorAccent));
        b.tv4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        b.iv.setImageResource(R.drawable.status_black);
        b.iv3.setImageResource(R.drawable.status_black);
        b.iv4.setImageResource(R.drawable.state_in);
        b.line3.setBackgroundColor(getResources().getColor(R.color.color_33));
        b.line4.setBackgroundColor(getResources().getColor(R.color.color_33));
    }

    private void setView1() {
        b.tv1.setTextColor(getResources().getColor(R.color.color_33));
        b.tv1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        b.tv2.setTextColor(getResources().getColor(R.color.colorAccent));
        b.tv2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        b.iv1.setImageResource(R.drawable.status_black);
        b.iv2.setImageResource(R.drawable.state_in);
        b.line2.setBackgroundColor(getResources().getColor(R.color.color_33));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_write:
                Intent intent = new Intent(this, WriteLogisticsInfoActivity.class);
                Bundle bundle = new Bundle();
                if (refundDetail != null) {
                    bundle.putInt("goods_id", refundDetail.getOrder_id());
                    bundle.putString("address", refundDetail.getReturn_address());
                    bundle.putString("mobile", refundDetail.getReturn_mobile());
                    bundle.putString("contact", refundDetail.getReturn_contact());
                }
                bundle.putBoolean("flag", isWrited);
                if (isWrited) {
                    bundle.putInt("rid", refundDetail.getId());
                    bundle.putString("company_name", refundDetail.getCompany_name());
                    bundle.putString("packetid", refundDetail.getSid());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 0) {
            boolean flag = false;
            if (data != null) {
                flag = data.getBooleanExtra("flag", false);
            }
            if (flag) {
                initData();
            }
        }
    }
}
