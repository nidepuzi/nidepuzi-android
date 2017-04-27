package com.danlai.nidepuzi.ui.activity.user;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.SHA1Utils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseAppManager;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.danlai.nidepuzi.util.LoginUtils;
import com.mob.tools.utils.UIHandler;

import java.util.HashMap;
import java.util.Random;

import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends BaseActivity
    implements View.OnClickListener, Handler.Callback {
    public static final String SECRET = "a894a72567440fa7317843d76dd7bf03";
    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_LOGIN = 2;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR = 4;
    private static final int MSG_AUTH_COMPLETE = 5;
    private String timestamp;
    private String noncestr;
    private String sign_params;
    private String sign;
    private String headimgurl;
    private String nickname;
    private String openid;
    private String unionid;
    private Wechat wechat;
    private long firstTime = 0;

    @Override
    public boolean isAllWindow() {
        return true;
    }

    @Override
    protected void setListener() {
        findViewById(R.id.wx_login).setOnClickListener(this);
        findViewById(R.id.phone_login).setOnClickListener(this);
    }

    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    @Override
    public void initContentView() {
        setContentView(getContentViewLayoutID());
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.main_in, R.anim.main_out);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.main_in, R.anim.main_out);
    }

    @Override
    protected void initViews() {
        setSwipeBackEnable(false);
    }

    @Override
    protected void initData() {
        if (!LoginUtils.checkLoginState(getApplicationContext())) {
            removeWX(wechat);
        }
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 1000) {
                firstTime = secondTime;
                JUtils.Toast("再按一次退出程序!");
            } else {
                BaseAppManager.getInstance().clear();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wx_login:
                sha1();
                sign = SHA1Utils.hex_sha1(sign_params);
                wechat = new Wechat(this);
                authorize(wechat);
                break;
            case R.id.phone_login:
                removeWX(wechat);
                readyGo(PhoneLoginActivity.class);
                break;
        }
    }

    private void sha1() {
        timestamp = System.currentTimeMillis() / 1000 + "";//时间戳
        noncestr = getRandomString(8);//随机八位字符串
        sign_params = "noncestr=" + noncestr + "&secret=" + SECRET + "&timestamp=" + timestamp;
    }

    private void login(String plat) {
        Message msg = new Message();
        msg.what = MSG_LOGIN;
        msg.obj = plat;
        UIHandler.sendMessage(msg, this);
    }

    private void authorize(Platform plat) {
        showIndeterminateProgressDialog(false);
        if (plat == null) {
            return;
        }
        if (plat.isAuthValid()) {
            String userId = plat.getDb().getUserId();
            if (!TextUtils.isEmpty(userId)) {
                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
                login(plat.getName());
                return;
            }
        }
        plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                if (i == Platform.ACTION_USER_INFOR) {
                    UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE, LoginActivity.this);
                    login(platform.getName());
                }
                headimgurl = (String) hashMap.get("headimgurl");
                nickname = (String) hashMap.get("nickname");
                openid = (String) hashMap.get("openid");
                unionid = (String) hashMap.get("unionid");
                BaseApp.getUserInteractor(LoginActivity.this)
                    .wxappLogin(noncestr, timestamp, sign, headimgurl, nickname, openid, unionid,
                        new ServiceResponse<CodeBean>(mBaseActivity) {
                            @Override
                            public void onNext(CodeBean codeBean) {
                                if (codeBean != null) {
                                    hideIndeterminateProgressDialog();
                                    JUtils.Toast(codeBean.getMsg());
                                    if (0 == codeBean.getRcode()) {
                                        LoginUtils.saveLoginSuccess(true, mBaseActivity);
                                        readyGoThenKill(TabActivity.class);
                                    } else {
                                        removeWX(wechat);
                                        LoginUtils.delLoginInfo(mBaseActivity);
                                    }
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                JUtils.Toast("登录失败");
                                hideIndeterminateProgressDialog();
                            }
                        });
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                removeWX(wechat);
                JUtils.Toast("授权登录失败!");
                hideIndeterminateProgressDialog();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                removeWX(wechat);
                JUtils.Toast("取消授权登录!");
                hideIndeterminateProgressDialog();
            }
        });
        plat.SSOSetting(true);
        plat.showUser(null);
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_USERID_FOUND:
                break;
            case MSG_LOGIN:
                break;
            case MSG_AUTH_CANCEL:
                break;
            case MSG_AUTH_ERROR:
                break;
            case MSG_AUTH_COMPLETE:
                break;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        removeWX(wechat);
    }

    public void removeWX(Platform platform) {
        if (platform != null) {
            platform.removeAccount(true);
            platform.removeAccount();
        }
    }
}

