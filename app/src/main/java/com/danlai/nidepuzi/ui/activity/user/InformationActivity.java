package com.danlai.nidepuzi.ui.activity.user;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityInformationBinding;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.event.UserInfoEvent;
import com.danlai.nidepuzi.service.ServiceResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author wisdom
 * @date 2017年04月28日 上午11:20
 */

public class InformationActivity extends BaseMVVMActivity<ActivityInformationBinding>
    implements View.OnClickListener {
    private int userId;
    private String nick;
    private int sex;
    private String birthday;
    private String province;
    private String city;
    private String district;

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        BaseApp.getMainInteractor(mBaseActivity)
            .getProfile(new ServiceResponse<UserInfoBean>(mBaseActivity) {
                @Override
                public void onNext(UserInfoBean userInfoBean) {
                    userId = userInfoBean.getId();
                    nick = userInfoBean.getNick();
                    sex = userInfoBean.getSex();
                    birthday = userInfoBean.getBirthday_display();
                    province = userInfoBean.getProvince();
                    city = userInfoBean.getCity();
                    district = userInfoBean.getDistrict();
                    Glide.with(mBaseActivity).load(userInfoBean.getThumbnail()).into(b.headLayoutImg);
                    Glide.with(mBaseActivity).load(userInfoBean.getThumbnail()).into(b.codeLayoutImg);
                    b.nickLayout.setSummary(userInfoBean.getNick());
                    b.shopLayout.setSummary(userInfoBean.getNick() + "的铺子");
                    b.birthdayLayout.setSummary(userInfoBean.getBirthday_display());
                    if (userInfoBean.getSex() == 2) {
                        b.sexLayout.setSummary("女");
                    } else if (userInfoBean.getSex() == 1) {
                        b.sexLayout.setSummary("男");
                    } else {
                        b.sexLayout.setSummary("未知");
                    }
                    b.addressLayout.setSummary(userInfoBean.getProvince() + "-" + userInfoBean.getCity());
                }

                @Override
                public void onError(Throwable e) {
                    JUtils.Toast("信息获取失败!");
                }
            });
    }

    @Override
    protected void setListener() {
        b.nickLayout.setOnClickListener(this);
        b.birthdayLayout.setOnClickListener(this);
        b.sexLayout.setOnClickListener(this);
        b.addressLayout.setOnClickListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshInfo(UserInfoEvent event) {
        initData();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_information;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.birthday_layout:
            case R.id.nick_layout:
            case R.id.sex_layout:
            case R.id.address_layout:
                Bundle bundle = new Bundle();
                bundle.putInt("id", userId);
                bundle.putString("nick", nick);
                bundle.putInt("sex", sex);
                bundle.putString("birthday", birthday);
                bundle.putString("province", province);
                bundle.putString("city", city);
                bundle.putString("district", district);
                readyGo(SetInfoActivity.class, bundle);
                break;
        }
    }
}
