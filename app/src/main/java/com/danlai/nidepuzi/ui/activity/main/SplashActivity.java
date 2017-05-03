package com.danlai.nidepuzi.ui.activity.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.danlai.library.rx.RxCountDown;
import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseSubscriberContext;
import com.danlai.nidepuzi.ui.activity.user.LoginActivity;
import com.danlai.nidepuzi.util.LoginUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 优尼世界 on 15/12/29.
 * <p>
 * Copyright 2015年 上海己美. All rights reserved.
 */
public class SplashActivity extends AppCompatActivity implements BaseSubscriberContext, View.OnClickListener {

    private CompositeDisposable mCompositeDisposable;
    private ImageView imageView;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.main_in, R.anim.main_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ViewUtils.setWindowStatus(this);
        imageView = ((ImageView) findViewById(R.id.img));
        mView = findViewById(R.id.jump);
        mView.setOnClickListener(this);
        checkPermissionAndJump();
    }

    private void checkPermissionAndJump() {
        if (JUtils.isPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            RxCountDown.countdown(2).subscribe(integer -> {
                if (integer == 0) {
                    jumpToAds();
                }
            }, throwable -> jumpToAds());
        } else {
            new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("应用包含缓存节省流量功能,需要打开存储权限,应用才能正常使用。")
                .setPositiveButton("确认", (dialog, which) -> {
                    dialog.dismiss();
                    getAppDetailSettingIntent();
                })
                .setNegativeButton("取消", (dialog, which) -> {
                    dialog.dismiss();
                    finish();
                }).show();
        }
    }

    private void jumpToAds() {
        Glide.with(this).load(R.drawable.img_desc).into(imageView);
        mView.setVisibility(View.VISIBLE);
    }

    private void jumpAndFinish() {
        if (LoginUtils.checkLoginState(this)) {
            startActivity(new Intent(this, TabActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }


    @Override
    protected void onStop() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
        super.onStop();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.main_in, R.anim.main_out);
    }

    private void getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivity(localIntent);
        finish();
    }

    @Override
    public void addDisposable(Disposable d) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(d);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jump:
                jumpAndFinish();
                break;
        }
    }
}
