package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author wisdom-sun
 * @date 2017年05月24日 上午11:08
 */
public class AwardCarryBean {

    /**
     * count : 1
     * next :
     * previous :
     * results : [{"mama_id":5,"carry_num":30,"carry_type":1,"carry_type_name":"直荐奖励","contributor_nick":"","contributor_img":"","contributor_mama_id":10,"carry_plan_name":"","status":2,"status_display":"已确定","today_carry":30,"modified":"2016-03-14T13:52:40","created":"2016-03-14T13:52:40"}]
     */

    @SerializedName("count")
    private int mCount;
    @SerializedName("next")
    private String mNext;
    @SerializedName("previous")
    private String mPrevious;
    /**
     * mama_id : 5
     * carry_num : 30.0
     * carry_type : 1
     * carry_type_name : 直荐奖励
     * contributor_nick :
     * contributor_img :
     * contributor_mama_id : 10
     * carry_plan_name :
     * status : 2
     * status_display : 已确定
     * today_carry : 30.0
     * modified : 2016-03-14T13:52:40
     * created : 2016-03-14T13:52:40
     */

    @SerializedName("results")
    private List<ResultsEntity> mResults;

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    public String getNext() {
        return mNext;
    }

    public void setNext(String next) {
        this.mNext = next;
    }

    public String getPrevious() {
        return mPrevious;
    }

    public void setPrevious(String previous) {
        this.mPrevious = previous;
    }

    public List<ResultsEntity> getResults() {
        return mResults;
    }

    public void setResults(List<ResultsEntity> results) {
        this.mResults = results;
    }

    public static class ResultsEntity {
        @SerializedName("mama_id")
        private int mMamaId;
        @SerializedName("carry_num")
        private double mCarryNum;
        @SerializedName("carry_type")
        private int mCarryType;
        @SerializedName("carry_type_name")
        private String mCarryTypeName;
        @SerializedName("contributor_nick")
        private String mContributorNick;
        @SerializedName("contributor_img")
        private String mContributorImg;
        @SerializedName("contributor_mama_id")
        private int mContributorMamaId;
        @SerializedName("carry_plan_name")
        private String mCarryPlanName;
        @SerializedName("status")
        private int mStatus;
        @SerializedName("carry_description")
        private String mCarryDescription;
        @SerializedName("status_display")
        private String mStatusDisplay;

        public String getmDate_field() {
            return mDate_field;
        }

        public void setmDate_field(String mDate_field) {
            this.mDate_field = mDate_field;
        }

        @SerializedName("date_field")
        private String mDate_field;
        @SerializedName("today_carry")
        private double mTodayCarry;
        @SerializedName("modified")
        private String mModified;
        @SerializedName("created")
        private String mCreated;

        public String getmCarryDescription() {
            return mCarryDescription;
        }

        public void setmCarryDescription(String mCarryDescription) {
            this.mCarryDescription = mCarryDescription;
        }

        public int getMamaId() {
            return mMamaId;
        }

        public void setMamaId(int mamaId) {
            this.mMamaId = mamaId;
        }

        public double getCarryNum() {
            return mCarryNum;
        }

        public void setCarryNum(double carryNum) {
            this.mCarryNum = carryNum;
        }

        public int getCarryType() {
            return mCarryType;
        }

        public void setCarryType(int carryType) {
            this.mCarryType = carryType;
        }

        public String getCarryTypeName() {
            return mCarryTypeName;
        }

        public void setCarryTypeName(String carryTypeName) {
            this.mCarryTypeName = carryTypeName;
        }

        public String getContributorNick() {
            return mContributorNick;
        }

        public void setContributorNick(String contributorNick) {
            this.mContributorNick = contributorNick;
        }

        public String getContributorImg() {
            return mContributorImg;
        }

        public void setContributorImg(String contributorImg) {
            this.mContributorImg = contributorImg;
        }

        public int getContributorMamaId() {
            return mContributorMamaId;
        }

        public void setContributorMamaId(int contributorMamaId) {
            this.mContributorMamaId = contributorMamaId;
        }

        public String getCarryPlanName() {
            return mCarryPlanName;
        }

        public void setCarryPlanName(String carryPlanName) {
            this.mCarryPlanName = carryPlanName;
        }

        public int getStatus() {
            return mStatus;
        }

        public void setStatus(int status) {
            this.mStatus = status;
        }

        public String getStatusDisplay() {
            return mStatusDisplay;
        }

        public void setStatusDisplay(String statusDisplay) {
            this.mStatusDisplay = statusDisplay;
        }

        public double getTodayCarry() {
            return mTodayCarry;
        }

        public void setTodayCarry(double todayCarry) {
            this.mTodayCarry = todayCarry;
        }

        public String getModified() {
            return mModified;
        }

        public void setModified(String modified) {
            this.mModified = modified;
        }

        public String getCreated() {
            return mCreated;
        }

        public void setCreated(String created) {
            this.mCreated = created;
        }
    }
}
