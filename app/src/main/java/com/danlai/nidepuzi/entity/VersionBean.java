package com.danlai.nidepuzi.entity;

/**
 * Created by wisdom on 16/7/26.
 */
public class VersionBean {


    /**
     * download_link : http://image.xiaolu.so/xlmm_1469596721096md5-test.html
     * qrcode_link : http://image.xiaolu.so/xlmm_1469596724430md5-test.html
     * ios_qrcode_link : http://7xogkj.com2.z0.glb.qiniucdn.com/appstore_download_link.svg
     * ios_download_link : https://itunes.apple.com/us/app/xiao-lu-mei-mei/id1051166985
     * version : １
     * status : 0
     * release_time : 2016-07-27T00:00:00
     * auto_update : true
     * hash_value : FtBlKnut3DyGFftDf1xxmVF-Ujzr
     * version_code : 1
     * memo : 问问问问才
     */

    private String download_link;
    private String qrcode_link;
    private String ios_qrcode_link;
    private String ios_download_link;
    private String version;
    private int status;
    private String release_time;
    private boolean auto_update;
    private String hash_value;
    private int version_code;
    private String memo;

    public String getDownload_link() {
        return download_link;
    }

    public void setDownload_link(String download_link) {
        this.download_link = download_link;
    }

    public String getQrcode_link() {
        return qrcode_link;
    }

    public void setQrcode_link(String qrcode_link) {
        this.qrcode_link = qrcode_link;
    }

    public String getIos_qrcode_link() {
        return ios_qrcode_link;
    }

    public void setIos_qrcode_link(String ios_qrcode_link) {
        this.ios_qrcode_link = ios_qrcode_link;
    }

    public String getIos_download_link() {
        return ios_download_link;
    }

    public void setIos_download_link(String ios_download_link) {
        this.ios_download_link = ios_download_link;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public boolean isAuto_update() {
        return auto_update;
    }

    public void setAuto_update(boolean auto_update) {
        this.auto_update = auto_update;
    }

    public String getHash_value() {
        return hash_value;
    }

    public void setHash_value(String hash_value) {
        this.hash_value = hash_value;
    }

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
