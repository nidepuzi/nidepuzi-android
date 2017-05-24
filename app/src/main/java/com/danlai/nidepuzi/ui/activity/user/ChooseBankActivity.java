package com.danlai.nidepuzi.ui.activity.user;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.danlai.library.widget.DividerItemDecoration;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.BankListAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityChooseBankBinding;
import com.danlai.nidepuzi.entity.BankListEntity;
import com.danlai.nidepuzi.service.ServiceResponse;

/**
 * @author wisdom
 * @date 2017年05月19日 下午3:40
 */

public class ChooseBankActivity extends BaseMVVMActivity<ActivityChooseBankBinding> {

    private BankListAdapter mBankListAdapter;

    @Override
    protected void initViews() {
        b.rv.setLayoutManager(new LinearLayoutManager(mBaseActivity));
        b.rv.addItemDecoration(new DividerItemDecoration(mBaseActivity, DividerItemDecoration.VERTICAL));
        mBankListAdapter = new BankListAdapter(mBaseActivity);
        b.rv.setAdapter(mBankListAdapter);
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        BaseApp.getUserInteractor(mBaseActivity)
            .getBankList(new ServiceResponse<BankListEntity>(mBaseActivity) {
                @Override
                public void onNext(BankListEntity bankListEntity) {
                    hideIndeterminateProgressDialog();
                    mBankListAdapter.update(bankListEntity.getBanks());
                }

                @Override
                public void onError(Throwable e) {
                    initDataError();
                }
            });
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_choose_bank;
    }

    @Override
    public View getLoadingView() {
        return b.rv;
    }
}
