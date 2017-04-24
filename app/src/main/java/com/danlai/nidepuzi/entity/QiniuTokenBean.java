package com.danlai.nidepuzi.entity;

/**
 * Created by wulei on 2016/1/29.
 */
public class QiniuTokenBean {
  String uptoken;

  public String getUptoken() {
    return uptoken;
  }

  public void setUptoken(String uptoken) {
    this.uptoken = uptoken;
  }

  @Override public String toString() {
    return "QiniuTokenBean{" +
        "uptoken='" + uptoken + '\'' +
        '}';
  }
}
