package com.danlai.nidepuzi.ui.activity.shop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.DividerItemDecoration;
import com.danlai.library.widget.scrolllayout.ScrollableHelper;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.OrderAchieveAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityOrderAchieveBinding;
import com.danlai.nidepuzi.entity.OrderCarryBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * @author wisdom
 * @date 2017年05月06日 下午2:12
 */

public class OrderAchieveActivity extends BaseMVVMActivity<ActivityOrderAchieveBinding> implements ScrollableHelper.ScrollableContainer {
    private String mType;
    private OrderAchieveAdapter adapter;
    private int page = 2;
    private double mValue;

    @Override
    protected void initViews() {
        b.tvAmount.setText(JUtils.formatDouble(mValue));
        b.scrollableLayout.getHelper().setCurrentScrollableContainer(this);
        if ("share".equals(mType)) {
            b.titleView.setName("分享佣金");
            b.tvDesc.setText("累计分享佣金");
        } else {
            b.titleView.setName("自购佣金");
            b.tvDesc.setText("累计自购佣金");
        }
        b.xrv.setLayoutManager(new LinearLayoutManager(mBaseActivity));
        b.xrv.addItemDecoration(
            new DividerItemDecoration(mBaseActivity, DividerItemDecoration.VERTICAL));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setPullRefreshEnabled(false);
        b.xrv.setLoadingMoreEnabled(true);
        adapter = new OrderAchieveAdapter(mBaseActivity);
        b.xrv.setAdapter(adapter);
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
    public void initData() {
        showIndeterminateProgressDialog(false);
        loadMoreData(1);
    }

    private void loadMoreData(int page) {
        BaseApp.getVipInteractor(mBaseActivity)
            .getMamaAllOder(page, mType, new ServiceResponse<OrderCarryBean>(mBaseActivity) {
                @Override
                public void onNext(OrderCarryBean bean) {
                    if (bean != null) {
                        adapter.update(bean.getResults());
                        if (null == bean.getNext()) {
                            if (page != 1) {
                                JUtils.Toast("全部记录加载完成");
                            }
                            b.xrv.setLoadingMoreEnabled(false);
                        }
                    }
                    hideIndeterminateProgressDialog();
                    b.xrv.loadMoreComplete();
                }

                @Override
                public void onError(Throwable e) {
                    b.xrv.loadMoreComplete();
                    hideIndeterminateProgressDialog();
                }
            });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mType = extras.getString("type");
        mValue = extras.getDouble("value", 0);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_order_achieve;
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
