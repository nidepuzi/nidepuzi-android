package com.danlai.nidepuzi.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wl on 15-12-16.
 */
public class AllRefundsBean {
    /**
     * count : 7
     * next : null
     * previous : null
     * results : [{"id":8514,"url":"http://m.xiaolumeimei.com/rest/v1/refunds/8514","refund_no":"RF15091555f7943868910","trade_id":108531,"order_id":104427,"buyer_id":15977,"item_id":17682,"title":"可爱卡通印花哈衣/红色草莓","sku_id":68991,"sku_name":"6M/66","refund_num":1,"buyer_nick":"小鹿美美","mobile":"13816404857","phone":"","proof_pic":null,"total_fee":29,"payment":29,"created":"2015-09-15T11:44:56","modified":"2015-09-17T12:12:04","company_name":"","sid":"","reason":"错拍","pic_path":"https://mmbiz.qlogo.cn/mmbiz/yMhOQPTKhLvlAphyfwwyVle7KJbyNjk97CTC2baR370KldvKvMxokGRtyVibcBTN9oQCd3XGrzmaj9ibVFicnpFuA/0?wx_fmt=png","desc":"123","feedback":"","has_good_return":false,"has_good_change":false,"good_status":0,"status":3,"refund_fee":12,"status_display":"买家已经申请退款"},{"id":8507,"url":"http://m.xiaolumeimei.com/rest/v1/refunds/8507","refund_no":"RF15091555f791d28ff88","trade_id":108499,"order_id":104393,"buyer_id":15977,"item_id":17682,"title":"可爱卡通印花哈衣/红色草莓","sku_id":68993,"sku_name":"12M/80","refund_num":1,"buyer_nick":"小鹿美美","mobile":"13816404857","phone":"","proof_pic":null,"total_fee":29,"payment":29,"created":"2015-09-15T11:34:42","modified":"2015-09-15T11:34:42","company_name":"","sid":"","reason":"其他","pic_path":"https://mmbiz.qlogo.cn/mmbiz/yMhOQPTKhLvlAphyfwwyVle7KJbyNjk97CTC2baR370KldvKvMxokGRtyVibcBTN9oQCd3XGrzmaj9ibVFicnpFuA/0?wx_fmt=png","desc":"121212","feedback":"","has_good_return":false,"has_good_change":false,"good_status":0,"status":3,"refund_fee":12,"status_display":"买家已经申请退款"},{"id":8488,"url":"http://m.xiaolumeimei.com/rest/v1/refunds/8488","refund_no":"RF15091555f78855084f5","trade_id":108354,"order_id":104225,"buyer_id":15977,"item_id":17671,"title":"秋款休闲宝宝套装/粉色","sku_id":68945,"sku_name":"100","refund_num":2,"buyer_nick":"","mobile":"13816404857","phone":"","proof_pic":null,"total_fee":69,"payment":138,"created":"2015-09-15T10:54:13","modified":"2015-09-15T11:14:37","company_name":"shunfeng","sid":"123456","reason":"没有发货","pic_path":"http://image.xiaolu.so/MG-1449729609122-0.png","desc":"123","feedback":"","has_good_return":false,"has_good_change":false,"good_status":2,"status":1,"refund_fee":138,"status_display":"退款关闭"},{"id":8487,"url":"http://m.xiaolumeimei.com/rest/v1/refunds/8487","refund_no":"RF15091555f7882ed874d","trade_id":108354,"order_id":104224,"buyer_id":15977,"item_id":17671,"title":"秋款休闲宝宝套装/粉色","sku_id":68946,"sku_name":"110","refund_num":2,"buyer_nick":"小鹿美美","mobile":"13816404857","phone":"","proof_pic":null,"total_fee":69,"payment":138,"created":"2015-09-15T10:53:34","modified":"2015-09-15T11:16:29","company_name":"","sid":"","reason":"没有发货","pic_path":"http://image.xiaolu.so/MG-1449729609122-0.png","desc":"123","feedback":"","has_good_return":false,"has_good_change":false,"good_status":0,"status":1,"refund_fee":12,"status_display":"退款关闭"},{"id":7468,"url":"http://m.xiaolumeimei.com/rest/v1/refunds/7468","refund_no":"RF15091255f3cb8074795","trade_id":87670,"order_id":82078,"buyer_id":15977,"item_id":16422,"title":"名媛蕾丝背心公主裙/红色","sku_id":64064,"sku_name":"9/110","refund_num":1,"buyer_nick":"","mobile":"13816404857","phone":"","proof_pic":null,"total_fee":49,"payment":49,"created":"2015-09-12T14:51:44","modified":"2015-09-14T15:24:50","company_name":"","sid":"","reason":"没有发货","pic_path":"https://mmbiz.qlogo.cn/mmbiz/yMhOQPTKhLvt4h9EbG1yDs4ueZDgNbwYFHSRqZ3HeEULjic8JWMHLm0ekg4iaEibSAA0hwDyjJQzVzr24LKMG7twA/0?wx_fmt=png","desc":"让人","feedback":"","has_good_return":false,"has_good_change":false,"good_status":0,"status":2,"refund_fee":22,"status_display":"卖家拒绝退款"},{"id":7466,"url":"http://m.xiaolumeimei.com/rest/v1/refunds/7466","refund_no":"RF15091255f3cb5d3260f","trade_id":87170,"order_id":81573,"buyer_id":15977,"item_id":16228,"title":"不规则假两件裤裙/黑色","sku_id":63264,"sku_name":"均码","refund_num":1,"buyer_nick":"","mobile":"13816404857","phone":"","proof_pic":null,"total_fee":49,"payment":49,"created":"2015-09-12T14:51:09","modified":"2015-09-13T15:20:44","company_name":"","sid":"","reason":"未收到货","pic_path":"http://image.xiaolu.so/MG-1450756977876-不规则假两件裤裙03.png","desc":"ss","feedback":"","has_good_return":false,"has_good_change":false,"good_status":0,"status":3,"refund_fee":12,"status_display":"买家已经申请退款"},{"id":4084,"url":"http://m.xiaolumeimei.com/rest/v1/refunds/4084","refund_no":"RF15090155e50badd2437","trade_id":68268,"order_id":63024,"buyer_id":15977,"item_id":15474,"title":"韩版格子长袖连衣裙/藏蓝色","sku_id":60203,"sku_name":"L","refund_num":1,"buyer_nick":"","mobile":"13816404857","phone":"","proof_pic":null,"total_fee":59,"payment":59,"created":"2015-09-01T10:21:33","modified":"2015-09-01T12:05:18","company_name":"","sid":"","reason":"其他","pic_path":"https://mmbiz.qlogo.cn/mmbiz/yMhOQPTKhLs5dk1nGDdeaUKQFGTtRsPH4JGickBT5FpPMFufH3sRwBSBmpkGVBfQfghkLDssMyLFHTrp3Hw2LCw/0?wx_fmt=png","desc":"","feedback":"内部测试购买成功，商品很好，我要退。","has_good_return":false,"has_good_change":false,"good_status":0,"status":7,"refund_fee":59,"status_display":"退款成功"}]
     */

