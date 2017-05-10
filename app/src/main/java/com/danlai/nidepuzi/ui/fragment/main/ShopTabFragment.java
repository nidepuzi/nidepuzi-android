package com.danlai.nidepuzi.ui.fragment.main;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.StatusBarUtil;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.base.BaseWebViewActivity;
import com.danlai.nidepuzi.databinding.FragmentShopTabBinding;
import com.danlai.nidepuzi.entity.ActivityBean;
import com.danlai.nidepuzi.entity.MamaFortune;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.event.LoginEvent;
import com.danlai.nidepuzi.entity.event.LogoutEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.shop.AchievementActivity;
import com.danlai.nidepuzi.ui.activity.shop.IncomeActivity;
import com.danlai.nidepuzi.ui.activity.shop.SaleOrderActivity;
import com.danlai.nidepuzi.ui.activity.shop.VisitActivity;
import com.danlai.nidepuzi.ui.activity.trade.AllOrderActivity;
import com.danlai.nidepuzi.ui.activity.trade.AllRefundActivity;
import com.danlai.nidepuzi.ui.activity.trade.CartActivity;
import com.danlai.nidepuzi.ui.activity.user.AccountDetailActivity;
import com.danlai.nidepuzi.ui.activity.user.AddressActivity;
import com.danlai.nidepuzi.ui.activity.user.AllCouponActivity;
import com.danlai.nidepuzi.ui.activity.user.DrawCashActivity;
import com.danlai.nidepuzi.ui.activity.user.InformationActivity;
import com.danlai.nidepuzi.ui.activity.user.MessageActivity;
import com.danlai.nidepuzi.ui.activity.user.SettingActivity;
import com.danlai.nidepuzi.util.JumpUtils;
import com.danlai.nidepuzi.util.ShareUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author wisdom
 * @date 2017年04月24日 下午2:11
 */

public class ShopTabFragment extends BaseFragment<FragmentShopTabBinding> implements View.OnClickListener {
    public static ShopTabFragment newInstance() {
        return new ShopTabFragment();
    }

    @Override
    public View getLoadingView() {
        return b.loadingView;
    }

