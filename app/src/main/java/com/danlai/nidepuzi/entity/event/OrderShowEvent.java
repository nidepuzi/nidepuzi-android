package com.danlai.nidepuzi.entity.event;

/**
 * @author wisdom
 * @date 2017年05月04日 下午5:03
 */

public class OrderShowEvent {

    private boolean isShow;

    public OrderShowEvent(boolean isShow) {
        this.isShow = isShow;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
