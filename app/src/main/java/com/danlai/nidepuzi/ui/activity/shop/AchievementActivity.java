package com.danlai.nidepuzi.ui.activity.shop;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityAchievementBinding;
import com.danlai.nidepuzi.entity.MamaFortune;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.user.InviteActivity;

/**
 * @author wisdom
 * @date 2017年05月06日 上午9:50
 */

public class AchievementActivity extends BaseMVVMActivity<ActivityAchievementBinding>
    implements View.OnClickListener {
    private double selfValue, shareValue;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_achievement;
    }

    @Override
    protected void setListener() {
        b.shareWx.setOnClickListener(this);
        b.shareHistory.setOnClickListener(this);
        b.orderSelf.setOnClickListener(this);
        b.orderShare.setOnClickListener(this);
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
        showIndeterminateProgressDialog(false);
        BaseApp.getVipInteractor(mBaseActivity)
            .getMamaFortune()
            .subscribe(new ServiceResponse<MamaFortune>(mBaseActivity) {
                @Override
                public void onNext(MamaFortune mamaFortune) {
                    MamaFortune.MamaFortuneBean fortune = mamaFortune.getMama_fortune();
                    selfValue = fortune.getCash_self_display();
                    shareValue = fortune.getCash_share_display();
                    hideIndeterminateProgressDialog();
                }

                @Override
                public void onError(Throwable e) {
                    JUtils.Toast("数据加载失败!");
                    hideIndeterminateProgressDialog();
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
                readyGo(InviteActivity.class);
                break;
            case R.id.share_history:
                readyGo(ShareHistoryActivity.class);
                break;
            case R.id.order_self:
                bundle = new Bundle();
                bundle.putString("type", "self");
                bundle.putDouble("value", selfValue);
                readyGo(OrderAchieveActivity.class, bundle);
                break;
            case R.id.order_share:
                bundle = new Bundle();
                bundle.putString("type", "share");
                bundle.putDouble("value", shareValue);
                readyGo(OrderAchieveActivity.class, bundle);
                break;

        }
    }
}
