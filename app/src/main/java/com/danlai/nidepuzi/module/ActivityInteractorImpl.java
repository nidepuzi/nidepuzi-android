package com.danlai.nidepuzi.module;


import com.danlai.library.rx.DefaultTransform;
import com.danlai.nidepuzi.entity.ActivityBean;
import com.danlai.nidepuzi.entity.StartBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.service.api.ActivityService;

import javax.inject.Inject;

/**
 * Created by wisdom on 17/2/24.
 */

public class ActivityInteractorImpl implements ActivityInteractor {

    private final ActivityService service;

    @Inject
    public ActivityInteractorImpl(ActivityService service) {
        this.service = service;

    }

    @Override
    public void get_party_share_content(String id, ServiceResponse<ActivityBean> response) {
        service.get_party_share_content(id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getStartAds(ServiceResponse<StartBean> response) {
        service.getStartAds()
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }
}
