package com.danlai.nidepuzi.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.danlai.nidepuzi.ui.activity.user.LoginActivity;

/**
 * @author wisdom
 * @date 2016年04月25日 上午10:46
 */
public class JumpUtils {

    public static final String TAG = "JumpUtils";

    public static void push_jump_proc(Context context, String recvContent) {
        if (TextUtils.isEmpty(recvContent)) return;
        if (LoginUtils.checkLoginState(context)) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(recvContent)));
        } else {
            context.startActivity(new Intent(context, LoginActivity.class));
        }
    }

    public static void jumpWithNewTask(Context context, String recvContent) {
        if (TextUtils.isEmpty(recvContent)) return;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recvContent));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void jumpToWebViewWithCookies(Context context, String actlink, int id,
                                                Class<?> classname) {
        Bundle bundle = new Bundle();
        setBundleWithStart(context, bundle, classname, id, actlink);
    }

    public static void jumpToWebViewWithCookies(Context context, String actlink, int id,
                                                Class<?> classname, String title) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        setBundleWithStart(context, bundle, classname, id, actlink);
    }

    public static void jumpToWebViewWithCookies(Context context, String actlink, int id,
                                                Class<?> classname, String title, boolean share) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putBoolean("share", share);
        setBundleWithStart(context, bundle, classname, id, actlink);
    }


    public static void jumpToWebViewWithCookies(Context context, String actlink, int id,
                                                Class<?> classname, boolean share, boolean hide) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("share", share);
        bundle.putBoolean("hide", hide);
        setBundleWithStart(context, bundle, classname, id, actlink);
    }

    private static void setBundleWithStart(Context context, Bundle bundle, Class<?> classname,
                                           int id, String actlink) {
        Intent intent = new Intent(context, classname);
        SharedPreferences sharedPreferences =
            context.getSharedPreferences("CookiesAxiba", Context.MODE_PRIVATE);
        String cookies = sharedPreferences.getString("cookiesString", "");
        String domain = sharedPreferences.getString("cookiesDomain", "");
        String sessionId = sharedPreferences.getString("Cookie", "");
        bundle.putString("cookies", cookies);
        bundle.putString("domain", domain);
        bundle.putString("Cookie", sessionId);
        bundle.putString("actlink", actlink);
        bundle.putInt("id", id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
