package com.danlai.nidepuzi.module;


import com.danlai.library.rx.DefaultTransform;
import com.danlai.nidepuzi.entity.CartsHisBean;
import com.danlai.nidepuzi.entity.CartsInfoBean;
import com.danlai.nidepuzi.entity.CartsPayInfoBean;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.entity.ResultEntity;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.service.api.CartsService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Created by wisdom on 17/2/28.
 */

public class CartsInteractorImpl implements CartsInteractor {
    private final CartsService service;

    @Inject
    public CartsInteractorImpl(CartsService service) {
        this.service = service;
    }


    @Override
    public void getCartsList(int type, ServiceResponse<List<CartsInfoBean>> response) {
        service.getCartsList(type)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getCartsHisList(int type, ServiceResponse<List<CartsInfoBean>> response) {
        service.getCartsHisList(type)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getCartsPayInfoList(String cart_ids, ServiceResponse<CartsPayInfoBean> response) {
        service.getCartsPayInfoList(cart_ids)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getCartsPayInfoListV2(String cart_ids, ServiceResponse<CartsPayInfoBean> response) {
        service.getCartsPayInfoListV2(cart_ids, "app")
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void plusProductCarts(String id, ServiceResponse<Response<CodeBean>> response) {
        service.plus_product_carts(id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void minusProductCarts(String id, ServiceResponse<Response<CodeBean>> response) {
        service.minus_product_carts(id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void deleteCarts(String id, ServiceResponse<Response<CodeBean>> response) {
        service.delete_carts(id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void rebuy(int type, String item_id, String sku_id, String cart_id, ServiceResponse<CartsHisBean> response) {
        service.rebuy(type, item_id, sku_id, cart_id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void addToCart(int item_id, int sku_id, int num, int type, ServiceResponse<ResultEntity> response) {
        service.addToCart(item_id, sku_id, num, type)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }
}
