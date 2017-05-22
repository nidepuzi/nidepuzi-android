package com.danlai.nidepuzi.module;

import com.danlai.library.rx.DefaultTransform;
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
import com.danlai.nidepuzi.service.api.UserService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;


/**
 * Created by wisdom on 17/2/28.
 */

public class UserInteractorImpl implements UserInteractor {

    private final UserService service;

    @Inject
    public UserInteractorImpl(UserService service) {
        this.service = service;
    }

    @Override
    public void getUserInfo(ServiceResponse<UserInfoBean> response) {
        service.getUserInfo()
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void customerLogout(ServiceResponse<LogoutBean> response) {
        service.customerLogout()
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public Observable<CouponPagingBean> getCouponPaging(int status, int page) {
        return service.getCouponPaging(status, 1, page)
            .compose(new DefaultTransform<>());
    }

    @Override
    public void getCouponSelectEntity(String cart_ids, String type, int page, ServiceResponse<CouponPagingBean> response) {
        service.getCouponSelectEntity(cart_ids, type, page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void wxappLogin(String noncestr, String timestamp, String sign, String headimgurl,
                           String nickname, String openid, String unionid, ServiceResponse<CodeBean> response) {
        service.wxappLogin(noncestr, timestamp, sign, headimgurl, nickname, openid, unionid, "android")
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getUserAccount(String platform, String regid, String device_id,
                               Observer<UserAccountBean> response) {
        service.getUserAccount(platform, regid, device_id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void userWithDrawCash(String amount, String verify_code,
                                 ServiceResponse<UserWithDrawResult> response) {
        service.userWithDrawCash(amount, verify_code)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void userWithDrawCash(String amount, String verify_code, int card_id,
                                 ServiceResponse<UserWithDrawResult> response) {
        service.userWithDrawCash(amount, verify_code, "sandpay", card_id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getVerifyCode(ServiceResponse<ResultEntity> response) {
        service.getVerifyCode()
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getCodeBean(String mobile, String action, ServiceResponse<CodeBean> response) {
        service.getCodeBean(mobile, action)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void verifyCode(String mobile, String action, String code, ServiceResponse<CodeBean> response) {
        service.verifyCode(mobile, action, code, "android")
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void resetPassword(String mobile, String password1, String password2, String code,
                              ServiceResponse<CodeBean> response) {
        service.resetPassword(mobile, password1, password2, code)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void passwordLogin(String username, String password, String next,
                              ServiceResponse<CodeBean> response) {
        service.passwordLogin(username, password, next, "android")
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void budgetDetailBean(int page, ServiceResponse<BudgetDetailBean> response) {
        service.budgetDetailBean(page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getVersion(ServiceResponse<VersionBean> response) {
        service.getVersion()
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getBankList(ServiceResponse<BankListEntity> response) {
        service.getBankList()
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void createBankCard(String account_no, String account_name, String bank_name, ServiceResponse<BankResultEntity> response) {
        service.createBankCard(account_no, account_name, bank_name, true)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getDefaultCard(ServiceResponse<BankCardEntity> response) {
        service.getDefaultCard()
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getDrawCashList(int page,ServiceResponse<DrawCashListBean> response) {
        service.getDrawCashList(page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getDrawCashDetail(int id, ServiceResponse<DrawCashBean> response) {
        service.getDrawCashDetail(id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }
}
