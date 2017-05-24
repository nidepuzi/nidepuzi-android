package com.danlai.nidepuzi.ui.activity.shop;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.DividerItemDecoration;
import com.danlai.library.widget.scrolllayout.ScrollableHelper;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.SaleOrderAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivitySaleOrderBinding;
import com.danlai.nidepuzi.entity.OrderCarryBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * @author wisdom
 * @date 2017年05月10日 下午3:29
 */

public class SaleOrderActivity extends BaseMVVMActivity<ActivitySaleOrderBinding>
    implements ScrollableHelper.ScrollableContainer {
    private int page = 2;
    private SaleOrderAdapter mAdapter;

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
            new DividerItemDecoration(mBaseActivity, DividerItemDecoration.VERTICAL));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setPullRefreshEnabled(false);
        b.xrv.setLoadingMoreEnabled(true);
        mAdapter = new SaleOrderAdapter(this);
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
            .getMamaAllOder(page, new ServiceResponse<OrderCarryBean>(mBaseActivity) {
                @Override
                public void onNext(OrderCarryBean bean) {
                    if (bean != null) {
                        b.tvAll.setText(bean.getCount() + "");
                        mAdapter.update(bean.getResults());
                        if (null == bean.getNext()) {
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

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_sale_order;
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
