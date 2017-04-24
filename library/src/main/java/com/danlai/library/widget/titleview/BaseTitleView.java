package com.danlai.library.widget.titleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.danlai.library.R;

/**
 * @author wisdom
 * @date 2016年04月13日 下午2:22
 */
public class BaseTitleView extends RelativeLayout {
    private TextView name;
    private RelativeLayout layout;

    public BaseTitleView(Context context) {
        super(context);
        init(context, null);
    }

    public BaseTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.title_view, this, true);
        name = ((TextView) view.findViewById(R.id.tv_title));
        layout = ((RelativeLayout) view.findViewById(R.id.title_layout));
        RelativeLayout finishLayout = (RelativeLayout) view.findViewById(R.id.finish_rl);
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BaseTitleView);
            name.setText(array.getString(R.styleable.BaseTitleView_title));
            if (!array.getBoolean(R.styleable.BaseTitleView_show_finish, true)) {
                finishLayout.setVisibility(GONE);
            }
            setAlpha(array.getFloat(R.styleable.BaseTitleView_alpha, 1));
            setBackColor(array.getColor(R.styleable.BaseTitleView_back_color, getResources().getColor(R.color.colorAccent)));
        }
        if (context instanceof AppCompatActivity) {
            finishLayout.setOnClickListener(v -> ((AppCompatActivity) context).finish());
        }
    }

    public void setName(String nameStr) {
        name.setText(nameStr);
    }

    public void setBackColor(int color) {
        layout.setBackgroundColor(color);
    }
}
