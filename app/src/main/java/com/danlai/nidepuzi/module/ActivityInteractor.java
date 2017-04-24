package com.danlai.nidepuzi.module;


import com.danlai.nidepuzi.entity.ActivityBean;
import com.danlai.nidepuzi.entity.StartBean;
import com.danlai.nidepuzi.service.ServiceResponse;

/**
 * Created by wisdom on 17/2/24.
 */

public interface ActivityInteractor {

    void get_party_share_content(String id, ServiceResponse<ActivityBean> response);

    void getStartAds(ServiceResponse<StartBean> response);
}
