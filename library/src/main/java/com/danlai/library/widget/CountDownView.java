package com.danlai.library.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by wisdom on 16/9/26.
 */

public class CountDownView extends TextView {
    public static final int TYPE_ALL = 1;
    public static final int TYPE_MINUTE = 2;
    private int mHour, mMinute, mSecond;
    private CountDownTimer mCountDownTimer;
    private OnEndListener mOnEndListener;

    public CountDownView(Context context) {
        super(context);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void start(long millisecond, int type) {
        if (millisecond < 1000) {
            if (type == TYPE_ALL) {
                setText("00 : 00 : 00");
            } else if (type == TYPE_MINUTE) {
                setText("00 : 00");
            }
            return;
        }
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        mCountDownTimer = new CountDownTimer(millisecond, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                update(millisUntilFinished, type);
            }

            @Override
            public void onFinish() {
                mHour = 0;
                mMinute = 0;
                mSecond = 0;
                if (mOnEndListener != null) {
                    mOnEndListener.onEnd(CountDownView.this);
                }
                if (type == TYPE_ALL) {
                    setText("00 : 00 : 00");
                } else if (type == TYPE_MINUTE) {
                    setText("00 : 00");
                }
                invalidate();
            }
        };
        mCountDownTimer.start();
    }

    private void update(long ms, int type) {
        StringBuilder sb = new StringBuilder("");
        if (type == TYPE_ALL) {
            mHour = (int) ((ms % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            if (mHour < 10) {
                sb.append("0");
            }
            sb.append(mHour).append(" : ");
        }
        mMinute = (int) ((ms % (1000 * 60 * 60)) / (1000 * 60));
        mSecond = (int) ((ms % (1000 * 60)) / 1000);
        if (mMinute < 10) {
            sb.append("0");
        }
        sb.append(mMinute).append(" : ");
        if (mSecond < 10) {
            sb.append("0");
        }
        sb.append(mSecond);
        setText(sb.toString());
        invalidate();
    }

    public void cancel() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    public void setOnEndListener(OnEndListener onEndListener) {
        mOnEndListener = onEndListener;
    }


    public interface OnEndListener {
        void onEnd(CountDownView view);
    }
}