    @SerializedName("count")
    private int count;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private Object previous;

    @SerializedName("results")
    private List<ResultsEntity> results;

    public void setCount(int count) {
        this.count = count;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public Object getPrevious() {
        return previous;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "AllRefundsBean{" +
            "count=" + count +
            ", next=" + next +
            ", previous=" + previous +
            ", results=" + results +
            '}';
    }

    public static class ResultsEntity {

        private int id;
        private String url;
        private String refund_no;
        private int trade_id;
        private int order_id;
        private int buyer_id;
        private int item_id;
        private String title;
        private int sku_id;
        private String sku_name;
        private int refund_num;
        private String buyer_nick;
        private String mobile;
        private String phone;
        private double total_fee;
        private double payment;
        private String created;
        private String modified;
        private String company_name;
        private String sid;
        private String reason;
        private String pic_path;
        private String desc;
        private String feedback;
        private boolean has_good_return;
        private boolean has_good_change;
        private int good_status;
        private int status;
        private double refund_fee;
        private String return_address;
        private String status_display;
        private String return_mobile;
        private String return_contact;
        /**
         * desc :
         */

        private AmountFlowBean amount_flow;
        private String refund_channel;
        private List<String> proof_pic;
        /**
         * status_display : 申请退款
         * time : 2016-06-15T18:50:52
         */

        private List<StatusShaftBean> status_shaft;

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

        public String getRefund_no() {
            return refund_no;
        }

        public void setRefund_no(String refund_no) {
            this.refund_no = refund_no;
        }

        public int getTrade_id() {
            return trade_id;
        }

        public void setTrade_id(int trade_id) {
            this.trade_id = trade_id;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(int buyer_id) {
            this.buyer_id = buyer_id;
        }

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getSku_id() {
            return sku_id;
        }

        public void setSku_id(int sku_id) {
            this.sku_id = sku_id;
        }

        public String getSku_name() {
            return sku_name;
        }

        public void setSku_name(String sku_name) {
            this.sku_name = sku_name;
        }

        public int getRefund_num() {
            return refund_num;
        }

        public void setRefund_num(int refund_num) {
            this.refund_num = refund_num;
        }

        public String getBuyer_nick() {
            return buyer_nick;
        }

        public void setBuyer_nick(String buyer_nick) {
            this.buyer_nick = buyer_nick;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getPic_path() {
            return pic_path;
        }

        public void setPic_path(String pic_path) {
            this.pic_path = pic_path;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }

        public boolean isHas_good_return() {
            return has_good_return;
        }

        public void setHas_good_return(boolean has_good_return) {
            this.has_good_return = has_good_return;
        }

        public boolean isHas_good_change() {
            return has_good_change;
        }

        public void setHas_good_change(boolean has_good_change) {
            this.has_good_change = has_good_change;
        }

        public int getGood_status() {
            return good_status;
        }

        public void setGood_status(int good_status) {
            this.good_status = good_status;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getRefund_fee() {
            return refund_fee;
        }

        public void setRefund_fee(double refund_fee) {
            this.refund_fee = refund_fee;
        }

        public String getReturn_address() {
            return return_address;
        }

        public void setReturn_address(String return_address) {
            this.return_address = return_address;
        }

        public String getStatus_display() {
            return status_display;
        }

        public void setStatus_display(String status_display) {
            this.status_display = status_display;
        }

        public AmountFlowBean getAmount_flow() {
            return amount_flow;
        }

        public void setAmount_flow(AmountFlowBean amount_flow) {
            this.amount_flow = amount_flow;
        }

        public String getRefund_channel() {
            return refund_channel;
        }

        public void setRefund_channel(String refund_channel) {
            this.refund_channel = refund_channel;
        }

        public List<String> getProof_pic() {
            return proof_pic;
        }

        public void setProof_pic(List<String> proof_pic) {
            this.proof_pic = proof_pic;
        }

        public List<StatusShaftBean> getStatus_shaft() {
            return status_shaft;
        }

        public void setStatus_shaft(List<StatusShaftBean> status_shaft) {
            this.status_shaft = status_shaft;
        }

        public String getReturn_mobile() {
            return return_mobile;
        }

        public void setReturn_mobile(String return_mobile) {
            this.return_mobile = return_mobile;
        }

        public String getReturn_contact() {
            return return_contact;
        }

        public void setReturn_contact(String return_contact) {
            this.return_contact = return_contact;
        }

        public static class AmountFlowBean {
            private String desc;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }
        }

        public static class StatusShaftBean {
            private String status_display;
            private String time;

            public String getStatus_display() {
                return status_display;
            }

            public void setStatus_display(String status_display) {
                this.status_display = status_display;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }


}