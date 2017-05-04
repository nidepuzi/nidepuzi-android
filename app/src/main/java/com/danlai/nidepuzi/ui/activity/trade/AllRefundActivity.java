package com.danlai.nidepuzi.ui.activity.trade;

import android.view.View;

import com.danlai.library.manager.CustomLinearLayoutManager;
import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.SpaceItemDecoration;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.AllRefundsAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityAllRefundBinding;
import com.danlai.nidepuzi.entity.AllRefundsBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;
/**
 * @author wisdom
 * @date 17/4/7
 */
public class AllRefundActivity extends BaseMVVMActivity<ActivityAllRefundBinding> implements View.OnClickListener {

    private AllRefundsAdapter adapter;
    private int page;
    private String next;

    @Override
    protected void setListener() {
        b.btnJump.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_all_refund;
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        page = 1;
        loadMoreData();
    }

    @Override
    public View getLoadingView() {
        return b.xrv;
    }

    @Override
    protected void initViews() {
        adapter = new AllRefundsAdapter(this);
        b.xrv.setLayoutManager(new CustomLinearLayoutManager(this));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setRefreshProgressStyle(ProgressStyle.BallPulse);
        b.xrv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, 12));
        b.xrv.setAdapter(adapter);
        b.xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadMoreData();
            }

            @Override
            public void onLoadMore() {
                if (next != null && !"".equals(next)) {
                    page++;
                    loadMoreData();
                } else {
                    JUtils.Toast("没有更多了!");
                    b.xrv.loadMoreComplete();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jump:
                readyGo(TabActivity.class);
                finish();
                break;
        }
    }

    private void loadMoreData() {
        BaseApp.getTradeInteractor(this)
            .getRefunds(page, new ServiceResponse<AllRefundsBean>(mBaseActivity) {
                @Override
                public void onNext(AllRefundsBean allOrdersBean) {
                    if (allOrdersBean != null) {
                        List<AllRefundsBean.ResultsEntity> results = allOrdersBean.getResults();
                        if (results.size() == 0 && page == 1) {
                            b.emptyLayout.setVisibility(View.VISIBLE);
                        } else if (page == 1) {
                            adapter.updateWithClear(results);
                        } else {
                            adapter.update(results);
                        }
                        next = allOrdersBean.getNext();
                        if (allOrdersBean.getNext() == null && adapter.getItemCount() != 0) {
                            JUtils.Toast("全部订单加载完成!");
                        }
                    }
                }

                @Override
                public void onComplete() {
                    hideIndeterminateProgressDialog();
                    b.xrv.loadMoreComplete();
                    b.xrv.refreshComplete();
                }

                @Override
                public void onError(Throwable e) {
                    JUtils.Toast("数据加载失败");
                    hideIndeterminateProgressDialog();
                    b.xrv.loadMoreComplete();
                    b.xrv.refreshComplete();
                }
            });
    }
}
