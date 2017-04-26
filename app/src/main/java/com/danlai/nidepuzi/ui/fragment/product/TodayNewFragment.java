package com.danlai.nidepuzi.ui.fragment.product;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.scrolllayout.ScrollableHelper;
import com.danlai.library.widget.scrolllayout.ScrollableLayout;
import com.danlai.library.widget.scrollrecycler.OnScrollCallback;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.MainProductAdapter;
import com.danlai.nidepuzi.adapter.MainTabAdapter;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.base.BaseImageLoader;
import com.danlai.nidepuzi.databinding.FragmentTodayNewBinding;
import com.danlai.nidepuzi.entity.MainTodayBean;
import com.danlai.nidepuzi.entity.PortalBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.danlai.nidepuzi.util.JumpUtils;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by wisdom on 17/2/13.
 */

public class TodayNewFragment extends BaseFragment<FragmentTodayNewBinding>
    implements ScrollableHelper.ScrollableContainer, SwipeRefreshLayout.OnRefreshListener,
    ScrollableLayout.OnScrollListener, OnScrollCallback {
    private MainTabAdapter mainTabAdapter;
    private List<MainTodayBean> data = new ArrayList<>();
    private MainProductAdapter mainProductAdapter;
    private int width;
    private int scrollCount;
    private int state;
    private int moveX;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mainTabAdapter.setCurrentPosition(msg.what);
        }
    };

    public static TodayNewFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        TodayNewFragment fragment = new TodayNewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View getLoadingView() {
        return b.scrollLayout;
    }

    @Override
    public void initData() {
        showIndeterminateProgressDialog(false);
        BaseApp.getMainInteractor(mActivity)
            .getPortalBean("activitys,categorys", new ServiceResponse<PortalBean>(mFragment) {
                @Override
                public void onNext(PortalBean portalBean) {
                    initBanner(portalBean.getPosters());
                }
            });
        BaseApp.getMainInteractor(mActivity)
            .getMainTodayList(new ServiceResponse<List<MainTodayBean>>(mFragment) {
                @Override
                public void onNext(List<MainTodayBean> list) {
                    initTodayList(list);
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    initDataError();
                    hideIndeterminateProgressDialog();
                }
            });
    }

    private void initTodayList(List<MainTodayBean> list) {
        data.clear();
        data.addAll(list);
        mainTabAdapter.updateWithClear(list);
        b.bottomView.setVisibility(View.VISIBLE);
        b.bottomLine.setVisibility(View.VISIBLE);
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        b.recyclerTab.scrollBy(-scrollCount * width, 0);
        scrollCount = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHour() <= hour) {
                scrollCount = i;
            }
        }
        mainTabAdapter.setCurrentPosition(scrollCount + 2);
        b.recyclerTab.scrollBy(scrollCount * width, 0);
        if (list.size() > scrollCount) {
            mainProductAdapter.updateWithClear(list.get(scrollCount).getItems());
        } else {
            b.recyclerTab.setEnabled(false);
        }
        hideIndeterminateProgressDialog();
    }

    @Override
    public void setListener() {
        b.swipeLayout.setOnRefreshListener(this);
        b.scrollLayout.setOnScrollListener(this);
        b.recyclerTab.setOnScrollCallback(this);
    }

    @Override
    protected void initViews() {
        width = JUtils.getScreenWidth() / 5;
        b.swipeLayout.setColorSchemeResources(R.color.colorAccent);
        b.recyclerProduct.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mainProductAdapter = new MainProductAdapter(mActivity);
        b.recyclerProduct.setAdapter(mainProductAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        b.recyclerTab.setLayoutManager(manager);
        mainTabAdapter = new MainTabAdapter(mActivity) {
            @Override
            public void itemClick(int count, int position) {
                scrollCount += count;
                b.recyclerTab.scrollBy(count * width, 0);
                mainProductAdapter.updateWithClear(data.get(position).getItems());
                b.recyclerProduct.scrollToPosition(0);
            }
        };
        b.recyclerTab.setAdapter(mainTabAdapter);
        b.scrollLayout.getHelper().setCurrentScrollableContainer(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_today_new;
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
    public View getScrollableView() {
        return b.recyclerProduct;
    }

    @Override
    public void onRefresh() {
        b.swipeLayout.setRefreshing(false);
        initData();
    }

    @Override
    public void onScroll(int currentY, int maxY) {
        if (mActivity instanceof TabActivity) {
            double percent = (double) currentY / b.banner.getHeight();
            Message msg = new Message();
            msg.what = (int) (percent * 100);
            ((TabActivity) mActivity).mHandler.sendMessage(msg);
        }
        if (currentY > 0) {
            b.swipeLayout.setEnabled(false);
        } else {
            b.swipeLayout.setEnabled(true);
        }
    }

    @Override
    public void onStateChanged(int state) {
        this.state = state;
        if (state == 1) {
            moveX = 0;
        }
        if (state == 0) {
            int lastMoveX;
            if (moveX > 0 && moveX >= width / 2) {
                scrollCount += 1;
                lastMoveX = width - moveX;
            } else if (moveX < 0 && moveX <= -width / 2) {
                scrollCount -= 1;
                lastMoveX = -width - moveX;
            } else {
                lastMoveX = -moveX;
            }
            b.recyclerTab.scrollBy(lastMoveX, 0);
            if (scrollCount < 0) {
                scrollCount = 0;
            } else if (scrollCount > data.size() - 1) {
                scrollCount = data.size() - 1;
            }
            mainTabAdapter.setCurrentPosition(scrollCount + 2);
            if (scrollCount >= 0 && data.size() > scrollCount) {
                mainProductAdapter.updateWithClear(data.get(scrollCount).getItems());
            }
            b.recyclerProduct.scrollToPosition(0);
        }
        JUtils.Log("State:::" + state);
    }

    @Override
    public void onScroll(int dx) {
        JUtils.Log("Left :<<" + dx);
        if (state > 0) {
            moveX += dx;
            if (moveX / width != 0) {
                scrollCount += moveX / width;
                moveX = moveX % width;
            }
            if (moveX > 0 && moveX >= width / 2) {
                Message msg = new Message();
                msg.what = scrollCount + 3;
                handler.sendMessage(msg);
            } else if (moveX < 0 && moveX <= -width / 2) {
                Message msg = new Message();
                msg.what = scrollCount + 1;
                handler.sendMessage(msg);
            }
        }
    }
}
