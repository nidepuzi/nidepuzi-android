package com.danlai.nidepuzi.module;

import com.danlai.nidepuzi.entity.AddressBean;
import com.danlai.nidepuzi.entity.AddressResultBean;
import com.danlai.nidepuzi.entity.IdCardBean;
import com.danlai.nidepuzi.service.ServiceResponse;

import java.util.List;

/**
 * Created by wisdom on 17/2/24.
 */

public interface AddressInteractor {
    void getAddressList(ServiceResponse<List<AddressBean>> response);

    void delete_address(String id, ServiceResponse<AddressResultBean> response);

    void create_addressWithId(String receiver_state, String receiver_city,
                              String receiver_district, String receiver_address,
                              String receiver_name, String receiver_mobile,
                              String defaulta, String identification_no,
                              String card_facepath, String card_backpath,
                              ServiceResponse<AddressResultBean> response);

    void update_addressWithId(String id, String receiver_state, String receiver_city,
                              String receiver_district, String receiver_address,
                              String receiver_name, String receiver_mobile,
                              String defalut, String identification_no,
                              String card_facepath, String card_backpath,
                              ServiceResponse<AddressResultBean> response);

    void update_address(String id, String receiver_state, String receiver_city,
                        String receiver_district, String receiver_address,
                        String receiver_name, String receiver_mobile,
                        String logistic_company_code, String referal_trade_id,
                        ServiceResponse<AddressResultBean> response);

    void idCardIndentify(String side, String card_base64, ServiceResponse<IdCardBean> response);
}

