package com.danlai.nidepuzi.entity;

/**
 * @author wisdom
 * @date 2017年05月05日 下午2:10
 */

public class OrderContent {
    private int orderId;
    private String url;
    private String name;
    private String size;
    private double price;
    private int profit;
    private int num;
    private boolean self_buy;

    public OrderContent(int orderId, String url, String name, String size, double price, int profit, int num, boolean self_buy) {
        this.orderId = orderId;
        this.url = url;
        this.name = name;
        this.size = size;
        this.price = price;
        this.profit = profit;
        this.num = num;
        this.self_buy = self_buy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public boolean isSelf_buy() {
        return self_buy;
    }

    public void setSelf_buy(boolean self_buy) {
        this.self_buy = self_buy;
    }
}
