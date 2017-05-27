package com.danlai.nidepuzi.ui.activity.shop;

import android.os.Bundle;
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
import com.danlai.nidepuzi.entity.CarryListBean;
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
    private String mCarryValue;
    private int days;

    @Override
    protected void getBundleExtras(Bundle extras) {
        mCarryValue = extras.getString("carryValue");
        days = extras.getInt("days", -1);
    }

    @Override
    protected void initViews() {
        if (days == 7) {
            b.titleView.setName("本周收益记录");
            b.tvDesc.setText("本周累计收益");
        } else if (days == 30) {
            b.titleView.setName("本月收益记录");
            b.tvDesc.setText("本月累计收益");
        }
        b.scrollableLayout.getHelper().setCurrentScrollableContainer(this);
        initRecyclerView();
        b.tvAll.setText(mCarryValue);
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        loadMoreData(1);
    }

    private void initRecyclerView() {
        b.xrv.setLayoutManager(new LinearLayoutManager(mBaseActivity));
        b.xrv.addItemDecoration(
            new DividerItemDecoration(mBaseActivity, DividerItemDecoration.VERTICAL));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setPullRefreshEnabled(false);
        b.xrv.setLoadingMoreEnabled(true);
        mAdapter = new CarryAdapter(mBaseActivity);
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
        BaseApp.getVipInteractor(mBaseActivity)
            .getCarryList(page, days, new ServiceResponse<CarryListBean>(mBaseActivity) {
                @Override
                public void onNext(CarryListBean bean) {
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
