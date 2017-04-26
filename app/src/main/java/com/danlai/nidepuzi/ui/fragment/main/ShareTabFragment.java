package com.danlai.nidepuzi.ui.fragment.main;

import android.view.View;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.FragmentShareTabBinding;

/**
 * @author wisdom
 * @date 2017年04月25日 下午4:59
 */

public class ShareTabFragment extends BaseFragment<FragmentShareTabBinding> {

    public static ShareTabFragment newInstance() {
        return new ShareTabFragment();
    }

    @Override
    public View getLoadingView() {
        return b.loadingView;
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
        return R.layout.fragment_share_tab;
    }
}
