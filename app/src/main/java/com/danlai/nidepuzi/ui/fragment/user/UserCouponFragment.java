package com.danlai.nidepuzi.ui.fragment.user;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.danlai.library.manager.CustomLinearLayoutManager;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.CouponAdapter;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.FragmentUserCouponBinding;
import com.danlai.nidepuzi.entity.CouponEntity;
import com.danlai.nidepuzi.entity.CouponPagingBean;
import com.danlai.nidepuzi.entity.event.CouponEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author wisdom
 * @date 2017年04月14日 下午6:33
 */

public class UserCouponFragment extends BaseFragment<FragmentUserCouponBinding> implements View.OnClickListener {
    private static final String TYPE = "type";
    private static final String TITLE = "title";
    private int page;
    private String next;
    private CouponAdapter mCouponAdapter;
    private int status;
    private int type;
    private AlertDialog mRuleDialog;

    public static UserCouponFragment newInstance(int type, String title) {
        Bundle args = new Bundle();
        UserCouponFragment fragment = new UserCouponFragment();
        args.putString(TITLE, title);
        args.putInt(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setListener() {
        b.layoutProblem.setOnClickListener(this);
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    public void initData() {
        page = 1;
        refreshData(true);
    }

    @Override
    protected void initViews() {
        type = getArguments().getInt(TYPE);
        if (type == BaseConst.UNUSED_COUPON) {
            status = 0;
        } else if (type == BaseConst.PAST_COUPON) {
            status = 3;
        } else if (type == BaseConst.USED_COUPON) {
            status = 1;
        }
        b.recyclerView.setLayoutManager(new CustomLinearLayoutManager(mActivity));
        mCouponAdapter = new CouponAdapter(mActivity, type);
        b.recyclerView.setAdapter(mCouponAdapter);
        b.recyclerView.setLoadingMoreEnabled(true);
        b.recyclerView.setPullRefreshEnabled(true);
        b.recyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
        b.recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                b.recyclerView.setLoadingMoreEnabled(true);
                showIndeterminateProgressDialog(false);
                refreshData(true);
            }

            @Override
            public void onLoadMore() {
                if (next != null && !"".equals(next)) {
                    refreshData(false);
                } else {
                    JUtils.Toast("已经到底啦!");
                    b.recyclerView.loadMoreComplete();
                    b.recyclerView.setLoadingMoreEnabled(false);
                }
            }
        });
        mRuleDialog = new AlertDialog.Builder(mActivity)
            .setTitle("优惠说明")
            .setMessage(getResources().getString(R.string.coupon_rule))
            .setPositiveButton("同意", (dialog, which) -> dialog.dismiss())
            .create();
    }

    public void refreshData(boolean clear) {
        b.emptyLayout.setVisibility(View.GONE);
        BaseApp.getUserInteractor(mActivity)
            .getCouponPaging(status, page)
            .subscribe(new ServiceResponse<CouponPagingBean>(mActivity) {
                @Override
                public void onNext(CouponPagingBean bean) {
                    List<CouponEntity> results = bean.getResults();
                    if (results != null && results.size() > 0) {
                        if (clear) {
                            mCouponAdapter.updateWithClear(results);
                        } else {
                            mCouponAdapter.update(results);
                        }
                    } else {
                        b.emptyLayout.setVisibility(View.VISIBLE);
                    }
                    if (clear) {
                        String title = getTitle() + "(" + bean.getCount() + ")";
                        EventBus.getDefault().post(new CouponEvent(type, title));
                    }
                    next = bean.getNext();
                    if (next != null && !"".equals(next)) {
                        page++;
                    } else {
                        b.recyclerView.setLoadingMoreEnabled(false);
                        if (!clear) {
                            JUtils.Toast("已经到底啦!");
                        }
                    }
                    hideIndeterminateProgressDialog();
                    b.recyclerView.loadMoreComplete();
                    b.recyclerView.refreshComplete();
                }

                @Override
                public void onError(Throwable e) {
                    hideIndeterminateProgressDialog();
                    b.recyclerView.loadMoreComplete();
                    b.recyclerView.refreshComplete();
                    JUtils.Toast("数据加载有误!");
                }
            });

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_user_coupon;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_problem:
                mRuleDialog.show();
                break;
        }
    }
}
