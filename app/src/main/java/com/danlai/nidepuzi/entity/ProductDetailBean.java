package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * Created by wisdom on 16/8/9.
 */
public class ProductDetailBean {
    /**
     * id : 8432
     * detail_content : {"category":{"id":15},"is_newsales":false,"model_code":"91528721002","name":"时尚潮品休闲套装","sale_time":"2016-08-08","watermark_op":"","offshelf_time":"2016-08-10T10:00:00","is_recommend":false,"head_imgs":["http://image.xiaolu.so/MG_14558670230841.png","http://image.xiaolu.so/MG_14558670323242.png"],"properties":{},"is_saleopen":true,"content_imgs":["http://image.xiaolu.so/MG_1456055816802模版_01.jpg","http://image.xiaolu.so/MG_1456055823636模版_02.jpg","http://image.xiaolu.so/MG_1456055823800模版_03.jpg","http://image.xiaolu.so/MG_1456055823881模版_04.jpg"],"lowest_agent_price":89.9,"is_flatten":false,"is_sale_out":false,"lowest_std_sale_price":449,"item_marks":["包邮"]}
     * sku_info : [{"agent_price":89.9,"sku_items":[{"agent_price":89.9,"sku_id":130025,"name":"120","std_sale_price":449,"type":"size","free_num":15,"is_saleout":false},{"agent_price":89.9,"sku_id":130026,"name":"130","std_sale_price":449,"type":"size","free_num":15,"is_saleout":false},{"agent_price":89.9,"sku_id":130027,"name":"140","std_sale_price":449,"type":"size","free_num":15,"is_saleout":false},{"agent_price":89.9,"sku_id":130028,"name":"150","std_sale_price":449,"type":"size","free_num":15,"is_saleout":false},{"agent_price":89.9,"sku_id":130029,"name":"160","std_sale_price":449,"type":"size","free_num":13,"is_saleout":false}],"product_id":33097,"std_sale_price":449,"lowest_price":89.9,"outer_id":"915287210021","type":"color","product_img":"http://image.xiaolu.so/MG_14558670230841.png","name":"黑色"},{"agent_price":89.9,"sku_items":[{"agent_price":89.9,"sku_id":130030,"name":"120","std_sale_price":449,"type":"size","free_num":15,"is_saleout":false},{"agent_price":89.9,"sku_id":130031,"name":"130","std_sale_price":449,"type":"size","free_num":14,"is_saleout":false},{"agent_price":89.9,"sku_id":130032,"name":"140","std_sale_price":449,"type":"size","free_num":13,"is_saleout":false},{"agent_price":89.9,"sku_id":130033,"name":"150","std_sale_price":449,"type":"size","free_num":14,"is_saleout":false},{"agent_price":89.9,"sku_id":130034,"name":"160","std_sale_price":449,"type":"size","free_num":14,"is_saleout":false}],"product_id":33098,"std_sale_price":449,"lowest_price":89.9,"outer_id":"915287210022","type":"color","product_img":"http://image.xiaolu.so/MG_14558670323242.png","name":"灰色"}]
     * comparison : {"metrics":{},"attributes":[{"name":"商品编码","value":"91528721002"},{"name":"商品材质","value":"棉混纺"},{"name":"可选颜色","value":"黑色,灰色"},{"name":"洗涤说明","value":"洗涤时请深色、浅色衣物分开洗涤。最高洗涤温度不要超过40度， 不可漂白。有涂层、印花表面不能进行熨烫，会导致表面剥落。不可干洗，悬挂晾干。(如是婴幼儿衣物，请于成人衣物分开洗涤，避免交叉感染。建议手洗水温30度，使用婴幼儿专用衣物洗涤剂。）"},{"name":"备注说明","value":"连帽拉链卫衣+长裤，男童套，松紧裤腰，柔软舒适。"}],"tables":[{"table":[["尺码","建议身高","裤腰","衣长","胸围","肩宽","臀围","裤长"],["120","110","54-64","48","72","29.5","72","70"],["130","120","56-66","51","76","31","76","75"],["140","130","58-68","54","80","32.5","80","80"],["150","140","60-70","57","84","34","84","85"],["160","150","62-72","60","88","35.5","88","90"]]}]}
     * extras : {}
     * custom_info : {"is_favorite":false}
     */

