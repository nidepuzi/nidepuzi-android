package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * Created by wisdom on 17/3/21.
 */

public class MMCarryBean {

    /**
     * count : 360
     * next : http://0.0.0.0:8000/rest/v2/mmcarry?page=3
     * previous : http://0.0.0.0:8000/rest/v2/mmcarry
     * results : [{"amount":1500,"type":"兑换订单","budget_log_id":2546994,"created":"2017-03-21T00:51:14"},{"amount":400,"type":"兑换订单","budget_log_id":2546993,"created":"2017-03-21T00:51:00"},{"amount":700,"type":"兑换订单","budget_log_id":2546992,"created":"2017-03-21T00:50:52"},{"amount":4000,"type":"兑换订单","budget_log_id":2546991,"created":"2017-03-21T00:50:23"},{"amount":400,"type":"兑换订单","budget_log_id":2546990,"created":"2017-03-21T00:50:02"},{"amount":1500,"type":"兑换订单","budget_log_id":2546989,"created":"2017-03-21T00:49:55"},{"amount":18000,"type":"兑换订单","budget_log_id":2546988,"created":"2017-03-21T00:49:48"},{"amount":20000,"type":"兑换订单","budget_log_id":2546987,"created":"2017-03-21T00:49:39"},{"amount":3200,"type":"兑换订单","budget_log_id":2546968,"created":"2017-03-21T00:41:15"},{"amount":300,"type":"兑换订单","budget_log_id":2544669,"created":"2017-03-18T19:27:15"},{"amount":400,"type":"兑换订单","budget_log_id":2544668,"created":"2017-03-18T19:27:06"},{"amount":300,"type":"兑换订单","budget_log_id":2544664,"created":"2017-03-18T19:26:56"},{"amount":1000,"type":"兑换订单","budget_log_id":2544663,"created":"2017-03-18T19:26:47"},{"amount":600,"type":"兑换订单","budget_log_id":2544662,"created":"2017-03-18T19:26:37"},{"amount":700,"type":"兑换订单","budget_log_id":2544661,"created":"2017-03-18T19:26:03"}]
     * total : 951944
     */

    private int count;
    private String next;
    private String previous;
    private int total;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * amount : 1500
         * type : 兑换订单
         * budget_log_id : 2546994
         * created : 2017-03-21T00:51:14
         */

        private int amount;
        private String type;
        private int budget_log_id;
        private String created;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getBudget_log_id() {
            return budget_log_id;
        }

        public void setBudget_log_id(int budget_log_id) {
            this.budget_log_id = budget_log_id;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }
    }
}
