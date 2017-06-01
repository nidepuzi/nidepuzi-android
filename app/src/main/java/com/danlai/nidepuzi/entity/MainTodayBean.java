package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * Created by wisdom on 17/2/14.
 */

public class MainTodayBean {

    /**
     * items : [{"model_id":"24907","name":"MKII澳洲进口深度滋养绵羊油","hour":6,"profit":{"max":25,"min":10},"price":49,"pic":"http://img.xiaolumeimei.com/MG_1487045176818-1.jpg","start_time":"2017-02-14T06:00:00"}]
     * hour : 6
     */

    private int hour;
    private List<ItemsBean> items;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * model_id : 24907
         * name : MKII澳洲进口深度滋养绵羊油
         * hour : 6
         * profit : {"max":25,"min":10}
         * price : 49
         * pic : http://img.xiaolumeimei.com/MG_1487045176818-1.jpg
         * start_time : 2017-02-14T06:00:00
         */

        private int model_id;
        private int activity_id;
        private String name;
        private int hour;
        private ProfitBean profit;
        private double price;
        private String pic;
        private String start_time;
        private String watermark_op;
        private int selling_num;
        private int stock;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public ProfitBean getProfit() {
            return profit;
        }

        public void setProfit(ProfitBean profit) {
            this.profit = profit;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public int getModel_id() {
            return model_id;
        }

        public void setModel_id(int model_id) {
            this.model_id = model_id;
        }

        public int getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(int activity_id) {
            this.activity_id = activity_id;
        }

        public int getSelling_num() {
            return selling_num;
        }

        public void setSelling_num(int selling_num) {
            this.selling_num = selling_num;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getWatermark_op() {
            return watermark_op;
        }

        public void setWatermark_op(String watermark_op) {
            this.watermark_op = watermark_op;
        }

        public static class ProfitBean {
            /**
             * max : 25
             * min : 10
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
}
