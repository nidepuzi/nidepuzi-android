package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by itxuye(www.itxuye.com) on 2016/03/03.
 *
 * Copyright 2016年 上海己美. All rights reserved.
 */
public class MMShoppingBean {

  /**
   * customer : 1
   * name : meron@小鹿美美の外贸店
   * shop_link : http://m.xiaolumeimei.com/m/44?next=/pages/mmshop.html%3Fmm_linkid%3D44
   * preview_shop_link : http://m.xiaolumeimei.com/pages/preview-mmshop.html?mm_linkid=44
   * id : 3
   * first_pro_pic : http://image.xiaolu.so/MG_14636352750782.jpg
   * thumbnail : http://image.xiaolu.so/MG_14636352750782.jpg
   * desc : meron@小鹿美美の外贸店今天又上新啦！
   */

  @SerializedName("shop_info") private ShopInfoEntity mShopInfo;

  public ShopInfoEntity getShopInfo() {
    return mShopInfo;
  }

  public void setShopInfo(ShopInfoEntity shopInfo) {
    mShopInfo = shopInfo;
  }

  public static class ShopInfoEntity {
    @SerializedName("customer") private int mCustomer;
    @SerializedName("name") private String mName;
    @SerializedName("shop_link") private String mShopLink;
    @SerializedName("preview_shop_link") private String mPreviewShopLink;
    @SerializedName("id") private int mId;
    @SerializedName("first_pro_pic") private String mFirstProPic;
    @SerializedName("thumbnail") private String mThumbnail;
    @SerializedName("desc") private String mDesc;

    public int getCustomer() {
      return mCustomer;
    }

    public void setCustomer(int customer) {
      mCustomer = customer;
    }

    public String getName() {
      return mName;
    }

    public void setName(String name) {
      mName = name;
    }

    public String getShopLink() {
      return mShopLink;
    }

    public void setShopLink(String shopLink) {
      mShopLink = shopLink;
    }

    public String getPreviewShopLink() {
      return mPreviewShopLink;
    }

    public void setPreviewShopLink(String previewShopLink) {
      mPreviewShopLink = previewShopLink;
    }

    public int getId() {
      return mId;
    }

    public void setId(int id) {
      mId = id;
    }

    public String getFirstProPic() {
      return mFirstProPic;
    }

    public void setFirstProPic(String firstProPic) {
      mFirstProPic = firstProPic;
    }

    public String getThumbnail() {
      return mThumbnail;
    }

    public void setThumbnail(String thumbnail) {
      mThumbnail = thumbnail;
    }

    public String getDesc() {
      return mDesc;
    }

    public void setDesc(String desc) {
      mDesc = desc;
    }
  }
}
