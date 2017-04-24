package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * Created by wisdom on 16/8/19.
 */
public class UserTopic {

    /**
     * topics : ["CUSTOMER_PAY","XLMM","XLMM_VIP1"]
     * customer_id : 1
     */

    private List<String> topics;

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}
