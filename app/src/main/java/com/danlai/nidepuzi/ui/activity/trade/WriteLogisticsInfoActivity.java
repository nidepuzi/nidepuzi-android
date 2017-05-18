package com.danlai.nidepuzi.ui.activity.trade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.LogImageView;
import com.danlai.library.widget.LogMsgView;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityWriteLogisticsInfoBinding;
import com.danlai.nidepuzi.entity.LogisticsBean;
import com.danlai.nidepuzi.service.ServiceResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;

public class WriteLogisticsInfoActivity extends BaseMVVMActivity<ActivityWriteLogisticsInfoBinding>
    implements View.OnClickListener {
    private String address, mobile, contact;
    String company;
    int goods_id;
    private boolean flag;
    private String company_name;
    private String packetid;
    private int rid;

    @Override
    protected void setListener() {
        b.btnCommit.setOnClickListener(this);
        b.rlScan.setOnClickListener(this);
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected void initData() {
        if (TextUtils.isEmpty(address) || TextUtils.isEmpty(mobile)) {
            JUtils.Toast("未查询到详细退货信息，请联系客服查询");
        } else {
            b.tvName.setText("收件人:" + contact);
            b.tvMobile.setText("联系电话:" + mobile);
            b.tvAddress.setText(address);
        }
        if (flag) {
            BaseApp.getTradeInteractor(this)
                .getRefundLogistic(rid, packetid, company_name, new ServiceResponse<LogisticsBean>(mBaseActivity) {
                    @Override
                    public void onNext(LogisticsBean logisticsBean) {
                        if ("".equals(logisticsBean.getName())) {
                            b.logisticName.setText("暂时无法查询物流信息,请稍后再试");
                        } else {
                            b.logisticName.setText(logisticsBean.getName());
                        }
                        if ("".equals(logisticsBean.getOrder())) {
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            b.logisticNum.setText(df.format(new Date()));
                        } else {
                            b.logisticNum.setText("快递单号:  " + logisticsBean.getOrder());
                        }
                        if (logisticsBean.getData().size() > 0) {
                            for (int i = 0; i < logisticsBean.getData().size(); i++) {
                                String content = logisticsBean.getData().get(i).getContent();
                                String time = logisticsBean.getData().get(i).getTime().replace("T", " ");
                                if (i == 0) {
                                    b.tvOrderLastTime.setText(time);
                                    b.tvOrderLastState.setText(content);
                                } else {
                                    b.logImageLayout.addView(new LogImageView(WriteLogisticsInfoActivity.this));
                                    LogMsgView logMsgView = new LogMsgView(WriteLogisticsInfoActivity.this);
                                    logMsgView.setMsg(content);
                                    logMsgView.setTime(time);
                                    b.logMsgLayout.addView(logMsgView);
                                }
                            }
                        } else {
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            b.tvOrderLastTime.setText(df.format(new Date()));
                            b.tvOrderLastState.setText("暂时无法查询物流信息,请稍后再试");
                        }
                        b.logisticLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        JUtils.Toast("暂时无法查询物流信息,请稍后再试");
                        b.logisticName.setText("物流公司: " + company_name);
                        b.logisticNum.setText("物流单号: " + packetid);
                        b.msgLayout.setVisibility(View.GONE);
                        b.logImageLayout.setVisibility(View.VISIBLE);
                    }
                });
        }
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        goods_id = extras.getInt("goods_id");
        address = extras.getString("address");
        mobile = extras.getString("mobile");
        contact = extras.getString("contact");
        flag = extras.getBoolean("flag");
        company_name = extras.getString("company_name", "");
        packetid = extras.getString("packetid", "");
        rid = extras.getInt("rid");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_write_logistics_info;
    }

    @Override
    protected void initViews() {
        if (!flag) {
            b.writeLayout.setVisibility(View.VISIBLE);
        } else {
            b.titleView.setName("查询物流信息");
        }
        b.etLogisticsCompany.setOnTouchListener((v, event) -> {
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                Intent intent = new Intent(WriteLogisticsInfoActivity.this, LogisticsCompanyActivity.class);
                startActivityForResult(intent, 1);
            }
            return false;
        });
        SpannableStringBuilder builder = new SpannableStringBuilder(b.tvReason.getText().toString());
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.colorAccent));
        builder.setSpan(colorSpan, 20, 35, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        b.tvReason.setText(builder);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_scan:
                startActivityForResult(new Intent(this, CommonScanActivity.class), 2);
                break;
            case R.id.btn_commit:
                commit_logistics_info();
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 1) && (data != null)) {
            company = data.getExtras().getString("company");
            b.etLogisticsCompany.setText(company);
        }
        if (requestCode == 2 && data != null) {
            String number = data.getExtras().getString("number");
            b.etLogisticsNumber.setText(number);
        }
    }

    private void commit_logistics_info() {
        if (company == null || company.isEmpty() || b.etLogisticsNumber.getText().toString().trim().isEmpty()) {
            JUtils.Toast("提交物流信息为空，请重试！");
            return;
        }
        BaseApp.getTradeInteractor(this)
            .commitLogisticsInfo(goods_id, company, b.etLogisticsNumber.getText().toString().trim(),
                new ServiceResponse<ResponseBody>(mBaseActivity) {
                    @Override
                    public void onNext(ResponseBody resp) {
                        JUtils.Toast("提交物流信息成功，收货后我们会尽快为您处理退款！");
                        Intent intent = new Intent(WriteLogisticsInfoActivity.this,
                            RefundDetailActivity.class);
                        intent.putExtra("flag", true);
                        setResult(0, intent);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        JUtils.Toast("提交信息失败，请重试！");
                        super.onError(e);
                    }
                });
    }
}
