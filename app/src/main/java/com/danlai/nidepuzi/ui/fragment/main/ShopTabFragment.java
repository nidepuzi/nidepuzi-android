package com.danlai.nidepuzi.ui.fragment.main;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.StatusBarUtil;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.FragmentShopTabBinding;
import com.danlai.nidepuzi.entity.ShareModelBean;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.event.LoginEvent;
import com.danlai.nidepuzi.entity.event.LogoutEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.shop.FansActivity;
import com.danlai.nidepuzi.ui.activity.trade.AllOrderActivity;
import com.danlai.nidepuzi.ui.activity.trade.AllRefundActivity;
import com.danlai.nidepuzi.ui.activity.trade.CartActivity;
import com.danlai.nidepuzi.ui.activity.user.AddressActivity;
import com.danlai.nidepuzi.ui.activity.user.AllCouponActivity;
import com.danlai.nidepuzi.ui.activity.user.InformationActivity;
import com.danlai.nidepuzi.ui.activity.user.MessageActivity;
import com.danlai.nidepuzi.ui.activity.user.SettingActivity;
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
        b.layoutCoupon.setOnClickListener(this);
        b.layoutWallet.setOnClickListener(this);
        b.layoutAddress.setOnClickListener(this);
        b.inviteFriend.setOnClickListener(this);
        b.layoutAllOrder.setOnClickListener(this);
        b.layoutWaitPay.setOnClickListener(this);
        b.layoutWaitSend.setOnClickListener(this);
        b.layoutAllRefund.setOnClickListener(this);
        b.layoutFans.setOnClickListener(this);
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
                    b.shopName.setText("店铺名:  你的铺子 / 店铺序号:  " + userInfoBean.getUser_id());
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
            case R.id.layout_wallet:
                JUtils.Toast("暂未开通提现功能");
                break;
            case R.id.layout_address:
                readyGo(AddressActivity.class);
                break;
            case R.id.invite_friend:
                ShareUtils.shareShop(new ShareModelBean(), mActivity);
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
                readyGo(FansActivity.class);
                break;
        }

    }
}
