package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * Created by wisdom on 16/8/11.
 */
public class ProductListBean {

    /**
     * count : 77
     * next : http://m.xiaolumeimei.com/rest/v2/modelproducts/today?page=3
     * previous : http://m.xiaolumeimei.com/rest/v2/modelproducts/today
     * results : [{"id":16784,"url":"http://m.xiaolumeimei.com/rest/v2/modelproducts/16784","name":"优雅气质纯色连衣裙","category_id":60,"lowest_agent_price":99.9,"lowest_std_sale_price":499,"onshelf_time":"2016-08-11T10:00:00","offshelf_time":"2016-08-13T10:00:00","is_saleout":false,"sale_state":"on","head_img":"http://image.xiaolu.so/MG_1468482988133.jpg","web_url":"http://m.xiaolumeimei.com/mall/product/details/16784","watermark_op":""},{"id":16786,"url":"http://m.xiaolumeimei.com/rest/v2/modelproducts/16786","name":"小香风修身蕾丝套装","category_id":61,"lowest_agent_price":189.9,"lowest_std_sale_price":598,"onshelf_time":"2016-08-11T10:00:00","offshelf_time":"2016-08-13T10:00:00","is_saleout":false,"sale_state":"on","head_img":"http://image.xiaolu.so/MG_1468485247952.jpg","web_url":"http://m.xiaolumeimei.com/mall/product/details/16786","watermark_op":""},{"id":17239,"url":"http://m.xiaolumeimei.com/rest/v2/modelproducts/17239","name":"性感运动字母背心","category_id":70,"lowest_agent_price":29.9,"lowest_std_sale_price":99,"onshelf_time":"2016-08-11T10:00:00","offshelf_time":"2016-08-13T10:00:00","is_saleout":false,"sale_state":"on","head_img":"http://image.xiaolu.so/MG_1468998603232.jpg","web_url":"http://m.xiaolumeimei.com/mall/product/details/17239","watermark_op":""},{"id":17241,"url":"http://m.xiaolumeimei.com/rest/v2/modelproducts/17241","name":"简约必备条纹背心裙","category_id":60,"lowest_agent_price":39.9,"lowest_std_sale_price":99,"onshelf_time":"2016-08-11T10:00:00","offshelf_time":"2016-08-13T10:00:00","is_saleout":false,"sale_state":"on","head_img":"http://image.xiaolu.so/MG_1468999293588.jpg","web_url":"http://m.xiaolumeimei.com/mall/product/details/17241","watermark_op":""},{"id":17243,"url":"http://m.xiaolumeimei.com/rest/v2/modelproducts/17243","name":"修身百搭米老鼠T恤","category_id":70,"lowest_agent_price":39.9,"lowest_std_sale_price":99,"onshelf_time":"2016-08-11T10:00:00","offshelf_time":"2016-08-13T10:00:00","is_saleout":false,"sale_state":"on","head_img":"http://image.xiaolu.so/MG_1469000707853.jpg","web_url":"http://m.xiaolumeimei.com/mall/product/details/17243","watermark_op":""},{"id":17248,"url":"http://m.xiaolumeimei.com/rest/v2/modelproducts/17248","name":"简约必备背心裙两件套","category_id":61,"lowest_agent_price":69.9,"lowest_std_sale_price":199,"onshelf_time":"2016-08-11T10:00:00","offshelf_time":"2016-08-13T10:00:00","is_saleout":false,"sale_state":"on","head_img":"http://image.xiaolu.so/MG_1468981779124.jpg","web_url":"http://m.xiaolumeimei.com/mall/product/details/17248","watermark_op":""},{"id":17253,"url":"http://m.xiaolumeimei.com/rest/v2/modelproducts/17253","name":"时尚V领条纹套装","category_id":65,"lowest_agent_price":59.9,"lowest_std_sale_price":199,"onshelf_time":"2016-08-11T10:00:00","offshelf_time":"2016-08-13T10:00:00","is_saleout":false,"sale_state":"on","head_img":"http://image.xiaolu.so/MG_1469073420996.jpg","web_url":"http://m.xiaolumeimei.com/mall/product/details/17253","watermark_op":""},{"id":17254,"url":"http://m.xiaolumeimei.com/rest/v2/modelproducts/17254","name":"韩版芒果吊带裙","category_id":64,"lowest_agent_price":59.9,"lowest_std_sale_price":199,"onshelf_time":"2016-08-11T10:00:00","offshelf_time":"2016-08-13T10:00:00","is_saleout":true,"sale_state":"on","head_img":"http://image.xiaolu.so/MG_1469074382091-.jpg","web_url":"http://m.xiaolumeimei.com/mall/product/details/17254","watermark_op":""},{"id":17262,"url":"http://m.xiaolumeimei.com/rest/v2/modelproducts/17262","name":"时尚卡通动物套装","category_id":65,"lowest_agent_price":49.9,"lowest_std_sale_price":199,"onshelf_time":"2016-08-11T10:00:00","offshelf_time":"2016-08-13T10:00:00","is_saleout":false,"sale_state":"on","head_img":"http://image.xiaolu.so/MG_1469083185770-.jpg","web_url":"http://m.xiaolumeimei.com/mall/product/details/17262","watermark_op":""},{"id":17269,"url":"http://m.xiaolumeimei.com/rest/v2/modelproducts/17269","name":"韩版挂脖吊带套装","category_id":65,"lowest_agent_price":49.9,"lowest_std_sale_price":199,"onshelf_time":"2016-08-11T10:00:00","offshelf_time":"2016-08-13T10:00:00","is_saleout":false,"sale_state":"on","head_img":"http://image.xiaolu.so/MG_1469084398422-.jpg","web_url":"http://m.xiaolumeimei.com/mall/product/details/17269","watermark_op":""}]
     * offshelf_deadline : 2016-08-13T10:00:00
     * onshelf_starttime : 2016-08-11T10:00:00
     */

