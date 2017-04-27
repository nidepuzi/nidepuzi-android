package com.danlai.nidepuzi.ui.activity.trade;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.danlai.library.widget.zxing.ScanListener;
import com.danlai.library.widget.zxing.ScanManager;
import com.danlai.library.widget.zxing.decode.DecodeThread;
import com.danlai.library.widget.zxing.decode.Utils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityScanCodeBinding;
import com.google.zxing.Result;

public class CommonScanActivity extends BaseMVVMActivity<ActivityScanCodeBinding>
    implements ScanListener, View.OnClickListener {
    ScanManager scanManager;

    @Override
    protected void setListener() {
        b.qrcodeGGallery.setOnClickListener(this);
        b.qrcodeIcBack.setOnClickListener(this);
        b.ivLight.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_scan_code;
    }

    @Override
    protected void initViews() {
        scanManager = new ScanManager(this, b.capturePreview, b.captureContainer, b.captureCropView, b.captureScanLine,
                DecodeThread.ALL_MODE, this);
    }

    @Override
    public boolean isNeedShow() {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        scanManager.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        scanManager.onPause();
    }

    @Override
    public void scanResult(Result rawResult, Bundle bundle) {
        Intent intent = new Intent(this, WriteLogisticsInfoActivity.class);
        intent.putExtra("number", rawResult.getText());
        setResult(2, intent);
        finish();
    }

    @Override
    public void scanError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        if (e.getMessage() != null && e.getMessage().startsWith("相机")) {
            b.capturePreview.setVisibility(View.INVISIBLE);
        }
    }

    public void showPictures(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String photo_path;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 66:
                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor cursor = this.getContentResolver().query(data.getData(), proj, null, null, null);
                    assert cursor != null;
                    if (cursor.moveToFirst()) {
                        int colum_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        photo_path = cursor.getString(colum_index);
                        if (photo_path == null) {
                            photo_path = Utils.getPath(getApplicationContext(), data.getData());
                        }
                        scanManager.scanningImage(photo_path);
                    }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qrcode_g_gallery:
                showPictures(66);
                break;
            case R.id.iv_light:
                scanManager.switchLight();
                break;
            case R.id.qrcode_ic_back:
                finish();
                break;
            default:
                break;
        }
    }

}