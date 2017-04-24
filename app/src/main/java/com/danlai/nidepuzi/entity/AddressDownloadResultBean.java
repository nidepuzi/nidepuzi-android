package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by itxuye on 2016/7/15.
 */
public class AddressDownloadResultBean {

  /**
   * version : v20160714-0.0.2
   * hash : 2f5901750213d3338a8b95525b541dfbcfcca8d6
   * download_url : http://7xrst8.com2.z0.glb.qiniucdn.com//district/xiaolumm-district-v20160714-0.0.2.json
   */

  @SerializedName("version") private String version;
  @SerializedName("hash") private String hash;
  @SerializedName("download_url") private String downloadUrl;

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public void setDownloadUrl(String downloadUrl) {
    this.downloadUrl = downloadUrl;
  }

  @Override public String toString() {
    return "AddressDownloadResultBean{" +
        "version='" + version + '\'' +
        ", hash='" + hash + '\'' +
        ", downloadUrl='" + downloadUrl + '\'' +
        '}';
  }
}
