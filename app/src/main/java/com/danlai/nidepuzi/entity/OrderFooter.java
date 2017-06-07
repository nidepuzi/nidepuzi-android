package com.danlai.nidepuzi.entity;

/**
 * @author wisdom
 * @date 2017年05月05日 下午1:38
 */

public class OrderFooter {

    private int orderId;
    private double payment;
    private double postFee;
    private int status;
    private boolean self_buy;

    public OrderFooter(int orderId, double payment, double postFee, int status, boolean self_buy) {
        this.orderId = orderId;
        this.payment = payment;
        this.postFee = postFee;
        this.status = status;
        this.self_buy = self_buy;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSelf_buy() {
        return self_buy;
    }

    public void setSelf_buy(boolean self_buy) {
        this.self_buy = self_buy;
    }
}
