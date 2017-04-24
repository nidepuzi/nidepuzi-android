package com.danlai.nidepuzi.util;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseConst;

import java.io.File;


/**
 * Created by wisdom on 16/7/26.
 */

public abstract class VersionManager {

    private OnClickListener mPositiveListener;
    private OnClickListener mNegativeListener;
    private Dialog dialog;
    private String ignoreStr;

    public static VersionManager newInstance(int versionCode, String content, boolean msgflag) {
        return new VersionManager() {

            @Override
            public int getServerVersion() {
                return versionCode;
            }

            @Override
            public String getUpdateContent() {
                return content;
            }

            @Override
            public boolean showMsg() {
                return msgflag;
            }
        };
    }

    public abstract int getServerVersion();

    public abstract String getUpdateContent();

    public abstract boolean showMsg();

    public void checkVersion(Context context, int versionCode) {
        int localVersion = JUtils.getAppVersionCode();
        if (getServerVersion() > localVersion) {
            if (!isWifi(context)) {
                if (showMsg()) {
                    JUtils.Toast("版本有更新哦,请连接WiFi更新!");
                }
            }
            View view = LayoutInflater.from(context).inflate(R.layout.update_dialog, null);

            dialog = new Dialog(context, R.style.CustomDialog);
            dialog.setContentView(view);
            dialog.setCancelable(false);
            Button ignoreBtn = (Button) view.findViewById(R.id.ignore);
            Button okBtn = (Button) view.findViewById(R.id.ok);
            TextView contentTv = (TextView) view.findViewById(R.id.content);
            if (ignoreStr != null && !"".equals(ignoreStr)) {
                ignoreBtn.setText(ignoreStr);
            }
            okBtn.setOnClickListener(v -> {
                okBtn.setClickable(false);
                mPositiveListener.onClick(v);
            });
            contentTv.setText(getUpdateContent());
            if (mNegativeListener != null) {
                ignoreBtn.setOnClickListener(mNegativeListener);
            } else {
                ignoreBtn.setOnClickListener(v -> dialog.dismiss());
            }
            dialog.show();
        } else {
            if (showMsg()) {
                JUtils.Toast("当前已是最新版本!");
            }
            File updateFile = new File(BaseConst.BASE_DIR + "你的铺子" + versionCode + ".apk");
            if (updateFile.exists()) {
                updateFile.delete();
            }
        }
    }

    public void setPositiveListener(OnClickListener positiveListener) {
        mPositiveListener = positiveListener;
    }

    public void setNegativeListener(OnClickListener negativeListener) {
        mNegativeListener = negativeListener;
    }

    private boolean isWifi(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        return netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setIgnoreStr(String ignoreStr) {
        this.ignoreStr = ignoreStr;
    }
}