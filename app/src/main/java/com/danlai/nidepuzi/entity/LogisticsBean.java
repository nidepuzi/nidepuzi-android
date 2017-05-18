package com.danlai.nidepuzi.entity;

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

    private String status;
    private String name;
    private String errcode;
    private String id;
    private String message;
    private String order;
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
        private String content;
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
