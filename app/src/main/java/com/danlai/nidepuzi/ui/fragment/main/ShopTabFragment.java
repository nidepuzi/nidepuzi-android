package com.danlai.nidepuzi.ui.fragment.main;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.StatusBarUtil;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.base.BaseWebViewActivity;
import com.danlai.nidepuzi.databinding.FragmentShopTabBinding;
import com.danlai.nidepuzi.entity.RecentCarryBean;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.event.LoginEvent;
import com.danlai.nidepuzi.entity.event.LogoutEvent;
import com.danlai.nidepuzi.entity.event.UserInfoEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.shop.AchievementActivity;
import com.danlai.nidepuzi.ui.activity.shop.AllIncomeActivity;
import com.danlai.nidepuzi.ui.activity.shop.TodayFansActivity;
import com.danlai.nidepuzi.ui.activity.shop.TodayIncomeActivity;
import com.danlai.nidepuzi.ui.activity.shop.TodaySaleOrderActivity;
import com.danlai.nidepuzi.ui.activity.shop.VisitActivity;
import com.danlai.nidepuzi.ui.activity.trade.AllOrderActivity;
import com.danlai.nidepuzi.ui.activity.trade.AllRefundActivity;
import com.danlai.nidepuzi.ui.activity.trade.CartActivity;
import com.danlai.nidepuzi.ui.activity.user.AccountDetailActivity;
import com.danlai.nidepuzi.ui.activity.user.AddressActivity;
import com.danlai.nidepuzi.ui.activity.user.AllCouponActivity;
import com.danlai.nidepuzi.ui.activity.user.DrawCashActivity;
import com.danlai.nidepuzi.ui.activity.user.InformationActivity;
import com.danlai.nidepuzi.ui.activity.user.InviteActivity;
import com.danlai.nidepuzi.ui.activity.user.LoginActivity;
import com.danlai.nidepuzi.ui.activity.user.MessageActivity;
import com.danlai.nidepuzi.ui.activity.user.SettingActivity;
import com.danlai.nidepuzi.util.JumpUtils;
import com.danlai.nidepuzi.util.LoginUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author wisdom
 * @date 2017年04月24日 下午2:11
 */

public class ShopTabFragment extends BaseFragment<FragmentShopTabBinding> implements View.OnClickListener {

    private int type = 0;
    private Dialog vipDialog;
    private String todayCarryValue;
    private Bundle bundle = new Bundle();

    public static ShopTabFragment newInstance() {
        return new ShopTabFragment();
    }

    @Override
    public View getLoadingView() {
        return b.loadingView;
    }

    @Override
    public void setListener() {
        b.layoutSet.setOnClickListener(this);
        b.layoutCart.setOnClickListener(this);
        b.imgMessage.setOnClickListener(this);
        b.userHead.setOnClickListener(this);
        b.userName.setOnClickListener(this);
        b.layoutTodayVisit.setOnClickListener(this);
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
        b.layoutTodaySale.setOnClickListener(this);
        b.layoutTodaySaleOrder.setOnClickListener(this);
        b.layoutJoin.setOnClickListener(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            StatusBarUtil.setColorNoTranslucent(mActivity,
                mActivity.getResources().getColor(R.color.colorAccent));
        } else {
            StatusBarUtil.setColorNoTranslucent(mActivity,
                mActivity.getResources().getColor(R.color.shop_top));
            EventBus.getDefault().post(new UserInfoEvent());
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
                        b.shopName.setText("店铺序号:  " + userInfoBean.getXiaolumm().getId());
                        getVipData();
                        type = userInfoBean.getXiaolumm().getLast_renew_type();
                    } else {
                        if (userInfoBean.getCheck_xiaolumm() == 1) {
                            LoginUtils.delLoginInfo(mActivity);
                            mActivity.readyGoThenKill(LoginActivity.class);
                        } else {
                            b.shopName.setText("店铺名:  你的铺子");
                        }
                    }
                    if (type != 365) {
                        b.layoutJoinBottom.setVisibility(View.VISIBLE);
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
            .getRecentCarry("0", "1")
            .subscribe(new ServiceResponse<RecentCarryBean>(mActivity) {
                @Override
                public void onNext(RecentCarryBean recentCarryBean) {
                    RecentCarryBean.ResultsEntity entity = recentCarryBean.getResults().get(0);
                    todayCarryValue = JUtils.formatDouble(entity.getCarry());
                    b.tvSale.setText(todayCarryValue);
                    b.tvVisit.setText(Integer.toString(entity.getVisitorNum()));
                    b.tvSaleOrder.setText(Integer.toString(entity.getOrderNum()));
                    b.tvFans.setText(Integer.toString(entity.getToday_referal_num()));
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
        vipDialog = new Dialog(mActivity, R.style.CustomDialog);
        vipDialog.setContentView(R.layout.pop_join_vip);
        vipDialog.setCancelable(true);
        ((TextView) vipDialog.findViewById(R.id.tv_btn)).setText("成为掌柜");
        ((TextView) vipDialog.findViewById(R.id.tv_desc)).setText(Html.fromHtml("成为正式<big>掌柜</big>才可以提现哦!"));
        vipDialog.findViewById(R.id.cancel).setOnClickListener(v -> vipDialog.dismiss());
        vipDialog.findViewById(R.id.tv_btn).setOnClickListener(v -> {
            vipDialog.dismiss();
            JumpUtils.jumpToWebViewWithCookies(mActivity,
                "https://m.nidepuzi.com/mall/boutiqueinvite2", -1, BaseWebViewActivity.class);
        });
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshInfo(UserInfoEvent event) {
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshLogout(LogoutEvent event) {
        b.userName.setText("铺子用户");
        b.shopName.setText("店铺序号:  5201314");
        Glide.with(mFragment).load(R.drawable.img_user_head).into(b.userHead);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshLogin(LoginEvent event) {
        initData();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_shop_tab;
    }

    @Override
    public void onClick(View v) {
        bundle.clear();
        switch (v.getId()) {
            case R.id.layout_all_refund:
                readyGo(AllRefundActivity.class);
                break;
            case R.id.img_message:
                readyGo(MessageActivity.class);
                break;
            case R.id.layout_set:
                readyGo(SettingActivity.class);
                break;
            case R.id.layout_cart:
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
                if (type != 365) {
                    vipDialog.show();
                } else {
                    readyGo(DrawCashActivity.class);
                }
                break;
            case R.id.layout_address:
                readyGo(AddressActivity.class);
                break;
            case R.id.invite_friend:
                readyGo(InviteActivity.class);
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
                readyGo(TodayFansActivity.class);
                break;
            case R.id.layout_achievement:
                readyGo(AchievementActivity.class);
                break;
            case R.id.layout_wallet:
                readyGo(AccountDetailActivity.class);
                break;
            case R.id.layout_today_visit:
                bundle.putBoolean("isToday", true);
                readyGo(VisitActivity.class, bundle);
                break;
            case R.id.layout_income:
                readyGo(AllIncomeActivity.class);
                break;
            case R.id.layout_today_sale:
                bundle.putString("carryValue", todayCarryValue);
                readyGo(TodayIncomeActivity.class, bundle);
                break;
            case R.id.layout_today_sale_order:
                readyGo(TodaySaleOrderActivity.class);
                break;
            case R.id.layout_join:
                JumpUtils.jumpToWebViewWithCookies(mActivity,
                    "https://m.nidepuzi.com/mall/boutiqueinvite2", -1, BaseWebViewActivity.class);
                break;
        }

    }
}
