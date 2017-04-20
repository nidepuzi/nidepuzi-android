package com.danlai.library.widget.loading;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * VaryViewHelper可以方便添加或移除view
 */
public class VaryViewHelper implements IVaryViewHelper {
    private View view;
    private ViewGroup parentView;
    private int viewIndex;
    private ViewGroup.LayoutParams params;

    public VaryViewHelper(View view) {
        this.view = view;
    }

    private void init() {
        params = view.getLayoutParams();
        if (view.getParent() != null) {
            parentView = (ViewGroup) view.getParent();
        } else {
            parentView = (ViewGroup) view.getRootView().findViewById(android.R.id.content);
        }
        int count = parentView.getChildCount();
        for (int index = 0; index < count; index++) {
            if (view == parentView.getChildAt(index)) {
                viewIndex = index;
                break;
            }
        }
    }

    @Override
    public void restoreView() {
        showLayout(view);
    }

    @Override
    public void showLayout(View view) {
        if (parentView == null) {
            init();
        }
        if (parentView.getChildAt(viewIndex) != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            parentView.removeViewAt(viewIndex);
            parentView.addView(view, viewIndex, params);
        }
    }

    @Override
    public View inflate(int layoutId) {
        return LayoutInflater.from(view.getContext()).inflate(layoutId, null);
    }
}
