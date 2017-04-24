package com.danlai.nidepuzi.entity;

/**
 * @author wisdom
 * @date 2017年04月15日 上午11:42
 */
public class CouponEntity{

    /**
     * id : 1529916
     * template_id : 174
     * coupon_type : 8
     * coupon_type_display : 精品专用券
     * title : 精品丨9.9元精品通用
     * customer : 864138
     * coupon_no : yhq17041458f089f32bb26
     * coupon_value : 9.9
     * value : 9.9
     * valid : true
     * deadline : 2021-11-24T16:14:45
     * start_use_time : 2017-04-14T16:36:03
     * expires_time : 2021-11-24T16:14:45
     * status : 0
     * created : 2017-04-14T16:36:03
     * use_fee : 0
     * use_fee_des : 满0可用
     * pros_desc : 商品专用
     * start_time : 2017-04-14T16:36:03
     * poll_status : 1
     * wisecrack :
     * nick : 伍磊
     * head_img : http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0
     */

    private int id;
    private int template_id;
    private int coupon_type;
    private String coupon_type_display;
    private String title;
    private int customer;
    private String coupon_no;
    private double coupon_value;
    private double value;
    private boolean valid;
    private String deadline;
    private String start_use_time;
    private String expires_time;
    private int status;
    private String created;
    private double use_fee;
    private String use_fee_des;
    private String pros_desc;
    private String start_time;
    private int poll_status;
    private String wisecrack;
    private String nick;
    private String head_img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(int template_id) {
        this.template_id = template_id;
    }

    public int getCoupon_type() {
        return coupon_type;
    }

    public void setCoupon_type(int coupon_type) {
        this.coupon_type = coupon_type;
    }

    public String getCoupon_type_display() {
        return coupon_type_display;
    }

    public void setCoupon_type_display(String coupon_type_display) {
        this.coupon_type_display = coupon_type_display;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public String getCoupon_no() {
        return coupon_no;
    }

    public void setCoupon_no(String coupon_no) {
        this.coupon_no = coupon_no;
    }

    public double getCoupon_value() {
        return coupon_value;
    }

    public void setCoupon_value(double coupon_value) {
        this.coupon_value = coupon_value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStart_use_time() {
        return start_use_time;
    }

    public void setStart_use_time(String start_use_time) {
        this.start_use_time = start_use_time;
    }

    public String getExpires_time() {
        return expires_time;
    }

    public void setExpires_time(String expires_time) {
        this.expires_time = expires_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public double getUse_fee() {
        return use_fee;
    }

    public void setUse_fee(double use_fee) {
        this.use_fee = use_fee;
    }

    public String getUse_fee_des() {
        return use_fee_des;
    }

    public void setUse_fee_des(String use_fee_des) {
        this.use_fee_des = use_fee_des;
    }

    public String getPros_desc() {
        return pros_desc;
    }

    public void setPros_desc(String pros_desc) {
        this.pros_desc = pros_desc;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public int getPoll_status() {
        return poll_status;
    }

    public void setPoll_status(int poll_status) {
        this.poll_status = poll_status;
    }

    public String getWisecrack() {
        return wisecrack;
    }

    public void setWisecrack(String wisecrack) {
        this.wisecrack = wisecrack;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }
}
