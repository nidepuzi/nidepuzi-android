package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author wisdom
 * @date 2017年04月19日 上午9:54
 */
public class AddressBean {

  /**
   * id : 186220
   * url : http://m.xiaolumeimei.com/rest/v1/address/186220
   * cus_uid : 864138
   * receiver_name : 测试测试
   * receiver_state : 上海
   * receiver_city : 杨浦区
   * receiver_district : 内环中环之间
   * receiver_address : 大连路688号宝地广场A座2205室
   * receiver_zip :
   * receiver_mobile : 18676720901
   * personalinfo_level : 2
   * receiver_phone :
   * logistic_company_code :
   * default : false
   * status : normal
   * created : 2017-04-15T16:19:58
   * identification_no : 320981199111204472
   * idcard : {"back":"","face":""}
   */

  private int id;
  private String url;
  private int cus_uid;
  private String receiver_name;
  private String receiver_state;
  private String receiver_city;
  private String receiver_district;
  private String receiver_address;
  private String receiver_zip;
  private String receiver_mobile;
  private int personalinfo_level;
  private String receiver_phone;
  private String logistic_company_code;
  @SerializedName("default")
  private boolean defaultX;
  private String status;
  private String created;
  private String identification_no;
  private IdcardBean idcard;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getCus_uid() {
    return cus_uid;
  }

  public void setCus_uid(int cus_uid) {
    this.cus_uid = cus_uid;
  }

  public String getReceiver_name() {
    return receiver_name;
  }

  public void setReceiver_name(String receiver_name) {
    this.receiver_name = receiver_name;
  }

  public String getReceiver_state() {
    return receiver_state;
  }

  public void setReceiver_state(String receiver_state) {
    this.receiver_state = receiver_state;
  }

  public String getReceiver_city() {
    return receiver_city;
  }

  public void setReceiver_city(String receiver_city) {
    this.receiver_city = receiver_city;
  }

  public String getReceiver_district() {
    return receiver_district;
  }

  public void setReceiver_district(String receiver_district) {
    this.receiver_district = receiver_district;
  }

  public String getReceiver_address() {
    return receiver_address;
  }

  public void setReceiver_address(String receiver_address) {
    this.receiver_address = receiver_address;
  }

  public String getReceiver_zip() {
    return receiver_zip;
  }

  public void setReceiver_zip(String receiver_zip) {
    this.receiver_zip = receiver_zip;
  }

  public String getReceiver_mobile() {
    return receiver_mobile;
  }

  public void setReceiver_mobile(String receiver_mobile) {
    this.receiver_mobile = receiver_mobile;
  }

  public int getPersonalinfo_level() {
    return personalinfo_level;
  }

  public void setPersonalinfo_level(int personalinfo_level) {
    this.personalinfo_level = personalinfo_level;
  }

  public String getReceiver_phone() {
    return receiver_phone;
  }

  public void setReceiver_phone(String receiver_phone) {
    this.receiver_phone = receiver_phone;
  }

  public String getLogistic_company_code() {
    return logistic_company_code;
  }

  public void setLogistic_company_code(String logistic_company_code) {
    this.logistic_company_code = logistic_company_code;
  }

  public boolean isDefaultX() {
    return defaultX;
  }

  public void setDefaultX(boolean defaultX) {
    this.defaultX = defaultX;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public String getIdentification_no() {
    return identification_no;
  }

  public void setIdentification_no(String identification_no) {
    this.identification_no = identification_no;
  }

  public IdcardBean getIdcard() {
    return idcard;
  }

  public void setIdcard(IdcardBean idcard) {
    this.idcard = idcard;
  }

  public static class IdcardBean {
    /**
     * back :
     * face :
     */

    private String back;
    private String face;

    public String getBack() {
      return back;
    }

    public void setBack(String back) {
      this.back = back;
    }

    public String getFace() {
      return face;
    }

    public void setFace(String face) {
      this.face = face;
    }
  }
}
