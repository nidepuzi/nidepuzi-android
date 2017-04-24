package com.danlai.nidepuzi.entity;

/**
 * Created by 优尼世界 on 2016/01/15.
 * <p>
 * Copyright 2015年 上海己美. All rights reserved.
 */
public class CartsInfoBean {


    /**
     * id : 623373
     * url : http://m.xiaolumeimei.com/rest/v1/carts/623373
     * buyer_id : 1
     * buyer_nick : meron@小鹿美美
     * item_id : 64593
     * title : 双层水晶珍珠毛衣链/古铜色款
     * price : 139
     * std_sale_price : 199
     * sku_id : 242496
     * num : 1
     * total_fee : 139
     * sku_name : 均码
     * model_id : 19187
     * pic_path : http://img.xiaolumeimei.com/MG_14720022653682.jpg
     * created : 2016-08-24T14:46:24
     * is_repayable : false
     * status : 0
     * item_weburl : http://m.xiaolumeimei.com/mall/product/details/19187
     */

    private int id;
    private String url;
    private int buyer_id;
    private String buyer_nick;
    private String item_id;
    private String title;
    private double price;
    private double std_sale_price;
    private String sku_id;
    private int num;
    private double total_fee;
    private String sku_name;
    private int model_id;
    private String pic_path;
    private String created;
    private boolean is_repayable;
    private int status;
    private String item_weburl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getBuyer_nick() {
        return buyer_nick;
    }

    public void setBuyer_nick(String buyer_nick) {
        this.buyer_nick = buyer_nick;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getStd_sale_price() {
        return std_sale_price;
    }

    public void setStd_sale_price(double std_sale_price) {
        this.std_sale_price = std_sale_price;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(double total_fee) {
        this.total_fee = total_fee;
    }

    public String getSku_name() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name = sku_name;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isIs_repayable() {
        return is_repayable;
    }

    public void setIs_repayable(boolean is_repayable) {
        this.is_repayable = is_repayable;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getItem_weburl() {
        return item_weburl;
    }

    public void setItem_weburl(String item_weburl) {
        this.item_weburl = item_weburl;
    }

    @Override
    public String toString() {
        return "CartsInfoBean{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", buyer_id=" + buyer_id +
                ", buyer_nick='" + buyer_nick + '\'' +
                ", item_id='" + item_id + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", std_sale_price=" + std_sale_price +
                ", sku_id='" + sku_id + '\'' +
                ", num=" + num +
                ", total_fee=" + total_fee +
                ", sku_name='" + sku_name + '\'' +
                ", model_id=" + model_id +
                ", pic_path='" + pic_path + '\'' +
                ", created='" + created + '\'' +
                ", is_repayable=" + is_repayable +
                ", status=" + status +
                ", item_weburl='" + item_weburl + '\'' +
                '}';
    }
}
