package com.danlai.library.widget.loading;

import android.view.View;

interface IVaryViewHelper {

    void restoreView();

    void showLayout(View view);

    View inflate(int layoutId);

}