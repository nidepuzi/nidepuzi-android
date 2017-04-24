package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * Created by itxuye on 16/4/28.
 */
public class PortalBean {
    private List<PostersBean> posters;
    private List<CategorysBean> categorys;
    private List<ActivitysBean> activitys;
    private List<PromotionBrandsBean> promotion_brands;

    public List<PostersBean> getPosters() {
        return posters;
    }

    public void setPosters(List<PostersBean> posters) {
        this.posters = posters;
    }

    public List<CategorysBean> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<CategorysBean> categorys) {
        this.categorys = categorys;
    }

    public List<ActivitysBean> getActivitys() {
        return activitys;
    }

    public void setActivitys(List<ActivitysBean> activitys) {
        this.activitys = activitys;
    }

    public List<PromotionBrandsBean> getPromotion_brands() {
        return promotion_brands;
    }

    public void setPromotion_brands(List<PromotionBrandsBean> promotion_brands) {
        this.promotion_brands = promotion_brands;
    }

    public static class PostersBean {
        private String pic_link;
        private String app_link;

        public String getPic_link() {
            return pic_link;
        }

        public void setPic_link(String pic_link) {
            this.pic_link = pic_link;
        }

        public String getApp_link() {
            return app_link;
        }

        public void setApp_link(String app_link) {
            this.app_link = app_link;
        }
    }

    public static class CategorysBean {
        private String cat_link;
        private String cat_img;
        private String id;
        private String name;

        public String getCat_link() {
            return cat_link;
        }

        public void setCat_link(String cat_link) {
            this.cat_link = cat_link;
        }

        public String getCat_img() {
            return cat_img;
        }

        public void setCat_img(String cat_img) {
            this.cat_img = cat_img;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ActivitysBean {

        private int id;
        private String title;
        private boolean login_required;
        private String act_img;
        private String mask_link;
        private String act_link;

        /**
         * html : {"apply":"/mActivity/post/index.html"}
         * data : {}
         */

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isLogin_required() {
            return login_required;
        }

        public void setLogin_required(boolean login_required) {
            this.login_required = login_required;
        }

        public String getAct_img() {
            return act_img;
        }

        public void setAct_img(String act_img) {
            this.act_img = act_img;
        }

        public String getMask_link() {
            return mask_link;
        }

        public void setMask_link(String mask_link) {
            this.mask_link = mask_link;
        }

        public String getAct_link() {
            return act_link;
        }

        public void setAct_link(String act_link) {
            this.act_link = act_link;
        }
    }

    public static class PromotionBrandsBean {

        private int id;
        private String title;
        private String act_img;
        private String act_logo;
        private String act_applink;
        private ExtrasBean extras;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ExtrasBean getExtras() {
            return extras;
        }

        public void setExtras(ExtrasBean extras) {
            this.extras = extras;
        }

        public String getAct_applink() {
            return act_applink;
        }

        public void setAct_applink(String act_applink) {
            this.act_applink = act_applink;
        }

        public String getAct_logo() {
            return act_logo;
        }

        public void setAct_logo(String act_logo) {
            this.act_logo = act_logo;
        }

        public String getAct_img() {
            return act_img;
        }

        public void setAct_img(String act_img) {
            this.act_img = act_img;
        }

        public static class ExtrasBean {
            private BrandinfoBean brandinfo;

            public BrandinfoBean getBrandinfo() {
                return brandinfo;
            }

            public void setBrandinfo(BrandinfoBean brandinfo) {
                this.brandinfo = brandinfo;
            }

            public static class BrandinfoBean {
                private String tail_title;

                public String getTail_title() {
                    return tail_title;
                }

                public void setTail_title(String tail_title) {
                    this.tail_title = tail_title;
                }
            }
        }
    }
}
