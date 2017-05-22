package com.danlai.library.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author wisdom
 * @date 2016年06月21日 下午2:25
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int left, right, top, bottom;
    private boolean flag;

    public SpaceItemDecoration(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        flag = false;
    }

    public SpaceItemDecoration(int space) {
        this.space = space;
        flag = true;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (flag) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            outRect.top = space;
        } else {
            outRect.left = left;
            outRect.right = right;
            outRect.bottom = bottom;
            outRect.top = top;
        }
    }
}
