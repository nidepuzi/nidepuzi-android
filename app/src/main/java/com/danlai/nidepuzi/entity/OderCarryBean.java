package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by itxuye(www.itxuye.com) on 2016/03/14.
 *
 * Copyright 2016年 上海己美. All rights reserved.
 */
public class OderCarryBean {

  /**
   * count : 2
   * next :
   * previous :
   * results : [{"mama_id":5,"order_id":"xo16031356e575b2aa9a2","order_value":100,"carry_num":0,"carry_type":2,"carry_type_name":"App粉丝订单","sku_name":"测试商品","sku_img":"","contributor_nick":"笑嘻嘻","contributor_img":"
   * ","contributor_id":29,"agency_level":1,"carry_plan_name":"代理佣金初始计划","date_field":"2016-03-13","status":1,"status_display":"待确定","modified":"2016-03-14T12:41:15","created":"2016-03-13T22:23:44","today_carry":0},{"mama_id":5,"order_id":"xo16022956d3ed73aa987","order_value":156,"carry_num":0,"carry_type":2,"carry_type_name":"App粉丝订单","sku_name":"秒杀
   * 绅士蝴蝶结拼接衬衫/黑色","sku_img":"https://mmbiz.qlogo.cn/mmbiz/yMhOQPTKhLsyAicOriaIXNVf9EOd1QDQQV7AgIjWQq7E6wib5gBF1E7cIMZbHjiaecFjXVCs5sWIvSGIPu6CyDc7Yw/0?wx_fmt=png","contributor_nick":"笑嘻嘻","contributor_img":"
   * ","contributor_id":29,"agency_level":1,"carry_plan_name":"代理佣金初始计划","date_field":"2016-02-29","status":1,"status_display":"待确定","modified":"2016-03-13T22:03:36","created":"2016-03-12T13:54:12","today_carry":0}]
   */

  @SerializedName("count") private int mCount;
  @SerializedName("next") private String mNext;
  @SerializedName("previous") private String mPrevious;
  /**
   * mama_id : 5
   * order_id : xo16031356e575b2aa9a2
   * order_value : 100.0
   * carry_num : 0.0
   * carry_type : 2
   * carry_type_name : App粉丝订单
   * sku_name : 测试商品
   * sku_img :
   * contributor_nick : 笑嘻嘻
   * contributor_img :
   * contributor_id : 29
   * agency_level : 1
   * carry_plan_name : 代理佣金初始计划
   * date_field : 2016-03-13
   * status : 1
   * status_display : 待确定
   * modified : 2016-03-14T12:41:15
   * created : 2016-03-13T22:23:44
   * today_carry : 0.0
   */

  @SerializedName("results") private List<ResultsEntity> mResults;

  public int getCount() {
    return mCount;
  }

  public void setCount(int count) {
    this.mCount = count;
  }

  public String getNext() {
    return mNext;
  }

  public void setNext(String next) {
    this.mNext = next;
  }

  public String getPrevious() {
    return mPrevious;
  }

  public void setPrevious(String previous) {
    this.mPrevious = previous;
  }

  public List<ResultsEntity> getResults() {
    return mResults;
  }

  public void setResults(List<ResultsEntity> results) {
    this.mResults = results;
  }

  public static class ResultsEntity {

    /**
     * mama_id : 44
     * order_id : xo16083157c65a8f9a742
     * order_value : 37.9
     * carry_value : 6.6
     * carry_num : 6.6
     * carry_type : 2
     * carry_type_name : App订单额外+10%
     * sku_name : 可爱跳跳虎连体睡衣/跳跳虎
     * sku_img : http://img.xiaolumeimei.com/MG_1472439330933.jpg
     * contributor_nick : 小南
     * carry_description : APP粉丝佣金，粉丝多赚钱快！
     * contributor_img : http://wx.qlogo.cn/mmopen/PiajxSqBRaEKEW0CsV82dPCcbmDF1Lx0xxh9ozam8qOpQ5V7ZsKiaxFAHNibWl7zXa18bDMKVXcV7611nTdAMz9rg/0
     * contributor_id : 40476
     * agency_level : 2
     * carry_plan_name : 代理佣金初始计划
     * date_field : 2016-08-31
     * status : 1
     * status_display : 预计收益
     * modified : 2016-08-31T12:18:32
     * created : 2016-08-31T12:18:23
     * today_carry : 6.6
     * packetid :
     * company_code :
     */

