package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * Created by wisdom on 16/9/10.
 */
public class ChooseListBean {

    /**
     * count : 1
     * next :
     * previous :
     * results : [{"id":11828,"cid":"2-4","pic_path":"https://cbu01.alicdn.com/img/ibank/2015/441/619/2272916144_1867143362.400x400.jpg","name":"开衫女式披肩外套","lowest_std_sale_price":110,"lowest_agent_price":29.9,"shop_products_num":1,"sale_num":772,"sale_num_desc":"772人在卖","rebet_amount":2.99,"rebet_amount_desc":"佣2.99￥","next_rebet_amount_desc":"佣5.98￥","next_rebet_amount":5.98,"level_info":{"next_agencylevel_desc":"VIP1","agencylevel_desc":"A类","agencylevel":3,"next_agencylevel":2}}]
     */

    private int count;
    private String next;
    private String previous;
    /**
     * id : 11828
     * cid : 2-4
     * pic_path : https://cbu01.alicdn.com/img/ibank/2015/441/619/2272916144_1867143362.400x400.jpg
     * name : 开衫女式披肩外套
     * lowest_std_sale_price : 110.0
     * lowest_agent_price : 29.9
     * shop_products_num : 1
     * sale_num : 772
     * sale_num_desc : 772人在卖
     * rebet_amount : 2.99
     * rebet_amount_desc : 佣2.99￥
     * next_rebet_amount_desc : 佣5.98￥
     * next_rebet_amount : 5.98
     * level_info : {"next_agencylevel_desc":"VIP1","agencylevel_desc":"A类","agencylevel":3,"next_agencylevel":2}
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

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private int id;
        private String cid;
        private String pic_path;
        private String name;
        private double lowest_std_sale_price;
        private double lowest_agent_price;
        private int shop_products_num;
        private int sale_num;
        private String sale_num_desc;
        private double rebet_amount;
        private String rebet_amount_desc;
        private String next_rebet_amount_desc;
        private double next_rebet_amount;
        private boolean is_boutique;
        /**
         * next_agencylevel_desc : VIP1
         * agencylevel_desc : A类
         * agencylevel : 3
         * next_agencylevel : 2
         */


        private LevelInfoBean level_info;
        /**
         * elite_level_prices : {"next_elite_level_price":"升级后￥50.0","elite_level_price":"￥50.0"}
         */

        private EliteLevelPricesBean elite_level_prices;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getPic_path() {
            return pic_path;
        }

        public void setPic_path(String pic_path) {
            this.pic_path = pic_path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getLowest_std_sale_price() {
            return lowest_std_sale_price;
        }

        public void setLowest_std_sale_price(double lowest_std_sale_price) {
            this.lowest_std_sale_price = lowest_std_sale_price;
        }

        public double getLowest_agent_price() {
            return lowest_agent_price;
        }

        public void setLowest_agent_price(double lowest_agent_price) {
            this.lowest_agent_price = lowest_agent_price;
        }

        public int getShop_products_num() {
            return shop_products_num;
        }

        public void setShop_products_num(int shop_products_num) {
            this.shop_products_num = shop_products_num;
        }

        public int getSale_num() {
            return sale_num;
        }

        public void setSale_num(int sale_num) {
            this.sale_num = sale_num;
        }

        public String getSale_num_desc() {
            return sale_num_desc;
        }

        public void setSale_num_desc(String sale_num_desc) {
            this.sale_num_desc = sale_num_desc;
        }

        public double getRebet_amount() {
            return rebet_amount;
        }

        public void setRebet_amount(double rebet_amount) {
            this.rebet_amount = rebet_amount;
        }

        public String getRebet_amount_desc() {
            return rebet_amount_desc;
        }

        public void setRebet_amount_desc(String rebet_amount_desc) {
            this.rebet_amount_desc = rebet_amount_desc;
        }

        public String getNext_rebet_amount_desc() {
            return next_rebet_amount_desc;
        }

        public void setNext_rebet_amount_desc(String next_rebet_amount_desc) {
            this.next_rebet_amount_desc = next_rebet_amount_desc;
        }

        public double getNext_rebet_amount() {
            return next_rebet_amount;
        }

        public void setNext_rebet_amount(double next_rebet_amount) {
            this.next_rebet_amount = next_rebet_amount;
        }

        public LevelInfoBean getLevel_info() {
            return level_info;
        }

        public void setLevel_info(LevelInfoBean level_info) {
            this.level_info = level_info;
        }

        public boolean is_boutique() {
            return is_boutique;
        }

        public void setIs_boutique(boolean is_boutique) {
            this.is_boutique = is_boutique;
        }

        public EliteLevelPricesBean getElite_level_prices() {
            return elite_level_prices;
        }

        public void setElite_level_prices(EliteLevelPricesBean elite_level_prices) {
            this.elite_level_prices = elite_level_prices;
        }

        public static class LevelInfoBean {
            private String next_agencylevel_desc;
            private String agencylevel_desc;
            private int agencylevel;
            private int next_agencylevel;

            public String getNext_agencylevel_desc() {
                return next_agencylevel_desc;
            }

            public void setNext_agencylevel_desc(String next_agencylevel_desc) {
                this.next_agencylevel_desc = next_agencylevel_desc;
            }

            public String getAgencylevel_desc() {
                return agencylevel_desc;
            }

            public void setAgencylevel_desc(String agencylevel_desc) {
                this.agencylevel_desc = agencylevel_desc;
            }

            public int getAgencylevel() {
                return agencylevel;
            }

            public void setAgencylevel(int agencylevel) {
                this.agencylevel = agencylevel;
            }

            public int getNext_agencylevel() {
                return next_agencylevel;
            }

            public void setNext_agencylevel(int next_agencylevel) {
                this.next_agencylevel = next_agencylevel;
            }
        }

        public static class EliteLevelPricesBean {
            /**
             * next_elite_level_price : 升级后￥50.0
             * elite_level_price : ￥50.0
             */

            private String next_elite_level_price;
            private String elite_level_price;

            public String getNext_elite_level_price() {
                return next_elite_level_price;
            }

            public void setNext_elite_level_price(String next_elite_level_price) {
                this.next_elite_level_price = next_elite_level_price;
            }

            public String getElite_level_price() {
                return elite_level_price;
            }

            public void setElite_level_price(String elite_level_price) {
                this.elite_level_price = elite_level_price;
            }
        }
    }
}
