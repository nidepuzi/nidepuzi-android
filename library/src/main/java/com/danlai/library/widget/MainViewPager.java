package com.danlai.library.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wisdom on 17/2/18.
 */

public class MainViewPager extends ViewPager {
    private boolean scrollable;

    public MainViewPager(Context context) {
        super(context);
        setScrollable(true);
    }

    public MainViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setScrollable(true);
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return scrollable && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return scrollable && super.onInterceptTouchEvent(arg0);
    }

}
