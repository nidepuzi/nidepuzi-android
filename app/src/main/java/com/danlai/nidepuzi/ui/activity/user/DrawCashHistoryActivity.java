package com.danlai.nidepuzi.ui.activity.user;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.DividerItemDecoration;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.DrawCashAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityDrawCashHistoryBinding;
import com.danlai.nidepuzi.entity.DrawCashListBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * @author wisdom
 * @date 2017年06月05日 上午11:02
 */

public class DrawCashHistoryActivity extends BaseMVVMActivity<ActivityDrawCashHistoryBinding> {
    private int page = 2;
    private DrawCashAdapter mAdapter;

    @Override
    protected void initViews() {
        b.xrv.setLayoutManager(new LinearLayoutManager(this));
        b.xrv.addItemDecoration(
            new DividerItemDecoration(mBaseActivity, DividerItemDecoration.VERTICAL));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setPullRefreshEnabled(false);
        b.xrv.setLoadingMoreEnabled(true);
        mAdapter = new DrawCashAdapter(this);
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

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        loadMoreData(1);
    }

    private void loadMoreData(int page) {
        BaseApp.getUserInteractor(this)
            .getDrawCashList(page, new ServiceResponse<DrawCashListBean>(mBaseActivity) {
                @Override
                public void onNext(DrawCashListBean bean) {
                    if (bean != null) {
                        mAdapter.update(bean.getResults());
                        if (null == bean.getNext()) {
                            if (page != 1) {
                                JUtils.Toast("全部记录加载完成");
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

    @Override
    public View getLoadingView() {
        return b.xrv;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_draw_cash_history;
    }
}
