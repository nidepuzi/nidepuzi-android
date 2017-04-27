package com.danlai.nidepuzi.ui.activity.user;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bumptech.glide.Glide;
import com.danlai.library.utils.CameraUtils;
import com.danlai.library.utils.FileUtils;
import com.danlai.library.utils.IdCardChecker;
import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.wheelcitypicker.CityPickerDialog;
import com.danlai.library.widget.wheelcitypicker.Util;
import com.danlai.library.widget.wheelcitypicker.address.City;
import com.danlai.library.widget.wheelcitypicker.address.County;
import com.danlai.library.widget.wheelcitypicker.address.Province;
import com.danlai.library.widget.zxing.decode.Utils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityChangeAddressBinding;
import com.danlai.nidepuzi.entity.AddressResultBean;
import com.danlai.nidepuzi.entity.IdCardBean;
import com.danlai.nidepuzi.entity.event.AddressChangeEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ChangeAddressActivity extends BaseMVVMActivity<ActivityChangeAddressBinding>
    implements View.OnClickListener {
    private String id;
    private ArrayList<Province> provinces = new ArrayList<>();
    private String city_string;
    private String clearaddressa;
    private String receiver_state;
    private String receiver_city;
    private String receiver_district;
    private String receiver_name;
    private String receiver_mobile;
    private County county;
    private boolean isDefaultX;
    private String idNo;
    private City city;
    private Province province;
    private ArrayList<City> cities;
    private ArrayList<County> counties;
    private int needLevel = 1;
    private String side;
    private String card_facepath;
    private String card_backpath;
    private File file;
    private String defaultAddress;

    @Override
    protected void setListener() {
        b.save.setOnClickListener(this);
        b.address.setOnClickListener(this);
        b.imageIdBefore.setOnClickListener(this);
        b.imageIdAfter.setOnClickListener(this);
        b.tvDelete.setOnClickListener(this);
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected void initViews() {
        if (needLevel == 2) {
            b.idLayout.setVisibility(View.VISIBLE);
        } else if (needLevel == 3) {
            b.idLayout.setVisibility(View.VISIBLE);
            b.idCardLayout.setVisibility(View.VISIBLE);
        }
        if (isDefaultX) {
            b.switchButton.setChecked(true);
        }
    }

    @Override
    protected void initData() {
        if (receiver_name != null) {
            b.name.setText(receiver_name);
            b.name.setSelection(receiver_name.length());
        }
        b.mobile.setText(receiver_mobile);
        b.address.setText(city_string);
        b.clearAddress.setText(clearaddressa);
        b.idNum.setText(idNo);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        needLevel = extras.getInt("needLevel", 1);
        receiver_name = extras.getString("receiver_name");
        receiver_mobile = extras.getString("mobile");
        city_string = extras.getString("address1");
        clearaddressa = extras.getString("address2");
        receiver_state = extras.getString("receiver_state");
        receiver_city = extras.getString("receiver_city");
        receiver_district = extras.getString("receiver_district");
        id = extras.getString("id");
        idNo = extras.getString("idNo");
        isDefaultX = extras.getBoolean("isDefaultX", false);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_change_address;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_delete:
                BaseApp.getAddressInteractor(this)
                    .delete_address(id, new ServiceResponse<AddressResultBean>(mBaseActivity) {
                        @Override
                        public void onNext(AddressResultBean addressResultBean) {
                            EventBus.getDefault().post(new AddressChangeEvent());
                            if (addressResultBean != null && addressResultBean.isRet()) {
                                finish();
                            }
                        }
                    });
                break;
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
                idNo = b.idNum.getText().toString().trim().toUpperCase();
                if (b.switchButton.isChecked()) {
                    defaultAddress = "true";
                } else {
                    defaultAddress = "false";
                }
                if (needLevel == 3 && (card_facepath == null || card_backpath == null || "".equals(card_facepath) ||
                    "".equals(card_backpath))) {
                    JUtils.Toast("身份证照片未完善");
                } else if (needLevel >= 2 && !IdCardChecker.isValidatedAllIdcard(idNo)) {
                    JUtils.Toast("请填写合法的身份证号码!");
                } else {
                    if (needLevel >= 2) {
                        new AlertDialog.Builder(this)
                            .setTitle("提示")
                            .setMessage("请确保身份证信息和收货人信息一致，否则无法通过海关检测!")
                            .setCancelable(false)
                            .setPositiveButton("同意", (dialog, which) -> {
                                dialog.dismiss();
                                commitInfo();
                            })
                            .show();
                    } else {
                        commitInfo();
                    }
                }
                break;
            case R.id.image_id_before:
                side = "face";
                CameraUtils.getSystemPicture(this);
                break;
            case R.id.image_id_after:
                side = "back";
                CameraUtils.getSystemPicture(this);
                break;
        }
    }

    private void commitInfo() {
        if (b.addressLayout.getVisibility() == View.VISIBLE) {
            receiver_state = b.etAddress1.getText().toString().trim();
            receiver_city = b.etAddress2.getText().toString().trim();
            receiver_district = b.etAddress3.getText().toString().trim();
            if (receiver_state == null || "".equals(receiver_state)) {
                receiver_state = receiver_city;
            }
        }
        if (checkInput(receiver_name, receiver_mobile, receiver_state + receiver_city + receiver_district, clearaddressa)) {
            BaseApp.getAddressInteractor(this)
                .update_addressWithId(id, receiver_state, receiver_city, receiver_district,
                    clearaddressa, receiver_name, receiver_mobile, defaultAddress, idNo, card_facepath
                    , card_backpath, new ServiceResponse<AddressResultBean>(mBaseActivity) {
                        @Override
                        public void onNext(AddressResultBean addressResultBean) {
                            EventBus.getDefault().post(new AddressChangeEvent());
                            if (addressResultBean != null) {
                                if (addressResultBean.getCode() == 0) {
                                    JUtils.Toast("修改成功");
                                    finish();
                                }
                            }
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            file = null;
            Uri uri = data.getData();
            String path = Utils.getPath(this, uri);
            if (path != null) {
                file = new File(path);
                if ("face".equals(side)) {
                    Glide.with(ChangeAddressActivity.this).load(file).into(b.imageIdBefore);
                } else {
                    Glide.with(ChangeAddressActivity.this).load(file).into(b.imageIdAfter);
                }
            } else {
                JUtils.Toast("获取照片失败");
            }
        } else {
            JUtils.Toast("请重新上传");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (file != null) {
            showIndeterminateProgressDialog(true);
            setDialogContent("上传中...");
            new compressTask().execute(file.getPath());
        }
    }

    public boolean checkInput(String receivername, String mobile, String address1, String address2) {
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
            } catch (Exception ignored) {
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ignored) {
                    }
                }
            }
            return false;
        }
    }

    private class compressTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(strings[0]);
                Bitmap compressImage = CameraUtils.imageZoom(bitmap, 50);
                file = null;
                return CameraUtils.getBitmapStrBase64(compressImage);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if ("".equals(s) || s == null) {
                hideIndeterminateProgressDialog();
            } else {
                BaseApp.getAddressInteractor(ChangeAddressActivity.this)
                    .idCardIndentify(side, s, new ServiceResponse<IdCardBean>(mBaseActivity) {
                        @Override
                        public void onNext(IdCardBean idCardBean) {
                            if (idCardBean.getCode() == 0) {
                                JUtils.Toast("上传成功");
                                if ("face".equals(side)) {
                                    card_facepath = idCardBean.getCard_infos().getCard_imgpath();
                                    JUtils.Log("-----------------------", idCardBean.getCard_infos().getName());
                                    JUtils.Log("-----------------------", idCardBean.getCard_infos().getNum());
                                } else {
                                    card_backpath = idCardBean.getCard_infos().getCard_imgpath();
                                }
                            } else {
                                JUtils.Toast(idCardBean.getInfo());
                            }
                            hideIndeterminateProgressDialog();
                        }

                        @Override
                        public void onError(Throwable e) {
                            hideIndeterminateProgressDialog();
                        }
                    });
            }

        }
    }
}
