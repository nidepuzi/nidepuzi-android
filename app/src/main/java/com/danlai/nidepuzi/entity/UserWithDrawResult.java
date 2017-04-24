package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wulei on 2016/2/29.
 */
public class UserWithDrawResult {

  /**
   * qrcode :
   * message : 提现成功
   * code : 0
   */

  @SerializedName("qrcode") private String qrcode;
  @SerializedName("message") private String message;
  @SerializedName("code") private int code;

  public void setQrcode(String qrcode) {
    this.qrcode = qrcode;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getQrcode() {
    return qrcode;
  }

  public String getMessage() {
    return message;
  }

  public int getCode() {
    return code;
  }
}