    private int count;
    private String next;
    private String previous;
    private String offshelf_deadline;
    private String onshelf_starttime;
    /**
     * id : 16784
     * url : http://m.xiaolumeimei.com/rest/v2/modelproducts/16784
     * name : 优雅气质纯色连衣裙
     * category_id : 60
     * lowest_agent_price : 99.9
     * lowest_std_sale_price : 499
     * onshelf_time : 2016-08-11T10:00:00
     * offshelf_time : 2016-08-13T10:00:00
     * is_saleout : false
     * sale_state : on
     * head_img : http://image.xiaolu.so/MG_1468482988133.jpg
     * web_url : http://m.xiaolumeimei.com/mall/product/details/16784
     * watermark_op :
     */

    private List<ResultsBean> results;

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

    public String getOffshelf_deadline() {
        return offshelf_deadline;
    }

    public void setOffshelf_deadline(String offshelf_deadline) {
        this.offshelf_deadline = offshelf_deadline;
    }

    public String getOnshelf_starttime() {
        return onshelf_starttime;
    }

    public void setOnshelf_starttime(String onshelf_starttime) {
        this.onshelf_starttime = onshelf_starttime;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private int id;
        private String url;
        private String name;
        private int category_id;
        private double lowest_agent_price;
        private double lowest_std_sale_price;
        private String onshelf_time;
        private String offshelf_time;
        private boolean is_saleout;
        private String sale_state;
        private String head_img;
        private String web_url;
        private String watermark_op;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public double getLowest_agent_price() {
            return lowest_agent_price;
        }

        public void setLowest_agent_price(double lowest_agent_price) {
            this.lowest_agent_price = lowest_agent_price;
        }

        public double getLowest_std_sale_price() {
            return lowest_std_sale_price;
        }

        public void setLowest_std_sale_price(double lowest_std_sale_price) {
            this.lowest_std_sale_price = lowest_std_sale_price;
        }

        public String getOnshelf_time() {
            return onshelf_time;
        }

        public void setOnshelf_time(String onshelf_time) {
            this.onshelf_time = onshelf_time;
        }

        public String getOffshelf_time() {
            return offshelf_time;
        }

        public void setOffshelf_time(String offshelf_time) {
            this.offshelf_time = offshelf_time;
        }

        public boolean isIs_saleout() {
            return is_saleout;
        }

        public void setIs_saleout(boolean is_saleout) {
            this.is_saleout = is_saleout;
        }

        public String getSale_state() {
            return sale_state;
        }

        public void setSale_state(String sale_state) {
            this.sale_state = sale_state;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public String getWatermark_op() {
            return watermark_op;
        }

        public void setWatermark_op(String watermark_op) {
            this.watermark_op = watermark_op;
        }
    }
}
