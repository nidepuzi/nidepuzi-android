package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by itxuye(www.itxuye.com) on 2016/02/26.
 * <p>
 * Copyright 2016年 上海己美. All rights reserved.
 */
public class BudgetDetailBean {

    /**
     * count : 2
     * next : null
     * previous : null
     * results : [{"budget_type":0,"budget_log_type":"envelop","budget_date":"2016-02-26","status":0,"budeget_detail_cash":2},{"budget_type":1,"budget_log_type":"consum","budget_date":"2016-02-26","status":0,"budeget_detail_cash":5}]
     */

    @SerializedName("count")
    private int mCount;
    @SerializedName("next")
    private String mNext;
    @SerializedName("previous")
    private String mPrevious;
    /**
     * "desc": "您通过红包收入1137.12元.",
     * budget_type : 0
     * budget_log_type : envelop
     * budget_date : 2016-02-26
     * status : 0
     * budeget_detail_cash : 2.0
     */

    @SerializedName("results")
    private List<ResultsEntity> mResults;

    public void setCount(int count) {
        this.mCount = count;
    }

    public void setNext(String next) {
        this.mNext = next;
    }

    public void setPrevious(String previous) {
        this.mPrevious = previous;
    }

    public void setResults(List<ResultsEntity> results) {
        this.mResults = results;
    }

    public int getCount() {
        return mCount;
    }

    public String getNext() {
        return mNext;
    }

    public String getPrevious() {
        return mPrevious;
    }

    public List<ResultsEntity> getResults() {
        return mResults;
    }

    public static class ResultsEntity implements Serializable {
        @SerializedName("desc")
        private String mDesc;
        @SerializedName("budget_type")
        private int mBudgetType;
        @SerializedName("budget_log_type")
        private String mBudgetLogType;
        @SerializedName("budget_date")
        private String mBudgetDate;
        @SerializedName("status")
        private int mStatus;
        @SerializedName("budeget_detail_cash")
        private double mBudegetDetailCash;

        public void setDesc(String desc) {
            this.mDesc = desc;
        }

        public void setBudgetType(int budgetType) {
            this.mBudgetType = budgetType;
        }

        public void setBudgetLogType(String budgetLogType) {
            this.mBudgetLogType = budgetLogType;
        }

        public void setBudgetDate(String budgetDate) {
            this.mBudgetDate = budgetDate;
        }

        public void setStatus(int status) {
            this.mStatus = status;
        }

        public void setBudegetDetailCash(double budegetDetailCash) {
            this.mBudegetDetailCash = budegetDetailCash;
        }

        public String getDesc() {
            return mDesc;
        }

        public int getBudgetType() {
            return mBudgetType;
        }

        public String getBudgetLogType() {
            return mBudgetLogType;
        }

        public String getBudgetDate() {
            return mBudgetDate;
        }

        public int getStatus() {
            return mStatus;
        }

        public double getBudegetDetailCash() {
            return mBudegetDetailCash;
        }
    }
}
