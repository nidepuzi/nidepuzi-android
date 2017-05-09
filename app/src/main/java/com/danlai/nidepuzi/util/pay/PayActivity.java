package com.danlai.nidepuzi.util.pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alipay.sdk.app.PayTask;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.entity.event.PayEvent;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by wisdom on 16/11/16.
 */

public class PayActivity extends AppCompatActivity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String pay_result;
            String error_msg = (String) msg.obj;
            switch (msg.what) {
                case 9000:
                    pay_result = "success";
                    break;
                case 4000:
                    pay_result = "fail";
                    break;
                case 6001:
                    pay_result = "cancel";
                    break;
                default:
                    pay_result = "error";
                    break;
            }
            finishAndSendMsg(pay_result, error_msg, "");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Bundle extras = getIntent().getExtras();
        String channel = extras.getString("channel", "");
        if (!"".equals(channel)) {
            try {
                if ("wx".equals(channel)) {
                    IWXAPI api = WXAPIFactory.createWXAPI(this, BaseConst.WX_APP_ID);
                    if (isWXAppInstalledAndSupported(api)) {
                        String wx = extras.getString("wx");
                        JSONObject wxObj = new JSONObject(wx);
                        PayReq request = new PayReq();
                        request.appId = wxObj.optString("appId");
                        request.partnerId = wxObj.optString("partnerId");
                        request.prepayId = wxObj.optString("prepayId");
                        request.packageValue = wxObj.optString("packageValue");
                        request.nonceStr = wxObj.optString("nonceStr");
                        request.timeStamp = wxObj.optString("timeStamp");
                        request.sign = wxObj.optString("sign");
                        api.sendReq(request);
                    } else if (!api.isWXAppInstalled()) {
                        finishAndSendMsg("invalid", "未安装客户端!", "");
                    } else if (!api.isWXAppSupportAPI()) {
                        finishAndSendMsg("error", "暂不支持支付!", "");
                    } else {
                        finishAndSendMsg("error", "未知异常!", "");
                    }
                } else if ("alipay".equals(channel)) {
                    String orderInfo = extras.getString("orderInfo");
                    Runnable runnable = () -> {
                        PayTask payTask = new PayTask(this);
                        Map<String, String> result = payTask.payV2(orderInfo, true);
                        Message msg = new Message();
                        msg.what = Integer.parseInt(result.get("resultStatus"));
                        msg.obj = result.get("memo");
                        mHandler.sendMessage(msg);
                    };
                    new Thread(runnable).start();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPayResult(PayEvent event) {
        finishAndSendMsg(event.getPay_result(), event.getError_msg(), "");
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void finishAndSendMsg(String pay_result, String error_msg, String extra_msg) {
        Intent intent = new Intent();
        intent.putExtra("pay_result", pay_result);
        intent.putExtra("error_msg", error_msg);
        intent.putExtra("extra_msg", extra_msg);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private boolean isWXAppInstalledAndSupported(IWXAPI api) {
        return api.isWXAppInstalled() && api.isWXAppSupportAPI();
    }
}
