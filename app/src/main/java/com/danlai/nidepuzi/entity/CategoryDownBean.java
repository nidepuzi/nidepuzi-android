package com.danlai.nidepuzi.entity;

/**
 * Created by wisdom on 16/8/3.
 */
public class CategoryDownBean {

    /**
     * version : v20160803
     * download_url : http://7xrst8.com2.z0.glb.qiniucdn.com/category/xiaolumm-category-v20160803.json
     * sha1 : e470f2ac4a47fbd3357950517baea76e47255056
     */

    private String version;
    private String download_url;
    private String sha1;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    @Override
    public String toString() {
        return "CategoryDownBean{" +
                "version='" + version + '\'' +
                ", download_url='" + download_url + '\'' +
                ", sha1='" + sha1 + '\'' +
                '}';
    }
}
