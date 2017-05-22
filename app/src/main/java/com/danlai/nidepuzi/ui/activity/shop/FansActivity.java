package com.danlai.nidepuzi.ui.activity.shop;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.FansAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityFansBinding;
import com.danlai.nidepuzi.entity.FansBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * @author wisdom
 * @date 2017年05月05日 上午9:36
 */

public class FansActivity extends BaseMVVMActivity<ActivityFansBinding> {
    private int page = 1;
    private FansAdapter mAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_fans;
    }

    @Override
    protected void initViews() {
        b.xrv.setLayoutManager(new LinearLayoutManager(mBaseActivity));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setPullRefreshEnabled(false);
        b.xrv.setLoadingMoreEnabled(true);
        mAdapter = new FansAdapter(mBaseActivity);
        b.xrv.setAdapter(mAdapter);
        b.xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        loadMoreData();
    }

    private void loadMoreData() {
        BaseApp.getVipInteractor(mBaseActivity)
            .getFans(page, new ServiceResponse<FansBean>(mBaseActivity) {
                @Override
                public void onNext(FansBean fansBeen) {
                    if (fansBeen != null) {
                        mAdapter.update(fansBeen.getResults());
                        if (fansBeen.getNext() == null) {
                            if (page != 1) {
                                JUtils.Toast("全部加载完成!");
                            }
                            b.xrv.setLoadingMoreEnabled(false);
                        } else {
                            page++;
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


}
