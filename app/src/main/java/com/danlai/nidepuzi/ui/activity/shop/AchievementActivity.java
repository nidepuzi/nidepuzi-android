package com.danlai.nidepuzi.ui.activity.shop;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityAchievementBinding;
import com.danlai.nidepuzi.entity.ActivityBean;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.util.ShareUtils;

/**
 * @author wisdom
 * @date 2017年05月06日 上午9:50
 */

public class AchievementActivity extends BaseMVVMActivity<ActivityAchievementBinding>
    implements View.OnClickListener {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_achievement;
    }

    @Override
    protected void setListener() {
        b.shareWx.setOnClickListener(this);
        b.customIncome.setOnClickListener(this);
        b.orderShare.setOnClickListener(this);
        b.orderSelf.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        BaseApp.getMainInteractor(mBaseActivity)
            .getProfile(new ServiceResponse<UserInfoBean>(mBaseActivity) {
                @Override
                public void onNext(UserInfoBean userInfoBean) {
                    b.tvName.setText(userInfoBean.getNick());
                    Glide.with(mBaseActivity).load(userInfoBean.getThumbnail()).into(b.ivHead);
                }
            });
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle;
        switch (v.getId()) {
            case R.id.share_wx:
                BaseApp.getActivityInteractor(mBaseActivity)
                    .getActivityBean("8", new ServiceResponse<ActivityBean>(mBaseActivity) {
                        @Override
                        public void onNext(ActivityBean activityBean) {
                            ShareUtils.shareShop(activityBean, mBaseActivity);
                        }
                    });
                break;
            case R.id.custom_income:
                readyGo(CustomIncomeActivity.class);
                break;
            case R.id.order_share:
                bundle = new Bundle();
                bundle.putInt("type", BaseConst.ORDER_SHARE);
                readyGo(OrderAchieveActivity.class, bundle);
                break;
            case R.id.order_self:
                bundle = new Bundle();
                bundle.putInt("type", BaseConst.ALL_ORDER);
                readyGo(OrderAchieveActivity.class, bundle);
                break;
        }
    }
}
