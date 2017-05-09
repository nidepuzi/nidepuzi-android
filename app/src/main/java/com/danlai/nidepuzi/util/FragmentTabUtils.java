package com.danlai.nidepuzi.util;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.danlai.nidepuzi.base.BaseFragment;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;

import java.util.List;


/**
 * Created by wisdom on 16/11/28.
 */
public class FragmentTabUtils implements RadioGroup.OnCheckedChangeListener {
    private FragmentManager manager;
    private List<BaseFragment> fragments;
    private int containerId;
    private String data;
    private String userId;
    private Activity mActivity;
    private int lastId;

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
        if (group.getChildAt(3).getId() == checkedId && !TextUtils.isEmpty(data) && !TextUtils.isEmpty(data)) {
            String title = "铺子客服";
            YSFUserInfo ysfUserInfo = new YSFUserInfo();
            ysfUserInfo.userId = userId;
            ysfUserInfo.data = data;
            Unicorn.setUserInfo(ysfUserInfo);
            ConsultSource source = new ConsultSource("http://m.nidepuzi.com", "Android客户端", "Android客户端");
            Unicorn.openServiceActivity(mActivity, title, source);
            group.check(lastId);
        } else {
            for (int i = 0; i < group.getChildCount(); i++) {
                BaseFragment fragment = fragments.get(i);
                if (group.getChildAt(i).getId() == checkedId) {
                    if (!fragment.isAdded()) {
                        getFragmentTransaction().add(containerId, fragment).commitAllowingStateLoss();
                        fragment.setUserVisibleHint(true);
                    }
                    lastId = checkedId;
                    getFragmentTransaction().show(fragment).commitAllowingStateLoss();
                } else {
                    getFragmentTransaction().hide(fragment).commitAllowingStateLoss();
                }
            }
        }
    }


    private FragmentTransaction getFragmentTransaction() {
        return manager.beginTransaction();
    }

    public void setUserInfo(String data, String userId) {
        this.data = data;
        this.userId = userId;
    }
}