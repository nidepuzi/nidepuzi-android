package com.danlai.nidepuzi.htmlJsBridge;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.BuildConfig;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.entity.CallNativeFuncBean;
import com.danlai.nidepuzi.entity.JumpBean;
import com.danlai.nidepuzi.entity.PayInfoBean;
import com.danlai.nidepuzi.ui.activity.user.LoginActivity;
import com.danlai.nidepuzi.util.JumpUtils;
import com.danlai.nidepuzi.util.pay.PayUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by itxuye(www.itxuye.com) on 2016/02/16.
 * <p>
 * Copyright 2016年 上海己美. All rights reserved.
 */
public class AndroidJsBridge {

    private static final String TAG = "AndroidJsBridge";
    private BaseActivity mContext;

    public AndroidJsBridge(BaseActivity context) {
        this.mContext = context;
    }

    @Deprecated
    @JavascriptInterface
    public void callNativeShareFunc(String uform, String activity_id) {
        JUtils.Log("BaseWebViewActivity", uform + "======activity_id =" + activity_id);
    }

    @JavascriptInterface
    public void jumpToNativeLocation(String json) {
        Gson gson = new Gson();
        JumpBean jumpBean = gson.fromJson(json, new TypeToken<JumpBean>() {
        }.getType());
        String url = jumpBean.getTarget_url();
        JUtils.Log("BaseWebViewActivity", url);
        JumpUtils.push_jump_proc(mContext, url);
        JUtils.Log(url);
    }

