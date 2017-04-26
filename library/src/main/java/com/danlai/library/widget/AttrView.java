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
public class AttrView extends LinearLayout {
    private TextView nameTv, valueTv;

    public AttrView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_attr_view, this, true);
        nameTv = ((TextView) view.findViewById(R.id.name));
        valueTv = ((TextView) view.findViewById(R.id.value));
    }

    public void setAttrName(String name) {
        nameTv.setText(name);
    }

    public void setAttrValue(String value) {
        valueTv.setText(value);
    }
}
