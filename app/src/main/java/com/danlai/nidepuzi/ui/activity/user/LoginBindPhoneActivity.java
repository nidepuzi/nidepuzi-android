package com.danlai.nidepuzi.ui.activity.user;

import android.support.v7.app.AlertDialog;
import android.view.View;

import com.danlai.library.rx.RxCountDown;
import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityLoginBindPhoneBinding;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.event.LoginEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.danlai.nidepuzi.util.LoginUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * @author wisdom-sun
 * @date 2017年05月08日 上午10:04
 */
public class LoginBindPhoneActivity extends BaseMVVMActivity<ActivityLoginBindPhoneBinding>
    implements View.OnClickListener {

    private String mobile;
    private AlertDialog mRuleDialog;

    @Override
    protected void setListener() {
        b.next.setOnClickListener(this);
        b.tvCode.setOnClickListener(this);
        b.serviceLayout.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        mRuleDialog = new AlertDialog.Builder(this)
            .setTitle("注册必读")
            .setMessage(getResources().getString(R.string.login_rule))
            .setPositiveButton("同意", (dialog, which) -> dialog.dismiss())
            .create();
    }

    @Override
    protected void initData() {
        BaseApp.getMainInteractor(this)
            .getProfile(new ServiceResponse<UserInfoBean>(mBaseActivity) {
                @Override
                public void onNext(UserInfoBean userInfoBean) {
                    if (userInfoBean != null) {
                        if (userInfoBean.getThumbnail() != null && !"".equals(userInfoBean.getThumbnail())) {
                            ViewUtils.loadImgToImgView(mBaseActivity, b.headImage, userInfoBean.getThumbnail());
                            b.tvNickName.setText("微信号： " + userInfoBean.getNick());
                        } else {
                            b.headImage.setVisibility(View.GONE);
                            b.tvNickName.setVisibility(View.GONE);
                        }
                    }
                }
            });
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_login_bind_phone;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                mobile = b.etPhone.getText().toString().trim();
                String invalid_code = b.etCode.getText().toString().trim();
                String name = b.etName.getText().toString().trim();
                if (checkInput(mobile, invalid_code, name)) {
                    bindMobilePhone(mobile, invalid_code, name);
                }
                break;
            case R.id.tv_code:
                mobile = b.etPhone.getText().toString().trim();
                if (checkMobileInput(mobile)) {
                    RxCountDown.countdown(60).doOnSubscribe(disposable -> {
                        addDisposable(disposable);
                        b.tvCode.setClickable(false);
                        BaseApp.getUserInteractor(mBaseActivity)
                            .getCodeBean(mobile, "bind", new ServiceResponse<CodeBean>(mBaseActivity) {
                                @Override
                                public void onNext(CodeBean codeBean) {
                                    JUtils.Toast(codeBean.getMsg());
                                    b.etPhone.setFocusable(false);
                                    b.etCode.setFocusable(true);
                                }
                            });
                    }).subscribe(new ServiceResponse<Integer>(mBaseActivity) {
                        @Override
                        public void onComplete() {
                            b.tvCode.setText("获取验证码");
                            b.tvCode.setClickable(true);
                        }

                        @Override
                        public void onNext(Integer integer) {
                            b.tvCode.setText(integer + "S");
                        }
                    });
                }
                break;
            case R.id.service_layout:
                mRuleDialog.show();
                break;
        }
    }

    public boolean checkMobileInput(String mobile) {
        if (mobile == null || mobile.trim().equals("")) {
            JUtils.Toast("请输入手机号");
        } else {
            if (mobile.length() != 11) {
                JUtils.Toast("请输入正确的手机号");
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean checkInput(String mobile, String code, String name) {
        if (checkMobileInput(mobile)) {
            if (code == null || code.trim().equals("")) {
                JUtils.Toast("验证码不能为空");
            } else {
                if (name.length() < 2) {
                    JUtils.Toast("请填写掌柜姓名");
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private void bindMobilePhone(String username, String valid_code, String name) {
        BaseApp.getUserInteractor(mBaseActivity)
            .verifyCode(username, "bind", valid_code, name, new ServiceResponse<CodeBean>(mBaseActivity) {
                @Override
                public void onNext(CodeBean codeBean) {
                    if (0 == codeBean.getRcode()) {
                        LoginUtils.saveLoginSuccess(true, mBaseActivity);
                        EventBus.getDefault().post(new LoginEvent());
                        JUtils.Toast("登录成功!");
                        readyGoThenKill(TabActivity.class);
                    } else {
                        JUtils.Toast(codeBean.getMsg());
                    }
                }
            });
    }
}
