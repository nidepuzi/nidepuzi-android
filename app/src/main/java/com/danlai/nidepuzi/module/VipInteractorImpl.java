package com.danlai.nidepuzi.module;

import com.danlai.library.rx.DefaultTransform;
import com.danlai.nidepuzi.entity.CategoryBean;
import com.danlai.nidepuzi.entity.ChooseListBean;
import com.danlai.nidepuzi.entity.MMCarryBean;
import com.danlai.nidepuzi.entity.MMShoppingBean;
import com.danlai.nidepuzi.entity.MMVisitorsBean;
import com.danlai.nidepuzi.entity.FansBean;
import com.danlai.nidepuzi.entity.MamaFortune;
import com.danlai.nidepuzi.entity.MamaSelfListBean;
import com.danlai.nidepuzi.entity.MamaUrl;
import com.danlai.nidepuzi.entity.NinePicBean;
import com.danlai.nidepuzi.entity.OderCarryBean;
import com.danlai.nidepuzi.entity.ProductNinePicBean;
import com.danlai.nidepuzi.entity.RecentCarryBean;
import com.danlai.nidepuzi.entity.SaveTimeBean;
import com.danlai.nidepuzi.entity.WxQrcode;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.service.api.MamaService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by wisdom on 17/3/1.
 */

public class VipInteractorImpl implements VipInteractor {
    private final MamaService service;

    @Inject
    public VipInteractorImpl(MamaService service) {
        this.service = service;
    }

    @Override
    public void getFans(int page, ServiceResponse<FansBean> response) {
        service.getFans(page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getMamaVisitor(int page, ServiceResponse<MMVisitorsBean> response) {
        service.getMamaVisitor(14, page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public Observable<MamaFortune> getMamaFortune() {
        return service.getMamaFortune()
            .compose(new DefaultTransform<>());
    }

    @Override
    public void getCategory(ServiceResponse<List<CategoryBean>> response) {
        service.getCategory()
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getChooseList(int page, String sort_field, String cid, int reverse,
                              ServiceResponse<ChooseListBean> response) {
        if ("".equals(sort_field) && "".equals(cid)) {
            service.getChooseList(page)
                .compose(new DefaultTransform<>())
                .subscribe(response);
        } else if ("".equals(sort_field)) {
            service.getChooseListByCid(page, cid)
                .compose(new DefaultTransform<>())
                .subscribe(response);
        } else if ("".equals(cid)) {
            service.getChooseListBySort(page, sort_field, reverse)
                .compose(new DefaultTransform<>())
                .subscribe(response);
        } else {
            service.getChooseList(page, sort_field, cid, reverse)
                .compose(new DefaultTransform<>())
                .subscribe(response);
        }
    }

    @Override
    public Observable<MMShoppingBean> getShareShopping() {
        return service.getShareShopping()
            .compose(new DefaultTransform<>());
    }

    @Override
    public Observable<MamaUrl> getMamaUrl() {
        return service.getMamaUrl("1.0")
            .compose(new DefaultTransform<>());
    }

    @Override
    public Observable<MamaSelfListBean> getMaMaSelfList() {
        return service.getMaMaSelfList()
            .compose(new DefaultTransform<>());
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
    public void getMamaAllOder(int page, ServiceResponse<OderCarryBean> response) {
        service.getMamaAllOder("direct", page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }

    @Override
    public void getWxCode(ServiceResponse<WxQrcode> response) {
        service.getWxCode()
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
    public void getCarryBean(int page, ServiceResponse<MMCarryBean> response) {
        service.getCarryBean(page)
            .compose(new DefaultTransform<>())
            .subscribe(response);
    }
}
