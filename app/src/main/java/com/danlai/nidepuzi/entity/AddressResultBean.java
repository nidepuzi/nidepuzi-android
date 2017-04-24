package com.danlai.nidepuzi.entity;

/**
 * @author wisdom
 * @date 2017年04月17日 下午5:54
 */
public class AddressResultBean {
  /**
   * info : 更新成功
   * msg : 更新成功
   * code : 0
   * result : {"personalinfo_level":2,"address_id":186392}
   * ret : true
   */

  private String info;
  private String msg;
  private int code;
  private ResultBean result;
  private boolean ret;

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public ResultBean getResult() {
    return result;
  }

  public void setResult(ResultBean result) {
    this.result = result;
  }

  public boolean isRet() {
    return ret;
  }

  public void setRet(boolean ret) {
    this.ret = ret;
  }

  public static class ResultBean {
    /**
     * personalinfo_level : 2
     * address_id : 186392
     */

    private int personalinfo_level;
    private int address_id;

    public int getPersonalinfo_level() {
      return personalinfo_level;
    }

    public void setPersonalinfo_level(int personalinfo_level) {
      this.personalinfo_level = personalinfo_level;
    }

    public int getAddress_id() {
      return address_id;
    }

    public void setAddress_id(int address_id) {
      this.address_id = address_id;
    }
  }

  /**
   * info : 更新成功
   * msg : 更新成功
   * code : 0
   * result : {"address_id":186264}
   * ret : true
   */



}
