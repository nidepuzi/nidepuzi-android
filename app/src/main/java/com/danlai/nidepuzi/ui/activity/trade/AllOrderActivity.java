package com.danlai.nidepuzi.ui.activity.trade;

import android.support.design.widget.TabLayout;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.BaseTabAdapter;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityAllOrderBinding;
import com.danlai.nidepuzi.ui.fragment.trade.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdom
 * @date 17/4/7
 */
public class AllOrderActivity extends BaseMVVMActivity<ActivityAllOrderBinding> {
    List<BaseFragment> fragments;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_all_order;
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    protected void initData() {
        initFragment();
        initTabLayout();
        switch_fragment();
    }

    private void initTabLayout() {
        BaseTabAdapter mAdapter = new BaseTabAdapter(getSupportFragmentManager(), fragments);
        b.viewPager.setAdapter(mAdapter);
        b.viewPager.setOffscreenPageLimit(3);
        b.tabLayout.setupWithViewPager(b.viewPager);
        b.tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(OrderListFragment.newInstance(BaseConst.ALL_ORDER, "所有订单"));
        fragments.add(OrderListFragment.newInstance(BaseConst.WAIT_PAY, "待付款"));
        fragments.add(OrderListFragment.newInstance(BaseConst.WAIT_SEND, "待收货"));
    }

    public void switch_fragment() {
        if (getIntent().getExtras() != null) {
            int tab_id = getIntent().getExtras().getInt("fragment", 0);
            if ((tab_id >= 1) && (tab_id <= 3)) {
                try {
                    b.tabLayout.setScrollPosition(tab_id - 1, 0, true);
                    b.viewPager.setCurrentItem(tab_id - 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
