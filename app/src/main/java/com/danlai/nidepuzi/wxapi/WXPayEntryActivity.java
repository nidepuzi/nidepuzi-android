package com.danlai.nidepuzi.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.entity.event.PayEvent;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wisdom on 16/11/15.
 */
public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, BaseConst.WX_APP_PAY_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            String pay_result;
            String error_msg;
            switch (baseResp.errCode) {
                case 0:
                    pay_result = "success";
                    error_msg = "支付成功!";
                    break;
                case -1:
                    pay_result = "fail";
                    error_msg = "支付失败!";
                    break;
                case -2:
                    pay_result = "cancel";
                    error_msg = "取消支付!";
                    break;
                default:
                    pay_result = "error";
                    error_msg = "支付异常!";
                    break;
            }
            EventBus.getDefault().post(new PayEvent(pay_result, error_msg, ""));
            finish();
        }
    }
}