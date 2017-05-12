package com.danlai.nidepuzi.entity;

public class ShopBean {

    /**
     * shop_info : {"customer":14,"name":"旭茂の精品店铺","shop_link":"http://m.nidepuzi.com/m/0?next=/mall/%3Fmm_linkid%3D0","preview_shop_link":"http://m.nidepuzi.com/mall/?mm_linkid=0","id":21,"first_pro_pic":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLCMe9eG5cF6PBV0PnMclI5pkoF5VTjEV7c8fGx5uajicsibAY6YUXvSFDj9XKNia3ge3Df8ibkPmw1MEA/0","thumbnail":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLCMe9eG5cF6PBV0PnMclI5pkoF5VTjEV7c8fGx5uajicsibAY6YUXvSFDj9XKNia3ge3Df8ibkPmw1MEA/0","desc":"旭茂の精品店铺上新品啦赶快抢！"}
     */

    private ShopInfoBean shop_info;

    public ShopInfoBean getShop_info() {
        return shop_info;
    }

    public void setShop_info(ShopInfoBean shop_info) {
        this.shop_info = shop_info;
    }

    public static class ShopInfoBean {
        /**
         * customer : 14
         * name : 旭茂の精品店铺
         * shop_link : http://m.nidepuzi.com/m/0?next=/mall/%3Fmm_linkid%3D0
         * preview_shop_link : http://m.nidepuzi.com/mall/?mm_linkid=0
         * id : 21
         * first_pro_pic : http://wx.qlogo.cn/mmopen/ajNVdqHZLLCMe9eG5cF6PBV0PnMclI5pkoF5VTjEV7c8fGx5uajicsibAY6YUXvSFDj9XKNia3ge3Df8ibkPmw1MEA/0
         * thumbnail : http://wx.qlogo.cn/mmopen/ajNVdqHZLLCMe9eG5cF6PBV0PnMclI5pkoF5VTjEV7c8fGx5uajicsibAY6YUXvSFDj9XKNia3ge3Df8ibkPmw1MEA/0
         * desc : 旭茂の精品店铺上新品啦赶快抢！
         */

        private int customer;
        private String name;
        private String shop_link;
        private String preview_shop_link;
        private int id;
        private String first_pro_pic;
        private String thumbnail;
        private String desc;

        public int getCustomer() {
            return customer;
        }

        public void setCustomer(int customer) {
            this.customer = customer;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShop_link() {
            return shop_link;
        }

        public void setShop_link(String shop_link) {
            this.shop_link = shop_link;
        }

        public String getPreview_shop_link() {
            return preview_shop_link;
        }

        public void setPreview_shop_link(String preview_shop_link) {
            this.preview_shop_link = preview_shop_link;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirst_pro_pic() {
            return first_pro_pic;
        }

        public void setFirst_pro_pic(String first_pro_pic) {
            this.first_pro_pic = first_pro_pic;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