    private int mama_id;
    private String order_id;
    private double order_value;
    private double carry_value;
    private double carry_num;
    private int carry_type;
    private String carry_type_name;
    private String sku_name;
    private String sku_img;
    private String contributor_nick;
    private String carry_description;
    private String contributor_img;
    private int contributor_id;
    private int agency_level;
    private String carry_plan_name;
    private String date_field;
    private int status;
    private String status_display;
    private String modified;
    private String created;
    private double today_carry;
    private String packetid;
    private String company_code;

    public int getMama_id() {
      return mama_id;
    }

    public void setMama_id(int mama_id) {
      this.mama_id = mama_id;
    }

    public String getOrder_id() {
      return order_id;
    }

    public void setOrder_id(String order_id) {
      this.order_id = order_id;
    }

    public double getOrder_value() {
      return order_value;
    }

    public void setOrder_value(double order_value) {
      this.order_value = order_value;
    }

    public double getCarry_value() {
      return carry_value;
    }

    public void setCarry_value(double carry_value) {
      this.carry_value = carry_value;
    }

    public double getCarry_num() {
      return carry_num;
    }

    public void setCarry_num(double carry_num) {
      this.carry_num = carry_num;
    }

    public int getCarry_type() {
      return carry_type;
    }

    public void setCarry_type(int carry_type) {
      this.carry_type = carry_type;
    }

    public String getCarry_type_name() {
      return carry_type_name;
    }

    public void setCarry_type_name(String carry_type_name) {
      this.carry_type_name = carry_type_name;
    }

    public String getSku_name() {
      return sku_name;
    }

    public void setSku_name(String sku_name) {
      this.sku_name = sku_name;
    }

    public String getSku_img() {
      return sku_img;
    }

    public void setSku_img(String sku_img) {
      this.sku_img = sku_img;
    }

    public String getContributor_nick() {
      return contributor_nick;
    }

    public void setContributor_nick(String contributor_nick) {
      this.contributor_nick = contributor_nick;
    }

    public String getCarry_description() {
      return carry_description;
    }

    public void setCarry_description(String carry_description) {
      this.carry_description = carry_description;
    }

    public String getContributor_img() {
      return contributor_img;
    }

    public void setContributor_img(String contributor_img) {
      this.contributor_img = contributor_img;
    }

    public int getContributor_id() {
      return contributor_id;
    }

    public void setContributor_id(int contributor_id) {
      this.contributor_id = contributor_id;
    }

    public int getAgency_level() {
      return agency_level;
    }

    public void setAgency_level(int agency_level) {
      this.agency_level = agency_level;
    }

    public String getCarry_plan_name() {
      return carry_plan_name;
    }

    public void setCarry_plan_name(String carry_plan_name) {
      this.carry_plan_name = carry_plan_name;
    }

    public String getDate_field() {
      return date_field;
    }

    public void setDate_field(String date_field) {
      this.date_field = date_field;
    }

    public int getStatus() {
      return status;
    }

    public void setStatus(int status) {
      this.status = status;
    }

    public String getStatus_display() {
      return status_display;
    }

    public void setStatus_display(String status_display) {
      this.status_display = status_display;
    }

    public String getModified() {
      return modified;
    }

    public void setModified(String modified) {
      this.modified = modified;
    }

    public String getCreated() {
      return created;
    }

    public void setCreated(String created) {
      this.created = created;
    }

    public double getToday_carry() {
      return today_carry;
    }

    public void setToday_carry(double today_carry) {
      this.today_carry = today_carry;
    }

    public String getPacketid() {
      return packetid;
    }

    public void setPacketid(String packetid) {
      this.packetid = packetid;
    }

    public String getCompany_code() {
      return company_code;
    }

    public void setCompany_code(String company_code) {
      this.company_code = company_code;
    }
  }
}
