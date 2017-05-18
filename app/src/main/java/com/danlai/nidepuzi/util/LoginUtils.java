package com.danlai.nidepuzi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Log;

import com.danlai.library.utils.DataClearManager;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.entity.UserAccountBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by itxuye(www.itxuye.com) on 15/12/29.
 * <p>
 * Copyright 2015年 上海己美. All rights reserved.
 */
public class LoginUtils {
    private static String TAG = "LoginUtils";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences sharedPreferences1;
    private static SharedPreferences sharedPreferences2;
    private static SharedPreferences sharedPreferences3;
    private static SharedPreferences sharedPreferences4;
    private static SharedPreferences sharedPreferences5;
    private static SharedPreferences.Editor editor;
    private static SharedPreferences.Editor editor1;
    private static SharedPreferences.Editor editor2;
    private static SharedPreferences.Editor editor3;
    private static SharedPreferences.Editor editor4;
    private static SharedPreferences.Editor editor5;

    public static void saveLoginInfo(boolean isSuccess, BaseActivity context, String username,
                                     String password) {
        sharedPreferences = context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("name", username);
        editor.putString("password", password);
        editor.putBoolean("success", isSuccess);
        editor.apply();
        Log.d(TAG, "save logininfo ");
    }

    public static void saveLoginSuccess(boolean isSuccess, BaseActivity context) {
        sharedPreferences = context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean("success", isSuccess);
        editor.apply();
    }

    public static boolean getMamaInfo(Context context) {
        SharedPreferences shared = context.getSharedPreferences("mama_info", Context.MODE_PRIVATE);
        return shared.getBoolean("success", false);
    }

    public static void delLoginInfo(Context context) {
        sharedPreferences = context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
        sharedPreferences1 = context.getSharedPreferences("CookiesAxiba", Context.MODE_PRIVATE);
        sharedPreferences3 = context.getSharedPreferences("CookiePersistence", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        editor1 = sharedPreferences1.edit();
        editor1.clear();
        editor1.apply();
        editor3 = sharedPreferences3.edit();
        editor3.clear();
        editor3.apply();
    }

    //获取用户信息
    public static String[] getLoginInfo(Context context) {

        sharedPreferences = context.getSharedPreferences("login_info", Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        boolean success = sharedPreferences.getBoolean("success", false);

        return new String[]{username, password, success + ""};
    }

    //获取用户登录状态
    public static boolean checkLoginState(Context context) {
        sharedPreferences = context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("success", false);
    }

    public static void registerMiPush(BaseActivity mActivity) {
        String android_id = Settings.Secure.getString(mActivity.getContentResolver(), Settings.Secure.ANDROID_ID);
        String mRegId = MiPushClient.getRegId(mActivity);
        BaseApp.getUserInteractor(mActivity)
            .getUserAccount("android", mRegId, android_id, new ServiceResponse<UserAccountBean>(mActivity) {

                @Override
                public void onNext(UserAccountBean user) {
                    MiPushClient.setUserAccount(mActivity, user.getUserAccount(), null);
                }
            });
    }


    public static void clearCacheEveryWeek(Context context) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int i = calendar.get(Calendar.WEEK_OF_YEAR);
        SharedPreferences preferences = context.getSharedPreferences("clear_cache", Context.MODE_PRIVATE);
        int flag = preferences.getInt("flag", -1);
        if (i != flag) {
            SharedPreferences.Editor edit = preferences.edit();
            edit.putInt("flag", i);
            edit.apply();
            DataClearManager.cleanApplicationData(BaseApp.getInstance());
        }
    }
}