    private int id;
    /**
     * category : {"id":15}
     * is_newsales : false
     * model_code : 91528721002
     * name : 时尚潮品休闲套装
     * sale_time : 2016-08-08
     * watermark_op :
     * offshelf_time : 2016-08-10T10:00:00
     * is_recommend : false
     * head_imgs : ["http://image.xiaolu.so/MG_14558670230841.png","http://image.xiaolu.so/MG_14558670323242.png"]
     * properties : {}
     * is_saleopen : true
     * content_imgs : ["http://image.xiaolu.so/MG_1456055816802模版_01.jpg","http://image.xiaolu.so/MG_1456055823636模版_02.jpg","http://image.xiaolu.so/MG_1456055823800模版_03.jpg","http://image.xiaolu.so/MG_1456055823881模版_04.jpg"]
     * lowest_agent_price : 89.9
     * is_flatten : false
     * is_sale_out : false
     * lowest_std_sale_price : 449
     * item_marks : ["包邮"]
     */

    private DetailContentBean detail_content;
    /**
     * metrics : {}
     * attributes : [{"name":"商品编码","value":"91528721002"},{"name":"商品材质","value":"棉混纺"},{"name":"可选颜色","value":"黑色,灰色"},{"name":"洗涤说明","value":"洗涤时请深色、浅色衣物分开洗涤。最高洗涤温度不要超过40度， 不可漂白。有涂层、印花表面不能进行熨烫，会导致表面剥落。不可干洗，悬挂晾干。(如是婴幼儿衣物，请于成人衣物分开洗涤，避免交叉感染。建议手洗水温30度，使用婴幼儿专用衣物洗涤剂。）"},{"name":"备注说明","value":"连帽拉链卫衣+长裤，男童套，松紧裤腰，柔软舒适。"}]
     * tables : [{"table":[["尺码","建议身高","裤腰","衣长","胸围","肩宽","臀围","裤长"],["120","110","54-64","48","72","29.5","72","70"],["130","120","56-66","51","76","31","76","75"],["140","130","58-68","54","80","32.5","80","80"],["150","140","60-70","57","84","34","84","85"],["160","150","62-72","60","88","35.5","88","90"]]}]
     */

    private ComparisonBean comparison;
    private ExtrasBean extras;
    /**
     * is_favorite : false
     */

    private CustomInfoBean custom_info;
    /**
     * agent_price : 89.9
     * sku_items : [{"agent_price":89.9,"sku_id":130025,"name":"120","std_sale_price":449,"type":"size","free_num":15,"is_saleout":false},{"agent_price":89.9,"sku_id":130026,"name":"130","std_sale_price":449,"type":"size","free_num":15,"is_saleout":false},{"agent_price":89.9,"sku_id":130027,"name":"140","std_sale_price":449,"type":"size","free_num":15,"is_saleout":false},{"agent_price":89.9,"sku_id":130028,"name":"150","std_sale_price":449,"type":"size","free_num":15,"is_saleout":false},{"agent_price":89.9,"sku_id":130029,"name":"160","std_sale_price":449,"type":"size","free_num":13,"is_saleout":false}]
     * product_id : 33097
     * std_sale_price : 449
     * lowest_price : 89.9
     * outer_id : 915287210021
     * type : color
     * product_img : http://image.xiaolu.so/MG_14558670230841.png
     * name : 黑色
     */

    private List<SkuInfoBean> sku_info;

    private TeamBuyInfo teambuy_info;

    private String buy_coupon_url;

    private int source_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DetailContentBean getDetail_content() {
        return detail_content;
    }

    public void setDetail_content(DetailContentBean detail_content) {
        this.detail_content = detail_content;
    }

    public ComparisonBean getComparison() {
        return comparison;
    }

    public void setComparison(ComparisonBean comparison) {
        this.comparison = comparison;
    }

    public ExtrasBean getExtras() {
        return extras;
    }

    public void setExtras(ExtrasBean extras) {
        this.extras = extras;
    }

    public CustomInfoBean getCustom_info() {
        return custom_info;
    }

    public void setCustom_info(CustomInfoBean custom_info) {
        this.custom_info = custom_info;
    }

