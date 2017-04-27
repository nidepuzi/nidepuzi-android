package com.danlai.nidepuzi.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.danlai.library.rx.RxCountDown;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.ui.activity.user.LoginActivity;
import com.danlai.nidepuzi.util.LoginUtils;

public class AdvertisementActivity extends BaseActivity implements View.OnClickListener {

    private TextView text;
    private boolean isDestroy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.main_in, R.anim.main_out);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initContentView() {
        setContentView(getContentViewLayoutID());
    }

    @Override
    protected void initViews() {
        setSwipeBackEnable(false);
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    protected void initData() {
        ViewUtils.setWindowStatus(this);
        String link = getIntent().getExtras().getString("link");
        int id = getIntent().getExtras().getInt("resId", -1);
        isDestroy = false;
        ImageView img = (ImageView) findViewById(R.id.img);
        text = (TextView) findViewById(R.id.text);
        assert text != null;
        text.setOnClickListener(this);
        assert img != null;
        if (link != null) {
            Glide.with(this).load(link).into(img);
        } else if (id != -1) {
            Glide.with(this).load(id).into(img);
        }
        readyToJump();
    }

    private void readyToJump() {
        RxCountDown.countdown(3).subscribe(
            integer -> {
                if (!isDestroy) {
                    text.setText("跳过  " + integer);
                    if (integer == 0) {
                        jumpAndFinish();
                    }
                }
            }, e -> {
                if (!isDestroy) {
                    jumpAndFinish();
                }
            });
    }

    @Override
    protected void onDestroy() {
        isDestroy = true;
        super.onDestroy();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_advertisement;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.main_in, R.anim.main_out);
    }

    private void jumpAndFinish() {
        isDestroy = true;
        if (LoginUtils.checkLoginState(this)) {
            startActivity(new Intent(AdvertisementActivity.this, TabActivity.class));
        } else {
            startActivity(new Intent(AdvertisementActivity.this, LoginActivity.class));
        }
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text:
                jumpAndFinish();
                break;
        }
    }
}
