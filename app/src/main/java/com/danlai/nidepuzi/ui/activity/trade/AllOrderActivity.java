package com.danlai.nidepuzi.ui.activity.trade;

import android.support.design.widget.TabLayout;
import android.view.View;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.BaseTabAdapter;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityAllOrderBinding;
import com.danlai.nidepuzi.entity.event.OrderShowEvent;
import com.danlai.nidepuzi.ui.fragment.trade.OrderListFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdom
 * @date 17/4/7
 */
public class AllOrderActivity extends BaseMVVMActivity<ActivityAllOrderBinding> implements View.OnClickListener {
    List<BaseFragment> fragments;
    private boolean isShow = false;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_all_order;
    }

    @Override
    protected void setListener() {
        b.layoutEye.setOnClickListener(this);
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    protected void initData() {
        isShow = false;
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
        fragments.add(OrderListFragment.newInstance(BaseConst.ALL_ORDER, "全部订单"));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_eye:
                if (isShow) {
                    EventBus.getDefault().post(new OrderShowEvent(false));
                    isShow = false;
                    b.ivEye.setImageResource(R.drawable.icon_eye_close);
                } else {
                    EventBus.getDefault().post(new OrderShowEvent(true));
                    isShow = true;
                    b.ivEye.setImageResource(R.drawable.icon_eye_open);
                }
                break;
        }
    }
}
