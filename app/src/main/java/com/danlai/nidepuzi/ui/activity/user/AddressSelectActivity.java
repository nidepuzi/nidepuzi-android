package com.danlai.nidepuzi.ui.activity.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.widget.DividerItemDecoration;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.AddressSelectAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityAddressSelectBinding;
import com.danlai.nidepuzi.entity.AddressBean;
import com.danlai.nidepuzi.entity.event.AddressChangeEvent;
import com.danlai.nidepuzi.service.ServiceResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class AddressSelectActivity extends BaseMVVMActivity<ActivityAddressSelectBinding>
    implements View.OnClickListener {

    private AddressSelectAdapter adapter;
    private String addressId;
    private int needLevel;

    @Override
    protected void setListener() {
        b.btn.setOnClickListener(this);
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        BaseApp.getAddressInteractor(this)
            .getAddressList(new ServiceResponse<List<AddressBean>>(mBaseActivity) {
                @Override
                public void onNext(List<AddressBean> list) {
                    super.onNext(list);
                    if (list != null) {
                        adapter.updateWithClear(list);
                        hideIndeterminateProgressDialog();
                    }
                }
            });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        addressId = extras.getString("addressId");
        needLevel = extras.getInt("needLevel", 1);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_address_select;
    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
        b.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        b.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter = new AddressSelectAdapter(this, addressId);
        adapter.setNeedLevel(needLevel);
        b.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshAddress(AddressChangeEvent event) {
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                Bundle addressBundle = new Bundle();
                addressBundle.putInt("needLevel", needLevel);
                readyGo(AddAddressActivity.class, addressBundle);
                break;
        }
    }


}
