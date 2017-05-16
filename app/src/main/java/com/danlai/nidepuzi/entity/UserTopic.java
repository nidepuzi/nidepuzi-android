package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * @author wisdom
 * @date 2016年08月19日 上午9:28
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
