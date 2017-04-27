package com.danlai.nidepuzi.ui.activity.user;

import android.support.design.widget.TabLayout;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.BaseTabAdapter;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityAllCouponBinding;
import com.danlai.nidepuzi.entity.event.CouponEvent;
import com.danlai.nidepuzi.ui.fragment.user.UserCouponFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class AllCouponActivity extends BaseMVVMActivity<ActivityAllCouponBinding> {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_all_coupon;
    }

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
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(UserCouponFragment.newInstance(BaseConst.UNUSED_COUPON,"未使用"));
        fragments.add(UserCouponFragment.newInstance(BaseConst.GOOD_COUPON,"精品券"));
        fragments.add(UserCouponFragment.newInstance(BaseConst.PAST_COUPON,"已过期"));
        fragments.add(UserCouponFragment.newInstance(BaseConst.USED_COUPON,"已使用"));
        BaseTabAdapter mAdapter = new BaseTabAdapter(getSupportFragmentManager(), fragments);
        b.viewPager.setAdapter(mAdapter);
        b.viewPager.setOffscreenPageLimit(3);
        b.tabLayout.setupWithViewPager(b.viewPager);
        b.tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshTitle(CouponEvent event) {
        if (event != null) {
            TabLayout.Tab tabAt = b.tabLayout.getTabAt(event.getPosition());
            if (tabAt!=null) {
                tabAt.setText(event.getTitle());
            }
        }
    }
}
