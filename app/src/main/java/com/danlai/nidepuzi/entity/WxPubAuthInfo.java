package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wulei on 2016/2/29.
 */
public class WxPubAuthInfo {

  /**
   * auth_msg : 将图片二维码图片保存本地后，打开微信扫一扫从相册选取二维码图片
   * auth_link : http://www.baidu.com
   */

  @SerializedName("auth_msg") private String authMsg;
  @SerializedName("auth_link") private String authLink;

  public void setAuthMsg(String authMsg) {
    this.authMsg = authMsg;
  }

  public void setAuthLink(String authLink) {
    this.authLink = authLink;
  }

  public String getAuthMsg() {
    return authMsg;
  }

  public String getAuthLink() {
    return authLink;
  }

  @Override public String toString() {
    return "WxPubAuthInfo{" +
        "authMsg='" + authMsg + '\'' +
        ", authLink='" + authLink + '\'' +
        '}';
  }
}
