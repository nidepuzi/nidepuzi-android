package com.danlai.nidepuzi.ui.activity.product;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.BaseTabAdapter;
import com.danlai.nidepuzi.base.BaseFragment;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityCategoryProductBinding;
import com.danlai.nidepuzi.entity.event.SortEvent;
import com.danlai.nidepuzi.ui.fragment.product.ProductFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdom
 * @date 17/3/17
 */

public class CategoryProductActivity extends BaseMVVMActivity<ActivityCategoryProductBinding> implements View.OnClickListener {
    private ArrayList<String> nameList;
    private ArrayList<String> cidList;
    private int position;
    private ImageView sortDefaultImg;
    private ImageView sortPriceImg;
    private LinearLayout defaultSortLayout;
    private LinearLayout priceSortLayout;
    private PopupWindow popupWindow;


    @Override
    protected void setListener() {
        b.sortLayout.setOnClickListener(this);
        defaultSortLayout.setOnClickListener(this);
        priceSortLayout.setOnClickListener(this);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        nameList = extras.getStringArrayList("name");
        cidList = extras.getStringArrayList("cid");
        position = extras.getInt("position");
    }

    @Override
    protected void initViews() {
        b.titleView.setName(nameList.get(0));
        nameList.set(0, "全部");
        List<BaseFragment> fragments = new ArrayList<>();
        if (nameList.size() == cidList.size()) {
            for (int i = 0; i < nameList.size(); i++) {
                fragments.add(ProductFragment.newInstance(cidList.get(i), nameList.get(i),false));
            }
        }
        BaseTabAdapter mAdapter = new BaseTabAdapter(getSupportFragmentManager(), fragments);
        b.viewPager.setAdapter(mAdapter);
        b.viewPager.setOffscreenPageLimit(fragments.size());
        b.tabLayout.setupWithViewPager(b.viewPager);
        b.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        b.viewPager.setCurrentItem(position, true);
        View view = LayoutInflater.from(this).inflate(R.layout.pop_product_sort, null);
        sortDefaultImg = ((ImageView) view.findViewById(R.id.check_sort));
        sortPriceImg = ((ImageView) view.findViewById(R.id.check_price));
        defaultSortLayout = ((LinearLayout) view.findViewById(R.id.default_sort_layout));
        priceSortLayout = ((LinearLayout) view.findViewById(R.id.price_sort_layout));
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.sort_pop_bg));
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_category_product;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sort_layout:
                popupWindow.showAsDropDown(b.tabLayout);
                break;
            case R.id.default_sort_layout:
                b.sortImg.setImageResource(R.drawable.icon_sort);
                sortDefaultImg.setVisibility(View.VISIBLE);
                sortPriceImg.setVisibility(View.GONE);
                popupWindow.dismiss();
                EventBus.getDefault().post(new SortEvent(false));
                break;
            case R.id.price_sort_layout:
                b.sortImg.setImageResource(R.drawable.icon_sort_price);
                sortDefaultImg.setVisibility(View.GONE);
                sortPriceImg.setVisibility(View.VISIBLE);
                popupWindow.dismiss();
                EventBus.getDefault().post(new SortEvent(true));
                break;
        }
    }
}
