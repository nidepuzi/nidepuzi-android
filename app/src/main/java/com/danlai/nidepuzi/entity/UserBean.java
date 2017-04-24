package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 优尼世界 on 15/12/29.
 *
 * Copyright 2015年 上海己美. All rights reserved.
 */
public class UserBean {

  /**
   * code : 0
   * result : login
   * next : /index.html
   */

  @SerializedName("code") private int mCode;
  @SerializedName("result") private String mResult;
  @SerializedName("next") private String mNext;

  public int getCode() {
    return mCode;
  }

  public void setCode(int code) {
    this.mCode = code;
  }

  public String getResult() {
    return mResult;
  }

  public void setResult(String result) {
    this.mResult = result;
  }

  public String getNext() {
    return mNext;
  }

  public void setNext(String next) {
    this.mNext = next;
  }

  @Override public String toString() {
    return "UserBean{" +
        "mCode=" + mCode +
        ", mResult='" + mResult + '\'' +
        ", mNext='" + mNext + '\'' +
        '}';
  }
}
