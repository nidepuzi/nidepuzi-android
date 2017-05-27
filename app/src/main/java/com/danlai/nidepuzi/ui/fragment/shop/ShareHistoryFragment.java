package com.danlai.nidepuzi.ui.fragment.shop;

import android.os.Bundle;
import android.view.View;

import com.danlai.library.manager.CustomLinearLayoutManager;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.ShareHistoryAdapter;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.FragmentShareHistoryBinding;
import com.danlai.nidepuzi.entity.FansBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * @author wisdom
 * @date 2017年05月26日 上午11:21
 */

public class ShareHistoryFragment extends BaseFragment<FragmentShareHistoryBinding> {
    private ShareHistoryAdapter adapter;
    private int page;
    private String next;
    private String type;

    public static ShareHistoryFragment newInstance(String title, String type) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("type", type);
        ShareHistoryFragment fragment = new ShareHistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString("type");
        }
    }

    @Override
    public View getLoadingView() {
        return b.xrv;
    }

    @Override
    public void initData() {
        page = 1;
        refreshData(true);
    }

    @Override
    protected void initViews() {
        b.xrv.setLayoutManager(new CustomLinearLayoutManager(mActivity));
        b.xrv.setRefreshProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        adapter = new ShareHistoryAdapter(mActivity);
        b.xrv.setAdapter(adapter);
        b.xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                b.xrv.setLoadingMoreEnabled(true);
                showIndeterminateProgressDialog(false);
                refreshData(true);
            }

            @Override
            public void onLoadMore() {
                if (next != null && !"".equals(next)) {
                    refreshData(false);
                } else {
                    JUtils.Toast("已经到底啦!");
                    b.xrv.loadMoreComplete();
                    b.xrv.setLoadingMoreEnabled(false);
                }
            }
        });
    }

    private void refreshData(boolean clear) {
        BaseApp.getVipInteractor(mActivity)
            .getFans(page, type, new ServiceResponse<FansBean>(mFragment) {
                @Override
                public void onNext(FansBean bean) {
                    List<FansBean.ResultsBean> results = bean.getResults();
                    if (results != null && results.size() > 0) {
                        if (clear) {
                            adapter.updateWithClear(results);
                        } else {
                            adapter.update(results);
                        }
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
                    b.xrv.loadMoreComplete();
                    b.xrv.refreshComplete();
                    hideIndeterminateProgressDialog();
                }
            });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_share_history;
    }
}
