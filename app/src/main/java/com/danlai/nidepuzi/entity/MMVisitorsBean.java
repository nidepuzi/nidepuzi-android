package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by itxuye(www.itxuye.com) on 2016/03/22.
 *
 * Copyright 2016年 上海己美. All rights reserved.
 */
public class MMVisitorsBean {

  /**
   * count : 6
   * next : null
   * previous : null
   * results : [{"mama_id":5,"visitor_nick":"匿名用户","visitor_img":"","visitor_description":"来自微信点击访问","uni_key":"dsffdaaggfdfsd","modified":"2016-03-19T22:36:02","created":"2016-03-19T22:36:02"},{"mama_id":5,"visitor_nick":"匿名用户","visitor_img":"","visitor_description":"来自微信点击访问","uni_key":"dddfdf","modified":"2016-03-19T20:41:59","created":"2016-03-19T20:41:59"},{"mama_id":5,"visitor_nick":"匿名用户","visitor_img":"","visitor_description":"来自微信点击访问","uni_key":"wedfsdfsd34","modified":"2016-03-19T20:40:08","created":"2016-03-19T20:40:08"},{"mama_id":5,"visitor_nick":"匿名用户","visitor_img":"","visitor_description":"来自微信点击访问","uni_key":"878989879","modified":"2016-03-19T20:37:20","created":"2016-03-19T20:37:20"},{"mama_id":5,"visitor_nick":"匿名用户","visitor_img":"","visitor_description":"来自微信点击访问","uni_key":"34243dfdfsdfs","modified":"2016-03-19T20:35:22","created":"2016-03-19T20:35:22"},{"mama_id":5,"visitor_nick":"匿名用户","visitor_img":"","visitor_description":"来自微信点击访问","uni_key":"23434554dsfdffd","modified":"2016-03-19T20:34:47","created":"2016-03-19T20:34:47"}]
   */

  @SerializedName("count") private int mCount;
  @SerializedName("next") private String mNext;
  @SerializedName("previous") private String mPrevious;
  /**
   * mama_id : 5
   * visitor_nick : 匿名用户
   * visitor_img :
   * visitor_description : 来自微信点击访问
   * uni_key : dsffdaaggfdfsd
   * modified : 2016-03-19T22:36:02
   * created : 2016-03-19T22:36:02
   */

  @SerializedName("results") private List<ResultsEntity> mResults;

  public int getCount() {
    return mCount;
  }

  public void setCount(int count) {
    mCount = count;
  }

  public String getNext() {
    return mNext;
  }

  public void setNext(String next) {
    mNext = next;
  }

  public String getPrevious() {
    return mPrevious;
  }

  public void setPrevious(String previous) {
    mPrevious = previous;
  }

  public List<ResultsEntity> getResults() {
    return mResults;
  }

  public void setResults(List<ResultsEntity> results) {
    mResults = results;
  }

  public static class ResultsEntity {
    @SerializedName("mama_id") private int mMamaId;
    @SerializedName("visitor_nick") private String mVisitorNick;
    @SerializedName("visitor_img") private String mVisitorImg;
    @SerializedName("visitor_description") private String mVisitorDescription;
    @SerializedName("uni_key") private String mUniKey;
    @SerializedName("modified") private String mModified;
    @SerializedName("created") private String mCreated;

    public int getMamaId() {
      return mMamaId;
    }

    public void setMamaId(int mamaId) {
      mMamaId = mamaId;
    }

    public String getVisitorNick() {
      return mVisitorNick;
    }

    public void setVisitorNick(String visitorNick) {
      mVisitorNick = visitorNick;
    }

    public String getVisitorImg() {
      return mVisitorImg;
    }

    public void setVisitorImg(String visitorImg) {
      mVisitorImg = visitorImg;
    }

    public String getVisitorDescription() {
      return mVisitorDescription;
    }

    public void setVisitorDescription(String visitorDescription) {
      mVisitorDescription = visitorDescription;
    }

    public String getUniKey() {
      return mUniKey;
    }

    public void setUniKey(String uniKey) {
      mUniKey = uniKey;
    }

    public String getModified() {
      return mModified;
    }

    public void setModified(String modified) {
      mModified = modified;
    }

    public String getCreated() {
      return mCreated;
    }

    public void setCreated(String created) {
      mCreated = created;
    }
  }
}
