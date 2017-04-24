package com.danlai.nidepuzi.util;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.danlai.nidepuzi.base.BaseFragment;

import java.util.List;


/**
 * Created by wisdom on 16/11/28.
 */
public class FragmentTabUtils implements RadioGroup.OnCheckedChangeListener {
    private FragmentManager manager;
    private List<BaseFragment> fragments;
    private int containerId;

    public FragmentTabUtils(FragmentManager manager, RadioGroup rgs, List<BaseFragment> fragments, int containerId) {
        this.manager = manager;
        this.fragments = fragments;
        this.containerId = containerId;
        rgs.setOnCheckedChangeListener(this);
        RadioButton rBtn = (RadioButton) rgs.getChildAt(0);
        rBtn.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount(); i++) {
            BaseFragment fragment = fragments.get(i);
            if (group.getChildAt(i).getId() == checkedId) {
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