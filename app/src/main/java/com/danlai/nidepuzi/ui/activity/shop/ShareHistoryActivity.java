package com.danlai.nidepuzi.ui.activity.shop;

import android.support.design.widget.TabLayout;
import android.view.View;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.BaseTabAdapter;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityShareHistoryBinding;
import com.danlai.nidepuzi.ui.fragment.shop.ShareHistoryFragment;

import java.util.ArrayList;

/**
 * @author wisdom
 * @date 2017年05月26日 上午10:42
 */

public class ShareHistoryActivity extends BaseMVVMActivity<ActivityShareHistoryBinding> implements View.OnClickListener {

    @Override
    protected void setListener() {
        b.finishRl.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(ShareHistoryFragment.newInstance("正式掌柜", "full"));
        fragments.add(ShareHistoryFragment.newInstance("试用掌柜", "trial"));
        BaseTabAdapter mAdapter = new BaseTabAdapter(getSupportFragmentManager(), fragments);
        b.viewPager.setAdapter(mAdapter);
        b.viewPager.setOffscreenPageLimit(2);
        b.tabLayout.setupWithViewPager(b.viewPager);
        b.tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_share_history;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish_rl:
                finish();
                break;
        }
    }
}
