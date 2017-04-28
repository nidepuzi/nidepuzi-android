package com.danlai.library.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.danlai.library.R;

import java.util.Calendar;


/**
 * Created by wisdom on 16/4/11.
 */
public class PreferenceView extends RelativeLayout implements View.OnClickListener {

    private TextView summaryText;
    private ImageView imageView;
    private Context context;
    private Activity activity;
    private Intent intent;
    private long lastClickTime = 0;
    private SwitchCompat mSwitchCompat;

    public PreferenceView(Context context) {
        super(context);
        init(context, null);
    }

    public PreferenceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PreferenceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PreferenceView);
        View view;
        if (array.getBoolean(R.styleable.PreferenceView_preference_hide_icon, false)) {
            view = LayoutInflater.from(context).inflate(R.layout.preference_list_text, this, true);
            imageView = (ImageView) view.findViewById(R.id.img);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.preference_list_item, this, true);
            mSwitchCompat = ((SwitchCompat) view.findViewById(R.id.check_switch));
            imageView = (ImageView) view.findViewById(R.id.img);
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            Drawable drawable = array.getDrawable(R.styleable.PreferenceView_preference_icon);
            icon.setImageDrawable(drawable);
            if (array.getBoolean(R.styleable.PreferenceView_preference_show_switch, false)) {
                hideImg();
                mSwitchCompat.setVisibility(VISIBLE);
            }
        }
        TextView titleText = ((TextView) view.findViewById(R.id.title));
        summaryText = ((TextView) view.findViewById(R.id.summary));
        setOnClickListener(this);
        titleText.setText(array.getString(R.styleable.PreferenceView_preference_title));
        summaryText.setText(array.getString(R.styleable.PreferenceView_preference_desc));
    }

    public void hideImg() {
        imageView.setVisibility(GONE);
    }

    public void setSummary(String summary) {
        summaryText.setText(summary);
    }

    public void setSwitchListener(CompoundButton.OnCheckedChangeListener listener) {
        mSwitchCompat.setOnCheckedChangeListener(listener);
    }

    public void bindActivity(Activity activity, Class clz, Bundle bundle) {
        this.activity = activity;
        intent = new Intent(context, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
    }

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > 2000L) {
            lastClickTime = currentTime;
            if (intent != null) {
                activity.startActivity(intent);
            }
        }
    }

    public void addBundle(Bundle bundle) {
        intent.putExtras(bundle);
    }
}
