package com.danlai.nidepuzi.ui.fragment.product;

import android.os.Bundle;
import android.view.View;

import com.danlai.library.manager.CustomGridLayoutManager;
import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.SpaceItemDecoration;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.ProductListAdapter;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.FragmentProductBinding;
import com.danlai.nidepuzi.entity.ProductListBean;
import com.danlai.nidepuzi.entity.event.SortEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by wisdom on 17/2/13.
 */

public class ProductFragment extends BaseFragment<FragmentProductBinding> {

    private String cid;
    private int page;

    private ProductListAdapter mProductListAdapter;
    private String next;
    private String sortBy;
    private boolean mainFlag;

    public static ProductFragment newInstance(String cid, String title, boolean mainFlag) {
        Bundle args = new Bundle();
        args.putString("cid", cid);
        args.putString("title", title);
        args.putBoolean("mainFlag", mainFlag);
        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cid = getArguments().getString("cid");
            mainFlag = getArguments().getBoolean("mainFlag");
        }
    }

    @Override
    public View getLoadingView() {
        return b.xrv;
    }

    @Override
    public void initData() {
        page = 1;
        showIndeterminateProgressDialog(false);
        refreshData(true);
    }

    @Override
    protected void initViews() {
        sortBy = "";
        EventBus.getDefault().register(this);
        b.xrv.setLayoutManager(new CustomGridLayoutManager(mActivity, 2));
        b.xrv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        b.xrv.addItemDecoration(new SpaceItemDecoration(10));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setRefreshProgressStyle(ProgressStyle.BallPulse);
        mProductListAdapter = new ProductListAdapter(mActivity);
        b.xrv.setAdapter(mProductListAdapter);
        b.xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                b.xrv.setLoadingMoreEnabled(true);
                showIndeterminateProgressDialog(false);
                refreshData(true);
            }

            @Override
            public void onLoadMore() {
                if (next != null && !"".equals(next)) {
                    refreshData(false);
                } else {
                    JUtils.Toast("已经到底啦!");
                    b.xrv.loadMoreComplete();
                    b.xrv.setLoadingMoreEnabled(false);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshSort(SortEvent event) {
        if (!mainFlag) {
            if (event.isSortByPrice()) {
                sortBy = "price";
            } else {
                sortBy = "";
            }
            if (getUserVisibleHint()) {
                page = 1;
                b.xrv.setLoadingMoreEnabled(true);
                showIndeterminateProgressDialog(false);
                refreshData(true);
            }
        }
    }

    public void refreshData(boolean clear) {
        b.emptyLayout.setVisibility(View.GONE);
        BaseApp.getProductInteractor(mActivity)
            .getCategoryProductList(cid, page, sortBy, new ServiceResponse<ProductListBean>(mFragment) {
                @Override
                public void onNext(ProductListBean bean) {
                    List<ProductListBean.ResultsBean> results = bean.getResults();
                    if (results != null && results.size() > 0) {
                        if (clear) {
                            mProductListAdapter.updateWithClear(results);
                        } else {
                            mProductListAdapter.update(results);
                        }
                    } else {
                        b.emptyLayout.setVisibility(View.VISIBLE);
                    }
                    next = bean.getNext();
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
                    hideIndeterminateProgressDialog();
                    b.xrv.loadMoreComplete();
                    b.xrv.refreshComplete();
                    JUtils.Toast("数据加载有误!");
                }
            });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_product;
    }
}
