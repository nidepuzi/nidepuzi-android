package com.danlai.nidepuzi.ui.activity.product;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.SpaceItemDecoration;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.ProductListAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivitySearchBinding;
import com.danlai.nidepuzi.entity.ProductListBean;
import com.danlai.nidepuzi.entity.SearchHistoryBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

/**
 * @author wisdom
 * @date 2017年01月10日 下午5:13
 */

public class SearchActivity extends BaseMVVMActivity<ActivitySearchBinding> implements View.OnClickListener, TextView.OnEditorActionListener, XRecyclerView.LoadingListener {

    private ProductListAdapter adapter;
    private int page;
    private String searchStr;
    private String next;

    @Override
    protected void initData() {
        b.flowLayout.removeAllViews();
        BaseApp.getProductInteractor(this)
            .getSearchHistory(new ServiceResponse<SearchHistoryBean>(mBaseActivity) {
                @Override
                public void onNext(SearchHistoryBean searchHistoryBean) {
                    List<SearchHistoryBean.ResultsBean> results = searchHistoryBean.getResults();
                    if (results != null) {
                        for (int i = 0; i < results.size(); i++) {
                            TextView textView = new TextView(SearchActivity.this);
                            String content = results.get(i).getContent();
                            textView.setText(content);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                            layoutParams.setMargins(12, 12, 12, 12);
                            textView.setLayoutParams(layoutParams);
                            textView.setTextColor(getResources().getColor(R.color.color_33));
                            textView.setTextSize(COMPLEX_UNIT_DIP, 14);
                            textView.setBackgroundResource(R.drawable.search_tag_bg);
                            textView.setOnClickListener(v -> {
                                b.et.setText(content);
                                b.et.setSelection(content.length());
                                search();
                            });
                            b.flowLayout.addView(textView);
                        }
                    }
                }
            });
    }

    @Override
    protected void initViews() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        b.xrv.setLayoutManager(manager);
        b.xrv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        b.xrv.addItemDecoration(new SpaceItemDecoration(10));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setRefreshProgressStyle(ProgressStyle.BallPulse);
        adapter = new ProductListAdapter(this);
        b.xrv.setAdapter(adapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            b.finishLayout.setTransitionName("finish");
            b.searchLayout.setTransitionName("search");
            postponeEnterTransition();
            startPostponedEnterTransition();
        }
    }

    @Override
    protected void setListener() {
        b.xrv.setLoadingListener(this);
        b.finishLayout.setOnClickListener(this);
        b.search.setOnClickListener(this);
        b.et.setOnEditorActionListener(this);
        b.clear.setOnClickListener(this);
        b.delete.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    public View getLoadingView() {
        return b.flowContainer;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish_layout:
                finish();
                break;
            case R.id.search:
                search();
                break;
            case R.id.clear:
                b.et.setText("");
                b.flowContainer.setVisibility(View.VISIBLE);
                b.emptyLayout.setVisibility(View.GONE);
                b.xrv.setVisibility(View.GONE);
                adapter.clear();
                initData();
                break;
            case R.id.delete:
                BaseApp.getProductInteractor(this)
                    .clearSearch(new ServiceResponse<Object>(mBaseActivity) {
                        @Override
                        public void onNext(Object o) {
                            initData();
                        }
                    });
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
            (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            search();
            return true;
        }
        return false;
    }

    private void search() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        if (isOpen) {
            try {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        searchStr = b.et.getText().toString().trim();
        if ("".equals(searchStr)) {
            b.flowContainer.setVisibility(View.VISIBLE);
            b.emptyLayout.setVisibility(View.GONE);
            b.xrv.setVisibility(View.GONE);
            adapter.clear();
            initData();
        } else {
            b.flowContainer.setVisibility(View.GONE);
            refreshData(true);
        }
    }

    @Override
    public void onRefresh() {
        refreshData(true);
    }

    @Override
    public void onLoadMore() {
        if (next != null && !"".equals(next)) {
            refreshData(false);
        } else {
            JUtils.Toast("已经到底啦!");
            b.xrv.loadMoreComplete();
        }
    }

    public void refreshData(boolean clear) {
        if (clear) {
            adapter.clear();
            page = 1;
        }
        BaseApp.getProductInteractor(this)
            .searchProduct(searchStr, page, new ServiceResponse<ProductListBean>(mBaseActivity) {
                @Override
                public void onNext(ProductListBean bean) {
                    List<ProductListBean.ResultsBean> results = bean.getResults();
                    if (results != null && results.size() > 0) {
                        adapter.update(results);
                        b.xrv.setVisibility(View.VISIBLE);
                        b.emptyLayout.setVisibility(View.GONE);
                    } else {
                        b.emptyLayout.setVisibility(View.VISIBLE);
                        b.xrv.setVisibility(View.GONE);
                    }
                    next = bean.getNext();
                    if (next != null && !"".equals(next)) {
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
}
