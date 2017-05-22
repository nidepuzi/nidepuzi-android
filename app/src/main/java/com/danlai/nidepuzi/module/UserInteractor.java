package com.danlai.nidepuzi.module;


import com.danlai.nidepuzi.entity.BankCardEntity;
import com.danlai.nidepuzi.entity.BankListEntity;
import com.danlai.nidepuzi.entity.BankResultEntity;
import com.danlai.nidepuzi.entity.BudgetDetailBean;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.entity.CouponPagingBean;
import com.danlai.nidepuzi.entity.DrawCashBean;
import com.danlai.nidepuzi.entity.DrawCashListBean;
import com.danlai.nidepuzi.entity.LogoutBean;
import com.danlai.nidepuzi.entity.ResultEntity;
import com.danlai.nidepuzi.entity.UserAccountBean;
import com.danlai.nidepuzi.entity.UserInfoBean;
import com.danlai.nidepuzi.entity.UserWithDrawResult;
import com.danlai.nidepuzi.entity.VersionBean;
import com.danlai.nidepuzi.service.ServiceResponse;

import io.reactivex.Observable;
import io.reactivex.Observer;


/**
 * Created by wisdom on 17/2/28.
 */

public interface UserInteractor {
    void getUserInfo(ServiceResponse<UserInfoBean> response);

    void customerLogout(ServiceResponse<LogoutBean> response);

    Observable<CouponPagingBean> getCouponPaging(int status, int page);

    void getCouponSelectEntity(String cart_ids, String type, int page, ServiceResponse<CouponPagingBean> response);

    void wxappLogin(String noncestr, String timestamp, String sign, String headimgurl,
                    String nickname, String openid, String unionid,
                    ServiceResponse<CodeBean> response);

    void getUserAccount(String platform, String regid, String device_id,
                        Observer<UserAccountBean> response);

    void userWithDrawCash(String amount, String verify_code, ServiceResponse<UserWithDrawResult> response);

    void userWithDrawCash(String amount, String verify_code, int card_id, ServiceResponse<UserWithDrawResult> response);

    void getVerifyCode(ServiceResponse<ResultEntity> response);

    void getCodeBean(String mobile, String action, ServiceResponse<CodeBean> response);

    void verifyCode(String mobile, String action, String code, ServiceResponse<CodeBean> response);

    void resetPassword(String mobile, String password1, String password2, String code,
                       ServiceResponse<CodeBean> response);

    void passwordLogin(String username, String password, String next,
                       ServiceResponse<CodeBean> response);

    void budgetDetailBean(int page, ServiceResponse<BudgetDetailBean> response);

    void getVersion(ServiceResponse<VersionBean> response);

    void getBankList(ServiceResponse<BankListEntity> response);

    void createBankCard(String account_no, String account_name, String bank_name, ServiceResponse<BankResultEntity> response);

    void getDefaultCard(ServiceResponse<BankCardEntity> response);

    void getDrawCashList(int page, ServiceResponse<DrawCashListBean> response);

    void getDrawCashDetail(int id, ServiceResponse<DrawCashBean> response);
}


