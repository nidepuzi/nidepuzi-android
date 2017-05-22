package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author wisdom
 * @date 2017年05月22日 上午10:55
 */

public class DrawCashBean {

    /**
     * id : 8
     * customer_id : 25
     * amount : 10
     * platform : sandpay
     * platform_name : 银行卡转账
     * recipient : 6
     * receiver : 18621623915
     * service_fee : 1
     * state : success
     * fail_msg :
     * bank_card : {"id":6,"user":40,"account_no":"6216261000000000018","account_name":"全渠道","bank_name":"招商银行","bank_img":"http://img.nidepuzi.com/banks/bank_zsyh.png","created":"2017-05-18T15:33:01.058851","default":false}
     * created : 2017-05-18T15:35:26.733555
     * send_time : 2017-05-18T15:35:28
     * success_time : null
     */

    private int id;
    private int customer_id;
    private double amount;
    private String platform;
    private String platform_name;
    private String recipient;
    private String receiver;
    private double service_fee;
    private String state;
    private String fail_msg;
    private BankCardBean bank_card;
    private String created;
    private String send_time;
    private String success_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public double getService_fee() {
        return service_fee;
    }

    public void setService_fee(double service_fee) {
        this.service_fee = service_fee;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFail_msg() {
        return fail_msg;
    }

    public void setFail_msg(String fail_msg) {
        this.fail_msg = fail_msg;
    }

    public BankCardBean getBank_card() {
        return bank_card;
    }

    public void setBank_card(BankCardBean bank_card) {
        this.bank_card = bank_card;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getSuccess_time() {
        return success_time;
    }

    public void setSuccess_time(String success_time) {
        this.success_time = success_time;
    }

    public static class BankCardBean {
        /**
         * id : 6
         * user : 40
         * account_no : 6216261000000000018
         * account_name : 全渠道
         * bank_name : 招商银行
         * bank_img : http://img.nidepuzi.com/banks/bank_zsyh.png
         * created : 2017-05-18T15:33:01.058851
         * default : false
         */

        private int id;
        private int user;
        private String account_no;
        private String account_name;
        private String bank_name;
        private String bank_img;
        private String created;
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

        public String getBank_img() {
            return bank_img;
        }

        public void setBank_img(String bank_img) {
            this.bank_img = bank_img;
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
    }
}
