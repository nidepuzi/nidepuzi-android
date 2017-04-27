package com.danlai.nidepuzi.ui.fragment.main;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.FragmentShopTabBinding;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.trade.AllOrderActivity;
import com.danlai.nidepuzi.ui.activity.trade.CartActivity;
import com.danlai.nidepuzi.ui.activity.user.AddressActivity;
import com.danlai.nidepuzi.ui.activity.user.AllCouponActivity;
import com.danlai.nidepuzi.ui.activity.user.InviteActivity;
import com.danlai.nidepuzi.ui.activity.user.MessageActivity;
import com.danlai.nidepuzi.ui.activity.user.SettingActivity;

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
        b.layoutCoupon.setOnClickListener(this);
        b.layoutWallet.setOnClickListener(this);
        b.layoutAddress.setOnClickListener(this);
        b.inviteFriend.setOnClickListener(this);
        b.layoutAllOrder.setOnClickListener(this);
        b.layoutWaitPay.setOnClickListener(this);
        b.layoutWaitSend.setOnClickListener(this);
    }

    @Override
    public void initData() {
        BaseApp.getMainInteractor(mActivity)
            .getProfile(new ServiceResponse<UserInfoBean>(mFragment) {
                @Override
                public void onNext(UserInfoBean userInfoBean) {
                    b.userName.setText(userInfoBean.getNick());
                    b.shopName.setText("店铺名:  你的铺子 / 店铺序号:  " + userInfoBean.getUser_id());
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

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_shop_tab;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.img_message:
                readyGo(MessageActivity.class);
                break;
            case R.id.img_set:
                readyGo(SettingActivity.class);
                break;
            case R.id.img_cart:
                readyGo(CartActivity.class);
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
        }

    }
}
