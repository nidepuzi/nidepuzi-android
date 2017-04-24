package com.danlai.nidepuzi.module;


import com.danlai.nidepuzi.entity.CartsHisBean;
import com.danlai.nidepuzi.entity.CartsInfoBean;
import com.danlai.nidepuzi.entity.CartsPayInfoBean;
import com.danlai.nidepuzi.entity.CodeBean;
import com.danlai.nidepuzi.entity.ResultEntity;
import com.danlai.nidepuzi.service.ServiceResponse;

import java.util.List;

import retrofit2.Response;

/**
 * Created by wisdom on 17/2/28.
 */

public interface CartsInteractor {

    void getCartsList(int type, ServiceResponse<List<CartsInfoBean>> response);

    void getCartsHisList(int type, ServiceResponse<List<CartsInfoBean>> response);

    void getCartsPayInfoList(String cart_ids, ServiceResponse<CartsPayInfoBean> response);

    void getCartsPayInfoListV2(String cart_ids, ServiceResponse<CartsPayInfoBean> response);

    void plusProductCarts(String id, ServiceResponse<Response<CodeBean>> response);

    void minusProductCarts(String id, ServiceResponse<Response<CodeBean>> response);

    void deleteCarts(String id, ServiceResponse<Response<CodeBean>> response);

    void rebuy(int type, String item_id, String sku_id, String cart_id, ServiceResponse<CartsHisBean> response);

    void addToCart(int item_id, int sku_id, int num, int type, ServiceResponse<ResultEntity> response);
}
