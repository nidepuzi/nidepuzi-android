package com.danlai.nidepuzi.ui.fragment.product;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.SpaceItemDecoration;
import com.danlai.library.widget.scrolllayout.ScrollableHelper;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.ActivityAdapter;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.base.BaseImageLoader;
import com.danlai.nidepuzi.databinding.FragmentActivityBinding;
import com.danlai.nidepuzi.entity.PortalBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.util.JumpUtils;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wisdom on 17/2/28.
 */

public class ActivityFragment extends BaseFragment<FragmentActivityBinding>
    implements SwipeRefreshLayout.OnRefreshListener, ScrollableHelper.ScrollableContainer {
    private ActivityAdapter adapter;

    public static ActivityFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        ActivityFragment fragment = new ActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    public void setListener() {
        b.swipeLayout.setOnRefreshListener(this);
    }

    @Override
    public void initData() {
        BaseApp.getMainInteractor(mActivity)
            .getPortalBean("categorys", new ServiceResponse<PortalBean>(mFragment) {
                @Override
                public void onNext(PortalBean portalBean) {
                    adapter.update(portalBean.getActivitys());
                    initBanner(portalBean.getPosters());
                    hideIndeterminateProgressDialog();
                    if (b.swipeLayout.isRefreshing()) {
                        b.swipeLayout.setRefreshing(false);
                    }

                }

                @Override
                public void onError(Throwable e) {
                    JUtils.Toast("数据加载失败，请下拉刷新重试!");
                    hideIndeterminateProgressDialog();
                    if (b.swipeLayout.isRefreshing()) {
                        b.swipeLayout.setRefreshing(false);
                    }
                }
            });
    }

    private void initBanner(List<PortalBean.PostersBean> posters) {
        List<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < posters.size(); i++) {
            imageUrls.add(posters.get(i).getPic_link());
        }
        b.banner.setOnBannerListener(position -> JumpUtils.push_jump_proc(mActivity, posters.get(position).getApp_link()));
        b.banner.setImageLoader(new BaseImageLoader());
        b.banner.setImages(imageUrls);
        b.banner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR);
        b.banner.start();
        b.banner.setDelayTime(3000);
    }

    @Override
    protected void initViews() {
        b.swipeLayout.setColorSchemeResources(R.color.colorAccent);

        b.recyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        b.recyclerView.addItemDecoration(new SpaceItemDecoration(0, 0, 0, 18));
        adapter = new ActivityAdapter(mActivity);
        b.recyclerView.setAdapter(adapter);

        b.layout.getHelper().setCurrentScrollableContainer(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_activity;
    }

    @Override
    public void onRefresh() {
        adapter.clear();
        showIndeterminateProgressDialog(true);
        initData();
    }

    @Override
    public View getScrollableView() {
        return b.recyclerView;
    }
}
