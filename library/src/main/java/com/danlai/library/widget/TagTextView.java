package com.danlai.library.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danlai.library.R;

/**
 * Created by wisdom on 16/8/10.
 */
public class TagTextView extends LinearLayout {
    private TextView nameTv;

    public TagTextView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.tag_view, this, true);
        nameTv = ((TextView) view.findViewById(R.id.name));
    }

    public void setTagName(String name) {
        nameTv.setText(name);
    }
}
