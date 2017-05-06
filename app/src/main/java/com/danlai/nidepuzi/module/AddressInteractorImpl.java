package com.danlai.nidepuzi.module;

import com.danlai.library.rx.DefaultTransform;
import com.danlai.nidepuzi.entity.AddressBean;
import com.danlai.nidepuzi.entity.AddressResultBean;
import com.danlai.nidepuzi.entity.IdCardBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.service.api.AddressService;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by wisdom on 17/2/24.
 */

public class AddressInteractorImpl implements AddressInteractor {
    private final AddressService service;

    @Inject
    public AddressInteractorImpl(AddressService service) {
        this.service = service;
    }

    @Override
    public void getAddressList(ServiceResponse<List<AddressBean>> response) {
        service.getAddressList()
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void delete_address(String id, ServiceResponse<AddressResultBean> response) {
        service.delete_address(id)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void create_addressWithId(String state, String city, String district, String address,
                                     String name, String mobile, String defaulta,
                                     String identification_no, String face, String back,
                                     ServiceResponse<AddressResultBean> response) {
        if (face == null || back == null || "".equals(face) || "".equals(back)) {
            service.create_addressWithId(state, city, district, address, name, mobile,
                defaulta, identification_no)
                .compose(new DefaultTransform<>())
                .subscribe(response);
        } else {
            service.create_addressWithId(state, city, district, address, name, mobile,
                defaulta, identification_no, face, back)
                .compose(new DefaultTransform<>())
                .subscribe(response);
        }

    }

    @Override
    public void update_addressWithId(String id, String state, String city, String district,
                                     String address, String name, String mobile, String defaulta,
                                     String identification_no, String face, String back,
                                     ServiceResponse<AddressResultBean> response) {
        if ("".equals(identification_no) || identification_no == null) {
            service.update_address(id, state, city, district,
                address, name, mobile, defaulta)
                .compose(new DefaultTransform<>())
                .subscribe(response);
        } else {
            if (face == null || back == null || "".equals(face) || "".equals(back)) {
                service.update_addressWithId(id, state, city, district,
                    address, name, mobile, defaulta, identification_no)
                    .compose(new DefaultTransform<>())
                    .subscribe(response);
            } else {
                service.update_addressWithId(id, state, city, district,
                    address, name, mobile, defaulta, identification_no, face, back)
                    .compose(new DefaultTransform<>())
                    .subscribe(response);
            }
        }
    }

    @Override
    public void update_address(String id, String state, String city, String district, String address,
                               String name, String mobile, String logistic_company_code,
                               String referal_trade_id, ServiceResponse<AddressResultBean> response) {
        service.update_address(id, state, city, district,
            address, name, mobile, logistic_company_code, referal_trade_id)
            .compose(new DefaultTransform<>())
            .subscribe(response);

    }

    @Override
    public void idCardIndentify(String side, String card_base64, ServiceResponse<IdCardBean> response) {
        service.idCardIndentify(side, card_base64)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }
}
