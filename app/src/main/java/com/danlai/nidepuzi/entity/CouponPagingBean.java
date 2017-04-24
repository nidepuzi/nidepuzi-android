package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * @author wisdom
 * @date 2017年04月15日 上午9:27
 */

public class CouponPagingBean {

    /**
     * count : 32
     * next : http://m.xiaolumeimei.com/rest/v1/usercoupons/get_user_coupons?page=2&paging=1&status=0
     * previous : null
     * results : [{"id":1529923,"template_id":175,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨19.9元精品通用","customer":864138,"coupon_no":"yhq17041458f0967b693e6","coupon_value":19.9,"value":19.9,"valid":true,"deadline":"2021-11-25T13:58:57","start_use_time":"2017-04-14T17:29:31","expires_time":"2021-11-25T13:58:57","status":0,"created":"2017-04-14T17:29:31","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T17:29:31","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"},{"id":1529924,"template_id":175,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨19.9元精品通用","customer":864138,"coupon_no":"yhq17041458f0967b6bc91","coupon_value":19.9,"value":19.9,"valid":true,"deadline":"2021-11-25T13:58:57","start_use_time":"2017-04-14T17:29:31","expires_time":"2021-11-25T13:58:57","status":0,"created":"2017-04-14T17:29:31","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T17:29:31","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"},{"id":1529925,"template_id":175,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨19.9元精品通用","customer":864138,"coupon_no":"yhq17041458f0967b6de6b","coupon_value":19.9,"value":19.9,"valid":true,"deadline":"2021-11-25T13:58:57","start_use_time":"2017-04-14T17:29:31","expires_time":"2021-11-25T13:58:57","status":0,"created":"2017-04-14T17:29:31","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T17:29:31","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"},{"id":1529926,"template_id":175,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨19.9元精品通用","customer":864138,"coupon_no":"yhq17041458f0967b701f6","coupon_value":19.9,"value":19.9,"valid":true,"deadline":"2021-11-25T13:58:57","start_use_time":"2017-04-14T17:29:31","expires_time":"2021-11-25T13:58:57","status":0,"created":"2017-04-14T17:29:31","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T17:29:31","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"},{"id":1529927,"template_id":175,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨19.9元精品通用","customer":864138,"coupon_no":"yhq17041458f0967b729f1","coupon_value":19.9,"value":19.9,"valid":true,"deadline":"2021-11-25T13:58:57","start_use_time":"2017-04-14T17:29:31","expires_time":"2021-11-25T13:58:57","status":0,"created":"2017-04-14T17:29:31","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T17:29:31","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"},{"id":1529916,"template_id":174,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨9.9元精品通用","customer":864138,"coupon_no":"yhq17041458f089f32bb26","coupon_value":9.9,"value":9.9,"valid":true,"deadline":"2021-11-24T16:14:45","start_use_time":"2017-04-14T16:36:03","expires_time":"2021-11-24T16:14:45","status":0,"created":"2017-04-14T16:36:03","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T16:36:03","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"},{"id":1529917,"template_id":174,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨9.9元精品通用","customer":864138,"coupon_no":"yhq17041458f089f32fa32","coupon_value":9.9,"value":9.9,"valid":true,"deadline":"2021-11-24T16:14:45","start_use_time":"2017-04-14T16:36:03","expires_time":"2021-11-24T16:14:45","status":0,"created":"2017-04-14T16:36:03","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T16:36:03","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"},{"id":1529918,"template_id":174,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨9.9元精品通用","customer":864138,"coupon_no":"yhq17041458f089f3335d2","coupon_value":9.9,"value":9.9,"valid":true,"deadline":"2021-11-24T16:14:45","start_use_time":"2017-04-14T16:36:03","expires_time":"2021-11-24T16:14:45","status":0,"created":"2017-04-14T16:36:03","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T16:36:03","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"},{"id":1529919,"template_id":174,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨9.9元精品通用","customer":864138,"coupon_no":"yhq17041458f089f337be8","coupon_value":9.9,"value":9.9,"valid":true,"deadline":"2021-11-24T16:14:45","start_use_time":"2017-04-14T16:36:03","expires_time":"2021-11-24T16:14:45","status":0,"created":"2017-04-14T16:36:03","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T16:36:03","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"},{"id":1529920,"template_id":174,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨9.9元精品通用","customer":864138,"coupon_no":"yhq17041458f089f33cb84","coupon_value":9.9,"value":9.9,"valid":true,"deadline":"2021-11-24T16:14:45","start_use_time":"2017-04-14T16:36:03","expires_time":"2021-11-24T16:14:45","status":0,"created":"2017-04-14T16:36:03","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T16:36:03","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"},{"id":1529824,"template_id":174,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨9.9元精品通用","customer":864138,"coupon_no":"yhq17041458f03fbda9305","coupon_value":9.9,"value":9.9,"valid":true,"deadline":"2021-11-24T16:14:45","start_use_time":"2017-04-14T11:19:25","expires_time":"2021-11-24T16:14:45","status":0,"created":"2017-04-14T11:19:25","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T11:19:25","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"},{"id":1529825,"template_id":174,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨9.9元精品通用","customer":864138,"coupon_no":"yhq17041458f03fbdac993","coupon_value":9.9,"value":9.9,"valid":true,"deadline":"2021-11-24T16:14:45","start_use_time":"2017-04-14T11:19:25","expires_time":"2021-11-24T16:14:45","status":0,"created":"2017-04-14T11:19:25","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T11:19:25","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"},{"id":1529826,"template_id":174,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨9.9元精品通用","customer":864138,"coupon_no":"yhq17041458f03fbdb090b","coupon_value":9.9,"value":9.9,"valid":true,"deadline":"2021-11-24T16:14:45","start_use_time":"2017-04-14T11:19:25","expires_time":"2021-11-24T16:14:45","status":0,"created":"2017-04-14T11:19:25","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T11:19:25","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"},{"id":1529827,"template_id":174,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨9.9元精品通用","customer":864138,"coupon_no":"yhq17041458f03fbdb45ed","coupon_value":9.9,"value":9.9,"valid":true,"deadline":"2021-11-24T16:14:45","start_use_time":"2017-04-14T11:19:25","expires_time":"2021-11-24T16:14:45","status":0,"created":"2017-04-14T11:19:25","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T11:19:25","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"},{"id":1529828,"template_id":174,"coupon_type":8,"coupon_type_display":"精品专用券","title":"精品丨9.9元精品通用","customer":864138,"coupon_no":"yhq17041458f03fbdb8507","coupon_value":9.9,"value":9.9,"valid":true,"deadline":"2021-11-24T16:14:45","start_use_time":"2017-04-14T11:19:25","expires_time":"2021-11-24T16:14:45","status":0,"created":"2017-04-14T11:19:25","use_fee":0,"use_fee_des":"满0可用","pros_desc":"商品专用","start_time":"2017-04-14T11:19:25","poll_status":1,"wisecrack":"","nick":"伍磊","head_img":"http://wx.qlogo.cn/mmopen/jfpC8E6yr8eAicOVqlBHXZs5iau6SiaYgH55jBxE57FYdj8dsbicTJbG8hXFZnHovLKu6aCbHqvmaMdWdBiaShZIVGszxhggbhvDia/0"}]
     * info: ""
     * disable_coupon_count: 27
     * code: 0
     * usable_coupon_count: 5
     */

    private int count;
    private String next;
    private String previous;
    private List<CouponEntity> results;
    private String info;
    private int disable_coupon_count;
    private int code;
    private int usable_coupon_count;


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

    public List<CouponEntity> getResults() {
        return results;
    }

    public void setResults(List<CouponEntity> results) {
        this.results = results;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getDisable_coupon_count() {
        return disable_coupon_count;
    }

    public void setDisable_coupon_count(int disable_coupon_count) {
        this.disable_coupon_count = disable_coupon_count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getUsable_coupon_count() {
        return usable_coupon_count;
    }

    public void setUsable_coupon_count(int usable_coupon_count) {
        this.usable_coupon_count = usable_coupon_count;
    }
}
