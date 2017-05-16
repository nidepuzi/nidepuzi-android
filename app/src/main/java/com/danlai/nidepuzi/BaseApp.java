package com.danlai.nidepuzi;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseUnicornImageLoader;
import com.danlai.nidepuzi.module.ActivityInteractor;
import com.danlai.nidepuzi.module.AddressInteractor;
import com.danlai.nidepuzi.module.CartsInteractor;
import com.danlai.nidepuzi.module.MainInteractor;
import com.danlai.nidepuzi.module.ProductInteractor;
import com.danlai.nidepuzi.module.TradeInteractor;
import com.danlai.nidepuzi.module.UserInteractor;
import com.danlai.nidepuzi.module.VipInteractor;
import com.facebook.stetho.Stetho;
import com.qiyukf.unicorn.api.SavePowerConfig;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFOptions;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

import cn.sharesdk.framework.ShareSDK;

/**
 * @author wisdom
 * @date 2017年04月20日 上午11:44
 */

public class BaseApp extends MultiDexApplication {
    private static Context mContext;
    private AppComponent component;
    private boolean isShow;

    public static Context getInstance() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(this);
        isShow = false;
//        Thread.setDefaultUncaughtExceptionHandler(new MyUnCaughtExceptionHandler());
        mContext = getApplicationContext();
        Stetho.initializeWithDefaults(this);
        JUtils.initialize(this);
        Unicorn.init(this, "6df3367932bd8e384f359611ea48e90b", options(), new BaseUnicornImageLoader(getInstance()));
        JUtils.setDebug(BuildConfig.DEBUG, "nidepuzi");
        component = DaggerAppComponent.builder().appModule(new AppModule()).build();
        //初始化push推送服务
        if (shouldInit()) {
            MiPushClient.registerPush(this, BaseConst.MIPUSH_ID, BaseConst.MIPUSH_KEY);
        }
        //打开Log
        LoggerInterface newLogger = new LoggerInterface() {

            @Override
            public void setTag(String tag) {

            }

            @Override
            public void log(String content, Throwable t) {
                JUtils.Log(content);
                JUtils.Log(t.getMessage());
            }

            @Override
            public void log(String content) {
                JUtils.Log(content);
            }
        };
        Logger.setLogger(this, newLogger);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private YSFOptions options() {
        YSFOptions options = new YSFOptions();
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        options.savePowerConfig = new SavePowerConfig();
        return options;
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    //异常退出的时候,自动重启
    class MyUnCaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            ex.printStackTrace();
            Intent intent = new Intent(BaseApp.this, TabActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApp.this.startActivity(intent);
            Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    private AppComponent component() {
        return component;
    }

    private static BaseApp get(Context context) {
        return (BaseApp) context.getApplicationContext();
    }

    public static ActivityInteractor getActivityInteractor(Context context) {
        return BaseApp.get(context).component().getActivityInteractor();
    }

    public static MainInteractor getMainInteractor(Context context) {
        return BaseApp.get(context).component().getMainInteractor();
    }

    public static ProductInteractor getProductInteractor(Context context) {
        return BaseApp.get(context).component().getProductInteractor();
    }

    public static AddressInteractor getAddressInteractor(Context context) {
        return BaseApp.get(context).component().getAddressInteractor();
    }

    public static CartsInteractor getCartsInteractor(Context context) {
        return BaseApp.get(context).component().getCartsInteractor();
    }

    public static UserInteractor getUserInteractor(Context context) {
        return BaseApp.get(context).component().getUserInteractor();
    }

    public static VipInteractor getVipInteractor(Context context) {
        return BaseApp.get(context).component().getVipInteractor();
    }

    public static TradeInteractor getTradeInteractor(Context context) {
        return BaseApp.get(context).component().getTradeInteractor();
    }
}
