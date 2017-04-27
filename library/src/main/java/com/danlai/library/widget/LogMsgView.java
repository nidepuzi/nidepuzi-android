package com.danlai.library.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danlai.library.R;

/**
 * Created by wisdom on 16/4/12.
 */
public class LogMsgView extends LinearLayout {
    private TextView timeTv;
    private TextView msgTv;

    public LogMsgView(Context context) {
        super(context);
        init(context);
    }

    public LogMsgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LogMsgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LogMsgView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.log_msg_view, this);
        timeTv = ((TextView) view.findViewById(R.id.time));
        msgTv = ((TextView) view.findViewById(R.id.msg));
    }

    public void setTime(String time){
        timeTv.setText(time);
    }

    public void setMsg(String msg){
        msgTv.setText(msg);
    }
}
