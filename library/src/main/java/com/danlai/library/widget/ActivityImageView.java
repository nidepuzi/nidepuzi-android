package com.danlai.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.danlai.library.utils.JUtils;

/**
 * Created by wisdom on 17/2/28.
 */

public class ActivityImageView extends ImageView {
    public ActivityImageView(Context context) {
        super(context);
    }

    public ActivityImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(JUtils.getScreenWidth(), JUtils.getScreenWidth() / 2);
    }
}
