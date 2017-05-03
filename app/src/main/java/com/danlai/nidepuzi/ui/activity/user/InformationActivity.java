package com.danlai.nidepuzi.ui.activity.user;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;

import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityInformationBinding;

import java.util.Calendar;
import java.util.Date;

/**
 * @author wisdom
 * @date 2017年04月28日 上午11:20
 */

public class InformationActivity extends BaseMVVMActivity<ActivityInformationBinding>
    implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    protected void setListener() {
        b.birthdayLayout.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_information;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.birthday_layout:
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
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        b.birthdayLayout.setSummary(year + "-" + month + "-" + dayOfMonth);
    }
}
