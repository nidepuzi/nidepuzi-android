package com.danlai.nidepuzi.ui.activity.shop;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.danlai.library.widget.PageSelectedListener;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.ImageAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityImagePagerBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itxuye on 2016/02/29.
 * <p>
 * Copyright 2016年 上海己美. All rights reserved.
 */
public class ImagePagerActivity extends BaseMVVMActivity<ActivityImagePagerBinding> {

    public static final String INTENT_IMGURLS = "imgurls";
    public static final String INTENT_POSITION = "position";
    private List<View> guideViewList = new ArrayList<>();

    public static void startImagePagerActivity(Context context, List<String> imgUrls, int position) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        intent.putStringArrayListExtra(INTENT_IMGURLS, new ArrayList<>(imgUrls));
        intent.putExtra(INTENT_POSITION, position);
        context.startActivity(intent);
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_image_pager;
    }

    @Override
    protected void initViews() {
        int startPos = getIntent().getIntExtra(INTENT_POSITION, 0);
        ArrayList<String> imgUrls = getIntent().getStringArrayListExtra(INTENT_IMGURLS);
        ImageAdapter mAdapter = new ImageAdapter(this, this);
        mAdapter.setDatas(imgUrls);
        b.pager.setAdapter(mAdapter);
        b.pager.addOnPageChangeListener(new PageSelectedListener() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < guideViewList.size(); i++) {
                    guideViewList.get(i).setSelected(i == position);
                }
            }
        });
        b.pager.setCurrentItem(startPos);
        addGuideView(b.guideLayout, startPos, imgUrls);
    }

    private void addGuideView(LinearLayout guideGroup, int startPos, ArrayList<String> imgUrls) {
        if (imgUrls != null && imgUrls.size() > 0) {
            guideViewList.clear();
            for (int i = 0; i < imgUrls.size(); i++) {
                View view = new View(this);
                view.setBackgroundResource(R.drawable.selector_guide_bg);
                view.setSelected(i == startPos);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    getResources().getDimensionPixelSize(R.dimen.guide_view_width),
                    getResources().getDimensionPixelSize(R.dimen.guide_view_height));
                layoutParams.setMargins(10, 0, 10, 0);
                guideGroup.addView(view, layoutParams);
                guideViewList.add(view);
            }
        }
    }
}
