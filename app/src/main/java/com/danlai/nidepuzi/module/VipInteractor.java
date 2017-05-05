package com.danlai.nidepuzi.module;

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

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by wisdom on 17/3/1.
 */

public interface VipInteractor {

    void getFans(int page, ServiceResponse<FansBean> response);

    void getMamaVisitor(int page, ServiceResponse<MMVisitorsBean> response);

    Observable<MamaFortune> getMamaFortune();

    void getCategory(ServiceResponse<List<CategoryBean>> response);

    void getChooseList(int page, String sort_field, String cid, int reverse,
                       ServiceResponse<ChooseListBean> response);

    Observable<MMShoppingBean> getShareShopping();

    Observable<MamaUrl> getMamaUrl();

    Observable<MamaSelfListBean> getMaMaSelfList();

    Observable<RecentCarryBean> getRecentCarry(String from, String days);

    void getNinePic(int sale_category, ServiceResponse<List<NinePicBean>> response);

    void getNinePicByModelId(int model_id, int page, ServiceResponse<ProductNinePicBean> response);

    void getMamaAllOder(int page, ServiceResponse<OderCarryBean> response);

    void getWxCode(ServiceResponse<WxQrcode> response);

    void saveTime(int id, int save_times, ServiceResponse<SaveTimeBean> response);

    void getCarryBean(int page, ServiceResponse<MMCarryBean> response);
}
