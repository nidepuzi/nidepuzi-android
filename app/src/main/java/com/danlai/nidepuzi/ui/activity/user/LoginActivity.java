package com.danlai.nidepuzi.ui.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.SHA1Utils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseAppManager;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.entity.AccessToken;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.WxUserInfoBean;
import com.danlai.nidepuzi.entity.event.LoginEvent;
import com.danlai.nidepuzi.entity.event.WxLoginEvent;
import com.danlai.nidepuzi.module.WxApiModule;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.danlai.nidepuzi.util.LoginUtils;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Random;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    public static final String SECRET = "a894a72567440fa7317843d76dd7bf03";
    private String timestamp;
    private String noncestr;
    private String sign_params;
    private String sign;
    private String headimgurl;
    private String nickname;
    private String openid;
    private String unionid;
    private long firstTime = 0;

    @Override
    public boolean isAllWindow() {
        return true;
    }

    @Override
    protected void setListener() {
        findViewById(R.id.wx_login).setOnClickListener(this);
        findViewById(R.id.phone_login).setOnClickListener(this);
        findViewById(R.id.account_login).setOnClickListener(this);
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
        EventBus.getDefault().register(this);
        setSwipeBackEnable(false);
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
    protected int getContentViewLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wx_login:
                showIndeterminateProgressDialog(false);
                sha1();
                sign = SHA1Utils.hex_sha1(sign_params);
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "nidepuzi";
                IWXAPI api = WXAPIFactory.createWXAPI(this, BaseConst.WX_APP_ID);
                if (isWXAppInstalledAndSupported(api)) {
                    api.sendReq(req);
                } else if (!api.isWXAppInstalled()) {
                    JUtils.Toast("未安装客户端");
                    hideIndeterminateProgressDialog();
                } else if (!api.isWXAppSupportAPI()) {
                    JUtils.Toast("暂不支持登录");
                    hideIndeterminateProgressDialog();
                } else {
                    JUtils.Toast("未知异常");
                    hideIndeterminateProgressDialog();
                }
                break;
            case R.id.phone_login:
                readyGo(PhoneLoginActivity.class);
                break;
            case R.id.account_login:
                readyGo(AccountLoginActivity.class);
                break;
        }
    }

    private void sha1() {
        timestamp = System.currentTimeMillis() / 1000 + "";//时间戳
        noncestr = getRandomString(8);//随机八位字符串
        sign_params = "noncestr=" + noncestr + "&secret=" + SECRET + "&timestamp=" + timestamp;
    }

    private boolean isWXAppInstalledAndSupported(IWXAPI api) {
        return api.isWXAppInstalled() && api.isWXAppSupportAPI();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshAddress(WxLoginEvent event) {
        switch (event.getErrCode()) {
            case BaseResp.ErrCode.ERR_OK:
                wxLogin(event.getCode());
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                JUtils.Toast("已拒绝授权");
                hideIndeterminateProgressDialog();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                JUtils.Toast("已取消登录");
                hideIndeterminateProgressDialog();
                break;
        }
    }


    private void wxLogin(String code) {
        showIndeterminateProgressDialog(false);
        WxApiModule.newInstance()
            .getAccessToken(code, new ServiceResponse<AccessToken>(mBaseActivity) {
                @Override
                public void onNext(AccessToken accessToken) {
                    openid = accessToken.getOpenid();
                    unionid = accessToken.getUnionid();
                    String token = accessToken.getAccess_token();
                    WxApiModule.newInstance()
                        .getUserInfo(token, openid, new ServiceResponse<WxUserInfoBean>(mBaseActivity) {
                            @Override
                            public void onNext(WxUserInfoBean bean) {
                                headimgurl = bean.getHeadimgurl();
                                nickname = bean.getNickname();
                                BaseApp.getUserInteractor(LoginActivity.this)
                                    .wxappLogin(noncestr, timestamp, sign, headimgurl, nickname, openid, unionid,
                                        new ServiceResponse<CodeBean>(mBaseActivity) {
                                            @Override
                                            public void onNext(CodeBean codeBean) {
                                                if (0 == codeBean.getRcode()) {
                                                    BaseApp.getMainInteractor(mBaseActivity)
                                                        .getProfile(new ServiceResponse<UserInfoBean>(mBaseActivity) {
                                                            @Override
                                                            public void onNext(UserInfoBean userInfoBean) {
                                                                hideIndeterminateProgressDialog();
                                                                UserInfoBean.XiaolummBean bean = userInfoBean.getXiaolumm();
                                                                if (bean != null && "effect".equals(bean.getStatus())) {
                                                                    if (TextUtils.isEmpty(userInfoBean.getMobile())){
                                                                        JUtils.Toast("绑定手机后才可以登录!");
                                                                        readyGo(LoginBindPhoneActivity.class);
                                                                    }else {
                                                                        LoginUtils.saveLoginSuccess(true, mBaseActivity);
                                                                        EventBus.getDefault().post(new LoginEvent());
                                                                        JUtils.Toast("登录成功!");
                                                                        readyGoThenKill(TabActivity.class);
                                                                    }
                                                                } else {
                                                                    JUtils.Toast("您不是会员暂时无法登录!");
                                                                }
                                                            }

                                                            @Override
                                                            public void onError(Throwable e) {
                                                                loginFail("登录失败,请重试!");
                                                            }
                                                        });
                                                } else {
                                                    loginFail(codeBean.getMsg());
                                                }
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                loginFail("登录失败,请重试!");
                                            }
                                        });
                            }

                            @Override
                            public void onError(Throwable e) {
                                loginFail("获取用户信息失败,请重试!");
                            }
                        });

                }

                @Override
                public void onError(Throwable e) {
                    hideIndeterminateProgressDialog();
                    JUtils.Toast("获取授权失败!");
                }
            });
    }

    private void loginFail(String msg) {
        hideIndeterminateProgressDialog();
        JUtils.Toast(msg);
        LoginUtils.delLoginInfo(mBaseActivity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

