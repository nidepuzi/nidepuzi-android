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
    private int mDay, mHour, mMinute, mSecond;
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
                setText("0 时 0 分 0 秒");
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
                mDay = 0;
                mHour = 0;
                mMinute = 0;
                mSecond = 0;
                if (mOnEndListener != null) {
                    mOnEndListener.onEnd(CountDownView.this);
                }
                if (type == TYPE_ALL) {
                    setText("0 时 0 分 0 秒");
                } else if (type == TYPE_MINUTE) {
                    setText("00 : 00");
                }
                invalidate();
            }
        };
        mCountDownTimer.start();
    }

    private void update(long ms, int type) {
        if (type == TYPE_ALL) {
            mDay = (int) (ms / (1000 * 60 * 60 * 24));
            mHour = (int) ((ms % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            mMinute = (int) ((ms % (1000 * 60 * 60)) / (1000 * 60));
            mSecond = (int) ((ms % (1000 * 60)) / 1000);
            if (mDay > 0) {
                setText(mDay + " 天 " + mHour + " 时 " + mMinute + " 分 " + mSecond + " 秒 ");
            } else {
                setText(mHour + " 时 " + mMinute + " 分 " + mSecond + " 秒 ");
            }
        } else if (type == TYPE_MINUTE) {
            mMinute = (int) ((ms % (1000 * 60 * 60)) / (1000 * 60));
            mSecond = (int) ((ms % (1000 * 60)) / 1000);
            if (mMinute < 10) {
                setText("0" + mMinute + " : ");
            } else {
                setText(mMinute + " : ");
            }
            if (mSecond < 10) {
                setText(getText() + "0" + mSecond);
            } else {
                setText(getText() + "" + mSecond);
            }
        }
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
