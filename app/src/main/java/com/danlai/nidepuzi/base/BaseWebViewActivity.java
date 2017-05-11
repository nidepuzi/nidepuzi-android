package com.danlai.nidepuzi.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.HttpAuthHandler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.BuildConfig;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.databinding.ActivityBaseWebViewBinding;
import com.danlai.nidepuzi.entity.ActivityBean;
import com.danlai.nidepuzi.htmlJsBridge.AndroidJsBridge;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.util.pay.PayUtils;

import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * @author wisdom
 * @date 2017年04月25日 下午6:11
 */

public class BaseWebViewActivity extends BaseMVVMActivity<ActivityBaseWebViewBinding>
    implements View.OnClickListener {

    private String cookies;
    private String actlink;
    private ActivityBean partyShareInfo;
    private String domain;
    private String sessionid;
    private int id;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_base_web_view;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected void setListener() {
        b.ivShare.setOnClickListener(this);
    }

    @Override
    public void getBundleExtras(Bundle extras) {
        cookies = extras.getString("cookies");
        domain = extras.getString("domain");
        sessionid = extras.getString("Cookie");
        actlink = extras.getString("actlink");
        id = extras.getInt("id");
    }

    @Override
    public void getIntentUrl(Uri uri) {
        SharedPreferences sharedPreferences = getSharedPreferences("CookiesAxiba", Context.MODE_PRIVATE);
        cookies = sharedPreferences.getString("cookiesString", "");
        domain = sharedPreferences.getString("cookiesDomain", "");
        sessionid = sharedPreferences.getString("Cookie", "");
        actlink = uri.getQueryParameter("url");
        String id = uri.getQueryParameter("activity_id");
        if (id != null) {
            this.id = Integer.valueOf(id);
        } else {
            this.id = -1;
        }
    }

    @Override
    protected void initData() {
        try {
            Map<String, String> extraHeaders = new HashMap<>();
            extraHeaders.put("Cookie", sessionid);
            b.webView.loadUrl(actlink, extraHeaders);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (id != -1) {
            getShareContent(id);
        } else {
            b.ivShare.setVisibility(View.GONE);
        }
    }

    private void getShareContent(int id) {
        BaseApp.getActivityInteractor(this)
            .getActivityBean(id + "", new ServiceResponse<ActivityBean>(mBaseActivity) {
                @Override
                public void onNext(ActivityBean activityBean) {
                    if (null != activityBean) {
                        partyShareInfo = activityBean;
                    }
                }
            });
    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initViews() {
        showIndeterminateProgressDialog(false);
        setDialogContent("页面载入中...");
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getString("title") != null) {
                b.titleView.setName(getIntent().getExtras().getString("title"));
            }
            if (getIntent().getExtras().getBoolean("hide")) {
                b.titleView.setVisibility(View.GONE);
            }
        }
        if (getIntent().getExtras() != null && getIntent().getExtras().getBoolean("share", true)) {
            b.ivShare.setVisibility(View.VISIBLE);
        } else if (getIntent().getData() != null) {
            b.ivShare.setVisibility(View.VISIBLE);
        }
        try {
            if (Build.VERSION.SDK_INT >= 19) {
                b.webView.getSettings().setLoadsImagesAutomatically(true);
            } else {
                b.webView.getSettings().setLoadsImagesAutomatically(false);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                b.webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
            String userAgentString = b.webView.getSettings().getUserAgentString();
            b.webView.getSettings().setUserAgentString(userAgentString +
                "; ndpz/" + BuildConfig.VERSION_NAME + ";");
            b.webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            b.webView.getSettings().setJavaScriptEnabled(true);
            AndroidJsBridge mAndroidJsBridge = new AndroidJsBridge(this);
            b.webView.addJavascriptInterface(mAndroidJsBridge, "AndroidBridge");
            b.webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            b.webView.getSettings().setAllowFileAccess(true);
            b.webView.getSettings().setAllowFileAccess(true);
            b.webView.getSettings().setAppCacheEnabled(true);
            b.webView.getSettings().setDomStorageEnabled(true);
            b.webView.getSettings().setDatabaseEnabled(true);
            b.webView.getSettings().setUseWideViewPort(true);
            b.webView.getSettings().setTextZoom(100);
            b.webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            b.webView.setInitialScale(100);
            b.webView.getSettings().setSupportZoom(true);
            b.webView.setDrawingCacheEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG);
            }
            b.webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    b.progressBar.setProgress(newProgress);
                    if (newProgress == 100) {
                        b.progressBar.setVisibility(View.GONE);
                        hideIndeterminateProgressDialog();
                    } else {
                        b.progressBar.setVisibility(View.VISIBLE);
                    }
                }

                public boolean onJsAlert(WebView view, String url, String message,
                                         JsResult result) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("提示")
                        .setMessage(message)
                        .setPositiveButton("确定", null);
                    builder.setOnKeyListener((dialog, keyCode, event) -> {
                        Log.v("onJsAlert", "keyCode==" + keyCode + "event=" + event);
                        return true;
                    });
                    builder.setCancelable(false);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    result.confirm();
                    return true;
                }

                public boolean onJsConfirm(WebView view, String url, String message,
                                           final JsResult result) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("提示")
                        .setMessage(message)
                        .setPositiveButton("确定", (dialog, which) -> result.confirm())
                        .setNeutralButton("取消", (dialog, which) -> result.cancel());
                    builder.setOnCancelListener(dialog -> result.cancel());
                    builder.setOnKeyListener((dialog, keyCode, event) -> {
                        Log.v("onJsConfirm", "keyCode==" + keyCode + "event=" + event);
                        return true;
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return true;
                }

                public boolean onJsPrompt(WebView view, String url, String message,
                                          String defaultValue, final JsPromptResult result) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("提示").setMessage(message);
                    final EditText et = new EditText(view.getContext());
                    et.setSingleLine();
                    et.setText(defaultValue);
                    builder.setView(et)
                        .setPositiveButton("确定", (dialog, which) -> result.confirm(et.getText().toString()))
                        .setNeutralButton("取消", (dialog, which) -> result.cancel());
                    builder.setOnKeyListener((dialog, keyCode, event) -> {
                        Log.v("onJsPrompt", "keyCode==" + keyCode + "event=" + event);
                        return true;
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return true;
                }
            });
            b.webView.setWebViewClient(new WebViewClient() {

                @Override
                public void onPageFinished(WebView view, String url) {
                    CookieSyncManager.getInstance().sync();
                    if (b.webView != null && !b.webView.getSettings().getLoadsImagesAutomatically()) {
                        b.webView.getSettings().setLoadsImagesAutomatically(true);
                    }
                }

                @Override
                public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host,
                                                      String realm) {
                    view.reload();
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, url);
                }

                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        syncCookie(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == PayUtils.REQUEST_CODE_PAYMENT) {
            if (resultCode == RESULT_OK) {
                String result = intent.getExtras().getString("pay_result");
                String errorMsg = intent.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = intent.getExtras().getString("extra_msg"); // 错误信息
                if (result != null) {
                    switch (result) {
                        case "cancel":
                            JUtils.Toast("已取消支付!");
                            break;
                        case "success":
                            JUtils.Toast("支付成功！");
                            finish();
                            break;
                        default:
                            showMsg(result, errorMsg, extraMsg);
                            break;
                    }
                }
                if (b.webView != null && b.webView.canGoBack()) {
                    b.webView.goBack();
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (b.webView.canGoBack()) {
                b.webView.goBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_share:
                sharePartyInfo();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (b.webView != null) {
            b.webView.removeAllViews();
            b.webView.destroy();
        }
        b.layout.removeView(b.webView);
    }

    public void syncCookie(Context context) {
        try {
            CookieSyncManager.createInstance(context);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeSessionCookie();// 移除
            cookieManager.removeAllCookie();
            cookieManager.setAcceptCookie(true);
            cookieManager.setCookie(domain, cookies);
            CookieSyncManager.getInstance().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sharePartyInfo() {
        if (partyShareInfo == null) {
            JUtils.Toast("页面加载未完成!");
            return;
        }
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(partyShareInfo.getTitle());
        oks.setTitleUrl(partyShareInfo.getShareLink());
        oks.setText(partyShareInfo.getActiveDec());
        oks.setImageUrl(partyShareInfo.getShareIcon());
        oks.setUrl(partyShareInfo.getShareLink());
        Bitmap enableLogo =
            BitmapFactory.decodeResource(mBaseActivity.getResources(), R.drawable.ssdk_oks_logo_copy);
        View.OnClickListener listener = v -> {
            JUtils.copyToClipboard(partyShareInfo.getShareLink() + "");
            JUtils.Toast("已复制链接");
        };
        oks.setCustomerLogo(enableLogo, "复制链接", listener);
        oks.show(this);
    }

}
