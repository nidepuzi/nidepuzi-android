package com.danlai.nidepuzi.module;


import com.danlai.library.rx.DefaultTransform;
import com.danlai.nidepuzi.entity.ActivityBean;
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
    public void getActivityBean(String id, ServiceResponse<ActivityBean> response) {
        service.getActivityBean(id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }
}
