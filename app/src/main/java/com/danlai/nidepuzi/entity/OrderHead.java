package com.danlai.nidepuzi.entity;

/**
 * @author wisdom
 * @date 2017年05月05日 下午1:38
 */

public class OrderHead {
    private String time;
    private String status;

    public OrderHead(String time, String status) {
        this.time = time;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
