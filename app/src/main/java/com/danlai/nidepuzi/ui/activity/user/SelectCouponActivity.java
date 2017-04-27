package com.danlai.nidepuzi.ui.activity.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.BaseTabAdapter;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivitySelectCouponBinding;
import com.danlai.nidepuzi.entity.event.CouponEvent;
import com.danlai.nidepuzi.ui.fragment.user.ChooseCouponFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class SelectCouponActivity extends BaseMVVMActivity<ActivitySelectCouponBinding> {
    String selected_coupon_id;
    String cart_ids;
    List<BaseFragment> fragments = new ArrayList<>();


    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        BaseTabAdapter mAdapter = new BaseTabAdapter(getSupportFragmentManager(), fragments);
        fragments.add(ChooseCouponFragment.newInstance(BaseConst.COUPON_USABLE, "可用优惠券", selected_coupon_id, cart_ids));
        fragments.add(ChooseCouponFragment.newInstance(BaseConst.COUPON_DISABLE, "不可用优惠券", selected_coupon_id, cart_ids));
        b.viewPager.setAdapter(mAdapter);
        b.viewPager.setOffscreenPageLimit(2);
        b.tabLayout.setupWithViewPager(b.viewPager);
        b.tabLayout.setTabMode(TabLayout.MODE_FIXED);
        hideIndeterminateProgressDialog();
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        selected_coupon_id = extras.getString("coupon_id", "");
        cart_ids = extras.getString("cart_ids", "");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_coupon;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshTitle(CouponEvent event) {
        if (event != null) {
            TabLayout.Tab tabAt = b.tabLayout.getTabAt(event.getPosition());
            if (tabAt != null) {
                tabAt.setText(event.getTitle());
            }
        }
    }
}
