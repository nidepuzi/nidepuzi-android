package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wulei on 3/15/16.
 */
public class RecentCarryBean {


    /**
     * count : 1
     * next : null
     * previous : null
     * results : [{"order_num":5,"carry":202.15,"date_field":"2016-03-25","visitor_num":73}]
     */

    @SerializedName("count")
    private int count;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private String previous;
    /**
     * order_num : 5
     * carry : 202.15
     * date_field : 2016-03-25
     * visitor_num : 73
     */

    @SerializedName("results")
    private List<ResultsEntity> results;

    public void setCount(int count) {
        this.count = count;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public static class ResultsEntity {
        @SerializedName("order_num")
        private int orderNum;
        @SerializedName("carry")
        private double carry;
        @SerializedName("date_field")
        private String dateField;
        @SerializedName("visitor_num")
        private int visitorNum;
        private int today_referal_num;

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public void setCarry(double carry) {
            this.carry = carry;
        }

        public void setDateField(String dateField) {
            this.dateField = dateField;
        }

        public void setVisitorNum(int visitorNum) {
            this.visitorNum = visitorNum;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public double getCarry() {
            return carry;
        }

        public String getDateField() {
            return dateField;
        }

        public int getVisitorNum() {
            return visitorNum;
        }

        public int getToday_referal_num() {
            return today_referal_num;
        }

        public void setToday_referal_num(int today_referal_num) {
            this.today_referal_num = today_referal_num;
        }
    }

    @Override
    public String toString() {
        return "RecentCarryBean{" +
            "count=" + count +
            ", next=" + next +
            ", previous=" + previous +
            ", results=" + results +
            '}';
    }
}
