package com.danlai.library.widget.message;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.danlai.library.R;

/**
 * @author wisdom
 * @date 2017年04月22日 下午4:06
 */

public class MessageItemView extends RelativeLayout {
    private TextView mTimeText;
    private TextView mDescText;

    public MessageItemView(Context context) {
        super(context);
        init(context, null);
    }

    public MessageItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_item_view, this, true);
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MessageItemView);
            ((TextView) view.findViewById(R.id.message_title)).setText(array.getString(
                R.styleable.MessageItemView_message_title));
            mTimeText = (TextView) view.findViewById(R.id.message_time);
            mDescText = (TextView) view.findViewById(R.id.message_desc);
            mTimeText.setText(array.getString(R.styleable.MessageItemView_message_time));
            mDescText.setText(array.getString(R.styleable.MessageItemView_message_desc));
            Drawable drawable = array.getDrawable(R.styleable.MessageItemView_message_icon);
            if (drawable != null) {
                ((ImageView) view.findViewById(R.id.message_icon)).setImageDrawable(drawable);
            }
        }
    }

    public void setMessageTime(String timeStr) {
        mTimeText.setText(timeStr);
    }

    public void setMessageDesc(String descStr) {
        mDescText.setText(descStr);
    }
}
