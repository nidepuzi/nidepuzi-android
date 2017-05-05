package com.danlai.nidepuzi.entity;

/**
 * @author wisdom
 * @date 2017年05月05日 下午1:38
 */

public class OrderFooter {

    private int orderId;
    private double payment;
    private double postFee;

    public OrderFooter(int orderId, double payment, double postFee) {
        this.orderId = orderId;
        this.payment = payment;
        this.postFee = postFee;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getPostFee() {
        return postFee;
    }

    public void setPostFee(double postFee) {
        this.postFee = postFee;
    }
}
