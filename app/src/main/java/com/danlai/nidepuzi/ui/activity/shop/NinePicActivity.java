package com.danlai.nidepuzi.ui.activity.shop;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.NinePicAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityNinePicBinding;
import com.danlai.nidepuzi.entity.NinePicBean;
import com.danlai.nidepuzi.entity.ProductNinePicBean;
import com.danlai.nidepuzi.service.ServiceResponse;

import java.util.List;


/**
 * @author wisdom
 * @date 16/6/7 上午09:45
 */
public class NinePicActivity extends BaseMVVMActivity<ActivityNinePicBinding>
    implements SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {

    private NinePicAdapter mAdapter;
    private int mSale_category = -1;
    private int mModel_id = -1;
    int page = 1;
    private String next;
    private boolean flag;

    @Override
    protected void setListener() {
        b.refreshLayout.setOnRefreshListener(this);
        b.circleLv.setOnScrollListener(this);
    }

    @Override
    protected void initData() {
        loadData();
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    private void loadData() {
        showIndeterminateProgressDialog(false);
        if (mModel_id == -1) {
            BaseApp.getVipInteractor(this)
                .getNinePic(mSale_category, new ServiceResponse<List<NinePicBean>>(mBaseActivity) {
                    @Override
                    public void onNext(List<NinePicBean> list) {
                        doWhileSuccess(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        doWhileFail(e);
                    }
                });
        } else {
            BaseApp.getVipInteractor(this)
                .getNinePicByModelId(mModel_id, page, new ServiceResponse<ProductNinePicBean>(mBaseActivity) {
                    @Override
                    public void onNext(ProductNinePicBean bean) {
                        next = bean.getNext();
                        doWhileSuccess(bean.getResults());
                        if (next == null || "".equals(next)) {
                            JUtils.Toast("全部加载完成!");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        doWhileFail(e);
                    }
                });
        }
    }

    private void doWhileSuccess(List<NinePicBean> ninePicBean) {
        if (ninePicBean != null && ninePicBean.size() > 0) {
            mAdapter.update(ninePicBean);
        }
        if (mAdapter.getCount() == 0) {
            b.emptyLayout.setVisibility(View.VISIBLE);
        }
        page++;
        if (b.refreshLayout.isRefreshing()) {
            b.refreshLayout.setRefreshing(false);
        }
        hideIndeterminateProgressDialog();
    }

    private void doWhileFail(Throwable e) {
        e.printStackTrace();
        if (b.refreshLayout.isRefreshing()) {
            b.refreshLayout.setRefreshing(false);
        }
        JUtils.Toast("数据加载失败,请下拉刷新重试!");
        hideIndeterminateProgressDialog();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mSale_category = extras.getInt("sale_category", -1);
        mModel_id = extras.getInt("model_id", -1);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nine_pic;
    }

    @Override
    protected void initViews() {
        mAdapter = new NinePicAdapter(this);
        b.circleLv.setAdapter(mAdapter);
        b.refreshLayout.setColorSchemeResources(R.color.colorAccent);
    }

    @Override
    public void onRefresh() {
        mAdapter.clear();
        page = 1;
        loadData();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && flag && mModel_id != -1) {
            if (next != null && !"".equals(next)) {
                loadData();
            }
            flag = false;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if ((firstVisibleItem + visibleItemCount) == totalItemCount && totalItemCount > 0) {
            flag = true;
        }
    }
}
