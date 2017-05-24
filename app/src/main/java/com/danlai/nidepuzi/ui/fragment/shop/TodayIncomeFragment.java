package com.danlai.nidepuzi.ui.fragment.shop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.DividerItemDecoration;
import com.danlai.library.widget.scrolllayout.ScrollableHelper;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.CarryAdapter;
import com.danlai.nidepuzi.adapter.CarryAwardAdapter;
import com.danlai.nidepuzi.adapter.CarryOrderAdapter;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.FragmentTodayIncomeBinding;
import com.danlai.nidepuzi.entity.AwardCarryBean;
import com.danlai.nidepuzi.entity.CarryListBean;
import com.danlai.nidepuzi.entity.OrderCarryBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * @author wisdom
 * @date 2017年05月24日 上午10:30
 */

public class TodayIncomeFragment extends BaseFragment<FragmentTodayIncomeBinding> implements ScrollableHelper.ScrollableContainer {
    private int page = 2;
    private CarryAdapter mCarryAdapter;
    private CarryAwardAdapter mCarryAwardAdapter;
    private CarryOrderAdapter mCarryOrderAdapter;
    private int currentType;

    public static TodayIncomeFragment newInstance(String title, int type) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("type", type);
        TodayIncomeFragment fragment = new TodayIncomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View getLoadingView() {
        return b.xrv;
    }

    @Override
    public void initData() {
        loadMoreData(1);
    }

    @Override
    protected void initViews() {
        currentType = getArguments().getInt("type");
        b.xrv.setLayoutManager(new LinearLayoutManager(mActivity));
        b.xrv.addItemDecoration(
            new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        b.xrv.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        b.xrv.setPullRefreshEnabled(false);
        b.xrv.setLoadingMoreEnabled(true);
        if (currentType == BaseConst.CARRY_ALL) {
            mCarryAdapter = new CarryAdapter(mActivity);
            mCarryAdapter.setToday(true);
            b.xrv.setAdapter(mCarryAdapter);
        } else if (currentType == BaseConst.CARRY_AWARD) {
            mCarryAwardAdapter = new CarryAwardAdapter(mActivity);
            mCarryAwardAdapter.setToday(true);
            b.xrv.setAdapter(mCarryAwardAdapter);
        } else if (currentType == BaseConst.CARRY_ORDER) {
            mCarryOrderAdapter = new CarryOrderAdapter(mActivity);
            mCarryOrderAdapter.setToday(true);
            b.xrv.setAdapter(mCarryOrderAdapter);
        }
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
    protected int getContentViewId() {
        return R.layout.fragment_today_income;
    }

    @Override
    public View getScrollableView() {
        return b.xrv;
    }

    private void loadMoreData(int page) {
        if (currentType == BaseConst.CARRY_ALL) {
            BaseApp.getVipInteractor(mActivity)
                .getCarryListToday(page, new ServiceResponse<CarryListBean>(mActivity) {
                    @Override
                    public void onNext(CarryListBean bean) {
                        if (bean != null) {
                            mCarryAdapter.update(bean.getResults());
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
        } else if (currentType == BaseConst.CARRY_AWARD) {
            BaseApp.getVipInteractor(mActivity)
                .getMamaAllAwardToday(page, new ServiceResponse<AwardCarryBean>(mActivity) {
                    @Override
                    public void onNext(AwardCarryBean bean) {
                        if (bean != null) {
                            mCarryAwardAdapter.update(bean.getResults());
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
        } else if (currentType == BaseConst.CARRY_ORDER) {
            BaseApp.getVipInteractor(mActivity)
                .getMamaAllOderToday(page, new ServiceResponse<OrderCarryBean>(mActivity) {
                    @Override
                    public void onNext(OrderCarryBean bean) {
                        if (bean != null) {
                            mCarryOrderAdapter.update(bean.getResults());
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
    }
}