    @JavascriptInterface
    public String getNativeMobileSNCode() {
        try {
            return Settings.Secure.getString(BaseApp.getInstance().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @JavascriptInterface
    public void showLoading(String isShow) {
        if (isShow.contains("true")) {
            mContext.showIndeterminateProgressDialog(false);
        } else if (isShow.contains("false")) {
            mContext.hideIndeterminateProgressDialog();
        }
    }

    @JavascriptInterface
    public void callNativeLoginActivity(String pageUrl) {
        JUtils.Log(TAG, "pageUrl" + pageUrl);
        Intent intent = new Intent(mContext, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("login", "h5");
        bundle.putString("actlink", pageUrl);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
        mContext.finish();
    }

    @JavascriptInterface
    public void jumpToNativeLogin(String json) {
        Gson gson = new Gson();
        JumpBean jumpBean = gson.fromJson(json, new TypeToken<JumpBean>() {
        }.getType());
        String url = jumpBean.getTarget_url();
        Intent intent = new Intent(mContext, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("login", "h5");
        bundle.putString("actlink", url);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
        mContext.finish();
    }

    @JavascriptInterface
    public void jumpToNativeLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
        mContext.finish();
    }

    @JavascriptInterface
    public void callNativeUniShareFunc(String json) {
        JUtils.Log(TAG, "callNativeUniShareFunc====" + json);
        if (!TextUtils.isEmpty(json)) {
            Gson gson = new Gson();
            CallNativeFuncBean callNativeFuncBean = gson.fromJson(json, CallNativeFuncBean.class);
            if (null != callNativeFuncBean) {
                String shareTo = callNativeFuncBean.getShareTo();
                if (!TextUtils.isEmpty(shareTo)) {
                    switch (shareTo) {
                        case "wxapp":
                            shareToWx(callNativeFuncBean);
                            break;
                        case "pyq":
                            shareToPyq(callNativeFuncBean);
                            break;
                        case "qq":
                            shareToQq(callNativeFuncBean);
                            break;
                        case "qqspa":
                            shareToQQspa(callNativeFuncBean);
                            break;
                        case "sinawb":
                            shareToSina(callNativeFuncBean);
                            break;
                        case "web":
                            JUtils.copyToClipboard(callNativeFuncBean.getLink());
                            JUtils.Toast("文字已经复制");
                            break;
                    }
                } else {
                    share(callNativeFuncBean.getShareTitle(), callNativeFuncBean.getLink(),
                        callNativeFuncBean.getShareDesc(), callNativeFuncBean.getShareIcon());
                }
            }
        }
    }

    @JavascriptInterface
    public void callNativePurchase(String charge) {
        try {
            PayInfoBean payInfoBean = new Gson().fromJson(charge, PayInfoBean.class);
            PayUtils.createPayment(mContext, new Gson().toJson(payInfoBean.getCharge()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void shareToSina(CallNativeFuncBean callNativeFuncBean) {
        SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
        sp.setText(callNativeFuncBean.getShareDesc());
        sp.setImageUrl(callNativeFuncBean.getShareIcon());
        Platform weibo = ShareSDK.getPlatform(mContext, SinaWeibo.NAME);
        weibo.setPlatformActionListener((PlatformActionListener) mContext);
        weibo.share(sp);
    }

    private void shareToQQspa(CallNativeFuncBean callNativeFuncBean) {
        QZone.ShareParams sp = new QZone.ShareParams();
        sp.setTitle(callNativeFuncBean.getShareTitle());
        sp.setTitleUrl(callNativeFuncBean.getLink());
        sp.setText(callNativeFuncBean.getShareDesc());
        sp.setImageUrl(callNativeFuncBean.getShareIcon());
        sp.setSiteUrl(callNativeFuncBean.getLink());
        Platform qzone = ShareSDK.getPlatform(mContext, QZone.NAME);
        qzone.share(sp);
    }

    private void shareToQq(CallNativeFuncBean callNativeFuncBean) {
        QQ.ShareParams sp = new QQ.ShareParams();
        sp.setTitle(callNativeFuncBean.getShareTitle());
        sp.setText(callNativeFuncBean.getShareDesc());
        sp.setImageUrl(callNativeFuncBean.getShareIcon());
        sp.setTitleUrl(callNativeFuncBean.getLink());
        Platform qq = ShareSDK.getPlatform(mContext, QQ.NAME);
        qq.share(sp);
    }

    private void shareToPyq(CallNativeFuncBean callNativeFuncBean) {
        WechatMoments.ShareParams sp = new WechatMoments.ShareParams();
        sp.setTitle(callNativeFuncBean.getShareTitle());
        sp.setImageUrl(callNativeFuncBean.getShareIcon());
        sp.setShareType(Platform.SHARE_WEBPAGE);
        Platform pyq = ShareSDK.getPlatform(mContext, WechatMoments.NAME);
        pyq.share(sp);
    }

    private void shareToWx(CallNativeFuncBean callNativeFuncBean) {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitle(callNativeFuncBean.getShareTitle());
        sp.setText(callNativeFuncBean.getShareDesc());
        sp.setUrl(callNativeFuncBean.getLink());
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setImageUrl(callNativeFuncBean.getShareIcon());
        Platform wx = ShareSDK.getPlatform(mContext, Wechat.NAME);
        wx.share(sp);
    }

    protected void share(String title, String sharelink, String desc, String shareimg) {
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setTitleUrl(sharelink);
        oks.setText(desc);
        oks.setImageUrl(shareimg);
        oks.setUrl(sharelink);
        Bitmap enableLogo =
            BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ssdk_oks_logo_copy);
        View.OnClickListener listener = v -> {
            JUtils.copyToClipboard(sharelink);
            JUtils.Toast("文字已经复制");
        };
        oks.setCustomerLogo(enableLogo, "复制链接", listener);
        oks.setShareContentCustomizeCallback(new ShareContentCustom(desc));
        oks.show(mContext);
    }

    @JavascriptInterface
    public String appVersion() {
        return String.valueOf(BuildConfig.VERSION_CODE);
    }

    private class ShareContentCustom implements ShareContentCustomizeCallback {

        private String text;

        ShareContentCustom(String text) {
            this.text = text;
        }

        @Override
        public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
            if (WechatMoments.NAME.equals(platform.getName())) {
                paramsToShare.setTitle(text);
            }
        }
    }
}
