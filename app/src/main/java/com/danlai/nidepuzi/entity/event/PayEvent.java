package com.danlai.nidepuzi.entity.event;

/**
 * Created by wisdom on 16/11/17.
 */

public class PayEvent {
    private String pay_result;
    private String error_msg;
    private String extra_msg;

    public PayEvent(String pay_result, String error_msg, String extra_msg) {
        this.pay_result = pay_result;
        this.error_msg = error_msg;
        this.extra_msg = extra_msg;
    }

    public String getPay_result() {
        return pay_result;
    }

    public void setPay_result(String pay_result) {
        this.pay_result = pay_result;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getExtra_msg() {
        return extra_msg;
    }

    public void setExtra_msg(String extra_msg) {
        this.extra_msg = extra_msg;
    }
}
