package com.danlai.nidepuzi.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.danlai.nidepuzi.BaseApp;

/**
 * @author wisdom
 * @date 2017年04月20日 下午3:22
 */

public class BaseApi {
    public static final String APP_BASE_URL = "http://m.nidepuzi.com";
    public static final String QINIU_UPLOAD_URL_BASE =
        "http://7xkyoy.com2.z0.glb.qiniucdn.com/";

    public static String getAppUrl() {
        SharedPreferences preferences = BaseApp.getInstance().getSharedPreferences("APICLIENT", Context.MODE_PRIVATE);
        if (!TextUtils.isEmpty(preferences.getString("BASE_URL", ""))) {
            return "http://" + preferences.getString("BASE_URL", "");
        }
        return BaseApi.APP_BASE_URL;
    }
}
