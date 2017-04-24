package com.danlai.library.widget.scrollrecycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by wisdom on 17/2/23.
 */

public class ScrollRecycler extends RecyclerView {
    public ScrollRecycler(Context context) {
        super(context);
    }

    public ScrollRecycler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollRecycler(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnScrollCallback(final OnScrollCallback callback) {
        if (callback == null) {
            return;
        }
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                callback.onStateChanged(newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                callback.onScroll(dx);
            }
        });
    }
}
