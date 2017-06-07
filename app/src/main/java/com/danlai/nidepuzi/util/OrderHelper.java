package com.danlai.nidepuzi.util;

import com.danlai.nidepuzi.entity.AllOrdersBean;
import com.danlai.nidepuzi.entity.OrderContent;
import com.danlai.nidepuzi.entity.OrderFooter;
import com.danlai.nidepuzi.entity.OrderHead;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdom
 * @date 2017年05月05日 下午2:34
 */

public class OrderHelper {

    public static List<Object> translateOrderBean(List<AllOrdersBean.ResultsBean> list) {
        List<Object> data = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            AllOrdersBean.ResultsBean entity = list.get(i);
            data.add(new OrderHead(entity.getCreated(),
                entity.getStatus_display(),
                entity.getExtras().getSale_type()));
            for (int j = 0; j < entity.getOrders().size(); j++) {
                AllOrdersBean.ResultsBean.OrdersBean bean = entity.getOrders().get(j);
                data.add(new OrderContent(entity.getId(),
                    bean.getPic_path(),
                    bean.getTitle(),
                    bean.getSku_name(),
                    bean.getTotal_fee() / bean.getNum(),
                    bean.getExtras().getOrder_profit(),
                    bean.getNum(),
                    entity.getExtras().isSelf_buy()));
            }
            data.add(new OrderFooter(entity.getId(),
                entity.getPayment(),
                entity.getPost_fee(),
                entity.getStatus(),
                entity.getExtras().isSelf_buy()));
        }
        return data;
    }
}
