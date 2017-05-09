package com.danlai.nidepuzi.module;


import com.danlai.library.rx.DefaultTransform;
import com.danlai.nidepuzi.entity.AddressDownloadResultBean;
import com.danlai.nidepuzi.entity.CartsNumResultBean;
import com.danlai.nidepuzi.entity.CategoryDownBean;
import com.danlai.nidepuzi.entity.EduBean;
import com.danlai.nidepuzi.entity.MainTodayBean;
import com.danlai.nidepuzi.entity.PortalBean;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.UserTopic;
import com.danlai.nidepuzi.entity.VersionBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.service.api.MainService;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by wisdom on 17/2/24.
 */

public class MainInteractorImpl implements MainInteractor {
    private final MainService service;

    @Inject
    public MainInteractorImpl(MainService service) {
        this.service = service;
    }

    @Override
    public void getProfile(ServiceResponse<UserInfoBean> serviceResponse) {
        service.getProfile()
            .compose(new DefaultTransform<>())
            .subscribe(serviceResponse);
    }

    @Override
    public void getCartsNum(int type, ServiceResponse<CartsNumResultBean> serviceResponse) {
        service.getCartsNum(type)
            .compose(new DefaultTransform<>())
            .subscribe(serviceResponse);
    }

    @Override
    public void getPortalBean(String exclude_fields, ServiceResponse<PortalBean> serviceResponse) {
        service.getPortalBean(exclude_fields)
            .compose(new DefaultTransform<>())
            .subscribe(serviceResponse);
    }

    @Override
    public void getAddressVersionAndUrl(ServiceResponse<AddressDownloadResultBean> serviceResponse) {
        service.getAddressVersionAndUrl()
            .compose(new DefaultTransform<>())
            .subscribe(serviceResponse);
    }

    @Override
    public void getVersion(ServiceResponse<VersionBean> serviceResponse) {
        service.getVersion()
            .compose(new DefaultTransform<>())
            .subscribe(serviceResponse);
    }

    @Override
    public void getCategoryDown(ServiceResponse<CategoryDownBean> serviceResponse) {
        service.getCategoryDown()
            .compose(new DefaultTransform<>())
            .subscribe(serviceResponse);
    }

    @Override
    public void getTopic(ServiceResponse<UserTopic> serviceResponse) {
        service.getTopic()
            .compose(new DefaultTransform<>())
            .subscribe(serviceResponse);
    }

    @Override
    public void getMainTodayList(ServiceResponse<List<MainTodayBean>> serviceResponse) {
        service.getMainTodayList()
            .compose(new DefaultTransform<>())
            .subscribe(serviceResponse);
    }

    @Override
    public void getEduBean(int page, ServiceResponse<EduBean> serviceResponse) {
        service.getEduBean(page)
            .compose(new DefaultTransform<>())
            .subscribe(serviceResponse);
    }
}
