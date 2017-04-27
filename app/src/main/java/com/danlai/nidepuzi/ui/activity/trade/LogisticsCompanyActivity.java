package com.danlai.nidepuzi.ui.activity.trade;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.LogisticCompanyAdapter;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityLogisticsCompanyBinding;
import com.danlai.nidepuzi.entity.LogisticsCompanyInfo;

import java.util.ArrayList;
import java.util.List;

public class LogisticsCompanyActivity extends BaseMVVMActivity<ActivityLogisticsCompanyBinding> implements View.OnClickListener {
    final List<LogisticsCompanyInfo> company_list = new ArrayList<>();
    private LogisticCompanyAdapter mCompanyAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_logistics_company;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected void setListener() {
        b.write.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        fillCompanyInfo();
        mCompanyAdapter = new LogisticCompanyAdapter(this, company_list);
        b.lvLogisticsCompany.setAdapter(mCompanyAdapter);
        b.lvLogisticsCompany.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
            Intent intent = new Intent(LogisticsCompanyActivity.this,
                WriteLogisticsInfoActivity.class);
            intent.putExtra("company", company_list.get(arg2).getName());
            setResult(1, intent);
            finish();
        });
    }

    //从server端获得所有订单数据，可能要查询几次
    @Override
    protected void initData() {
        b.sideBar.setTextView(b.dialog);
        //设置右侧触摸监听
        b.sideBar.setOnTouchingLetterChangedListener(
            s -> {
                //该字母首次出现的位置
                int position = mCompanyAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    b.lvLogisticsCompany.setSelection(position);
                }
            });
    }

    private void fillCompanyInfo() {
        company_list.add(new LogisticsCompanyInfo("A", "安信达快递"));
        company_list.add(new LogisticsCompanyInfo("B", "百世汇通"));
        company_list.add(new LogisticsCompanyInfo("C", "超联物流"));
        company_list.add(new LogisticsCompanyInfo("D", "大田快运"));
        company_list.add(new LogisticsCompanyInfo("D", "德邦快递"));
        company_list.add(new LogisticsCompanyInfo("D", "DHL快递"));
        company_list.add(new LogisticsCompanyInfo("D", "迪邦宅急送"));
        company_list.add(new LogisticsCompanyInfo("F", "飞达急便速递"));
        company_list.add(new LogisticsCompanyInfo("F", "飞鸿快递"));
        company_list.add(new LogisticsCompanyInfo("F", "飞马快递"));
        company_list.add(new LogisticsCompanyInfo("F", "飞天达快递"));
        company_list.add(new LogisticsCompanyInfo("F", "飞扬快递"));
        company_list.add(new LogisticsCompanyInfo("G", "国鑫快递"));
        company_list.add(new LogisticsCompanyInfo("H", "华运通物流"));
        company_list.add(new LogisticsCompanyInfo("H", "华宇物流"));
        company_list.add(new LogisticsCompanyInfo("J", "佳吉快运"));
        company_list.add(new LogisticsCompanyInfo("J", "佳加物流"));
        company_list.add(new LogisticsCompanyInfo("J", "捷安快递"));
        company_list.add(new LogisticsCompanyInfo("J", "捷森物流"));
        company_list.add(new LogisticsCompanyInfo("J", "京广快递"));
        company_list.add(new LogisticsCompanyInfo("K", "快捷快递"));
        company_list.add(new LogisticsCompanyInfo("K", "快马运输"));
        company_list.add(new LogisticsCompanyInfo("L", "联邦快递"));
        company_list.add(new LogisticsCompanyInfo("L", "联通快递"));
        company_list.add(new LogisticsCompanyInfo("L", "联邮速递"));
        company_list.add(new LogisticsCompanyInfo("Q", "勤诚快递"));
        company_list.add(new LogisticsCompanyInfo("Q", "奇速快递"));
        company_list.add(new LogisticsCompanyInfo("Q", "驱达国际快递"));
        company_list.add(new LogisticsCompanyInfo("Q", "全一快运"));
        company_list.add(new LogisticsCompanyInfo("Q", "全峰快递"));
        company_list.add(new LogisticsCompanyInfo("S", "申通快递"));
        company_list.add(new LogisticsCompanyInfo("S", "顺丰速运"));
        company_list.add(new LogisticsCompanyInfo("S", "顺风快递"));
        company_list.add(new LogisticsCompanyInfo("T", "天天快递"));
        company_list.add(new LogisticsCompanyInfo("U", "UPS快递"));
        company_list.add(new LogisticsCompanyInfo("W", "闻达快递"));
        company_list.add(new LogisticsCompanyInfo("X", "小红马快递"));
        company_list.add(new LogisticsCompanyInfo("X", "星辉快递"));
        company_list.add(new LogisticsCompanyInfo("Y", "亚风快递"));
        company_list.add(new LogisticsCompanyInfo("Y", "阳光快递"));
        company_list.add(new LogisticsCompanyInfo("Y", "壹时通物流"));
        company_list.add(new LogisticsCompanyInfo("Y", "一通快递"));
        company_list.add(new LogisticsCompanyInfo("Y", "一统快递"));
        company_list.add(new LogisticsCompanyInfo("Y", "邮政EMS"));
        company_list.add(new LogisticsCompanyInfo("Y", "远顺物流"));
        company_list.add(new LogisticsCompanyInfo("Y", "圆通速递"));
        company_list.add(new LogisticsCompanyInfo("Y", "越丰物流"));
        company_list.add(new LogisticsCompanyInfo("Y", "韵达快递"));
        company_list.add(new LogisticsCompanyInfo("Z", "宅急送"));
        company_list.add(new LogisticsCompanyInfo("Z", "泽龙物流"));
        company_list.add(new LogisticsCompanyInfo("Z", "中邦速递"));
        company_list.add(new LogisticsCompanyInfo("Z", "中诚快递"));
        company_list.add(new LogisticsCompanyInfo("Z", "中国速递"));
        company_list.add(new LogisticsCompanyInfo("Z", "中铁快运"));
        company_list.add(new LogisticsCompanyInfo("Z", "中通快递"));
        company_list.add(new LogisticsCompanyInfo("Z", "中外运"));
        company_list.add(new LogisticsCompanyInfo("Z", "中驿快递"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.write:
                View view = LayoutInflater.from(this).inflate(R.layout.dialog_write_logistic, null);
                AlertDialog dialog = new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setView(view)
                    .create();
                dialog.show();
                EditText editText = (EditText) view.findViewById(R.id.et);
                View cancelBtn = view.findViewById(R.id.cancel);
                View commitBtn = view.findViewById(R.id.commit);
                cancelBtn.setOnClickListener(view1 -> dialog.dismiss());
                commitBtn.setOnClickListener(view1 -> {
                    if (editText.getText().toString().trim().length() >= 2) {
                        Intent intent = new Intent(LogisticsCompanyActivity.this,
                            WriteLogisticsInfoActivity.class);
                        intent.putExtra("company", editText.getText().toString().trim());
                        setResult(1, intent);
                        dialog.dismiss();
                        finish();
                    } else {
                        JUtils.Toast("请输入正确的快递名称");
                    }
                });
                dialog.show();
                break;
        }
    }
}
