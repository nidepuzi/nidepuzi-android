package com.danlai.nidepuzi.ui.activity.product;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.SpaceItemDecoration;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.CategoryListAdapter;
import com.danlai.nidepuzi.adapter.CategoryNameListAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityCategoryListBinding;
import com.danlai.nidepuzi.entity.PortalBean;
import com.danlai.nidepuzi.entity.ProductListBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * @author wisdom
 * @date 2017年05月18日 下午2:39
 */

public class CategoryListActivity extends BaseMVVMActivity<ActivityCategoryListBinding>
    implements View.OnClickListener, AdapterView.OnItemClickListener {
    private int page;
    private String cid;

    private CategoryListAdapter adapter;
    private CategoryNameListAdapter mCategoryNameListAdapter;

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_category_list;
    }

    @Override
    protected void initViews() {
        mCategoryNameListAdapter = new CategoryNameListAdapter(mBaseActivity);
        b.lv.setAdapter(mCategoryNameListAdapter);

        LinearLayoutManager manager = new GridLayoutManager(mBaseActivity, 2);
        b.xrv.setLayoutManager(manager);
        b.xrv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        b.xrv.addItemDecoration(new SpaceItemDecoration(10));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setRefreshProgressStyle(ProgressStyle.BallPulse);
        adapter = new CategoryListAdapter(mBaseActivity);
        b.xrv.setAdapter(adapter);
        b.xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                loadData(true);
            }

            @Override
            public void onLoadMore() {
                loadData(false);
            }
        });
    }

    private void loadData(boolean clear) {
        if (clear) {
            page = 1;
            b.xrv.setLoadingMoreEnabled(true);
            showIndeterminateProgressDialog(false);
        }
        BaseApp.getProductInteractor(mBaseActivity)
            .getCategoryProductList(cid, page, "", new ServiceResponse<ProductListBean>(mBaseActivity) {
                @Override
                public void onNext(ProductListBean bean) {
                    List<ProductListBean.ResultsBean> results = bean.getResults();
                    if (results != null && results.size() > 0) {
                        if (clear) {
                            adapter.updateWithClear(results);
                        } else {
                            adapter.update(results);
                        }
                    } else {
                        b.emptyLayout.setVisibility(View.VISIBLE);
                    }
                    String next = bean.getNext();
                    if (TextUtils.isEmpty(next)) {
                        b.xrv.setLoadingMoreEnabled(false);
                        if (!clear) {
                            JUtils.Toast("已经到底啦!");
                        }
                    } else {
                        page++;
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
    protected void initData() {
        showIndeterminateProgressDialog(false);
        BaseApp.getMainInteractor(mBaseActivity)
            .getPortalBean("activitys,posters", new ServiceResponse<PortalBean>(mBaseActivity) {
                @Override
                public void onNext(PortalBean portalBean) {
                    if (portalBean.getCategorys() != null && portalBean.getCategorys().size() > 0) {
                        mCategoryNameListAdapter.update(portalBean.getCategorys());
                        cid = portalBean.getCategorys().get(0).getId();
                        loadData(true);
                    } else {
                        hideIndeterminateProgressDialog();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    initDataError();
                    e.printStackTrace();
                }
            });
    }

    @Override
    public void setListener() {
        b.lv.setOnItemClickListener(this);
        b.searchLayout.setOnClickListener(this);
        b.finishLayout.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        b.emptyLayout.setVisibility(View.GONE);
        cid = mCategoryNameListAdapter.getItem(position).getId();
        mCategoryNameListAdapter.setCid(cid);
        loadData(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_layout:
                Intent intent = new Intent(this, SearchActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Pair<View, String> searchPair = new Pair<>(b.searchLayout, "search");
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,
                        searchPair).toBundle());
                } else {
                    startActivity(intent);
                }
                break;
            case R.id.finish_layout:
                finish();
                break;
        }
    }

}
