package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author wisdom
 * @date 2017年05月19日 上午10:19
 */

public class BankCardEntity {


    /**
     * id : 30
     * user : 28
     * account_no : 6225887802491304
     * account_name : 伍磊
     * bank_name : 中国建设银行
     * created : 2017-05-19T16:04:50.467461
     * default : true
     */

    private int id;
    private int user;
    private String account_no;
    private String account_name;
    private String bank_name;
    private String created;
    private String bank_img;
    @SerializedName("default")
    private boolean defaultX;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isDefaultX() {
        return defaultX;
    }

    public void setDefaultX(boolean defaultX) {
        this.defaultX = defaultX;
    }

    public String getBank_img() {
        return bank_img;
    }

    public void setBank_img(String bank_img) {
        this.bank_img = bank_img;
    }
}
