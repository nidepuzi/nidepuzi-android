package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by itxuye(www.itxuye.com) on 2016/04/07.
 *
 * Copyright 2016年 上海己美. All rights reserved.
 */
public class CartsHisBean {

  /**
   * info : 购物车已存在
   * code : 1
   * result : 1
   */

  @SerializedName("info") private String mInfo;
  @SerializedName("code") private int mCode;
  @SerializedName("result") private String mResult;

  public String getInfo() {
    return mInfo;
  }

  public void setInfo(String info) {
    mInfo = info;
  }

  public int getCode() {
    return mCode;
  }

  public void setCode(int code) {
    mCode = code;
  }

  public String getResult() {
    return mResult;
  }

  public void setResult(String result) {
    mResult = result;
  }
}
