package com.danlai.nidepuzi.entity;

/**
 * Created by wisdom on 16/8/9.
 */
public class ShareModelBean {


    /**
     * url : http://m.xiaolumeimei.com/rest/v1/share/3
     * id : 3
     * title : 时尚潮品休闲套装
     * desc : 时尚潮品休闲套装--小鹿美美
     * share_img : http://image.xiaolu.so/MG_1459241020654.png?imageMogr2/thumbnail/108/quality/80/format/jpg/crop/108x108/
     * active_at : 2016-01-22
     * created : 2016-01-22T16:02:27
     * status : true
     * share_link : http://m.xiaolumeimei.com/m/44?next=/mall/product/details/8432
     */

    private String url;
    private int id;
    private String title;
    private String desc;
    private String share_img;
    private String active_at;
    private String created;
    private boolean status;
    private String share_link;
    /**
     * profit : {"max":13.9,"min":3.9}
     */

    private ProfitBean profit;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getShare_img() {
        return share_img;
    }

    public void setShare_img(String share_img) {
        this.share_img = share_img;
    }

    public String getActive_at() {
        return active_at;
    }

    public void setActive_at(String active_at) {
        this.active_at = active_at;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getShare_link() {
        return share_link;
    }

    public void setShare_link(String share_link) {
        this.share_link = share_link;
    }

    public ProfitBean getProfit() {
        return profit;
    }

    public void setProfit(ProfitBean profit) {
        this.profit = profit;
    }

    public static class ProfitBean {
        /**
         * max : 13.9
         * min : 3.9
         */

        private double max;
        private double min;

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }
    }
}
