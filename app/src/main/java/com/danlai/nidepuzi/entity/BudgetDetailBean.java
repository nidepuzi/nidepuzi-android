package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * @author wisdom-sun
 * @date 2017年05月09日 下午2:00
 */
public class BudgetDetailBean {

    /**
     * count : 4
     * next : null
     * previous : null
     * results : [{"desc":"您通过提现支出88.0元.","budget_type":1,"budget_log_type":"cashout","budget_date":"2017-05-09","get_status_display":"待确定","status":2,"budeget_detail_cash":88,"modified":"2017-05-09T10:22:30.096974"},{"desc":"您通过退款收入88.0元.","budget_type":0,"budget_log_type":"refund","budget_date":"2017-05-09","get_status_display":"已确定","status":0,"budeget_detail_cash":88,"modified":"2017-05-09T10:05:39.151877"},{"desc":"您通过消费支出88.0元.消费的订单号71.","budget_type":1,"budget_log_type":"consum","budget_date":"2017-05-09","get_status_display":"已确定","status":0,"budeget_detail_cash":88,"modified":"2017-05-09T10:03:07.995981"},{"desc":"您通过退款收入88.0元.","budget_type":0,"budget_log_type":"refund","budget_date":"2017-05-09","get_status_display":"已确定","status":0,"budeget_detail_cash":88,"modified":"2017-05-09T09:55:33.877371"}]
     */

    private int count;
    private String next;
    private String previous;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * desc : 您通过提现支出88.0元.
         * budget_type : 1
         * budget_log_type : cashout
         * budget_date : 2017-05-09
         * get_status_display : 待确定
         * status : 2
         * budeget_detail_cash : 88.0
         * modified : 2017-05-09T10:22:30.096974
         */

        private String desc;
        private int budget_type;
        private String budget_log_type;
        private String budget_date;
        private String get_status_display;
        private int status;
        private double budeget_detail_cash;
        private String modified;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getBudget_type() {
            return budget_type;
        }

        public void setBudget_type(int budget_type) {
            this.budget_type = budget_type;
        }

        public String getBudget_log_type() {
            return budget_log_type;
        }

        public void setBudget_log_type(String budget_log_type) {
            this.budget_log_type = budget_log_type;
        }

        public String getBudget_date() {
            return budget_date;
        }

        public void setBudget_date(String budget_date) {
            this.budget_date = budget_date;
        }

        public String getGet_status_display() {
            return get_status_display;
        }

        public void setGet_status_display(String get_status_display) {
            this.get_status_display = get_status_display;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getBudeget_detail_cash() {
            return budeget_detail_cash;
        }

        public void setBudeget_detail_cash(double budeget_detail_cash) {
            this.budeget_detail_cash = budeget_detail_cash;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }
    }
}
