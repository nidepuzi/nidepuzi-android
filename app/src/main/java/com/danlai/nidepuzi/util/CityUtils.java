package com.danlai.nidepuzi.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdom
 * @date 2017年06月13日 下午5:16
 */

public class CityUtils {

    public static List<CityBean> getList() {
        ArrayList<CityBean> cityBean = new ArrayList<>();
        cityBean.add(new CityBean("浙江省", "杭州"));
        cityBean.add(new CityBean("浙江省", "温州"));
        cityBean.add(new CityBean("浙江省", "衢州"));
        cityBean.add(new CityBean("浙江省", "金华"));
        cityBean.add(new CityBean("浙江省", "宁波"));
        cityBean.add(new CityBean("浙江省", "舟山"));
        cityBean.add(new CityBean("江苏省", "南京"));
        cityBean.add(new CityBean("江苏省", "南通"));
        cityBean.add(new CityBean("江苏省", "盐城"));
        cityBean.add(new CityBean("江苏省", "苏州"));
        cityBean.add(new CityBean("江苏省", "徐州"));
        cityBean.add(new CityBean("江苏省", "宿迁"));
        cityBean.add(new CityBean("江苏省", "常州"));
        cityBean.add(new CityBean("江苏省", "扬州"));
        cityBean.add(new CityBean("上海市", "浦东"));
        cityBean.add(new CityBean("上海市", "徐家汇"));
        cityBean.add(new CityBean("上海市", "杨浦"));
        cityBean.add(new CityBean("上海市", "崇明岛"));
        cityBean.add(new CityBean("上海市", "松江"));
        cityBean.add(new CityBean("上海市", "嘉定"));
        cityBean.add(new CityBean("上海市", "黄浦"));
        return cityBean;
    }
}
