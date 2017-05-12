package com.danlai.nidepuzi.ui.activity.user;

import android.graphics.Color;
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

    @Override
    protected void setListener() {
        b.next.setOnClickListener(this);
        b.getCheckCode.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        BaseApp.getUserInteractor(this)
            .getUserInfo(new ServiceResponse<UserInfoBean>(mBaseActivity) {
                @Override
                public void onNext(UserInfoBean userInfoBean) {
                    if (userInfoBean != null) {
                        if (userInfoBean.getThumbnail() != null && !"".equals(userInfoBean.getThumbnail())) {
                            ViewUtils.loadImgToImgView(mBaseActivity, b.headImage, userInfoBean.getThumbnail());
                            b.tvNickName.setText("微信账号： " + userInfoBean.getNick());
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
                mobile = b.registerName.getText().toString().trim();
                String invalid_code = b.checkCode.getText().toString().trim();
                if (checkInput(mobile, invalid_code)) {
                    bindMobilePhone(mobile, invalid_code);
                }
                break;
            case R.id.get_check_code:
                mobile = b.registerName.getText().toString().trim();
                if (checkMobileInput(mobile)) {
                    RxCountDown.countdown(60).doOnSubscribe(disposable -> {
                        addDisposable(disposable);
                        b.getCheckCode.setClickable(false);
                        b.getCheckCode.setBackgroundColor(Color.parseColor("#f3f3f4"));
                        BaseApp.getUserInteractor(mBaseActivity)
                            .getCodeBean(mobile, "bind", new ServiceResponse<CodeBean>(mBaseActivity) {
                                @Override
                                public void onNext(CodeBean codeBean) {
                                    JUtils.Toast(codeBean.getMsg());
                                    b.registerName.setFocusable(false);
                                    b.checkCode.setFocusable(true);
                                }
                            });
                    }).subscribe(new ServiceResponse<Integer>(mBaseActivity) {
                        @Override
                        public void onComplete() {
                            b.getCheckCode.setText("获取验证码");
                            b.getCheckCode.setClickable(true);
                        }

                        @Override
                        public void onNext(Integer integer) {
                            b.getCheckCode.setText(integer + "S");
                        }
                    });
                }
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

    public boolean checkInput(String mobile, String code) {
        if (checkMobileInput(mobile)) {
            if (code == null || code.trim().equals("")) {
                JUtils.Toast("验证码不能为空");
            } else {
                return true;
            }
        }
        return false;
    }

    private void bindMobilePhone(String username, String valid_code) {
        BaseApp.getUserInteractor(mBaseActivity)
            .verifyCode(username, "bind", valid_code, new ServiceResponse<CodeBean>(mBaseActivity) {
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
