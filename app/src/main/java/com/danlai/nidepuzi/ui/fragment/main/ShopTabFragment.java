package com.danlai.nidepuzi.ui.fragment.main;

import android.content.Intent;
import android.view.View;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.FragmentShopTabBinding;
import com.danlai.nidepuzi.ui.activity.user.MessageActivity;
import com.danlai.nidepuzi.ui.activity.user.SettingActivity;

/**
 * @author wisdom
 * @date 2017年04月24日 下午2:11
 */

public class ShopTabFragment extends BaseFragment<FragmentShopTabBinding> implements View.OnClickListener {
    public static ShopTabFragment newInstance() {
        return new ShopTabFragment();
    }

    @Override
    public View getLoadingView() {
        return b.loadingView;
    }

    @Override
    public void setListener() {
        b.imgSet.setOnClickListener(this);
        b.imgMessage.setOnClickListener(this);
    }

    @Override
    public void initData() {
        hideIndeterminateProgressDialog();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_shop_tab;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_message:
                startActivity(new Intent(mActivity, MessageActivity.class));
                break;
            case R.id.img_set:
                startActivity(new Intent(mActivity, SettingActivity.class));
                break;
        }

    }
}
