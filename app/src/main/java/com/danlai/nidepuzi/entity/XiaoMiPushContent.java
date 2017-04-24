package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wulei on 2016/2/21.
 */
public class XiaoMiPushContent {

    /**
     * target_url : target_url
     */

    @SerializedName("target_url")
    private String targetUrl;
    @SerializedName("url")
    private String url;

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    @Override
    public String toString() {
        return "XiaoMiPushContent{" +
                "targetUrl='" + targetUrl + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
