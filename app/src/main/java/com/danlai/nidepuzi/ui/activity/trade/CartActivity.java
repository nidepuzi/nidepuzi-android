package com.danlai.nidepuzi.ui.activity.trade;

import android.os.Bundle;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.DividerItemDecoration;
import com.danlai.library.widget.ScrollLinearLayoutManager;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.CartHistoryAdapter;
import com.danlai.nidepuzi.adapter.CartListAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityCartBinding;
import com.danlai.nidepuzi.entity.CartsInfoBean;
import com.danlai.nidepuzi.entity.CartsPayInfoBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.ui.activity.main.TabActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wisdom on 16/12/2.
 */

public class CartActivity extends BaseMVVMActivity<ActivityCartBinding> implements View.OnClickListener {
    private List<Integer> ids = new ArrayList<>();
    private List<CartsInfoBean> cartList = new ArrayList<>();
    private List<CartsInfoBean> cartHisList = new ArrayList<>();
    private CartListAdapter cartListAdapter;
    private CartHistoryAdapter cartHisAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_cart;
    }

    @Override
    protected void initViews() {
        b.rvCart.setNestedScrollingEnabled(false);
        b.rvCart.setHasFixedSize(false);
        b.rvCart.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        ScrollLinearLayoutManager layoutManager = new ScrollLinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(false);
        b.rvCart.setLayoutManager(layoutManager);
        cartListAdapter = new CartListAdapter(this, cartList);
        b.rvCart.setAdapter(cartListAdapter);

        b.rvHistory.setNestedScrollingEnabled(false);
        b.rvHistory.setHasFixedSize(false);
        b.rvHistory.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        ScrollLinearLayoutManager manager = new ScrollLinearLayoutManager(this);
        manager.setAutoMeasureEnabled(false);
        b.rvHistory.setLayoutManager(manager);
        cartHisAdapter = new CartHistoryAdapter(this, cartHisList);
        b.rvHistory.setAdapter(cartHisAdapter);
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected void initData() {
        refreshCartList();
        refreshHisCartList();
    }

    @Override
    public void setListener() {
        b.goMain.setOnClickListener(this);
        b.confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_main:
                readyGoThenKill(TabActivity.class);
                break;
            case R.id.confirm:
                if (ids.size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("ids", getIds());
                    readyGoThenKill(PayInfoActivity.class,bundle);
                } else {
                    JUtils.Toast("请至少选择一件商品哦!");
                }
                break;
        }
    }

    private String getIds() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.size(); i++) {
            sb.append(ids.get(i)).append(",");
        }
        String str = new String(sb);
        return str.substring(0, str.length() - 1);
    }

    public void setPriceText() {
        BaseApp.getCartsInteractor(this)
            .getCartsPayInfoList(getIds(), new ServiceResponse<CartsPayInfoBean>(mBaseActivity) {
                @Override
                public void onNext(CartsPayInfoBean cartsPayInfoBean) {
                    if (cartsPayInfoBean != null) {
                        b.totalPrice.setText("¥" + cartsPayInfoBean.getTotalFee());
                        b.confirmLayout.setVisibility(View.VISIBLE);
                    }
                }
            });
    }

    private void refreshHisCartList() {
        BaseApp.getCartsInteractor(this)
            .getCartsHisList(0, new ServiceResponse<List<CartsInfoBean>>(mBaseActivity) {
                @Override
                public void onNext(List<CartsInfoBean> cartsInfoBeen) {
                    cartHisList.clear();
                    if (cartsInfoBeen != null && cartsInfoBeen.size() > 0) {
                        cartHisList.addAll(cartsInfoBeen);
                        cartHisAdapter.notifyDataSetChanged();
                        b.tvLine.setVisibility(View.VISIBLE);
                    } else {
                        b.tvLine.setVisibility(View.GONE);
                    }
                }
            });
    }

    public void removeCartList(CartsInfoBean cartsInfoBean) {
        cartList.remove(cartsInfoBean);
        if (cartList.size() == 0) {
            b.emptyContent.setVisibility(View.VISIBLE);
            b.totalPrice.setText("¥0");
            b.confirmLayout.setVisibility(View.GONE);
        }
        cartListAdapter.notifyDataSetChanged();
        refreshIds();
    }

    private void refreshIds() {
        BaseApp.getCartsInteractor(this)
            .getCartsList(0, new ServiceResponse<List<CartsInfoBean>>(mBaseActivity) {
                @Override
                public void onNext(List<CartsInfoBean> cartsInfoBeen) {
                    if (cartsInfoBeen != null && cartsInfoBeen.size() > 0) {
                        ids.clear();
                        for (int i = 0; i < cartsInfoBeen.size(); i++) {
                            ids.add(cartsInfoBeen.get(i).getId());
                        }
                        setPriceText();
                    } else {
                        ids.clear();
                    }
                }
            });
    }

    public void removeHistory(CartsInfoBean cartsInfoBean) {
        cartHisList.remove(cartsInfoBean);
        cartHisAdapter.notifyDataSetChanged();
        if (cartHisList.size() == 0) b.tvLine.setVisibility(View.GONE);
    }

    public void addHistory(CartsInfoBean cartsInfoBean) {
        cartHisList.add(0, cartsInfoBean);
        cartHisAdapter.notifyDataSetChanged();
        b.tvLine.setVisibility(View.VISIBLE);
    }

    public void refreshCartList() {
        showIndeterminateProgressDialog(false);
        BaseApp.getCartsInteractor(this)
            .getCartsList(0, new ServiceResponse<List<CartsInfoBean>>(mBaseActivity) {
                @Override
                public void onNext(List<CartsInfoBean> cartsInfoBeen) {
                    cartList.clear();
                    if (cartsInfoBeen != null && cartsInfoBeen.size() > 0) {
                        cartList.addAll(cartsInfoBeen);
                        b.emptyContent.setVisibility(View.GONE);
                        cartListAdapter.notifyDataSetChanged();
                        ids.clear();
                        for (int i = 0; i < cartsInfoBeen.size(); i++) {
                            ids.add(cartsInfoBeen.get(i).getId());
                        }
                        setPriceText();
                    } else {
                        b.emptyContent.setVisibility(View.VISIBLE);
                        b.totalPrice.setText("¥0");
                        b.confirmLayout.setVisibility(View.GONE);
                    }
                    b.sv.scrollTo(0, 0);
                    hideIndeterminateProgressDialog();
                }
            });
    }
}
