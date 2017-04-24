package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wulei on 2016/2/20.
 */
public class UserAccountBean {

  /**
   * user_account : customer-19
   */

  @SerializedName("user_account") private String userAccount;

  public void setUserAccount(String userAccount) {
    this.userAccount = userAccount;
  }

  public String getUserAccount() {
    return userAccount;
  }

  @Override public String toString() {
    return "UserAccountBean{" +
        "userAccount='" + userAccount + '\'' +
        '}';
  }
}
