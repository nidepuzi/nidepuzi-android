package com.danlai.nidepuzi.service.api;

import com.danlai.nidepuzi.entity.ActivityBean;
import com.danlai.nidepuzi.entity.StartBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wisdom on 16/11/23.
 */

public interface ActivityService {

    //活动内容分享
    @GET("/rest/v1/activitys/{id}/get_share_params")
    Observable<ActivityBean> get_party_share_content(
        @Path("id") String id);

    @GET("/rest/v1/activitys/startup_diagrams")
    Observable<StartBean> getStartAds();
}
