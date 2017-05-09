package com.danlai.nidepuzi.service.api;

import com.danlai.nidepuzi.entity.AccessToken;
import com.danlai.nidepuzi.entity.WxUserInfoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author wisdom
 * @date 2017年05月08日 下午4:00
 */

public interface WxService {

    @GET("/sns/oauth2/access_token")
    Observable<AccessToken> getAccessToken(
        @Query("appid") String appId,
        @Query("secret") String secret,
        @Query("code") String code,
        @Query("grant_type") String grant_type
    );

    @GET("/sns/userinfo")
    Observable<WxUserInfoBean> getUserInfo(
        @Query("access_token") String access_token,
        @Query("openid") String openId
    );
}
