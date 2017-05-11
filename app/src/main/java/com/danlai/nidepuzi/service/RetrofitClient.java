package com.danlai.nidepuzi.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;

import com.danlai.library.utils.NetUtil;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.BuildConfig;
import com.danlai.nidepuzi.base.BaseApi;
import com.danlai.nidepuzi.service.okhttp3.PersistentCookieJar;
import com.danlai.nidepuzi.service.okhttp3.cache.SetCookieCache;
import com.danlai.nidepuzi.service.okhttp3.persistence.SharedPrefsCookiePersistor;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wisdom on 16/11/23.
 */

public class RetrofitClient {

    private static OkHttpClient mOkHttpClient;
    private static OkHttpClient.Builder builder;

    static {
        builder = initOkHttpClient();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor).addNetworkInterceptor(new StethoInterceptor());
        }
        mOkHttpClient = builder.build();
    }

    public static Retrofit createAdapter() {
        SharedPreferences sharedPreferences = BaseApp.getInstance().getSharedPreferences("APICLIENT", Context.MODE_PRIVATE);
        String baseUrl;
        if (!TextUtils.isEmpty(sharedPreferences.getString("BASE_URL", ""))) {
            baseUrl = "https://" + sharedPreferences.getString("BASE_URL", "");
        } else {
            baseUrl = BaseApi.APP_BASE_URL;
        }
        return new Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(mOkHttpClient)
            .build();
    }

    /**
     * 初始化OKHttpClient
     */
    private static OkHttpClient.Builder initOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitClient.class) {
                if (mOkHttpClient == null) {
                    //设置Http缓存
                    Cache cache = new Cache(new File(BaseApp.getInstance().getCacheDir(), "OkHttpCache"),
                        1024 * 1024 * 100);
                    builder = new OkHttpClient.Builder()
                        .readTimeout(20, TimeUnit.SECONDS)
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .cache(cache)
                        .addInterceptor(chain -> {
                            Request request = chain.request();
//                            if (JUtils.isNetWorkAvilable()) {
                            request = request.newBuilder()
                                .header("User-Agent", "Android/" + Build.VERSION.RELEASE + " App/"
                                    + String.valueOf(BuildConfig.VERSION_CODE) + " Mobile/"
                                    + Build.MODEL + " NetType/" + NetUtil.getNetType(BaseApp.getInstance()))
                                .build();
//                            } else {
//                                request = request.newBuilder()
//                                    .cacheControl(CacheControl.FORCE_CACHE)
//                                    .header("User-Agent", "Android/" + Build.VERSION.RELEASE + " App/"
//                                        + String.valueOf(BuildConfig.VERSION_CODE) + " Mobile/"
//                                        + Build.MODEL + " NetType/" + NetUtil.getNetType(BaseApp.getInstance()))
//                                    .build();
//                            }
//                            okhttp3.Response response = chain.proceed(request);
//                            if (JUtils.isNetWorkAvilable()) {
//                                int maxAge = 60 * 60;
//                                response.newBuilder()
//                                    .removeHeader("Pragma")
//                                    .header("Cache-Control", "public, max-age=" + maxAge)
//                                    .build();
//                            } else {
//                                int maxStale = 60 * 60 * 24 * 28;
//                                response.newBuilder()
//                                    .removeHeader("Pragma")
//                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                                    .build();
//                            }
                            return chain.proceed(request);
                        })
                        .cookieJar(new PersistentCookieJar(new SetCookieCache(),
                            new SharedPrefsCookiePersistor(BaseApp.getInstance())));
                }
            }
        }
        return builder;
    }

}
