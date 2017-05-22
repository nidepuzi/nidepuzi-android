package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by itxuye on 2016/02/29.
 *
 * Copyright 2016年 上海己美. All rights reserved.
 */
public class NinePicBean {

  /**
   * id : 35
   * title : 【小鹿美美】 外贸原单 天天惊喜
   * 开学了，送孩子去学校，我家宝贝的新衣服就是抢眼，要不要告诉她们是在小鹿商城买的呢？要不要呢？毫不犹豫：要的，让所有孩子一起亮起来
   * start_time : 2016-02-29T10:00:00
   * turns_num : 1
   * pic_arry : ["http://image.xiaolu.so/poster_1456714203491","http://image.xiaolu.so/poster_1456714203918","http://image.xiaolu.so/poster_1456714205048","http://image.xiaolu.so/poster_1456714206038","http://image.xiaolu.so/poster_1456714207101","http://image.xiaolu.so/poster_1456714208353","http://image.xiaolu.so/poster_1456714208530","http://image.xiaolu.so/poster_1456714208785","http://image.xiaolu.so/poster_1456714209955"]
   * could_share : 1
   * description : 【小鹿美美】 外贸原单 天天惊喜
   * 开学了，送孩子去学校，我家宝贝的新衣服就是抢眼，要不要告诉她们是在小鹿商城买的呢？要不要呢？毫不犹豫：要的，让所有孩子一起亮起来
   */

  @SerializedName("id") private int mId;
  @SerializedName("title") private String mTitle;
  @SerializedName("start_time") private String mStartTime;
  @SerializedName("turns_num") private int mTurnsNum;
  @SerializedName("could_share") private int mCouldShare;
  @SerializedName("save_times")private  int save_times;
  @SerializedName("share_times")private  int share_times;
  @SerializedName("description") private String mDescription;
  @SerializedName("title_content") private String title_content;
  @SerializedName("pic_arry") private List<String> mPicArry;
  @SerializedName("show_qrcode")private boolean show_qrcode;

  public int getId() {
    return mId;
  }

  public void setId(int id) {
    this.mId = id;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    this.mTitle = title;
  }

  public String getStartTime() {
    return mStartTime;
  }

  public void setStartTime(String startTime) {
    this.mStartTime = startTime;
  }

  public int getTurnsNum() {
    return mTurnsNum;
  }

  public void setTurnsNum(int turnsNum) {
    this.mTurnsNum = turnsNum;
  }

  public int getCouldShare() {
    return mCouldShare;
  }

  public void setCouldShare(int couldShare) {
    this.mCouldShare = couldShare;
  }

  public String getDescription() {
    return mDescription;
  }

  public void setDescription(String description) {
    this.mDescription = description;
  }

  public List<String> getPicArry() {
    return mPicArry;
  }

  public void setPicArry(List<String> picArry) {
    this.mPicArry = picArry;
  }

  public int getSave_times() {
    return save_times;
  }

  public void setSave_times(int save_times) {
    this.save_times = save_times;
  }

  public int getShare_times() {
    return share_times;
  }

  public void setShare_times(int share_times) {
    this.share_times = share_times;
  }

  public String getTitle_content() {
    return title_content;
  }

  public void setTitle_content(String title_content) {
    this.title_content = title_content;
  }

  public boolean isShow_qrcode() {
    return show_qrcode;
  }

  public void setShow_qrcode(boolean show_qrcode) {
    this.show_qrcode = show_qrcode;
  }
}
