package com.danlai.nidepuzi.module;

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

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by wisdom on 17/3/1.
 */

public interface VipInteractor {

    void getMamaVisitor(int page, ServiceResponse<MMVisitorsBean> response);

    void getMamaVisitorToday(int page, ServiceResponse<MMVisitorsBean> response);

    Observable<MamaFortune> getMamaFortune();

    void getShopBean(ServiceResponse<ShopBean> response);

    Observable<RecentCarryBean> getRecentCarry(String from, String days);

    void getNinePic(int sale_category, ServiceResponse<List<NinePicBean>> response);

    void getNinePicByModelId(int model_id, int page, ServiceResponse<ProductNinePicBean> response);

    void getMamaAllOder(int page, ServiceResponse<OrderCarryBean> response);

    void getMamaAllOder(int page, String type, ServiceResponse<OrderCarryBean> response);

    void getMamaAllOderToday(int page, ServiceResponse<OrderCarryBean> response);

    void getMamaAllAwardToday(int page, ServiceResponse<AwardCarryBean> response);

    void saveTime(int id, int save_times, ServiceResponse<SaveTimeBean> response);

    void getCarryList(int page, int days,ServiceResponse<CarryListBean> response);

    void getCarryListToday(int page, ServiceResponse<CarryListBean> response);

    void getFans(int page, ServiceResponse<FansBean> response);

    void getFans(int page, String type, ServiceResponse<FansBean> response);

    void getFans(int page, int type, ServiceResponse<FansBean> response);
}
