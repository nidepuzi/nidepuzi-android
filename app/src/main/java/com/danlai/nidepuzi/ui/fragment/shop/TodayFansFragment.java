package com.danlai.nidepuzi.ui.fragment.shop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.FansAdapter;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.FragmentTodayFansBinding;
import com.danlai.nidepuzi.entity.FansBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;


/**
 * @author wisdom
 * @date 2017年05月24日 下午6:08
 */

public class TodayFansFragment extends BaseFragment<FragmentTodayFansBinding> {
    private int type;
    private int page;
    private String next;
    private FansAdapter mAdapter;

    public static TodayFansFragment newInstance(String title, int type) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("type", type);
        TodayFansFragment fragment = new TodayFansFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View getLoadingView() {
        return b.xrv;
    }

    @Override
    public void initData() {
        page = 1;
        loadMoreData(true);
    }

    @Override
    protected void initViews() {
        type = getArguments().getInt("type");
        b.xrv.setLayoutManager(new LinearLayoutManager(mActivity));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setRefreshProgressStyle(ProgressStyle.BallPulse);
        mAdapter = new FansAdapter(mActivity);
        b.xrv.setAdapter(mAdapter);
        b.xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                b.xrv.setLoadingMoreEnabled(true);
                loadMoreData(true);
            }

            @Override
            public void onLoadMore() {
                if (next != null && !"".equals(next)) {
                    loadMoreData(false);
                } else {
                    JUtils.Toast("没有更多了!");
                    b.xrv.loadMoreComplete();
                }
            }
        });
    }

    private void loadMoreData(boolean clear) {
        BaseApp.getVipInteractor(mActivity)
            .getFans(page, type, new ServiceResponse<FansBean>(mFragment) {
                @Override
                public void onNext(FansBean bean) {
                    List<FansBean.ResultsBean> results = bean.getResults();
                    if (results != null && results.size() > 0) {
                        if (clear) {
                            mAdapter.updateWithClear(results);
                        } else {
                            mAdapter.update(results);
                        }
                    } else {
                        b.emptyLayout.setVisibility(View.VISIBLE);
                    }
                    next = bean.getNext();
                    if (next != null && !"".equals(next)) {
                        page++;
                    } else {
                        b.xrv.setLoadingMoreEnabled(false);
                        if (!clear) {
                            JUtils.Toast("已经到底啦!");
                        }
                    }
                    hideIndeterminateProgressDialog();
                    b.xrv.loadMoreComplete();
                    b.xrv.refreshComplete();
                }

                @Override
                public void onError(Throwable e) {
                    hideIndeterminateProgressDialog();
                    initDataError();
                }
            });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_today_fans;
    }
}
