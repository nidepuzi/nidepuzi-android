package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by itxuye on 2016/02/19.
 * <p>
 * Copyright 2016年 上海己美. All rights reserved.
 */
public class ActivityBean {

    /**
     * active_dec : 免费送出1000个空气棉恒温睡袋！全国包邮免费送！
     * share_type : link
     * title : 免费试用活动
     * qrcode_link : http://192.168.1.11:9000/sale/promotion/ercode/
     * id : 1
     * share_link : http://dev.xiaolumeimei.com/sale/promotion/activity/
     * share_icon : http://7xogkj.com2.z0.glb.qiniucdn.com/dress_%E5%88%86%E4%BA%AB%E5%9B%BE%E7%89%87.png
     */

    @SerializedName("active_dec")
    private String mActiveDec;
    @SerializedName("share_type")
    private String mShareType;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("qrcode_link")
    private String mQrcodeLink;
    @SerializedName("id")
    private int mId;
    @SerializedName("share_link")
    private String mShareLink;
    @SerializedName("share_icon")
    private String mShareIcon;

    public ActivityBean() {
    }

    public ActivityBean(String activeDec, String title, String shareLink, String shareIcon) {
        mActiveDec = activeDec;
        mTitle = title;
        mShareLink = shareLink;
        mShareIcon = shareIcon;
    }

    public String getActiveDec() {
        return mActiveDec;
    }

    public void setActiveDec(String activeDec) {
        mActiveDec = activeDec;
    }

    public String getShareType() {
        return mShareType;
    }

    public void setShareType(String shareType) {
        mShareType = shareType;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getQrcodeLink() {
        return mQrcodeLink;
    }

    public void setQrcodeLink(String qrcodeLink) {
        mQrcodeLink = qrcodeLink;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getShareLink() {
        return mShareLink;
    }

    public void setShareLink(String shareLink) {
        mShareLink = shareLink;
    }

    public String getShareIcon() {
        return mShareIcon;
    }

    public void setShareIcon(String shareIcon) {
        mShareIcon = shareIcon;
    }

    @Override
    public String toString() {
        return "ActivityBean{" +
            "mActiveDec='" + mActiveDec + '\'' +
            ", mShareType='" + mShareType + '\'' +
            ", mTitle='" + mTitle + '\'' +
            ", mQrcodeLink='" + mQrcodeLink + '\'' +
            ", mId=" + mId +
            ", mShareLink='" + mShareLink + '\'' +
            ", mShareIcon='" + mShareIcon + '\'' +
            '}';
    }
}
