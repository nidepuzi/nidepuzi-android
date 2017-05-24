package com.danlai.nidepuzi.ui.fragment.main;

import android.view.View;

import com.danlai.library.manager.CustomLinearLayoutManager;
import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.DividerItemDecoration;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.EduAdapter;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.FragmentEduTabBinding;
import com.danlai.nidepuzi.entity.EduBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * @author wisdom
 * @date 2017年04月25日 下午4:59
 */

public class EduTabFragment extends BaseFragment<FragmentEduTabBinding> {
    private int page;
    private String next;
    private EduAdapter adapter;

    public static EduTabFragment newInstance() {
        return new EduTabFragment();
    }

    @Override
    public View getLoadingView() {
        return b.loadingView;
    }

    @Override
    public void initData() {
        showIndeterminateProgressDialog(false);
        page = 1;
        loadMoreData(true);
    }

    @Override
    protected void initViews() {
        b.xrv.setLayoutManager(new CustomLinearLayoutManager(mActivity));
        b.xrv.setPullRefreshEnabled(true);
        b.xrv.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setRefreshProgressStyle(ProgressStyle.BallPulse);
        adapter = new EduAdapter(mActivity);
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
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_edu_tab;
    }


    private void loadMoreData(boolean clear) {
        BaseApp.getMainInteractor(mActivity)
            .getEduBean(page, new ServiceResponse<EduBean>(mActivity) {
                @Override
                public void onNext(EduBean bean) {
                    List<EduBean.ResultsBean> results = bean.getResults();
                    if (results != null && results.size() > 0) {
                        if (clear) {
                            adapter.updateWithClear(results);
                        } else {
                            adapter.update(results);
                        }
                    } else {
                        b.layoutEmpty.setVisibility(View.VISIBLE);
                        b.xrv.setVisibility(View.GONE);
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
                    b.xrv.loadMoreComplete();
                    b.xrv.refreshComplete();
                    hideIndeterminateProgressDialog();
                }
            });
    }
}
