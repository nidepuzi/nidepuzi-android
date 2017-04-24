package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by itxuye on 16/5/23.
 */
public class CallNativeFuncBean {

  /**
   * share_desc : 宽松白色打底衫
   * 商品入口：　http://m.xiaolumeimei.com/tongkuan.html?id=10290&mm_linkid=0
   * share_icon : http://image.xiaolu.so/MG_1459395421959%E5%A4%B4%E5%9B%BE%E8%83%8C%E6%99%AF.png?imageMogr2/thumbnail/108/quality/80/format/jpg/crop/108x108/
   * share_type : link
   * link : http://m.xiaolumeimei.com/tongkuan.html?id=10290&mm_linkid=0
   */

  @SerializedName("share_title") private String shareTitle;
  @SerializedName("share_desc") private String shareDesc;
  @SerializedName("share_to") private String shareTo;
  @SerializedName("share_icon") private String shareIcon;
  @SerializedName("share_type") private String shareType;
  @SerializedName("link") private String link;

  public String getShareTo() {
    return shareTo;
  }

  public String getShareTitle() {
    return shareTitle;
  }

  public String getShareDesc() {
    return shareDesc;
  }

  public void setShareDesc(String shareDesc) {
    this.shareDesc = shareDesc;
  }

  public String getShareIcon() {
    return shareIcon;
  }

  public void setShareIcon(String shareIcon) {
    this.shareIcon = shareIcon;
  }

  public String getShareType() {
    return shareType;
  }

  public void setShareType(String shareType) {
    this.shareType = shareType;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }
}
