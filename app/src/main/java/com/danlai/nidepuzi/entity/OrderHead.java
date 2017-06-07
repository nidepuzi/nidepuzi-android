package com.danlai.nidepuzi.entity;

/**
 * @author wisdom
 * @date 2017年05月05日 下午1:38
 */

public class OrderHead {
    private String time;
    private String status;
    private int sale_type;

    public OrderHead(String time, String status, int sale_type) {
        this.time = time;
        this.status = status;
        this.sale_type = sale_type;
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

    public int getSale_type() {
        return sale_type;
    }

    public void setSale_type(int sale_type) {
        this.sale_type = sale_type;
    }
}
