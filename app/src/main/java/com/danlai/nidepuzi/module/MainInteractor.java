package com.danlai.nidepuzi.module;


import com.danlai.nidepuzi.entity.AddressDownloadResultBean;
import com.danlai.nidepuzi.entity.CartsNumResultBean;
import com.danlai.nidepuzi.entity.CategoryDownBean;
import com.danlai.nidepuzi.entity.MainTodayBean;
import com.danlai.nidepuzi.entity.PortalBean;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.UserTopic;
import com.danlai.nidepuzi.entity.VersionBean;
import com.danlai.nidepuzi.service.ServiceResponse;

import java.util.List;

/**
 * Created by wisdom on 17/2/24.
 */

public interface MainInteractor {
    void getProfile(ServiceResponse<UserInfoBean> serviceResponse);

    void getCartsNum(int type, ServiceResponse<CartsNumResultBean> serviceResponse);

    void getPortalBean(String exclude_fields, ServiceResponse<PortalBean> serviceResponse);

    void getAddressVersionAndUrl(ServiceResponse<AddressDownloadResultBean> serviceResponse);

    void getVersion(ServiceResponse<VersionBean> serviceResponse);

    void getCategoryDown(ServiceResponse<CategoryDownBean> serviceResponse);

    void getTopic(ServiceResponse<UserTopic> serviceResponse);

    void getMainTodayList(ServiceResponse<List<MainTodayBean>> serviceResponse);
}
