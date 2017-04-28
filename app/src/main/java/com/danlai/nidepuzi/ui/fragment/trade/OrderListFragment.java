package com.danlai.nidepuzi.ui.fragment.trade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.danlai.library.manager.CustomLinearLayoutManager;
import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.SpaceItemDecoration;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.AllOrdersAdapter;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.FragmentOrderListBinding;
import com.danlai.nidepuzi.entity.AllOrdersBean;
import com.danlai.nidepuzi.entity.event.RefreshOrderListEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


public class OrderListFragment extends BaseFragment<FragmentOrderListBinding> implements View.OnClickListener {

    private int type;
    private AllOrdersAdapter adapter;
    private int page;
    private String next;

    public static OrderListFragment newInstance(int type, String title) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }
    }

    @Override
    public void initData() {
        showIndeterminateProgressDialog(false);
        page = 1;
        loadMoreData(true);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_order_list;
    }

    @Override
    public View getLoadingView() {
        return b.xrv;
    }

    @Override
    public void setListener() {
        b.btnJump.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
        b.xrv.setLayoutManager(new CustomLinearLayoutManager(mActivity));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setRefreshProgressStyle(ProgressStyle.BallPulse);
        b.xrv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, 20));
        adapter = new AllOrdersAdapter(mActivity);
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
                    page++;
                    loadMoreData(false);
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
                Intent intent = new Intent(getActivity(), TabActivity.class);
                mActivity.startActivity(intent);
                mActivity.finish();
                break;
        }
    }


    private void loadMoreData(boolean clear) {
        BaseApp.getTradeInteractor(mActivity)
            .getOrderList(type, page, new ServiceResponse<AllOrdersBean>(mFragment) {
                @Override
                public void onNext(AllOrdersBean allOrdersBean) {
                    List<AllOrdersBean.ResultsEntity> results = allOrdersBean.getResults();
                    if (results != null && results.size() > 0) {
                        if (clear) {
                            adapter.updateWithClear(results);
                        } else {
                            adapter.update(results);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reLoadData(RefreshOrderListEvent event) {
        b.xrv.setLoadingMoreEnabled(true);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
