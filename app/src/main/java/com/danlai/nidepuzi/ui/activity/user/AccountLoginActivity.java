package com.danlai.nidepuzi.ui.activity.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityAccountLoginBinding;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.event.LoginEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.danlai.nidepuzi.util.LoginUtils;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;

import org.greenrobot.eventbus.EventBus;

/**
 * @author wisdom
 * @date 2017年05月11日 上午10:53
 */

public class AccountLoginActivity extends BaseMVVMActivity<ActivityAccountLoginBinding>
    implements View.OnClickListener, TextWatcher {

    private AlertDialog mRuleDialog;

    @Override
    protected void setListener() {
        b.tvVip.setOnClickListener(this);
        b.serviceLayout.setOnClickListener(this);
        b.tvForget.setOnClickListener(this);
        b.etPhone.addTextChangedListener(this);
        b.btnLogin.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        b.tvForget.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mRuleDialog = new AlertDialog.Builder(this)
            .setTitle("注册必读")
            .setMessage(getResources().getString(R.string.login_rule))
            .setPositiveButton("同意", (dialog, which) -> dialog.dismiss())
            .create();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_account_login;
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_vip:
                String deviceId = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                String title = "铺子客服";
                YSFUserInfo ysfUserInfo = new YSFUserInfo();
                ysfUserInfo.userId = deviceId;
                ysfUserInfo.data = "[ " +
                    "{\"key\":\"real_name\", \"value\":\"未注册新用户\"}, " +
                    "{\"key\":\"mobile_phone\", \"value\":\"12345678\"}, " +
                    "{\"key\":\"avatar\", \"value\": \"http://oon0iwvsw.bkt.clouddn.com/192_192.jpg\"}]";
                Unicorn.setUserInfo(ysfUserInfo);
                ConsultSource source = new ConsultSource("https://m.nidepuzi.com", "Android客户端", "Android客户端");
                Unicorn.openServiceActivity(mBaseActivity, title, source);
                break;
            case R.id.service_layout:
                mRuleDialog.show();
                break;
            case R.id.tv_forget:
                readyGo(VerifyPhoneForgetActivity.class);
                break;
            case R.id.btn_login:
                String phone = b.etPhone.getText().toString().trim();
                String password = b.etPassword.getText().toString().trim();
                if (checkInput(phone, password)) {
                    showIndeterminateProgressDialog(false);
                    SharedPreferences sharedPreferences = getSharedPreferences("password", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (b.cbPwd.isChecked()) {
                        editor.putString(phone, password);
                    } else {
                        editor.remove(phone);
                    }
                    editor.apply();
                    BaseApp.getUserInteractor(this)
                        .passwordLogin(phone, password, null, new ServiceResponse<CodeBean>(mBaseActivity) {
                            @Override
                            public void onNext(CodeBean codeBean) {
                                if (codeBean.getRcode() == 0) {
                                    BaseApp.getMainInteractor(mBaseActivity)
                                        .getProfile(new ServiceResponse<UserInfoBean>(mBaseActivity) {
                                            @Override
                                            public void onNext(UserInfoBean userInfoBean) {
                                                hideIndeterminateProgressDialog();
                                                UserInfoBean.XiaolummBean bean = userInfoBean.getXiaolumm();
                                                if (bean != null && "effect".equals(bean.getStatus())) {
                                                    if (TextUtils.isEmpty(userInfoBean.getMobile())) {
                                                        JUtils.Toast("绑定手机后才可以登录!");
                                                        readyGo(LoginBindPhoneActivity.class);
                                                    } else {
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
                break;
        }
    }

    private void loginFail(String msg) {
        hideIndeterminateProgressDialog();
        JUtils.Toast(msg);
        LoginUtils.delLoginInfo(mBaseActivity);
    }

    public boolean checkInput(String mobile, String password) {
        if (mobile == null || mobile.trim().trim().equals("")) {
            JUtils.Toast("请输入手机号");
        } else {
            if (mobile.length() != 11) {
                JUtils.Toast("请输入正确的手机号");
            } else if (password == null || password.trim().trim().equals("")) {
                JUtils.Toast("密码不能为空");
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String phone = s.toString();
        SharedPreferences sharedPreferences =
            getSharedPreferences("password", Context.MODE_PRIVATE);
        String password = sharedPreferences.getString(phone, "");
        if (!password.equals("")) {
            b.etPassword.setText(password);
            b.cbPwd.setChecked(true);
        } else {
            b.etPassword.setText("");
            b.cbPwd.setChecked(false);
        }
    }
}
