package com.danlai.library.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.danlai.library.R;


/**
 * Created by wisdom on 16/4/12.
 */
public class LogImageView extends LinearLayout {
    public LogImageView(Context context) {
        super(context);
        init(context);
    }

    public LogImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LogImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LogImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.log_image_view,this);
    }
}
