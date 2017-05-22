package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * @author wisdom
 * @date 2017年05月20日 上午10:59
 */

public class CarryListBean {

    /**
     * count : 10
     * next : null
     * previous : null
     * results : [{"mama_id":600004,"carry_value":100,"carry_num":100,"carry_type":3,"carry_type_name":"奖金","carry_description":"加入正式会员，奖金就会确认哦！","status":1,"status_display":"预计收益","today_carry":120,"date_field":"2017-05-19","modified":"2017-05-19T14:06:43.302551","created":"2017-05-19T14:06:43.302517"},{"mama_id":600004,"carry_value":5,"carry_num":5,"carry_type":2,"carry_type_name":"佣金","carry_description":"感谢佣金涌来的日子。","status":1,"status_display":"预计收益","today_carry":120,"date_field":"2017-05-19","modified":"2017-05-19T12:35:07.458806","created":"2017-05-19T12:35:07.458761"},{"mama_id":600004,"carry_value":10,"carry_num":10,"carry_type":2,"carry_type_name":"佣金","carry_description":"APP粉丝佣金，粉丝就是财富。","status":1,"status_display":"预计收益","today_carry":120,"date_field":"2017-05-19","modified":"2017-05-19T08:47:39.331727","created":"2017-05-19T08:47:39.331692"},{"mama_id":600004,"carry_value":5,"carry_num":5,"carry_type":2,"carry_type_name":"佣金","carry_description":"APP粉丝佣金，粉丝就是财富。","status":1,"status_display":"预计收益","today_carry":120,"date_field":"2017-05-19","modified":"2017-05-19T08:47:39.325438","created":"2017-05-19T08:47:39.325359"},{"mama_id":600004,"carry_value":100,"carry_num":100,"carry_type":3,"carry_type_name":"奖金","carry_description":"加入正式会员，奖金就会确认哦！","status":1,"status_display":"预计收益","today_carry":105.25,"date_field":"2017-05-18","modified":"2017-05-18T14:04:49.878118","created":"2017-05-18T14:04:49.878089"},{"mama_id":600004,"carry_value":5.25,"carry_num":5.25,"carry_type":2,"carry_type_name":"佣金","carry_description":"APP粉丝佣金，让粉丝更多吧～","status":2,"status_display":"确定收益","today_carry":105.25,"date_field":"2017-05-18","modified":"2017-05-18T20:03:34.044396","created":"2017-05-18T09:32:27.344356"},{"mama_id":600004,"carry_value":100,"carry_num":100,"carry_type":3,"carry_type_name":"奖金","carry_description":"加入正式会员，奖金就会确认哦！","status":1,"status_display":"预计收益","today_carry":100,"date_field":"2017-05-16","modified":"2017-05-16T22:04:57.767837","created":"2017-05-16T22:04:57.767808"},{"mama_id":600004,"carry_value":0,"carry_num":0,"carry_type":2,"carry_type_name":"佣金","carry_description":"APP粉丝佣金，让粉丝更多吧～","status":2,"status_display":"确定收益","today_carry":0,"date_field":"2017-05-15","modified":"2017-05-15T12:10:02.328810","created":"2017-05-15T12:10:02.328680"},{"mama_id":600004,"carry_value":0,"carry_num":0,"carry_type":2,"carry_type_name":"佣金","carry_description":"APP粉丝佣金，来得更直接！","status":1,"status_display":"预计收益","today_carry":0,"date_field":"2017-05-09","modified":"2017-05-09T12:10:08.778273","created":"2017-05-09T12:10:08.778228"},{"mama_id":600004,"carry_value":0,"carry_num":0,"carry_type":2,"carry_type_name":"佣金","carry_description":"APP粉丝佣金，真的粉丝经济。","status":1,"status_display":"预计收益","today_carry":0,"date_field":"2017-05-09","modified":"2017-05-09T12:10:06.902268","created":"2017-05-09T12:10:06.902233"}]
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
         * mama_id : 600004
         * carry_value : 100.0
         * carry_num : 100.0
         * carry_type : 3
         * carry_type_name : 奖金
         * carry_description : 加入正式会员，奖金就会确认哦！
         * status : 1
         * status_display : 预计收益
         * today_carry : 120.0
         * date_field : 2017-05-19
         * modified : 2017-05-19T14:06:43.302551
         * created : 2017-05-19T14:06:43.302517
         */

        private int mama_id;
        private double carry_value;
        private double carry_num;
        private int carry_type;
        private String carry_type_name;
        private String carry_description;
        private int status;
        private String status_display;
        private double today_carry;
        private String date_field;
        private String modified;
        private String created;

        public int getMama_id() {
            return mama_id;
        }

        public void setMama_id(int mama_id) {
            this.mama_id = mama_id;
        }

        public double getCarry_value() {
            return carry_value;
        }

        public void setCarry_value(double carry_value) {
            this.carry_value = carry_value;
        }

        public double getCarry_num() {
            return carry_num;
        }

        public void setCarry_num(double carry_num) {
            this.carry_num = carry_num;
        }

        public int getCarry_type() {
            return carry_type;
        }

        public void setCarry_type(int carry_type) {
            this.carry_type = carry_type;
        }

        public String getCarry_type_name() {
            return carry_type_name;
        }

        public void setCarry_type_name(String carry_type_name) {
            this.carry_type_name = carry_type_name;
        }

        public String getCarry_description() {
            return carry_description;
        }

        public void setCarry_description(String carry_description) {
            this.carry_description = carry_description;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatus_display() {
            return status_display;
        }

        public void setStatus_display(String status_display) {
            this.status_display = status_display;
        }

        public double getToday_carry() {
            return today_carry;
        }

        public void setToday_carry(double today_carry) {
            this.today_carry = today_carry;
        }

        public String getDate_field() {
            return date_field;
        }

        public void setDate_field(String date_field) {
            this.date_field = date_field;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }
    }
}