    public List<SkuInfoBean> getSku_info() {
        return sku_info;
    }

    public void setSku_info(List<SkuInfoBean> sku_info) {
        this.sku_info = sku_info;
    }

    public TeamBuyInfo getTeambuy_info() {
        return teambuy_info;
    }

    public void setTeambuy_info(TeamBuyInfo teambuy_info) {
        this.teambuy_info = teambuy_info;
    }

    public String getBuy_coupon_url() {
        return buy_coupon_url;
    }

    public void setBuy_coupon_url(String buy_coupon_url) {
        this.buy_coupon_url = buy_coupon_url;
    }

    public int getSource_type() {
        return source_type;
    }

    public void setSource_type(int source_type) {
        this.source_type = source_type;
    }

    public static class DetailContentBean {
        /**
         * id : 15
         */

        private CategoryBean category;
        private String sale_state;
        private boolean is_newsales;
        private boolean is_boutique;
        private String model_code;
        private String name;
        private String sale_time;
        private String watermark_op;
        private String offshelf_time;
        private String refund_tips_url;
        private boolean is_onsale;
        private boolean is_recommend;
        private boolean is_saleopen;
        private double lowest_agent_price;
        private boolean is_flatten;
        private boolean is_sale_out;
        private double lowest_std_sale_price;
        private List<String> head_imgs;
        private List<String> content_imgs;
        private List<String> item_marks;

        public CategoryBean getCategory() {
            return category;
        }

        public void setCategory(CategoryBean category) {
            this.category = category;
        }

        public boolean isIs_newsales() {
            return is_newsales;
        }

        public void setIs_newsales(boolean is_newsales) {
            this.is_newsales = is_newsales;
        }

        public String getModel_code() {
            return model_code;
        }

