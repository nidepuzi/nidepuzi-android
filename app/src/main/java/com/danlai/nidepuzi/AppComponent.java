package com.danlai.nidepuzi;

/**
 * Created by wisdom on 17/2/23.
 */


import com.danlai.nidepuzi.module.ActivityInteractor;
import com.danlai.nidepuzi.module.AddressInteractor;
import com.danlai.nidepuzi.module.CartsInteractor;
import com.danlai.nidepuzi.module.MainInteractor;
import com.danlai.nidepuzi.module.ProductInteractor;
import com.danlai.nidepuzi.module.TradeInteractor;
import com.danlai.nidepuzi.module.UserInteractor;
import com.danlai.nidepuzi.module.VipInteractor;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    ActivityInteractor getActivityInteractor();

    MainInteractor getMainInteractor();

    ProductInteractor getProductInteractor();

    AddressInteractor getAddressInteractor();

    CartsInteractor getCartsInteractor();

    UserInteractor getUserInteractor();

    VipInteractor getVipInteractor();

    TradeInteractor getTradeInteractor();
}