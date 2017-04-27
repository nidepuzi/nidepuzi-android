package com.danlai.nidepuzi.ui.activity.user;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.widget.DividerItemDecoration;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.AddressAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityAddressBinding;
import com.danlai.nidepuzi.entity.AddressBean;
import com.danlai.nidepuzi.entity.event.AddressChangeEvent;
import com.danlai.nidepuzi.service.ServiceResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class AddressActivity extends BaseMVVMActivity<ActivityAddressBinding>
    implements View.OnClickListener {

    private AddressAdapter adapter;

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
        BaseApp.getAddressInteractor(this)
            .getAddressList(new ServiceResponse<List<AddressBean>>(mBaseActivity) {
                @Override
                public void onNext(List<AddressBean> list) {
                    super.onNext(list);
                    if (list != null) {
                        adapter.updateWithClear(list);
                    }
                }
            });
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_address;
    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
        b.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        b.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter = new AddressAdapter(this);
        b.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                startActivity(new Intent(AddressActivity.this, AddAddressActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshAddress(AddressChangeEvent event) {
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
