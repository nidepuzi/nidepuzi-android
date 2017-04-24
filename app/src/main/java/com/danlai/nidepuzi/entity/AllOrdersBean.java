package com.danlai.nidepuzi.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 优尼世界 on 15/12/29.
 * <p>
 * Copyright 2015年 上海己美. All rights reserved.
 */
public class AllOrdersBean {

    @Override
    public String toString() {
        return "AllOrdersBean{" +
                "mCount=" + mCount +
                ", mNext='" + mNext + '\'' +
                ", mPrevious=" + mPrevious +
                ", mResults=" + mResults +
                '}';
    }


    @SerializedName("count")
    private int mCount;
    @SerializedName("next")
    private String mNext;
    @SerializedName("previous")
    private Object mPrevious;


    @SerializedName("results")
    private List<ResultsEntity> mResults;

    public void setCount(int count) {
        this.mCount = count;
    }

    public void setNext(String next) {
        this.mNext = next;
    }

    public void setPrevious(Object previous) {
        this.mPrevious = previous;
    }

    public void setResults(List<ResultsEntity> results) {
        this.mResults = results;
    }

    public int getCount() {
        return mCount;
    }

    public String getNext() {
        return mNext;
    }

    public Object getPrevious() {
        return mPrevious;
    }

    public List<ResultsEntity> getResults() {
        return mResults;
    }

    public static class ResultsEntity {

        @Override
        public String toString() {
            return "ResultsEntity{" +
                    "mId=" + mId +
                    ", mUrl='" + mUrl + '\'' +
                    ", mTid='" + mTid + '\'' +
                    ", mBuyerNick='" + mBuyerNick + '\'' +
                    ", mBuyerId=" + mBuyerId +
                    ", mChannel='" + mChannel + '\'' +
                    ", mPayment=" + mPayment +
                    ", mPostFee=" + mPostFee +
                    ", mTotalFee=" + mTotalFee +
                    ", mDiscountFee=" + mDiscountFee +
                    ", mStatus=" + mStatus +
                    ", mStatusDisplay='" + mStatusDisplay + '\'' +
                    ", mOrderPic='" + mOrderPic + '\'' +
                    ", mBuyerMessage='" + mBuyerMessage + '\'' +
                    ", mTradeType=" + mTradeType +
                    ", mCreated='" + mCreated + '\'' +
                    ", mPayTime=" + mPayTime +
                    ", mConsignTime=" + mConsignTime +
                    ", mOutSid='" + mOutSid + '\'' +
                    ", mLogisticsCompany=" + mLogisticsCompany +
                    ", mReceiverName='" + mReceiverName + '\'' +
                    ", mReceiverState='" + mReceiverState + '\'' +
                    ", mReceiverCity='" + mReceiverCity + '\'' +
                    ", mReceiverDistrict='" + mReceiverDistrict + '\'' +
                    ", mReceiverAddress='" + mReceiverAddress + '\'' +
                    ", mReceiverMobile='" + mReceiverMobile + '\'' +
                    ", mReceiverPhone='" + mReceiverPhone + '\'' +
                    ", mOrders=" + mOrders +
                    '}';
        }

        @SerializedName("id")
        private int mId;
        @SerializedName("url")
        private String mUrl;
        @SerializedName("tid")
        private String mTid;
        @SerializedName("buyer_nick")
        private String mBuyerNick;
        @SerializedName("buyer_id")
        private int mBuyerId;
        @SerializedName("channel")
        private String mChannel;
        @SerializedName("payment")
        private double mPayment;
        @SerializedName("post_fee")
        private double mPostFee;
        @SerializedName("total_fee")
        private double mTotalFee;
        @SerializedName("discount_fee")
        private double mDiscountFee;
        @SerializedName("status")
        private int mStatus;
        @SerializedName("status_display")
        private String mStatusDisplay;
        @SerializedName("order_pic")
        private String mOrderPic;
        @SerializedName("buyer_message")
        private String mBuyerMessage;
        @SerializedName("trade_type")
        private int mTradeType;
        @SerializedName("created")
        private String mCreated;
        @SerializedName("pay_time")
        private Object mPayTime;
        @SerializedName("consign_time")
        private Object mConsignTime;
        @SerializedName("out_sid")
        private String mOutSid;
        @SerializedName("logistics_company")
        private Object mLogisticsCompany;
        @SerializedName("receiver_name")
        private String mReceiverName;
        @SerializedName("receiver_state")
        private String mReceiverState;
        @SerializedName("receiver_city")
        private String mReceiverCity;
        @SerializedName("receiver_district")
        private String mReceiverDistrict;
        @SerializedName("receiver_address")
        private String mReceiverAddress;
        @SerializedName("receiver_mobile")
        private String mReceiverMobile;
        @SerializedName("receiver_phone")
        private String mReceiverPhone;
        /**
         * id : 302107
         * oid : xo16011156933d7f55da7
         * item_id : 30786
         * title : 韩版百搭皮裙/黑色
         * sku_id : 120694
         * num : 1
         * outer_id : 821277480051
         * total_fee : 49.9
         * payment : 49.9
         * discount_fee : 0
         * sku_name : XL
         * pic_path : http://image.xiaolu.so/MG-1452155820775-韩版百搭皮裙3.png
         * status : 7
         * status_display : 交易关闭
         * refund_status : 0
         * refund_status_display : 没有退款
         * refund_id : null
         * kill_title : false
         */