    @Override
    public void setListener() {
        b.imgSet.setOnClickListener(this);
        b.imgCart.setOnClickListener(this);
        b.imgMessage.setOnClickListener(this);
        b.userHead.setOnClickListener(this);
        b.userName.setOnClickListener(this);
        b.layoutVisit.setOnClickListener(this);
        b.layoutCoupon.setOnClickListener(this);
        b.layoutWallet.setOnClickListener(this);
        b.tvDrawCash.setOnClickListener(this);
        b.layoutAddress.setOnClickListener(this);
        b.inviteFriend.setOnClickListener(this);
        b.layoutAllOrder.setOnClickListener(this);
        b.layoutWaitPay.setOnClickListener(this);
        b.layoutWaitSend.setOnClickListener(this);
        b.layoutAllRefund.setOnClickListener(this);
        b.layoutFans.setOnClickListener(this);
        b.layoutAchievement.setOnClickListener(this);
        b.layoutIncome.setOnClickListener(this);
        b.layoutSale.setOnClickListener(this);
        b.layoutSaleOrder.setOnClickListener(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            StatusBarUtil.setColorNoTranslucent(mActivity,
                mActivity.getResources().getColor(R.color.colorAccent));
        } else {
            StatusBarUtil.setColorNoTranslucent(mActivity,
                mActivity.getResources().getColor(R.color.shop_top));
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void initData() {
        BaseApp.getMainInteractor(mActivity)
            .getProfile(new ServiceResponse<UserInfoBean>(mFragment) {
                @Override
                public void onNext(UserInfoBean userInfoBean) {
                    b.userName.setText(userInfoBean.getNick());
                    if (userInfoBean.getXiaolumm() != null) {
                        b.shopName.setText("店铺名:  你的铺子 / 店铺序号:  " + userInfoBean.getXiaolumm().getId());
                        getVipData();
                    }
                    if (userInfoBean.getUser_budget() != null) {
                        b.tvWallet.setText(userInfoBean.getUser_budget().getBudget_cash() + "");
                    }
                    String thumbnail = userInfoBean.getThumbnail();
                    Glide.with(mFragment).load(thumbnail).into(b.userHead);
                    hideIndeterminateProgressDialog();
                }

                @Override
                public void onError(Throwable e) {
                    JUtils.Toast("数据加载失败");
                    hideIndeterminateProgressDialog();
                    initDataError();
                }
            });
    }

    private void getVipData() {
        BaseApp.getVipInteractor(mActivity)
            .getMamaFortune()
            .subscribe(new ServiceResponse<MamaFortune>(mActivity) {
                @Override
                public void onNext(MamaFortune mamaFortune) {
                    MamaFortune.MamaFortuneBean fortune = mamaFortune.getMamaFortune();
                    b.tvSaleOrder.setText(fortune.getOrderNum() + "");
                    b.tvSale.setText(JUtils.formatDouble(fortune.getCarryValue()));
                    b.tvVisit.setText(fortune.getTodayVisitorNum() + "");
                    b.tvFans.setText(fortune.getFansNum() + "");
                }

                @Override
                public void onError(Throwable e) {
                    JUtils.Toast("数据加载失败");
                    initDataError();
                }
            });

    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshLogin(LoginEvent event) {
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshLogout(LogoutEvent event) {
        b.userName.setText("铺子用户");
        b.shopName.setText("店铺名:  你的铺子 / 店铺序号:  5201314");
        Glide.with(mFragment).load(R.drawable.img_user_head).into(b.userHead);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_shop_tab;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.layout_all_refund:
                readyGo(AllRefundActivity.class);
                break;
            case R.id.img_message:
                readyGo(MessageActivity.class);
                break;
            case R.id.img_set:
                readyGo(SettingActivity.class);
                break;
            case R.id.img_cart:
                readyGo(CartActivity.class);
                break;
            case R.id.user_head:
            case R.id.user_name:
                readyGo(InformationActivity.class);
                break;
            case R.id.layout_coupon:
                readyGo(AllCouponActivity.class);
                break;
            case R.id.tv_draw_cash:
                readyGo(DrawCashActivity.class);
                break;
            case R.id.layout_address:
                readyGo(AddressActivity.class);
                break;
            case R.id.invite_friend:
                BaseApp.getActivityInteractor(mActivity)
                    .get_party_share_content("8", new ServiceResponse<ActivityBean>(mActivity) {
                        @Override
                        public void onNext(ActivityBean activityBean) {
                            ShareUtils.shareShop(activityBean, mActivity);
                        }
                    });
                break;
            case R.id.layout_all_order:
                bundle.putInt("fragment", 1);
                readyGo(AllOrderActivity.class, bundle);
                break;
            case R.id.layout_wait_pay:
                bundle.putInt("fragment", 2);
                readyGo(AllOrderActivity.class, bundle);
                break;
            case R.id.layout_wait_send:
                bundle.putInt("fragment", 3);
                readyGo(AllOrderActivity.class, bundle);
                break;
            case R.id.layout_fans:
                JumpUtils.jumpToWebViewWithCookies(mActivity, "http://m.nidepuzi.com/mall/mama/invited",
                    -1, BaseWebViewActivity.class, "粉丝");
                break;
            case R.id.layout_achievement:
                readyGo(AchievementActivity.class);
                break;
            case R.id.layout_wallet:
                readyGo(AccountDetailActivity.class);
                break;
            case R.id.layout_visit:
                readyGo(VisitActivity.class);
                break;
            case R.id.layout_income:
            case R.id.layout_sale:
                readyGo(IncomeActivity.class);
                break;
            case R.id.layout_sale_order:
                readyGo(SaleOrderActivity.class);
                break;
        }

    }
}
