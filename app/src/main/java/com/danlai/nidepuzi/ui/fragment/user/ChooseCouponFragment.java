package com.danlai.nidepuzi.ui.fragment.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.danlai.library.manager.CustomLinearLayoutManager;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.CouponAdapter;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.base.TestData;
import com.danlai.nidepuzi.databinding.FragmentChooseCouponBinding;
import com.danlai.nidepuzi.entity.CouponEntity;
import com.danlai.nidepuzi.entity.CouponPagingBean;
import com.danlai.nidepuzi.entity.event.CouponEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author wisdom
 * @date 2017年04月15日 下午2:31
 */

public class ChooseCouponFragment extends BaseFragment<FragmentChooseCouponBinding> implements View.OnClickListener {
    private static final String TYPE = "type";
    private static final String TITLE = "title";
    private int page;
    private String next;
    private CouponAdapter mCouponAdapter;
    private String cart_ids;
    private String typeStr;

    public static ChooseCouponFragment newInstance(int type, String title, String selectId, String cart_ids) {
        Bundle args = new Bundle();
        ChooseCouponFragment fragment = new ChooseCouponFragment();
        args.putString(TITLE, title);
        args.putInt(TYPE, type);
        args.putString("id", cart_ids);
        args.putString("select_id", selectId);
        fragment.setArguments(args);
        return fragment;
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
        int type = getArguments().getInt(TYPE);
        if (type == BaseConst.UNUSED_COUPON) {
            typeStr = "usable";
        } else {
            typeStr = "disable";
        }
        cart_ids = getArguments().getString("id");
        String selectId = getArguments().getString("select_id");
        b.recyclerView.setLayoutManager(new CustomLinearLayoutManager(mActivity));
        mCouponAdapter = new CouponAdapter(mActivity, type);
        if (type == BaseConst.UNUSED_COUPON) {
            mCouponAdapter.setInfo(true);
            b.btn.setVisibility(View.VISIBLE);
        } else {
            mCouponAdapter.setInfo(false);
            b.btn.setVisibility(View.GONE);
        }
        mCouponAdapter.setSelectCouponId(selectId);
        b.btn.setOnClickListener(this);
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
    }

    public void refreshData(boolean clear) {
        b.emptyLayout.setVisibility(View.GONE);
        BaseApp.getUserInteractor(mActivity)
            .getCouponSelectEntity(cart_ids, typeStr, page, new ServiceResponse<CouponPagingBean>(mActivity) {
                @Override
                public void onNext(CouponPagingBean bean) {
                    List<CouponEntity> results = bean.getResults();
                    results = new Gson().fromJson(TestData.COUPON, new TypeToken<List<CouponEntity>>() {
                    }.getType());
                    if (results != null && results.size() > 0) {
                        if (clear) {
                            mCouponAdapter.updateWithClear(results);
                        } else {
                            mCouponAdapter.update(results);
                        }
                    } else {
                        b.emptyLayout.setVisibility(View.VISIBLE);
                        b.btn.setVisibility(View.GONE);
                    }
                    if (clear) {
                        EventBus.getDefault().post(new CouponEvent(0,
                            "可用优惠券(" + bean.getUsable_coupon_count() + ")"));
                        EventBus.getDefault().post(new CouponEvent(1,
                            "不可用优惠券(" + bean.getDisable_coupon_count() + ")"));
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
        return R.layout.fragment_choose_coupon;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                Intent intent = new Intent();
                intent.putExtra("coupon_id", "");
                intent.putExtra("coupon_price", (double) 0);
                mActivity.setResult(Activity.RESULT_OK, intent);
                mActivity.finish();
                break;
        }
    }
}
