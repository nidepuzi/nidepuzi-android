package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wisdom on 16/4/12.
 */
public class LogisticsBean {

    /**
     * status : null
     * name :
     * errcode :
     * id :
     * message : 您的订单正在配货
     * data : []
     * order :
     */

    @SerializedName("status")
    private String status;
    @SerializedName("name")
    private String name;
    @SerializedName("errcode")
    private String errcode;
    @SerializedName("id")
    private String id;
    @SerializedName("message")
    private String message;
    @SerializedName("order")
    private String order;
    @SerializedName("data")
    private List<Msg> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<Msg> getData() {
        return data;
    }

    public void setData(List<Msg> data) {
        this.data = data;
    }

    public class Msg {
        @SerializedName("content")
        private String content;
        @SerializedName("time")
        private String time;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
