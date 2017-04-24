package com.danlai.nidepuzi.entity;

/**
 * Created by itxuye(www.itxuye.com) on 2016/03/28.
 *
 * Copyright 2016年 上海己美. All rights reserved.
 */
public class PayInfoBean {

  /**
   * info : 支付成功
   * trade : {"id":45555,"tid":"xd2016xxx","channel":"wx"}
   * charge : {"order_no":"xd160623576b3efc3a519","extra":{"open_id":"our5huIOSuFF5FdojFMFNP5HNOmA"},"app":"app_LOOajDn9u9WDjfHa","livemode":true,"currency":"cny","time_settle":null,"time_expire":1466653471,"id":"ch_WfbbTKyTKmT44iTmXLyLGKSK","subject":"小鹿美美平台交易","failure_msg":null,"channel":"wx_pub","metadata":{"color":"red"},"body":"订单ID(366559),订单金额(69.90)","credential":{"object":"credential","wx_pub":{"package":"prepay_id=wx201606230944313536d8cf380312696196","timeStamp":"1466646271","signType":"MD5","paySign":"3D1C88384E5F1420A7EFC432B3928053","appId":"wx3f91056a2928ad2d","nonceStr":"3b9fcceda9eed71e5913806f60935792"}},"client_ip":"121.199.168.159","description":null,"amount_refunded":0,"refunded":false,"object":"charge","paid":false,"amount_settle":6948,"time_paid":null,"failure_code":null,"refunds":{"url":"/v1/charges/ch_WfbbTKyTKmT44iTmXLyLGKSK/refunds","has_more":false,"object":"list","data":[]},"created":1466646271,"transaction_no":null,"amount":6990}
   * code : 0
   * channel : wx_pub
   */

  private String info;
  /**
   * id : 45555
   * tid : xd2016xxx
   * channel : wx
   */

  private TradeBean trade;
  //charge是Object ,禁止修改
  private Object charge;
  private int code;
  private String channel;

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public TradeBean getTrade() {
    return trade;
  }

  public void setTrade(TradeBean trade) {
    this.trade = trade;
  }

  public Object getCharge() {
    return charge;
  }

  public void setCharge(Object charge) {
    this.charge = charge;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public static class TradeBean {
    private int id;
    private String tid;
    private String channel;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getTid() {
      return tid;
    }

    public void setTid(String tid) {
      this.tid = tid;
    }

    public String getChannel() {
      return channel;
    }

    public void setChannel(String channel) {
      this.channel = channel;
    }
  }
}
