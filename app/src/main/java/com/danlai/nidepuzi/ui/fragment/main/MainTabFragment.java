package com.danlai.nidepuzi.ui.fragment.main;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.BaseTabAdapter;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.FragmentMainTabBinding;
import com.danlai.nidepuzi.entity.PortalBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.danlai.nidepuzi.ui.activity.product.CategoryActivity;
import com.danlai.nidepuzi.ui.activity.user.InviteActivity;
import com.danlai.nidepuzi.ui.activity.user.MessageActivity;
import com.danlai.nidepuzi.ui.fragment.product.ProductFragment;
import com.danlai.nidepuzi.ui.fragment.product.TodayNewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wisdom on 17/3/6.
 */

public class MainTabFragment extends BaseFragment<FragmentMainTabBinding> implements View.OnClickListener {

    public static MainTabFragment newInstance() {
        return new MainTabFragment();
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    public void setListener() {
        b.imgMessage.setOnClickListener(this);
        b.layoutInvite.setOnClickListener(this);
        b.layoutSearch.setOnClickListener(this);
    }

    @Override
    public void initData() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(TodayNewFragment.newInstance("每日焦点"));
//        fragments.add(ActivityFragment.newInstance("精品活动"));
        BaseApp.getMainInteractor(mActivity)
            .getPortalBean("activitys,posters", new ServiceResponse<PortalBean>(mFragment) {
                @Override
                public void onNext(PortalBean portalBean) {
                    List<PortalBean.CategorysBean> categorys = portalBean.getCategorys();
                    if (categorys != null && categorys.size() > 0) {
                        for (int i = 0; i < categorys.size(); i++) {
                            PortalBean.CategorysBean bean = categorys.get(i);
                            fragments.add(ProductFragment.newInstance(bean.getId(), bean.getName(), true));
                        }
                        BaseTabAdapter mAdapter = new BaseTabAdapter(getChildFragmentManager(), fragments);
                        b.viewPager.setAdapter(mAdapter);
                        b.viewPager.setOffscreenPageLimit(fragments.size());
                        b.tabLayout.setupWithViewPager(b.viewPager);
                        b.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    initDataError();
                    hideIndeterminateProgressDialog();
                }
            });
    }

    @Override
    protected void initViews() {
        ((TabActivity) mActivity).mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                setTabLayoutMarginTop((double) msg.what / 100);
            }
        };
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_main_tab;
    }

    public void setTabLayoutMarginTop(double percent) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, b.tabLayout.getHeight());
        if (percent > 0) {
            b.viewPager.setScrollable(false);
            double height = percent * b.tabLayout.getHeight();
            params.setMargins(0, (int) -height, 0, 0);
            b.tabLayout.setLayoutParams(params);
        } else {
            b.viewPager.setScrollable(true);
            params.setMargins(0, 0, 0, 0);
        }
        b.tabLayout.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_message:
                readyGo(MessageActivity.class);
                break;
            case R.id.layout_invite:
                readyGo(InviteActivity.class);
                break;
            case R.id.layout_search:
                readyGo(CategoryActivity.class);
                break;
        }
    }
}
