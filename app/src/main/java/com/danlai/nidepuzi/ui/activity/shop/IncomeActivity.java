package com.danlai.nidepuzi.ui.activity.shop;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.DividerItemDecoration;
import com.danlai.library.widget.scrolllayout.ScrollableHelper;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.CarryAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityIncomeBinding;
import com.danlai.nidepuzi.entity.MMCarryBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * @author wisdom
 * @date 2017年05月10日 下午2:41
 */

public class IncomeActivity extends BaseMVVMActivity<ActivityIncomeBinding> implements ScrollableHelper.ScrollableContainer {
    private int page = 2;
    private CarryAdapter mAdapter;

    @Override
    protected void initViews() {
        b.scrollableLayout.getHelper().setCurrentScrollableContainer(this);
        initRecyclerView();
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        loadMoreData(1);
    }

    private void initRecyclerView() {
        b.xrv.setLayoutManager(new LinearLayoutManager(this));
        b.xrv.addItemDecoration(
            new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setPullRefreshEnabled(false);
        b.xrv.setLoadingMoreEnabled(true);
        mAdapter = new CarryAdapter(this);
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
        BaseApp.getVipInteractor(this)
            .getCarryBean(page, new ServiceResponse<MMCarryBean>(mBaseActivity) {
                @Override
                public void onNext(MMCarryBean bean) {
                    if (bean != null) {
                        b.tvAll.setText(JUtils.formatDouble((double) bean.getTotal() / 100));
                        mAdapter.update(bean.getResults());
                        if (null == bean.getNext() && page != 1) {
                            JUtils.Toast("没有更多了");
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
    protected int getContentViewLayoutID() {
        return R.layout.activity_income;
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