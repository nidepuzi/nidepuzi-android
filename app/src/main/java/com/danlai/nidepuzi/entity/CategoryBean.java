package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * Created by wisdom on 16/8/3.
 */
public class CategoryBean {


    /**
     * childs : [{"grade":2,"cat_pic":"http://7xogkj.com1.z0.glb.clouddn.com/category/image/women1.jpg","parent_cid":"1","name":"上装","cid":"63"},{"grade":2,"cat_pic":"http://7xogkj.com1.z0.glb.clouddn.com/category/image/women1.jpg","parent_cid":"1","name":"连衣裙","cid":"64"},{"grade":2,"cat_pic":"http://7xogkj.com1.z0.glb.clouddn.com/category/image/women1.jpg","parent_cid":"1","name":"套装","cid":"65"},{"grade":2,"cat_pic":"http://7xogkj.com1.z0.glb.clouddn.com/category/image/women1.jpg","parent_cid":"1","name":"外套","cid":"66"},{"grade":2,"cat_pic":"http://7xogkj.com1.z0.glb.clouddn.com/category/image/women1.jpg","parent_cid":"1","name":"哈衣","cid":"67"},{"grade":2,"cat_pic":"http://7xogkj.com1.z0.glb.clouddn.com/category/image/women1.jpg","parent_cid":"1","name":"下装","cid":"68"},{"grade":2,"cat_pic":"http://7xogkj.com1.z0.glb.clouddn.com/category/image/women1.jpg","parent_cid":"1","name":"配饰","cid":"72"},{"grade":2,"cat_pic":"http://7xogkj.com1.z0.glb.clouddn.com/category/image/women1.jpg","parent_cid":"1","name":"亲子装","cid":"74"},{"grade":2,"cat_pic":"http://7xogkj.com1.z0.glb.clouddn.com/category/image/women1.jpg","parent_cid":"1","name":"内衣","cid":"75"}]
     * name : 童装
     * cid : 1
     * grade : 1
     * parent_cid : 0
     * cat_pic : http://7xogkj.com1.z0.glb.clouddn.com/category/image/women1.jpg
     */

    private String name;
    private String cid;
    private int grade;
    private String parent_cid;
    private String cat_pic;
    /**
     * grade : 2
     * cat_pic : http://7xogkj.com1.z0.glb.clouddn.com/category/image/women1.jpg
     * parent_cid : 1
     * name : 上装
     * cid : 63
     */

    private List<CategoryBean> childs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getParent_cid() {
        return parent_cid;
    }

    public void setParent_cid(String parent_cid) {
        this.parent_cid = parent_cid;
    }

    public String getCat_pic() {
        return cat_pic;
    }

    public void setCat_pic(String cat_pic) {
        this.cat_pic = cat_pic;
    }

    public List<CategoryBean> getChilds() {
        return childs;
    }

    public void setChilds(List<CategoryBean> childs) {
        this.childs = childs;
    }

    public static class ChildsBean {
        private int grade;
        private String cat_pic;
        private String parent_cid;
        private String name;
        private String cid;
        private List<ChildsBean> childs;

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getCat_pic() {
            return cat_pic;
        }

        public void setCat_pic(String cat_pic) {
            this.cat_pic = cat_pic;
        }

        public String getParent_cid() {
            return parent_cid;
        }

        public void setParent_cid(String parent_cid) {
            this.parent_cid = parent_cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public List<ChildsBean> getChilds() {
            return childs;
        }

        public void setChilds(List<ChildsBean> childs) {
            this.childs = childs;
        }
    }
}
