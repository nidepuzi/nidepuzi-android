package com.danlai.nidepuzi.entity;

/**
 * @author wisdom
 * @date 2017年05月08日 下午4:47
 */

public class WxUserInfoBean {


    /**
     * openid : oqcJJwSS77LDFxb-XLI2-BQV4tQU
     * nickname : 旭茂
     * sex : 1
     * language : zh_CN
     * city : Pudong New District
     * province : Shanghai
     * country : CN
     * headimgurl : http://wx.qlogo.cn/mmopen/ajNVdqHZLLCMe9eG5cF6PBV0PnMclI5pkoF5VTjEV7c8fGx5uajicsibAY6YUXvSFDj9XKNia3ge3Df8ibkPmw1MEA/0
     * privilege : []
     * unionid : oDifH0_wTIf1ckJSLPhsaWDdZKy8
     */

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

}
