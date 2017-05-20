package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * @author wisdom
 * @date 2017年05月19日 上午9:32
 */

public class BankListEntity {

    private List<BanksBean> banks;

    public List<BanksBean> getBanks() {
        return banks;
    }

    public void setBanks(List<BanksBean> banks) {
        this.banks = banks;
    }

    public static class BanksBean {
        /**
         * bank_name : 中国银行
         * bank_img : http://img.nidepuzi.com/banks/bank_zgyh.png
         */

        private String bank_name;
        private String bank_img;

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
    }
}
