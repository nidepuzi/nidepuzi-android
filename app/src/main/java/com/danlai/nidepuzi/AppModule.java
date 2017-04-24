package com.danlai.nidepuzi;


import com.danlai.nidepuzi.module.ActivityInteractor;
import com.danlai.nidepuzi.module.ActivityInteractorImpl;
import com.danlai.nidepuzi.module.AddressInteractor;
import com.danlai.nidepuzi.module.AddressInteractorImpl;
import com.danlai.nidepuzi.module.CartsInteractor;
import com.danlai.nidepuzi.module.CartsInteractorImpl;
import com.danlai.nidepuzi.module.MainInteractor;
import com.danlai.nidepuzi.module.MainInteractorImpl;
import com.danlai.nidepuzi.module.ProductInteractor;
import com.danlai.nidepuzi.module.ProductInteractorImpl;
import com.danlai.nidepuzi.module.TradeInteractor;
import com.danlai.nidepuzi.module.TradeInteractorImpl;
import com.danlai.nidepuzi.module.UserInteractor;
import com.danlai.nidepuzi.module.UserInteractorImpl;
import com.danlai.nidepuzi.module.VipInteractor;
import com.danlai.nidepuzi.module.VipInteractorImpl;
import com.danlai.nidepuzi.service.RetrofitClient;
import com.danlai.nidepuzi.service.api.ActivityService;
import com.danlai.nidepuzi.service.api.AddressService;
import com.danlai.nidepuzi.service.api.CartsService;
import com.danlai.nidepuzi.service.api.MainService;
import com.danlai.nidepuzi.service.api.MamaService;
import com.danlai.nidepuzi.service.api.ProductService;
import com.danlai.nidepuzi.service.api.TradeService;
import com.danlai.nidepuzi.service.api.UserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by wisdom on 17/2/22.
 */

@Module
public class AppModule {

    @Provides
    public Retrofit provideRestAdapter() {
        return RetrofitClient.createAdapter();
    }

    @Singleton
    @Provides
    public ActivityService provideActivityApi(Retrofit retrofit) {
        return retrofit.create(ActivityService.class);
    }

    @Singleton
    @Provides
    public ActivityInteractor provideActivityInteractor(ActivityService service) {
        return new ActivityInteractorImpl(service);
    }

    @Singleton
    @Provides
    public MainService provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainService.class);
    }

    @Singleton
    @Provides
    public MainInteractor provideMainInteractor(MainService service) {
        return new MainInteractorImpl(service);
    }

    @Singleton
    @Provides
    public ProductService provideProductApi(Retrofit retrofit) {
        return retrofit.create(ProductService.class);
    }

    @Singleton
    @Provides
    public ProductInteractor provideProductInteractor(ProductService service) {
        return new ProductInteractorImpl(service);
    }

    @Singleton
    @Provides
    public AddressService provideAddressApi(Retrofit retrofit) {
        return retrofit.create(AddressService.class);
    }

    @Singleton
    @Provides
    public AddressInteractor provideAddressInteractor(AddressService service) {
        return new AddressInteractorImpl(service);
    }

    @Singleton
    @Provides
    public CartsService provideCartsApi(Retrofit retrofit) {
        return retrofit.create(CartsService.class);
    }

    @Singleton
    @Provides
    public CartsInteractor provideCartsInteractor(CartsService service) {
        return new CartsInteractorImpl(service);
    }

    @Singleton
    @Provides
    public UserService provideUserApi(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }

    @Singleton
    @Provides
    public UserInteractor provideUserInteractor(UserService service) {
        return new UserInteractorImpl(service);
    }

    @Singleton
    @Provides
    public MamaService provideVipApi(Retrofit retrofit) {
        return retrofit.create(MamaService.class);
    }

    @Singleton
    @Provides
    public VipInteractor provideVipInteractor(MamaService service) {
        return new VipInteractorImpl(service);
    }

    @Singleton
    @Provides
    public TradeService provideTradeApi(Retrofit retrofit) {
        return retrofit.create(TradeService.class);
    }

    @Singleton
    @Provides
    public TradeInteractor provideTradeInteractor(TradeService service) {
        return new TradeInteractorImpl(service);
    }
}
