package com.danlai.nidepuzi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.danlai.nidepuzi.service.UpdateService;

/**
 * Created by wisdom on 16/7/26.
 */
public class UpdateBroadReceiver extends BroadcastReceiver {

    public static final String ACTION_RETRY_DOWNLOAD = "action.retry.download";

    @Override
    public void onReceive(Context context, Intent intent) {
        String downloadUrl = intent.getStringExtra(UpdateService.EXTRAS_DOWNLOAD_URL);
        int versionCode = intent.getIntExtra(UpdateService.VERSION_CODE, -1);
        Intent updateIntent = new Intent(context, UpdateService.class);
        updateIntent.putExtra(UpdateService.EXTRAS_DOWNLOAD_URL, downloadUrl);
        updateIntent.putExtra(UpdateService.VERSION_CODE, versionCode);
        context.startService(updateIntent);
    }
}
