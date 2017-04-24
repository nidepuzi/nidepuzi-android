package com.danlai.nidepuzi.util;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.danlai.library.utils.StatusBarUtil;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseFragment;

import java.util.List;


/**
 * Created by wisdom on 16/11/28.
 */
public class FragmentTabUtils implements RadioGroup.OnCheckedChangeListener {
    private FragmentManager manager;
    private List<BaseFragment> fragments;
    private int containerId;
    private Activity mActivity;

    public FragmentTabUtils(FragmentManager manager, RadioGroup rgs, List<BaseFragment> fragments,
                            int containerId, Activity mActivity) {
        this.manager = manager;
        this.fragments = fragments;
        this.containerId = containerId;
        this.mActivity = mActivity;
        rgs.setOnCheckedChangeListener(this);
        RadioButton rBtn = (RadioButton) rgs.getChildAt(0);
        rBtn.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount(); i++) {
            BaseFragment fragment = fragments.get(i);
            if (group.getChildAt(i).getId() == checkedId) {
                if (i == 2) {
                    StatusBarUtil.setColorNoTranslucent(mActivity,
                        mActivity.getResources().getColor(R.color.shop_top));
                }else {
                    StatusBarUtil.setColorNoTranslucent(mActivity,
                        mActivity.getResources().getColor(R.color.colorAccent));
                }
                if (!fragment.isAdded()) {
                    getFragmentTransaction().add(containerId, fragment).commitAllowingStateLoss();
                    fragment.setUserVisibleHint(true);
                }
                getFragmentTransaction().show(fragment).commitAllowingStateLoss();
            } else {
                getFragmentTransaction().hide(fragment).commitAllowingStateLoss();
            }
        }
    }


    private FragmentTransaction getFragmentTransaction() {
        return manager.beginTransaction();
    }
}