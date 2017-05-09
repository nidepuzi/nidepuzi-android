package com.danlai.nidepuzi.service;

import com.danlai.nidepuzi.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wisdom
 * @date 2017年05月08日 下午4:01
 */

public class WxRetrofitClient {
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor).addNetworkInterceptor(new StethoInterceptor());
        }
        mOkHttpClient = builder.build();
    }

    public static Retrofit createAdapter() {
        return new Retrofit.Builder().baseUrl("https://api.weixin.qq.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(mOkHttpClient)
            .build();
    }
}
