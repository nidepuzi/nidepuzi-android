package com.danlai.nidepuzi.module;

import com.danlai.library.rx.DefaultTransform;
import com.danlai.nidepuzi.entity.AwardCarryBean;
import com.danlai.nidepuzi.entity.CarryListBean;
import com.danlai.nidepuzi.entity.FansBean;
import com.danlai.nidepuzi.entity.MMVisitorsBean;
import com.danlai.nidepuzi.entity.MamaFortune;
import com.danlai.nidepuzi.entity.NinePicBean;
import com.danlai.nidepuzi.entity.OrderCarryBean;
import com.danlai.nidepuzi.entity.ProductNinePicBean;
import com.danlai.nidepuzi.entity.RecentCarryBean;
import com.danlai.nidepuzi.entity.SaveTimeBean;
import com.danlai.nidepuzi.entity.ShopBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.service.api.VipService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by wisdom on 17/3/1.
 */

public class VipInteractorImpl implements VipInteractor {
    private final VipService service;

    @Inject
    public VipInteractorImpl(VipService service) {
        this.service = service;
    }

    @Override
    public void getMamaVisitor(int page, ServiceResponse<MMVisitorsBean> response) {
        service.getMamaVisitor(60, page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getMamaVisitorToday(int page, ServiceResponse<MMVisitorsBean> response) {
        service.getMamaVisitor(1, page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public Observable<MamaFortune> getMamaFortune() {
        return service.getMamaFortune()
            .compose(new DefaultTransform<>());
    }

    @Override
    public void getShopBean(ServiceResponse<ShopBean> response) {
        service.getShop()
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public Observable<RecentCarryBean> getRecentCarry(String from, String days) {
        return service.getRecentCarry(from, days)
            .compose(new DefaultTransform<>());
    }

    @Override
    public void getNinePic(int sale_category, ServiceResponse<List<NinePicBean>> response) {
        if (sale_category == -1) {
            service.getNinePic()
                .compose(new DefaultTransform<>())
                .subscribe(response);
        } else {
            service.getNinePic(sale_category)
                .compose(new DefaultTransform<>())
                .subscribe(response);
        }
    }

    @Override
    public void getNinePicByModelId(int model_id, int page, ServiceResponse<ProductNinePicBean> response) {
        service.getNinePicByModelId(model_id, page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getMamaAllOder(int page, ServiceResponse<OrderCarryBean> response) {
        service.getMamaAllOder("direct", page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getMamaAllOder(int page, String type, ServiceResponse<OrderCarryBean> response) {
        service.getMamaAllOder(type, page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getMamaAllOderToday(int page, ServiceResponse<OrderCarryBean> response) {
        service.getMamaAllOderToday("direct", page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getMamaAllAwardToday(int page, ServiceResponse<AwardCarryBean> response) {
        service.getMamaAllAwardToday(page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void saveTime(int id, int save_times, ServiceResponse<SaveTimeBean> response) {
        service.saveTime(id, save_times)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getCarryList(int page, int days, ServiceResponse<CarryListBean> response) {
        if (days == -1) {
            service.getCarryList(page)
                .compose(new DefaultTransform<>())
                .subscribe(response);
        } else {
            service.getCarryList(page, days)
                .compose(new DefaultTransform<>())
                .subscribe(response);
        }
    }

    @Override
    public void getCarryListToday(int page, ServiceResponse<CarryListBean> response) {
        service.getCarryListToday(page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getFans(int page, ServiceResponse<FansBean> response) {
        service.getFans(page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getFans(int page, String type, ServiceResponse<FansBean> response) {
        service.getFans(page, type)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getFans(int page, int type, ServiceResponse<FansBean> response) {
        service.getFans(page, type)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }
}
