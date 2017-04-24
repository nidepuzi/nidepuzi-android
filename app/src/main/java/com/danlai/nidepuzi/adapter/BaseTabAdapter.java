package com.danlai.nidepuzi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.danlai.nidepuzi.base.BaseFragment;

import java.util.List;


/**
 * Created by wisdom on 16/9/12.
 */
public class BaseTabAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> listFragment;

    public BaseTabAdapter(FragmentManager fm, List<BaseFragment> listFragment) {
        super(fm);
        this.listFragment = listFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listFragment.get(position).getTitle();
    }
}
