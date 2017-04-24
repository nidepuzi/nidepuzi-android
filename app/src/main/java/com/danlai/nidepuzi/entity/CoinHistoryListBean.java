package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * Created by wisdom on 17/1/20.
 */

public class CoinHistoryListBean {

    /**
     * count : 5
     * next : null
     * previous : null
     * results : [{"id":5,"mama_id":1,"iro_type":"收入","amount":100,"subject":"退款","date_field":"2017-01-18","created":"2017-01-18T10:27:19"},{"id":4,"mama_id":1,"iro_type":"支出","amount":50,"subject":"消费","date_field":"2017-01-18","created":"2017-01-18T10:26:08"},{"id":3,"mama_id":1,"iro_type":"收入","amount":200,"subject":"充值","date_field":"2017-01-18","created":"2017-01-18T10:24:21"},{"id":2,"mama_id":1,"iro_type":"收入","amount":100,"subject":"充值","date_field":"2017-01-18","created":"2017-01-18T10:24:05"},{"id":1,"mama_id":1,"iro_type":"收入","amount":100,"subject":"充值","date_field":"2017-01-18","created":"2017-01-18T10:19:41"}]
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
         * id : 5
         * mama_id : 1
         * iro_type : 收入
         * amount : 100
         * subject : 退款
         * date_field : 2017-01-18
         * created : 2017-01-18T10:27:19
         */

        private int id;
        private int mama_id;
        private String iro_type;
        private double amount;
        private String subject;
        private String date_field;
        private String created;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMama_id() {
            return mama_id;
        }

        public void setMama_id(int mama_id) {
            this.mama_id = mama_id;
        }

        public String getIro_type() {
            return iro_type;
        }

        public void setIro_type(String iro_type) {
            this.iro_type = iro_type;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getDate_field() {
            return date_field;
        }

        public void setDate_field(String date_field) {
            this.date_field = date_field;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }
    }
}
