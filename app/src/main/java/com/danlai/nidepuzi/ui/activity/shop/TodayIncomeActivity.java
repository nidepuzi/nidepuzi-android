package com.danlai.nidepuzi.ui.activity.shop;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.danlai.library.widget.PageSelectedListener;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.BaseTabAdapter;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityTodayIncomeBinding;
import com.danlai.nidepuzi.ui.fragment.shop.TodayIncomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdom
 * @date 2017年05月23日 下午4:41
 */

public class TodayIncomeActivity extends BaseMVVMActivity<ActivityTodayIncomeBinding> {
    private String mCarryValue;
    List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected void getBundleExtras(Bundle extras) {
        mCarryValue = extras.getString("carryValue");
    }

    @Override
    protected void initViews() {
        b.tvAll.setText(mCarryValue);
        fragments.add(TodayIncomeFragment.newInstance("全部", BaseConst.CARRY_ALL));
        fragments.add(TodayIncomeFragment.newInstance("奖金", BaseConst.CARRY_AWARD));
        fragments.add(TodayIncomeFragment.newInstance("佣金", BaseConst.CARRY_ORDER));

        BaseTabAdapter mAdapter = new BaseTabAdapter(getSupportFragmentManager(), fragments);
        b.viewPager.setAdapter(mAdapter);
        b.viewPager.setOffscreenPageLimit(3);
        b.tabLayout.setupWithViewPager(b.viewPager);
        b.tabLayout.setTabMode(TabLayout.MODE_FIXED);
        b.scrollableLayout.getHelper().setCurrentScrollableContainer((TodayIncomeFragment) fragments.get(0));
    }

    @Override
    protected void initData() {
        b.viewPager.addOnPageChangeListener(new PageSelectedListener() {
            @Override
            public void onPageSelected(int position) {
                b.scrollableLayout.getHelper().setCurrentScrollableContainer((TodayIncomeFragment) fragments.get(position));
            }
        });
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_today_income;
    }

    @Override
    public View getLoadingView() {
        return b.scrollableLayout;
    }

}
