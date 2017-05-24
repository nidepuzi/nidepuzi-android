package com.danlai.nidepuzi.ui.activity.user;

import android.view.View;

import com.danlai.library.manager.CustomLinearLayoutManager;
import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.DividerItemDecoration;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.AccountDetailAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityAccountDetailBinding;
import com.danlai.nidepuzi.entity.BudgetDetailBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * @author wisdom
 * @date 2017年05月09日 上午11:53
 */

public class AccountDetailActivity extends BaseMVVMActivity<ActivityAccountDetailBinding> {
    private int page;
    private String next;
    private AccountDetailAdapter adapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_account_detail;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        page = 1;
        loadMoreData(true);
    }

    @Override
    protected void initViews() {
        b.xrv.setLayoutManager(new CustomLinearLayoutManager(mBaseActivity));
        b.xrv.setPullRefreshEnabled(true);
        b.xrv.addItemDecoration(new DividerItemDecoration(mBaseActivity, DividerItemDecoration.VERTICAL));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setRefreshProgressStyle(ProgressStyle.BallPulse);
        adapter = new AccountDetailAdapter(mBaseActivity);
        b.xrv.setAdapter(adapter);
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
                    JUtils.Toast("全部记录加载完成!");
                    b.xrv.loadMoreComplete();
                }
            }
        });
    }

    private void loadMoreData(boolean clear) {
        BaseApp.getUserInteractor(mBaseActivity)
            .budgetDetailBean(page, new ServiceResponse<BudgetDetailBean>(mBaseActivity) {
                @Override
                public void onNext(BudgetDetailBean budgetDetailBean) {
                    List<BudgetDetailBean.ResultsBean> results = budgetDetailBean.getResults();
                    if (results != null && results.size() > 0) {
                        if (clear) {
                            adapter.updateWithClear(results);
                        } else {
                            adapter.update(results);
                        }
                    } else {
                        b.emptyLayout.setVisibility(View.VISIBLE);
                        b.xrv.setVisibility(View.GONE);
                    }
                    next = budgetDetailBean.getNext();
                    if (next != null && !"".equals(next)) {
                        page++;
                    } else {
                        b.xrv.setLoadingMoreEnabled(false);
                        if (!clear) {
                            JUtils.Toast("全部记录加载完成!");
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
}
