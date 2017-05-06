package com.danlai.nidepuzi.ui.activity.shop;

import android.os.Bundle;
import android.view.View;

import com.danlai.library.manager.CustomLinearLayoutManager;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.AllOrderAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityOrderAchieveBinding;
import com.danlai.nidepuzi.entity.AllOrdersBean;
import com.danlai.nidepuzi.entity.event.OrderShowEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.danlai.nidepuzi.util.OrderHelper;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @author wisdom
 * @date 2017年05月06日 下午2:12
 */

public class OrderAchieveActivity extends BaseMVVMActivity<ActivityOrderAchieveBinding> implements View.OnClickListener {
    private boolean isShow = false;
    private int mType;
    private AllOrderAdapter adapter;
    private int page;
    private String next;

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
        b.xrv.setLayoutManager(new CustomLinearLayoutManager(mBaseActivity));
        b.xrv.setPullRefreshEnabled(true);
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setRefreshProgressStyle(ProgressStyle.BallPulse);
        adapter = new AllOrderAdapter(mBaseActivity);
        adapter.setAchievement(true);
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
                    JUtils.Toast("没有更多了!");
                    b.xrv.loadMoreComplete();
                }
            }
        });
        if (mType == 0) {
            adapter.setShare(true);
            b.titleView.setName("分享成交");
        } else {
            b.titleView.setName("自购成交");
        }
    }

    @Override
    public void initData() {
        showIndeterminateProgressDialog(false);
        page = 1;
        loadMoreData(true);
    }

    private void loadMoreData(boolean clear) {
        BaseApp.getTradeInteractor(mBaseActivity)
            .getOrderList(mType, page, new ServiceResponse<AllOrdersBean>(mBaseActivity) {
                @Override
                public void onNext(AllOrdersBean allOrdersBean) {
                    List<AllOrdersBean.ResultsEntity> results = allOrdersBean.getResults();
                    if (results != null && results.size() > 0) {
                        List<Object> objects = OrderHelper.translateOrderBean(results);
                        if (clear) {
                            adapter.updateWithClear(objects);
                        } else {
                            adapter.update(objects);
                        }
                    } else {
                        b.emptyLayout.setVisibility(View.VISIBLE);
                    }
                    next = allOrdersBean.getNext();
                    if (next != null && !"".equals(next)) {
                        page++;
                    } else {
                        b.xrv.setLoadingMoreEnabled(false);
                        if (!clear) {
                            JUtils.Toast("已经到底啦!");
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

    @Override
    protected void getBundleExtras(Bundle extras) {
        mType = extras.getInt("type");
    }

    @Override
    protected void setListener() {
        b.btnJump.setOnClickListener(this);
        b.layoutEye.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_order_achieve;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_eye:
                if (isShow) {
                    EventBus.getDefault().post(new OrderShowEvent(false));
                    isShow = false;
                    b.ivEye.setImageResource(R.drawable.icon_eye_close);
                } else {
                    EventBus.getDefault().post(new OrderShowEvent(true));
                    isShow = true;
                    b.ivEye.setImageResource(R.drawable.icon_eye_open);
                }
                break;
            case R.id.btn_jump:
                readyGoThenKill(TabActivity.class);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setOrderShow(OrderShowEvent event) {
        adapter.setShow(event.isShow());
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
