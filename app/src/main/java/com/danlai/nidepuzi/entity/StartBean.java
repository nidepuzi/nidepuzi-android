package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by itxuyeon 2016/03/30.
 *
 * Copyright 2016年 上海己美. All rights reserved.
 */
public class StartBean {

  /**
   * picture : http://7xogkj.com2.z0.glb.qiniucdn.com/startup_ios8_retina_HD5.5.png
   * created : 2016-03-30T19:43:15.430
   */

  @SerializedName("picture") private String mPicture;
  @SerializedName("created") private String mCreated;

  public String getPicture() {
    return mPicture;
  }

  public void setPicture(String picture) {
    mPicture = picture;
  }

  public String getCreated() {
    return mCreated;
  }

  public void setCreated(String created) {
    mCreated = created;
  }
}
