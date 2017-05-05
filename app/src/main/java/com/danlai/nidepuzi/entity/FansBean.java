package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wulei on 2016/2/5.
 */
public class FansBean {

    /**
     * count : 18
     * next : http://192.168.1.13:8000/rest/v2/mama/fans?page=2
     * previous : null
     * results : [{"fans_nick":"","fans_thumbnail":"","fans_description":"来自好友的分享","created":"2016-03-19T22:44:16"},{"fans_nick":"","fans_thumbnail":"","fans_description":"通过您的分享成为粉丝","created":"2016-03-15T17:26:18"},{"fans_nick":"","fans_thumbnail":"","fans_description":"通过您的分享成为粉丝","created":"2016-03-15T17:24:07"},{"fans_nick":"","fans_thumbnail":"","fans_description":"通过您的分享成为粉丝","created":"2016-03-15T17:23:16"},{"fans_nick":"","fans_thumbnail":"","fans_description":"通过您的分享成为粉丝","created":"2016-03-15T16:53:27"},{"fans_nick":"","fans_thumbnail":"","fans_description":"通过您的分享成为粉丝","created":"2016-03-15T16:50:36"},{"fans_nick":"","fans_thumbnail":"","fans_description":"通过您的分享成为粉丝","created":"2016-03-15T16:29:50"},{"fans_nick":"","fans_thumbnail":"","fans_description":"通过您的分享成为粉丝","created":"2016-03-15T16:28:14"},{"fans_nick":"","fans_thumbnail":"","fans_description":"通过您的分享成为粉丝","created":"2016-03-14T15:47:42"},{"fans_nick":"","fans_thumbnail":"","fans_description":"通过您的分享成为粉丝","created":"2016-03-14T15:47:11"}]
     */

    @SerializedName("count")
    private int mCount;
    @SerializedName("next")
    private String mNext;
    @SerializedName("previous")
    private String mPrevious;
    /**
     * fans_nick :
     * fans_thumbnail :
     * fans_description : 来自好友的分享
     * created : 2016-03-19T22:44:16
     */

    @SerializedName("results")
    private List<ResultsEntity> mResults;

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public String getNext() {
        return mNext;
    }

    public void setNext(String next) {
        mNext = next;
    }

    public String getPrevious() {
        return mPrevious;
    }

    public void setPrevious(String previous) {
        mPrevious = previous;
    }

    public List<ResultsEntity> getResults() {
        return mResults;
    }

    public void setResults(List<ResultsEntity> results) {
        mResults = results;
    }

    public static class ResultsEntity {
        @SerializedName("fans_nick")
        private String mFansNick;
        @SerializedName("fans_thumbnail")
        private String mFansThumbnail;
        @SerializedName("fans_description")
        private String mFansDescription;
        @SerializedName("created")
        private String mCreated;
        @SerializedName("fans_mobile")
        private String fans_mobile;

        public String getFansNick() {
            return mFansNick;
        }

        public void setFansNick(String fansNick) {
            mFansNick = fansNick;
        }

        public String getFansThumbnail() {
            return mFansThumbnail;
        }

        public void setFansThumbnail(String fansThumbnail) {
            mFansThumbnail = fansThumbnail;
        }

        public String getFansDescription() {
            return mFansDescription;
        }

        public void setFansDescription(String fansDescription) {
            mFansDescription = fansDescription;
        }

        public String getCreated() {
            return mCreated;
        }

        public void setCreated(String created) {
            mCreated = created;
        }

        public String getFans_mobile() {
            return fans_mobile;
        }

        public void setFans_mobile(String fans_mobile) {
            this.fans_mobile = fans_mobile;
        }
    }
}