        public void setModel_code(String model_code) {
            this.model_code = model_code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSale_time() {
            return sale_time;
        }

        public void setSale_time(String sale_time) {
            this.sale_time = sale_time;
        }

        public String getWatermark_op() {
            return watermark_op;
        }

        public void setWatermark_op(String watermark_op) {
            this.watermark_op = watermark_op;
        }

        public String getOffshelf_time() {
            return offshelf_time;
        }

        public void setOffshelf_time(String offshelf_time) {
            this.offshelf_time = offshelf_time;
        }

        public boolean isIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(boolean is_recommend) {
            this.is_recommend = is_recommend;
        }

        public boolean isIs_saleopen() {
            return is_saleopen;
        }

        public void setIs_saleopen(boolean is_saleopen) {
            this.is_saleopen = is_saleopen;
        }

        public double getLowest_agent_price() {
            return lowest_agent_price;
        }

        public void setLowest_agent_price(double lowest_agent_price) {
            this.lowest_agent_price = lowest_agent_price;
        }

        public boolean isIs_flatten() {
            return is_flatten;
        }

        public void setIs_flatten(boolean is_flatten) {
            this.is_flatten = is_flatten;
        }

        public boolean isIs_sale_out() {
            return is_sale_out;
        }

        public void setIs_sale_out(boolean is_sale_out) {
            this.is_sale_out = is_sale_out;
        }

        public double getLowest_std_sale_price() {
            return lowest_std_sale_price;
        }

        public void setLowest_std_sale_price(double lowest_std_sale_price) {
            this.lowest_std_sale_price = lowest_std_sale_price;
        }

        public List<String> getHead_imgs() {
            return head_imgs;
        }

        public void setHead_imgs(List<String> head_imgs) {
            this.head_imgs = head_imgs;
        }

        public List<String> getContent_imgs() {
            return content_imgs;
        }

        public void setContent_imgs(List<String> content_imgs) {
            this.content_imgs = content_imgs;
        }

        public List<String> getItem_marks() {
            return item_marks;
        }

        public void setItem_marks(List<String> item_marks) {
            this.item_marks = item_marks;
        }

        public String getSale_state() {
            return sale_state;
        }

        public void setSale_state(String sale_state) {
            this.sale_state = sale_state;
        }

        public boolean is_onsale() {
            return is_onsale;
        }

        public void setIs_onsale(boolean is_onsale) {
            this.is_onsale = is_onsale;
        }

        public boolean is_boutique() {
            return is_boutique;
        }

        public void setIs_boutique(boolean is_boutique) {
            this.is_boutique = is_boutique;
        }

        public String getRefund_tips_url() {
            return refund_tips_url;
        }

        public void setRefund_tips_url(String refund_tips_url) {
            this.refund_tips_url = refund_tips_url;
        }

        public static class CategoryBean {
            private int id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }

    public static class ComparisonBean {
        /**
         * name : 商品编码
         * value : 91528721002
         */

        private List<AttributesBean> attributes;
        private List<TablesBean> tables;

        public List<AttributesBean> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<AttributesBean> attributes) {
            this.attributes = attributes;
        }

        public List<TablesBean> getTables() {
            return tables;
        }

        public void setTables(List<TablesBean> tables) {
            this.tables = tables;
        }

        public static class AttributesBean {
            private String name;
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class TablesBean {
        }
    }

    public static class ExtrasBean {
    }

    public static class CustomInfoBean {
        private boolean is_favorite;

        public boolean isIs_favorite() {
            return is_favorite;
        }

        public void setIs_favorite(boolean is_favorite) {
            this.is_favorite = is_favorite;
        }
    }

    public static class SkuInfoBean {
        private double agent_price;
        private int product_id;
        private double std_sale_price;
        private double lowest_price;
        private String outer_id;
        private String type;
        private String product_img;
        private String name;
        /**
         * agent_price : 89.9
         * sku_id : 130025
         * name : 120
         * std_sale_price : 449
         * type : size
         * free_num : 15
         * is_saleout : false
         */

        private List<SkuItemsBean> sku_items;

        public double getAgent_price() {
            return agent_price;
        }

        public void setAgent_price(double agent_price) {
            this.agent_price = agent_price;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public double getStd_sale_price() {
            return std_sale_price;
        }

        public void setStd_sale_price(double std_sale_price) {
            this.std_sale_price = std_sale_price;
        }

        public double getLowest_price() {
            return lowest_price;
        }

        public void setLowest_price(double lowest_price) {
            this.lowest_price = lowest_price;
        }

        public String getOuter_id() {
            return outer_id;
        }

        public void setOuter_id(String outer_id) {
            this.outer_id = outer_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProduct_img() {
            return product_img;
        }

        public void setProduct_img(String product_img) {
            this.product_img = product_img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SkuItemsBean> getSku_items() {
            return sku_items;
        }

        public void setSku_items(List<SkuItemsBean> sku_items) {
            this.sku_items = sku_items;
        }

        public static class SkuItemsBean {
            private double agent_price;
            private int sku_id;
            private String name;
            private double std_sale_price;
            private String type;
            private int free_num;
            private boolean is_saleout;

            public double getAgent_price() {
                return agent_price;
            }

            public void setAgent_price(double agent_price) {
                this.agent_price = agent_price;
            }

            public int getSku_id() {
                return sku_id;
            }

            public void setSku_id(int sku_id) {
                this.sku_id = sku_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getStd_sale_price() {
                return std_sale_price;
            }

            public void setStd_sale_price(double std_sale_price) {
                this.std_sale_price = std_sale_price;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getFree_num() {
                return free_num;
            }

            public void setFree_num(int free_num) {
                this.free_num = free_num;
            }

            public boolean isIs_saleout() {
                return is_saleout;
            }

            public void setIs_saleout(boolean is_saleout) {
                this.is_saleout = is_saleout;
            }
        }
    }

    public static class TeamBuyInfo {

        /**
         * teambuy_person_num : 3
         * teambuy : true
         * teambuy_price : 22.2
         */

        private int teambuy_person_num;
        private boolean teambuy;
        private double teambuy_price;

        public int getTeambuy_person_num() {
            return teambuy_person_num;
        }

        public void setTeambuy_person_num(int teambuy_person_num) {
            this.teambuy_person_num = teambuy_person_num;
        }

        public boolean isTeambuy() {
            return teambuy;
        }

        public void setTeambuy(boolean teambuy) {
            this.teambuy = teambuy;
        }

        public double getTeambuy_price() {
            return teambuy_price;
        }

        public void setTeambuy_price(double teambuy_price) {
            this.teambuy_price = teambuy_price;
        }
    }
}
