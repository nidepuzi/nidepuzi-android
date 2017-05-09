package com.danlai.nidepuzi.module;

import com.danlai.library.rx.DefaultTransform;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.entity.AccessToken;
import com.danlai.nidepuzi.entity.WxUserInfoBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.service.WxRetrofitClient;
import com.danlai.nidepuzi.service.api.WxService;

/**
 * @author wisdom
 * @date 2017年05月08日 下午4:10
 */

public class WxApiModule {
    private static WxApiModule sWxApiModule = new WxApiModule();

    public static WxApiModule newInstance() {
        return sWxApiModule;
    }

    public void getAccessToken(String code, ServiceResponse<AccessToken> response) {
        WxRetrofitClient.createAdapter()
            .create(WxService.class)
            .getAccessToken(BaseConst.WX_APP_ID, BaseConst.WX_APP_SECRET, code, "authorization_code")
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }


    public void getUserInfo(String accessToken, String openId, ServiceResponse<WxUserInfoBean> response) {
        WxRetrofitClient.createAdapter()
            .create(WxService.class)
            .getUserInfo(accessToken, openId)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

}
