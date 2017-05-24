package com.danlai.nidepuzi.ui.activity.shop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.DividerItemDecoration;
import com.danlai.library.widget.scrolllayout.ScrollableHelper;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.VisitAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityVisitBinding;
import com.danlai.nidepuzi.entity.MMVisitorsBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * @author wisdom
 * @date 2017年05月10日 上午10:39
 */

public class VisitActivity extends BaseMVVMActivity<ActivityVisitBinding>
    implements ScrollableHelper.ScrollableContainer {
    private int page = 2;
    private VisitAdapter mAdapter;
    private boolean isToday;

    @Override
    protected void getBundleExtras(Bundle extras) {
        isToday = extras.getBoolean("isToday", false);
    }

    @Override
    protected void initViews() {
        b.scrollableLayout.getHelper().setCurrentScrollableContainer(this);
        initRecyclerView();
        if (isToday) {
            b.tvDesc.setText("今日访客记录");
        }
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        loadMoreData(1);
    }

    private void initRecyclerView() {
        b.xrv.setLayoutManager(new LinearLayoutManager(this));
        b.xrv.addItemDecoration(
            new DividerItemDecoration(mBaseActivity, DividerItemDecoration.VERTICAL));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setPullRefreshEnabled(false);
        b.xrv.setLoadingMoreEnabled(true);
        mAdapter = new VisitAdapter(this);
        mAdapter.setToday(isToday);
        b.xrv.setAdapter(mAdapter);
        b.xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                loadMoreData(page);
                page++;
            }
        });

    }

    private void loadMoreData(int page) {
        if (isToday) {
            BaseApp.getVipInteractor(this)
                .getMamaVisitorToday(page, new ServiceResponse<MMVisitorsBean>(mBaseActivity) {
                    @Override
                    public void onNext(MMVisitorsBean mmVisitorsBean) {
                        if (mmVisitorsBean != null) {
                            b.tvCount.setText(mmVisitorsBean.getCount() + "");
                            mAdapter.update(mmVisitorsBean.getResults());
                            if (null == mmVisitorsBean.getNext()) {
                                if (page != 1) {
                                    JUtils.Toast("全部加载完成!");
                                }
                                b.xrv.setLoadingMoreEnabled(false);
                            }
                        }
                        b.xrv.loadMoreComplete();
                        hideIndeterminateProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        b.xrv.loadMoreComplete();
                        hideIndeterminateProgressDialog();
                    }
                });
        } else {
            BaseApp.getVipInteractor(this)
                .getMamaVisitor(page, new ServiceResponse<MMVisitorsBean>(mBaseActivity) {
                    @Override
                    public void onNext(MMVisitorsBean mmVisitorsBean) {
                        if (mmVisitorsBean != null) {
                            b.tvCount.setText(mmVisitorsBean.getCount() + "");
                            mAdapter.update(mmVisitorsBean.getResults());
                            if (null == mmVisitorsBean.getNext()) {
                                if (page != 1) {
                                    JUtils.Toast("全部加载完成!");
                                }
                                b.xrv.setLoadingMoreEnabled(false);
                            }
                        }
                        b.xrv.loadMoreComplete();
                        hideIndeterminateProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        b.xrv.loadMoreComplete();
                        hideIndeterminateProgressDialog();
                    }
                });
        }
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_visit;
    }

    @Override
    public View getLoadingView() {
        return b.scrollableLayout;
    }

    @Override
    public View getScrollableView() {
        return b.xrv;
    }
}