        @SerializedName("orders")
        private List<OrdersEntity> mOrders;

        public void setId(int id) {
            this.mId = id;
        }

        public void setUrl(String url) {
            this.mUrl = url;
        }

        public void setTid(String tid) {
            this.mTid = tid;
        }

        public void setBuyerNick(String buyerNick) {
            this.mBuyerNick = buyerNick;
        }

        public void setBuyerId(int buyerId) {
            this.mBuyerId = buyerId;
        }

        public void setChannel(String channel) {
            this.mChannel = channel;
        }

        public void setPayment(double payment) {
            this.mPayment = payment;
        }


        public void setTotalFee(double totalFee) {
            this.mTotalFee = totalFee;
        }


        public void setStatus(int status) {
            this.mStatus = status;
        }

        public void setStatusDisplay(String statusDisplay) {
            this.mStatusDisplay = statusDisplay;
        }

        public void setOrderPic(String orderPic) {
            this.mOrderPic = orderPic;
        }

        public void setBuyerMessage(String buyerMessage) {
            this.mBuyerMessage = buyerMessage;
        }

        public void setTradeType(int tradeType) {
            this.mTradeType = tradeType;
        }

        public void setCreated(String created) {
            this.mCreated = created;
        }

        public void setPayTime(Object payTime) {
            this.mPayTime = payTime;
        }

        public void setConsignTime(Object consignTime) {
            this.mConsignTime = consignTime;
        }

        public void setOutSid(String outSid) {
            this.mOutSid = outSid;
        }

        public void setLogisticsCompany(Object logisticsCompany) {
            this.mLogisticsCompany = logisticsCompany;
        }

        public void setReceiverName(String receiverName) {
            this.mReceiverName = receiverName;
        }

        public void setReceiverState(String receiverState) {
            this.mReceiverState = receiverState;
        }

        public void setReceiverCity(String receiverCity) {
            this.mReceiverCity = receiverCity;
        }

        public void setReceiverDistrict(String receiverDistrict) {
            this.mReceiverDistrict = receiverDistrict;
        }

        public void setReceiverAddress(String receiverAddress) {
            this.mReceiverAddress = receiverAddress;
        }

        public void setReceiverMobile(String receiverMobile) {
            this.mReceiverMobile = receiverMobile;
        }

        public void setReceiverPhone(String receiverPhone) {
            this.mReceiverPhone = receiverPhone;
        }

        public void setOrders(List<OrdersEntity> orders) {
            this.mOrders = orders;
        }

        public int getId() {
            return mId;
        }

        public String getUrl() {
            return mUrl;
        }

        public String getTid() {
            return mTid;
        }

        public String getBuyerNick() {
            return mBuyerNick;
        }

        public int getBuyerId() {
            return mBuyerId;
        }

        public String getChannel() {
            return mChannel;
        }

        public double getPayment() {
            return mPayment;
        }

        public double getPostFee() {
            return mPostFee;
        }

        public double getTotalFee() {
            return mTotalFee;
        }

        public double getDiscountFee() {
            return mDiscountFee;
        }

        public int getStatus() {
            return mStatus;
        }

        public String getStatusDisplay() {
            return mStatusDisplay;
        }

        public String getOrderPic() {
            return mOrderPic;
        }

        public String getBuyerMessage() {
            return mBuyerMessage;
        }

        public int getTradeType() {
            return mTradeType;
        }

        public String getCreated() {
            return mCreated;
        }

        public Object getPayTime() {
            return mPayTime;
        }

        public Object getConsignTime() {
            return mConsignTime;
        }

        public String getOutSid() {
            return mOutSid;
        }

        public Object getLogisticsCompany() {
            return mLogisticsCompany;
        }

        public String getReceiverName() {
            return mReceiverName;
        }

        public String getReceiverState() {
            return mReceiverState;
        }

        public String getReceiverCity() {
            return mReceiverCity;
        }

        public String getReceiverDistrict() {
            return mReceiverDistrict;
        }

        public String getReceiverAddress() {
            return mReceiverAddress;
        }

        public String getReceiverMobile() {
            return mReceiverMobile;
        }

        public String getReceiverPhone() {
            return mReceiverPhone;
        }

        public List<OrdersEntity> getOrders() {
            return mOrders;
        }

        public static class OrdersEntity implements Serializable, Parcelable {

            /**
             * id : 415382
             * oid : xo1607025776989c3b986
             * item_id : 43545
             * title : 两条装夏季必备安全裤/白色
             * sku_id : 176490
             * num : 1
             * outer_id : 827291770021
             * total_fee : 19.9
             * payment : 19.9
             * discount_fee : 0
             * sku_name : L
             * pic_path : http://image.xiaolu.so/MG_14644935870321.jpg
             * status : 6
             * status_display : 退款关闭
             * refund_status : 7
             * refund_status_display : 退款成功
             * refund_id : 48517
             * kill_title : false
             * is_seckill : false
             * package_order_id : null
             */

