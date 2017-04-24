package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

public class LogoutBean {

    /**
     * code : 0
     * result : logout
     */

    @SerializedName("code")
    private int mCode;
    @SerializedName("result")
    private String mResult;

    public void setCode(int code) {
        this.mCode = code;
    }

    public void setResult(String result) {
        this.mResult = result;
    }

    public int getCode() {
        return mCode;
    }

    public String getResult() {
        return mResult;
    }
}
