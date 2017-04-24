package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by itxuye(www.itxuye.com) on 2016/02/15.
 *
 * Copyright 2016年 上海己美. All rights reserved.
 */
public class NeedSetInfoBean {

  /**
   * mobile : 18021397781
   * info :
   * code : 0
   * result : no
   */

  //@SerializedName("mobile") private String mMobile;
  @SerializedName("info") private String mInfo;
  @SerializedName("code") private int mCode;
  //@SerializedName("result") private String mResult;

  //public void setMobile(String mobile) {
  //  this.mMobile = mobile;
  //}
  //
  public void setInfo(String info) {
    this.mInfo = info;
  }

  public void setCode(int code) {
    this.mCode = code;
  }
  //
  //public void setResult(String result) {
  //  this.mResult = result;
  //}
  //
  //public String getMobile() {
  //  return mMobile;
  //}
  //
  public String getInfo() {
    return mInfo;
  }

  public int getCode() {
    return mCode;
  }

  //public String getResult() {
  //  return mResult;
  //}
}
