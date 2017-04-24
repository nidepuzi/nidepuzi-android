package com.danlai.nidepuzi.entity;

/**
 * Created by wisdom on 16/9/26.
 */

public class WxQrcode {

    /**
     * info :
     * qrcode_link : https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQH_7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL01rTXVsUHJsT09aQklkd1R1MjFfAAIEeybmVwMEAI0nAA==
     * code : 0
     */

    private String info;
    private String qrcode_link;
    private int code;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getQrcode_link() {
        return qrcode_link;
    }

    public void setQrcode_link(String qrcode_link) {
        this.qrcode_link = qrcode_link;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
