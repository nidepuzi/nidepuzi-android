package com.danlai.nidepuzi.ui.activity.user;

import android.view.View;

import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityInviteBinding;
import com.danlai.nidepuzi.entity.ActivityBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.util.ShareUtils;

/**
 * @author wisdom
 * @date 2017年04月22日 下午5:05
 */

public class InviteActivity extends BaseMVVMActivity<ActivityInviteBinding> implements View.OnClickListener {

    @Override
    protected void setListener() {
        b.btn.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_invite;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                BaseApp.getActivityInteractor(mBaseActivity)
                    .get_party_share_content("8", new ServiceResponse<ActivityBean>(mBaseActivity) {
                        @Override
                        public void onNext(ActivityBean activityBean) {
                            ShareUtils.shareShop(activityBean, mBaseActivity);
                        }
                    });
                break;
        }
    }
}
