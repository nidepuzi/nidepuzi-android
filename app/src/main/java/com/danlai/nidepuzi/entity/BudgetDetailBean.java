package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * @author wisdom-sun
 * @date 2017年05月09日 下午2:00
 */
public class BudgetDetailBean {

    /**
     * count : 37
     * next : http://m.nidepuzi.com/rest/v1/users/get_budget_detail?page=2
     * previous : null
     * results : [{"desc":"您通过收益取消支出6.99元.","budget_type":1,"budget_log_type":"cancel","budget_log_type_display":"收益取消","budget_date":"2017-05-17","get_status_display":"已确定","status":0,"budeget_detail_cash":6.99,"modified":"2017-05-17T19:10:01.022758"},{"desc":"您通过订单收益收入6.99元.","budget_type":0,"budget_log_type":"order","budget_log_type_display":"订单收益","budget_date":"2017-05-17","get_status_display":"已确定","status":0,"budeget_detail_cash":6.99,"modified":"2017-05-17T19:10:00.999393"},{"desc":"您通过收益取消支出35.69元.","budget_type":1,"budget_log_type":"cancel","budget_log_type_display":"收益取消","budget_date":"2017-05-17","get_status_display":"已确定","status":0,"budeget_detail_cash":35.69,"modified":"2017-05-17T11:00:47.696701"},{"desc":"您通过订单收益收入35.69元.","budget_type":0,"budget_log_type":"order","budget_log_type_display":"订单收益","budget_date":"2017-05-17","get_status_display":"已确定","status":0,"budeget_detail_cash":35.69,"modified":"2017-05-17T10:59:46.037171"},{"desc":"您通过收益取消支出4.45元.","budget_type":1,"budget_log_type":"cancel","budget_log_type_display":"收益取消","budget_date":"2017-05-17","get_status_display":"已确定","status":0,"budeget_detail_cash":4.45,"modified":"2017-05-17T10:57:28.133477"},{"desc":"您通过订单收益收入4.45元.","budget_type":0,"budget_log_type":"order","budget_log_type_display":"订单收益","budget_date":"2017-05-17","get_status_display":"已确定","status":0,"budeget_detail_cash":4.45,"modified":"2017-05-17T10:57:09.832914"},{"desc":"您通过收益取消支出13.35元.","budget_type":1,"budget_log_type":"cancel","budget_log_type_display":"收益取消","budget_date":"2017-05-15","get_status_display":"已确定","status":0,"budeget_detail_cash":13.35,"modified":"2017-05-15T17:35:00.839643"},{"desc":"您通过收益取消支出14.7元.","budget_type":1,"budget_log_type":"cancel","budget_log_type_display":"收益取消","budget_date":"2017-05-15","get_status_display":"已确定","status":0,"budeget_detail_cash":14.7,"modified":"2017-05-15T17:02:58.562533"},{"desc":"您通过订单收益收入14.7元.","budget_type":0,"budget_log_type":"order","budget_log_type_display":"订单收益","budget_date":"2017-05-15","get_status_display":"已确定","status":0,"budeget_detail_cash":14.7,"modified":"2017-05-15T17:02:58.527957"},{"desc":"您通过订单收益收入13.35元.","budget_type":0,"budget_log_type":"order","budget_log_type_display":"订单收益","budget_date":"2017-05-15","get_status_display":"已确定","status":0,"budeget_detail_cash":13.35,"modified":"2017-05-15T17:35:00.761329"},{"desc":"您通过收益取消支出5.9元.","budget_type":1,"budget_log_type":"cancel","budget_log_type_display":"收益取消","budget_date":"2017-05-15","get_status_display":"已确定","status":0,"budeget_detail_cash":5.9,"modified":"2017-05-15T16:41:22.277619"},{"desc":"您通过订单收益收入5.9元.","budget_type":0,"budget_log_type":"order","budget_log_type_display":"订单收益","budget_date":"2017-05-15","get_status_display":"已确定","status":0,"budeget_detail_cash":5.9,"modified":"2017-05-15T16:41:22.250556"},{"desc":"您通过提现失败退回收入2.0元.","budget_type":0,"budget_log_type":"cashfail","budget_log_type_display":"提现失败退回","budget_date":"2017-05-15","get_status_display":"已确定","status":0,"budeget_detail_cash":2,"modified":"2017-05-15T09:31:50.948717"},{"desc":"您通过提现失败退回收入2.0元.","budget_type":0,"budget_log_type":"cashfail","budget_log_type_display":"提现失败退回","budget_date":"2017-05-15","get_status_display":"已确定","status":0,"budeget_detail_cash":2,"modified":"2017-05-15T09:31:50.900664"},{"desc":"您通过收益取消支出6.4元.","budget_type":1,"budget_log_type":"cancel","budget_log_type_display":"收益取消","budget_date":"2017-05-13","get_status_display":"已确定","status":0,"budeget_detail_cash":6.4,"modified":"2017-05-13T17:20:00.805136"}]
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
         * desc : 您通过收益取消支出6.99元.
         * budget_type : 1
         * budget_log_type : cancel
         * budget_log_type_display : 收益取消
         * budget_date : 2017-05-17
         * get_status_display : 已确定
         * status : 0
         * budeget_detail_cash : 6.99
         * modified : 2017-05-17T19:10:01.022758
         */

        private String desc;
        private int budget_type;
        private String budget_log_type;
        private String budget_log_type_display;
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

        public String getBudget_log_type_display() {
            return budget_log_type_display;
        }

        public void setBudget_log_type_display(String budget_log_type_display) {
            this.budget_log_type_display = budget_log_type_display;
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
