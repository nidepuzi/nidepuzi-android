package com.danlai.nidepuzi.ui.fragment.main;

import android.view.View;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.databinding.FragmentEduTabBinding;

/**
 * @author wisdom
 * @date 2017年04月25日 下午4:59
 */

public class EduTabFragment extends BaseFragment<FragmentEduTabBinding> {

    public static EduTabFragment newInstance() {
        return new EduTabFragment();
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
        return R.layout.fragment_edu_tab;
    }
}
