package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 优尼世界 on 15/12/29.
 *
 * Copyright 2015年 上海己美. All rights reserved.
 */
public class NicknameBean {

  /**
   * code : 0
   * result : login
   * next : /index.html
   */

  @SerializedName("nick") private String mNickname;


  public String getNick() {
    return mNickname;
  }

  public void setNick(String nickname) {
    this.mNickname = nickname;
  }

  

  @Override public String toString() {
    return "NicknameBean{" +
        "mNickname=" + mNickname +
         '\'' +
        '}';
  }
}
