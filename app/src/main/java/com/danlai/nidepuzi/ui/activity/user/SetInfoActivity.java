package com.danlai.nidepuzi.ui.activity.user;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;

import com.danlai.library.utils.FileUtils;
import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.wheelcitypicker.CityPickerDialog;
import com.danlai.library.widget.wheelcitypicker.Util;
import com.danlai.library.widget.wheelcitypicker.address.Province;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivitySetInfoBinding;
import com.danlai.nidepuzi.entity.ResultBean;
import com.danlai.nidepuzi.entity.event.UserInfoEvent;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author wisdom
 * @date 2017年06月01日 下午5:08
 */

public class SetInfoActivity extends BaseMVVMActivity<ActivitySetInfoBinding>
    implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private int id;
    private String nick;
    private int sex;
    private String birthday;
    private String province;
    private String city;
    private String district;
    private ArrayList<Province> provinces = new ArrayList<>();

    @Override
    protected void setListener() {
        b.commit.setOnClickListener(this);
        b.tvSex.setOnClickListener(this);
        b.tvBirthday.setOnClickListener(this);
        b.tvAddress.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        b.etName.setText(nick);
        b.etName.setSelection(nick.length());
        if (sex == 2) {
            b.tvSex.setText("女");
        } else if (sex == 1) {
            b.tvSex.setText("男");
        }
        if (!TextUtils.isEmpty(birthday)) {
            b.tvBirthday.setText(birthday);
        }
        if (!TextUtils.isEmpty(province) && !TextUtils.isEmpty(city)) {
            b.tvAddress.setText(province + "-" + city);
        }
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        id = extras.getInt("id");
        nick = extras.getString("nick");
        sex = extras.getInt("sex");
        birthday = extras.getString("birthday");
        province = extras.getString("province");
        city = extras.getString("city");
        district = extras.getString("district");
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_set_info;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit:
                String trim = b.etName.getText().toString().trim();
                if (trim.length() < 2) {
                    JUtils.Toast("请按照要求填写姓名");
                } else {
                    BaseApp.getUserInteractor(mBaseActivity)
                        .updateProfile(id, sex, trim, birthday, province, city, district,
                            new ServiceResponse<ResultBean>(mBaseActivity) {
                                @Override
                                public void onNext(ResultBean resultBean) {
                                    JUtils.Toast(resultBean.getInfo());
                                    if (resultBean.getCode() == 0) {
                                        EventBus.getDefault().post(new UserInfoEvent());
                                        finish();
                                    }
                                }
                            });
                }
                break;
            case R.id.tv_birthday:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog pickerDialog = new DatePickerDialog(this, this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
                DatePicker datePicker = pickerDialog.getDatePicker();
                Date date = new Date();
                calendar.set(1900, 0, 1);
                datePicker.setMaxDate(date.getTime());
                datePicker.setMinDate(calendar.getTimeInMillis());
                pickerDialog.show();
                break;
            case R.id.tv_sex:
                if (sex == 2) {
                    sex = 1;
                    b.tvSex.setText("男");
                } else if (sex == 1) {
                    sex = 2;
                    b.tvSex.setText("女");
                } else {
                    sex = 1;
                    b.tvSex.setText("男");
                }
                break;
            case R.id.tv_address:
                if (provinces.size() > 0) {
                    showAddressDialog();
                } else {
                    new InitAreaTask(this).execute(0);
                }
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        birthday = year + "-" + (month + 1) + "-" + dayOfMonth;
        b.tvBirthday.setText(birthday);
    }

    private void showAddressDialog() {
        new CityPickerDialog(this, provinces, null, null, null,
            (selectProvince, selectCity, selectCounty) -> {
                province = selectProvince != null ? selectProvince.getName() : "";
                district = selectCounty != null ? selectCounty.getName() : "";
                city = selectCity != null ? selectCity.getName() : "";
                b.tvAddress.setText(province + "-" + city);
            }).show();
    }

    private class InitAreaTask extends AsyncTask<Integer, Integer, Boolean> {

        Context mContext;
        Dialog progressDialog;

        InitAreaTask(Context context) {
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
                JUtils.Toast("数据初始化失败，请重试");
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
                return true;
            } catch (Exception e) {
                e.printStackTrace();
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
}
