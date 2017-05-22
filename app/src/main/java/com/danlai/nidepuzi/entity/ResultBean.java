package com.danlai.nidepuzi.entity;

/**
 * Created by itxuye on 16/4/5.
 */
public class ResultBean {


    /**
     * message : 更换成功
     * code : 0
     */

    private String message;
    private int code;
    private String info;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
