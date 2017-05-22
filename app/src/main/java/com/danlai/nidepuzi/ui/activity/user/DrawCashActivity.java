package com.danlai.nidepuzi.ui.activity.user;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.DividerItemDecoration;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.DrawCashAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityDrawCashBinding;
import com.danlai.nidepuzi.entity.DrawCashListBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * @author wisdom
 * @date 2017年05月19日 上午10:36
 */

public class DrawCashActivity extends BaseMVVMActivity<ActivityDrawCashBinding> implements View.OnClickListener {
    private AlertDialog mRuleDialog;
    private int page = 2;
    private DrawCashAdapter mAdapter;

    @Override
    protected void initViews() {
        mRuleDialog = new AlertDialog.Builder(mBaseActivity)
            .setTitle("提现小知识")
            .setMessage(getResources().getString(R.string.draw_rule))
            .setPositiveButton("同意", (dialog, which) -> dialog.dismiss())
            .create();
        b.xrv.setLayoutManager(new LinearLayoutManager(this));
        b.xrv.addItemDecoration(
            new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
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
    protected void setListener() {
        b.layoutProblem.setOnClickListener(this);
        b.layoutDraw.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_draw_cash;
    }

    @Override
    public View getLoadingView() {
        return b.xrv;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_problem:
                mRuleDialog.show();
                break;
            case R.id.layout_draw:
                readyGo(DrawCashInfoActivity.class);
                break;
        }
    }
}
