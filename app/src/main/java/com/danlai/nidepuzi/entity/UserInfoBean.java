package com.danlai.nidepuzi.entity;

/**
 * Created by wisdom on 17/4/6.
 */

public class UserInfoBean {

  /**
   * id : 1
   * url :
   * user_id : 1
   * username : xiaolumm
   * nick : meron@小鹿美美
   * mobile : 18621623915
   * email :
   * phone :
   * score : 7342
   * thumbnail : http://wx.qlogo.cn/mmopen/H5vWSibzy59NIkAocecg2bR2JHC8vibsOLibcaqQJfZYvdDQLTMoUoVG8Arbu2fzMaEeqn0Pq6e69uk2JleqYgibjkGJY9DTKUjT/0
   * status : 1
   * created : 2015-04-09T11:08:12
   * modified : 2017-04-02T12:03:42
   * xiaolumm : {"id":44,"cash":2444048,"agencylevel":2,"created":"2015-04-11T12:20:23","status":"effect","referal_from":"INDIRECT","last_renew_type":183,"charge_status":"charged"}
   * has_usable_password : true
   * has_password : true
   * user_budget : {"budget_cash":24050.15,"is_cash_out":1,"cash_out_limit":2}
   * is_attention_public : 1
   * coupon_num : 68
   * waitpay_num : 0
   * waitgoods_num : 0
   * refunds_num : 0
   * xiaolu_coin : 1852.45
   */

  private int id;
  private String url;
  private String user_id;
  private String username;
  private String nick;
  private String mobile;
  private String email;
  private String phone;
  private int score;
  private String thumbnail;
  private int status;
  private int check_xiaolumm;
  private String created;
  private String modified;
  private XiaolummBean xiaolumm;
  private boolean has_usable_password;
  private boolean has_password;
  private UserBudgetBean user_budget;
  private int is_attention_public;
  private int coupon_num;
  private int waitpay_num;
  private int waitgoods_num;
  private int refunds_num;
  private double xiaolu_coin;

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

  public String getUser_id() {
    return user_id;
  }

  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public String getModified() {
    return modified;
  }

  public void setModified(String modified) {
    this.modified = modified;
  }

  public XiaolummBean getXiaolumm() {
    return xiaolumm;
  }

  public void setXiaolumm(XiaolummBean xiaolumm) {
    this.xiaolumm = xiaolumm;
  }

  public boolean isHas_usable_password() {
    return has_usable_password;
  }

  public void setHas_usable_password(boolean has_usable_password) {
    this.has_usable_password = has_usable_password;
  }

  public boolean isHas_password() {
    return has_password;
  }

  public void setHas_password(boolean has_password) {
    this.has_password = has_password;
  }

  public UserBudgetBean getUser_budget() {
    return user_budget;
  }

  public void setUser_budget(UserBudgetBean user_budget) {
    this.user_budget = user_budget;
  }

  public int getIs_attention_public() {
    return is_attention_public;
  }

  public void setIs_attention_public(int is_attention_public) {
    this.is_attention_public = is_attention_public;
  }

  public int getCoupon_num() {
    return coupon_num;
  }

  public void setCoupon_num(int coupon_num) {
    this.coupon_num = coupon_num;
  }

  public int getWaitpay_num() {
    return waitpay_num;
  }

  public void setWaitpay_num(int waitpay_num) {
    this.waitpay_num = waitpay_num;
  }

  public int getWaitgoods_num() {
    return waitgoods_num;
  }

  public void setWaitgoods_num(int waitgoods_num) {
    this.waitgoods_num = waitgoods_num;
  }

  public int getRefunds_num() {
    return refunds_num;
  }

  public void setRefunds_num(int refunds_num) {
    this.refunds_num = refunds_num;
  }

  public double getXiaolu_coin() {
    return xiaolu_coin;
  }

  public void setXiaolu_coin(double xiaolu_coin) {
    this.xiaolu_coin = xiaolu_coin;
  }

  public int getCheck_xiaolumm() {
    return check_xiaolumm;
  }

  public void setCheck_xiaolumm(int check_xiaolumm) {
    this.check_xiaolumm = check_xiaolumm;
  }

  public static class XiaolummBean {
    /**
     * id : 44
     * cash : 2444048
     * agencylevel : 2
     * created : 2015-04-11T12:20:23
     * status : effect
     * referal_from : INDIRECT
     * last_renew_type : 183
     * charge_status : charged
     */

    private int id;
    private int cash;
    private int agencylevel;
    private String created;
    private String status;
    private String referal_from;
    private int last_renew_type;
    private String charge_status;
    private String renew_time;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public int getCash() {
      return cash;
    }

    public void setCash(int cash) {
      this.cash = cash;
    }

    public int getAgencylevel() {
      return agencylevel;
    }

    public void setAgencylevel(int agencylevel) {
      this.agencylevel = agencylevel;
    }

    public String getCreated() {
      return created;
    }

    public void setCreated(String created) {
      this.created = created;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public String getReferal_from() {
      return referal_from;
    }

    public void setReferal_from(String referal_from) {
      this.referal_from = referal_from;
    }

    public int getLast_renew_type() {
      return last_renew_type;
    }

    public void setLast_renew_type(int last_renew_type) {
      this.last_renew_type = last_renew_type;
    }

    public String getCharge_status() {
      return charge_status;
    }

    public void setCharge_status(String charge_status) {
      this.charge_status = charge_status;
    }

    public String getRenew_time() {
      return renew_time;
    }

    public void setRenew_time(String renew_time) {
      this.renew_time = renew_time;
    }
  }

  public static class UserBudgetBean {
    /**
     * budget_cash : 24050.15
     * is_cash_out : 1
     * cash_out_limit : 2
     */

    private double budget_cash;
    private int is_cash_out;
    private double cash_out_limit;

    public double getBudget_cash() {
      return budget_cash;
    }

    public void setBudget_cash(double budget_cash) {
      this.budget_cash = budget_cash;
    }

    public int getIs_cash_out() {
      return is_cash_out;
    }

    public void setIs_cash_out(int is_cash_out) {
      this.is_cash_out = is_cash_out;
    }

    public double getCash_out_limit() {
      return cash_out_limit;
    }

    public void setCash_out_limit(double cash_out_limit) {
      this.cash_out_limit = cash_out_limit;
    }
  }
}