            private int id;
            private String oid;
            private String item_id;
            private String title;
            private String sku_id;
            private int model_id;
            private int num;
            private String outer_id;
            private double total_fee;
            private double payment;
            private double discount_fee;
            private String sku_name;
            private String pic_path;
            private int status;
            private String status_display;
            private int refund_status;
            private String refund_status_display;
            private int refund_id;
            private boolean kill_title;
            private boolean is_seckill;
            private String package_order_id;
            private boolean is_bonded_goods;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOid() {
                return oid;
            }

            public void setOid(String oid) {
                this.oid = oid;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSku_id() {
                return sku_id;
            }

            public void setSku_id(String sku_id) {
                this.sku_id = sku_id;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getOuter_id() {
                return outer_id;
            }

            public void setOuter_id(String outer_id) {
                this.outer_id = outer_id;
            }

            public double getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(double total_fee) {
                this.total_fee = total_fee;
            }

            public double getPayment() {
                return payment;
            }

            public void setPayment(double payment) {
                this.payment = payment;
            }

            public double getDiscount_fee() {
                return discount_fee;
            }

            public void setDiscount_fee(double discount_fee) {
                this.discount_fee = discount_fee;
            }

            public String getSku_name() {
                return sku_name;
            }

            public void setSku_name(String sku_name) {
                this.sku_name = sku_name;
            }

            public String getPic_path() {
                return pic_path;
            }

            public void setPic_path(String pic_path) {
                this.pic_path = pic_path;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getStatus_display() {
                return status_display;
            }

            public void setStatus_display(String status_display) {
                this.status_display = status_display;
            }

            public int getRefund_status() {
                return refund_status;
            }

            public void setRefund_status(int refund_status) {
                this.refund_status = refund_status;
            }

            public String getRefund_status_display() {
                return refund_status_display;
            }

            public void setRefund_status_display(String refund_status_display) {
                this.refund_status_display = refund_status_display;
            }

            public int getRefund_id() {
                return refund_id;
            }

            public void setRefund_id(int refund_id) {
                this.refund_id = refund_id;
            }

            public boolean isKill_title() {
                return kill_title;
            }

            public void setKill_title(boolean kill_title) {
                this.kill_title = kill_title;
            }

            public boolean is_seckill() {
                return is_seckill;
            }

            public void setIs_seckill(boolean is_seckill) {
                this.is_seckill = is_seckill;
            }

            public String getPackage_order_id() {
                return package_order_id;
            }

            public void setPackage_order_id(String package_order_id) {
                this.package_order_id = package_order_id;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.oid);
                dest.writeString(this.item_id);
                dest.writeString(this.title);
                dest.writeString(this.sku_id);
                dest.writeInt(this.num);
                dest.writeString(this.outer_id);
                dest.writeDouble(this.total_fee);
                dest.writeDouble(this.payment);
                dest.writeDouble(this.discount_fee);
                dest.writeString(this.sku_name);
                dest.writeString(this.pic_path);
                dest.writeInt(this.status);
                dest.writeString(this.status_display);
                dest.writeInt(this.refund_status);
                dest.writeString(this.refund_status_display);
                dest.writeInt(this.refund_id);
                dest.writeByte(this.kill_title ? (byte) 1 : (byte) 0);
                dest.writeByte(this.is_seckill ? (byte) 1 : (byte) 0);
                dest.writeString(this.package_order_id);
            }

            public OrdersEntity() {
            }

            protected OrdersEntity(Parcel in) {
                this.id = in.readInt();
                this.oid = in.readString();
                this.item_id = in.readString();
                this.title = in.readString();
                this.sku_id = in.readString();
                this.num = in.readInt();
                this.outer_id = in.readString();
                this.total_fee = in.readDouble();
                this.payment = in.readDouble();
                this.discount_fee = in.readInt();
                this.sku_name = in.readString();
                this.pic_path = in.readString();
                this.status = in.readInt();
                this.status_display = in.readString();
                this.refund_status = in.readInt();
                this.refund_status_display = in.readString();
                this.refund_id = in.readInt();
                this.kill_title = in.readByte() != 0;
                this.is_seckill = in.readByte() != 0;
                this.package_order_id = in.readString();
            }

            public static final Creator<OrdersEntity> CREATOR = new Creator<OrdersEntity>() {
                @Override
                public OrdersEntity createFromParcel(Parcel source) {
                    return new OrdersEntity(source);
                }

                @Override
                public OrdersEntity[] newArray(int size) {
                    return new OrdersEntity[size];
                }
            };

            public int getModel_id() {
                return model_id;
            }

            public void setModel_id(int model_id) {
                this.model_id = model_id;
            }

            public boolean is_bonded_goods() {
                return is_bonded_goods;
            }

            public void setIs_bonded_goods(boolean is_bonded_goods) {
                this.is_bonded_goods = is_bonded_goods;
            }
        }
    }
}
