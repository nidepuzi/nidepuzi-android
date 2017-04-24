package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * Created by wisdom on 16/9/5.
 */
public class TeamBuyBean {
    /**
     * id : 20
     * sku : 233417
     * product_info : {"model_id":18272,"agent_price":29.9,"name":"精致刺绣遮阳棒球帽/白色主图款","head_imgs":["http://img.xiaolumeimei.com/MG_14704827392384.jpg"],"item_marks":["包邮"],"team_price":22.2,"desc":""}
     * limit_time : 2016-09-06T14:43:23
     * limit_days : 3
     * status : 0
     * detail_info : [{"customer_nick":"meron@小鹿美美","oid":"xo16090357ca7107bd9fc","originizer":true,"tid":"xd16090357ca71050c5db","customer_id":1,"join_time":"2016-09-03T14:43:23","customer_thumbnail":"http://wx.qlogo.cn/mmopen/n24ek7Oc1iaXyxqzHobN7BicG5W1ljszSRWSdzaFeRkGGVwqjmQKTmicTylm8IkclpgDiaamWqZtiaTlcvLJ5z6x35wCKMWVbcYPU/0"}]
     */

    private int id;
    private int sku;
    /**
     * model_id : 18272
     * agent_price : 29.9
     * name : 精致刺绣遮阳棒球帽/白色主图款
     * head_imgs : ["http://img.xiaolumeimei.com/MG_14704827392384.jpg"]
     * item_marks : ["包邮"]
     * team_price : 22.2
     * desc :
     */

    private ProductInfoBean product_info;
    private String limit_time;
    private int limit_days;
    private int status;
    /**
     * customer_nick : meron@小鹿美美
     * oid : xo16090357ca7107bd9fc
     * originizer : true
     * tid : xd16090357ca71050c5db
     * customer_id : 1
     * join_time : 2016-09-03T14:43:23
     * customer_thumbnail : http://wx.qlogo.cn/mmopen/n24ek7Oc1iaXyxqzHobN7BicG5W1ljszSRWSdzaFeRkGGVwqjmQKTmicTylm8IkclpgDiaamWqZtiaTlcvLJ5z6x35wCKMWVbcYPU/0
     */

    private List<DetailInfoBean> detail_info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public ProductInfoBean getProduct_info() {
        return product_info;
    }

    public void setProduct_info(ProductInfoBean product_info) {
        this.product_info = product_info;
    }

    public String getLimit_time() {
        return limit_time;
    }

    public void setLimit_time(String limit_time) {
        this.limit_time = limit_time;
    }

    public int getLimit_days() {
        return limit_days;
    }

    public void setLimit_days(int limit_days) {
        this.limit_days = limit_days;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DetailInfoBean> getDetail_info() {
        return detail_info;
    }

    public void setDetail_info(List<DetailInfoBean> detail_info) {
        this.detail_info = detail_info;
    }

    public static class ProductInfoBean {
        private int model_id;
        private double agent_price;
        private String name;
        private double team_price;
        private String desc;
        private List<String> head_imgs;
        private List<String> item_marks;

        public int getModel_id() {
            return model_id;
        }

        public void setModel_id(int model_id) {
            this.model_id = model_id;
        }

        public double getAgent_price() {
            return agent_price;
        }

        public void setAgent_price(double agent_price) {
            this.agent_price = agent_price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getTeam_price() {
            return team_price;
        }

        public void setTeam_price(double team_price) {
            this.team_price = team_price;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<String> getHead_imgs() {
            return head_imgs;
        }

        public void setHead_imgs(List<String> head_imgs) {
            this.head_imgs = head_imgs;
        }

        public List<String> getItem_marks() {
            return item_marks;
        }

        public void setItem_marks(List<String> item_marks) {
            this.item_marks = item_marks;
        }
    }

    public static class DetailInfoBean {
        private String customer_nick;
        private String oid;
        private boolean originizer;
        private String tid;
        private int customer_id;
        private String join_time;
        private String customer_thumbnail;

        public String getCustomer_nick() {
            return customer_nick;
        }

        public void setCustomer_nick(String customer_nick) {
            this.customer_nick = customer_nick;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public boolean isOriginizer() {
            return originizer;
        }

        public void setOriginizer(boolean originizer) {
            this.originizer = originizer;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public int getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(int customer_id) {
            this.customer_id = customer_id;
        }

        public String getJoin_time() {
            return join_time;
        }

        public void setJoin_time(String join_time) {
            this.join_time = join_time;
        }

        public String getCustomer_thumbnail() {
            return customer_thumbnail;
        }

        public void setCustomer_thumbnail(String customer_thumbnail) {
            this.customer_thumbnail = customer_thumbnail;
        }
    }
}
