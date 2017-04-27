package com.danlai.nidepuzi.entity.event;

/**
 * @author wisdom
 * @date 2017年04月15日 上午10:47
 */

public class CouponEvent {
    private int position;
    private String title;

    public CouponEvent(int position, String title) {
        this.position = position;
        this.title = title;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
