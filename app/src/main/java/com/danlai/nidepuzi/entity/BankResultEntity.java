package com.danlai.nidepuzi.entity;

/**
 * @author wisdom
 * @date 2017年05月19日 下午4:28
 */

public class BankResultEntity {


    /**
     * code : 0
     * card : {"id":40,"user":28,"account_no":"6225887802491304","account_name":"伍磊","bank_name":"中国银行","bank_img":"http://img.nidepuzi.com/banks/bank_zgyh.png","created":"2017-05-19T16:27:46.347413","default":true}
     */

    private int code;
    private String info;
    private BankCardEntity card;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BankCardEntity getCard() {
        return card;
    }

    public void setCard(BankCardEntity card) {
        this.card = card;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
