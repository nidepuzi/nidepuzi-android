package com.danlai.nidepuzi.ui.activity.shop;

import android.support.design.widget.TabLayout;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.BaseTabAdapter;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityTodayFansBinding;
import com.danlai.nidepuzi.ui.fragment.shop.TodayFansFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdom
 * @date 2017年05月24日 下午6:02
 */

public class TodayFansActivity extends BaseMVVMActivity<ActivityTodayFansBinding> {

    @Override
    protected void initViews() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(TodayFansFragment.newInstance("正式掌柜", 2));
        fragments.add(TodayFansFragment.newInstance("转正掌柜", 3));
        fragments.add(TodayFansFragment.newInstance("试用掌柜", 1));
        fragments.add(TodayFansFragment.newInstance("冻结掌柜", 4));
        BaseTabAdapter mAdapter = new BaseTabAdapter(getSupportFragmentManager(), fragments);
        b.viewPager.setAdapter(mAdapter);
        b.viewPager.setOffscreenPageLimit(3);
        b.tabLayout.setTabMode(TabLayout.MODE_FIXED);
        b.tabLayout.setupWithViewPager(b.viewPager);

    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_today_fans;
    }
}
