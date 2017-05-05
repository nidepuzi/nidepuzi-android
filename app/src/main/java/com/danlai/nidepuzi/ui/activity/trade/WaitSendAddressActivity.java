package com.danlai.nidepuzi.ui.activity.trade;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.danlai.library.utils.FileUtils;
import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.wheelcitypicker.CityPickerDialog;
import com.danlai.library.widget.wheelcitypicker.Util;
import com.danlai.library.widget.wheelcitypicker.address.City;
import com.danlai.library.widget.wheelcitypicker.address.County;
import com.danlai.library.widget.wheelcitypicker.address.Province;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityWaitSendAddressBinding;
import com.danlai.nidepuzi.entity.AddressResultBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class WaitSendAddressActivity extends BaseMVVMActivity<ActivityWaitSendAddressBinding> implements View.OnClickListener {

    private String id;

    private ArrayList<Province> provinces = new ArrayList<>();
    private String city_string;
    private String clearaddressa;
    private String receiver_state;
    private String receiver_city;
    private String receiver_district;
    private String receiver_name;
    private String receiver_mobile;
    private String referal_trade_id;
    private County county;
    private City city;
    private Province province;
    private ArrayList<City> cities;
    private ArrayList<County> counties;

    @Override
    protected void setListener() {
        b.save.setOnClickListener(this);
        b.address.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (receiver_name != null) {
            b.name.setText(receiver_name);
            if (receiver_name.length() < 20) {
                b.name.setSelection(receiver_name.length());
            } else {
                b.name.setSelection(20);
            }
        }
        b.mobile.setText(receiver_mobile);
        b.address.setText(city_string);
        b.clearAddress.setText(clearaddressa);
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        receiver_name = extras.getString("receiver_name");
        receiver_mobile = extras.getString("mobile");
        city_string = extras.getString("address1");
        clearaddressa = extras.getString("address2");
        receiver_state = extras.getString("receiver_state");
        receiver_city = extras.getString("receiver_city");
        receiver_district = extras.getString("receiver_district");
        id = extras.getString("address_id");
        referal_trade_id = extras.getString("referal_trade_id");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_change_address;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address:
                InputMethodManager imm =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(b.mobile.getWindowToken(), 0);

                if (provinces.size() > 0) {
                    showAddressDialog();
                } else {
                    new InitAreaTask(this).execute(0);
                }
                break;
            case R.id.save:
                receiver_name = b.name.getText().toString().trim();
                receiver_mobile = b.mobile.getText().toString().trim();
                clearaddressa = b.clearAddress.getText().toString().trim();
                if (b.addressLayout.getVisibility() == View.VISIBLE) {
                    receiver_state = b.etAddress1.getText().toString().trim();
                    receiver_city = b.etAddress2.getText().toString().trim();
                    receiver_district = b.etAddress3.getText().toString().trim();
                    if (receiver_state == null || "".equals(receiver_state)) {
                        receiver_state = receiver_city;
                    }
                }
                if (checkInput(receiver_name, receiver_mobile, city_string, clearaddressa)) {
                    BaseApp.getAddressInteractor(this)
                        .update_address(id, receiver_state, receiver_city, receiver_district,
                            clearaddressa, receiver_name, receiver_mobile, null, referal_trade_id,
                            new ServiceResponse<AddressResultBean>(mBaseActivity) {
                                @Override
                                public void onNext(AddressResultBean addressResultBean) {
                                    if (addressResultBean != null) {
                                        if (addressResultBean.getCode() == 0) {
                                            JUtils.Toast("修改成功");
                                            finish();
                                        } else {
                                            JUtils.Toast("修改失败");
                                        }
                                    }
                                }
                            });
                }
                break;
        }
    }

    public boolean checkInput(String receivername, String mobile, String address1,
                              String address2) {
        if (receivername == null || receivername.trim().equals("")) {
            JUtils.Toast("请输入收货人姓名");
        } else {
            if (mobile == null || mobile.trim().equals("")) {
                JUtils.Toast("请输入手机号");
            } else {
                if (mobile.length() != 11) {
                    JUtils.Toast("请输入正确的手机号");
                } else if (address1 == null || address1.trim().equals("")) {
                    JUtils.Toast("请选择所在地区");
                } else if (address2 == null || address2.trim().equals("")) {
                    JUtils.Toast("请输入详细地址");
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private void showAddressDialog() {
        new CityPickerDialog(this, provinces, province, city, county,
            (selectProvince, selectCity, selectCounty) -> {
                receiver_state = selectProvince != null ? selectProvince.getName() : "";
                receiver_district = selectCounty != null ? selectCounty.getName() : "";
                receiver_city = selectCity != null ? selectCity.getName() : "";
                city_string = receiver_state + receiver_city + receiver_district;
                b.address.setText(city_string);
            }).show();
    }

    private class InitAreaTask extends AsyncTask<Integer, Integer, Boolean> {
        Context mContext;
        Dialog progressDialog;

        public InitAreaTask(Context context) {
            mContext = context;
            progressDialog = Util.createLoadingDialog(mContext, "请稍等...", true, 0);
        }

        @Override
        protected void onPreExecute() {

            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            progressDialog.dismiss();
            if (provinces.size() > 0) {
                showAddressDialog();
            } else {
                JUtils.Toast("数据初始化失败，请手动填写省市区");
                b.address.setVisibility(View.GONE);
                b.addressLayout.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            String address;
            InputStream in = null;
            try {
                if (FileUtils.isFileExist(BaseConst.BASE_DIR + "areas.json")) {
                    File file = new File(BaseConst.BASE_DIR + "areas.json");
                    in = new FileInputStream(file);
                } else {
                    in = mContext.getResources().getAssets().open("areas.json");
                }
                byte[] arrayOfByte = new byte[in.available()];
                in.read(arrayOfByte);
                address = new String(arrayOfByte, "UTF-8");
                Gson gson = new Gson();
                provinces = gson.fromJson(address, new TypeToken<List<Province>>() {
                }.getType());

                for (int i = 0; i < provinces.size(); i++) {
                    if (provinces.get(i).getName().equals(receiver_state)) {
                        province = provinces.get(i);
                        cities = provinces.get(i).getCities();
                    }
                }

                for (int i = 0; i < cities.size(); i++) {
                    if (cities.get(i).getName().equals(receiver_city)) {
                        city = cities.get(i);
                        counties = cities.get(i).getCounties();
                    }
                }

                for (int i = 0; i < counties.size(); i++) {
                    if (counties.get(i).getName().equals(receiver_district)) {
                        county = counties.get(i);
                    }
                }

                return true;
            } catch (Exception e) {
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
            }
            return false;
        }
    }
}
