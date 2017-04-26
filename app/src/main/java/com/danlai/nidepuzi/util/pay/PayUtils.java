package com.danlai.nidepuzi.util.pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wisdom on 16/11/15.
 */

public abstract class PayUtils {
    public static final int REQUEST_CODE_PAYMENT = 1005;

    public static void createPayment(Activity activity, String jsonStr) {
        try {
            JSONObject object = new JSONObject(jsonStr);
            String credential = object.optString("credential");
            JSONObject credentialObj = new JSONObject(credential);
            String channel = object.optString("channel");
            Bundle bundle = new Bundle();
            bundle.putString("channel", channel);
            if ("wx".equals(channel)) {
                String wx = credentialObj.optString("wx");
                bundle.putString("wx", wx);
            } else if ("alipay".equals(channel)) {
                String alipay = credentialObj.optString("alipay");
                JSONObject alipayObj = new JSONObject(alipay);
                String orderInfo = alipayObj.optString("orderInfo");
                bundle.putString("orderInfo", orderInfo);
            }
            Intent intent = new Intent(activity, PayActivity.class);
            intent.putExtras(bundle);
            activity.startActivityForResult(intent, REQUEST_CODE_PAYMENT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void createPayment(Fragment fragment, String jsonStr) {
        createPayment(fragment.getActivity(), jsonStr);
    }
}
