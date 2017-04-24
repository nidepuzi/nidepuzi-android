package com.danlai.library.widget.scrollrecycler;

/**
 * Created by wisdom on 17/2/23.
 */

public interface OnScrollCallback {

    void onStateChanged(int state);

    void onScroll(int dx);
}
