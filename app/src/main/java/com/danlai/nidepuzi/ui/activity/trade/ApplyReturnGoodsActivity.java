package com.danlai.nidepuzi.ui.activity.trade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;

import com.danlai.library.utils.CameraUtils;
import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.library.widget.zxing.decode.Utils;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseApi;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityApplyReturnGoodsBinding;
import com.danlai.nidepuzi.entity.AllOrdersBean;
import com.danlai.nidepuzi.entity.OrderDetailBean;
import com.danlai.nidepuzi.entity.QiniuTokenBean;
import com.danlai.nidepuzi.entity.RefundMsgBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ApplyReturnGoodsActivity extends BaseMVVMActivity<ActivityApplyReturnGoodsBinding>
    implements View.OnClickListener {
    String select_reason[] = new String[]{
        "七天无理由退换货", "发票问题", "与描述不符", "未收到货", "发错货/漏发", "开线/脱色/脱毛/有色差/有虫洞", "错拍", "其他原因"
    };
    AllOrdersBean.ResultsBean.OrdersBean goods_info;
    String reason = "";
    int num = 0;
    double apply_fee = 0;
    String desc = "";
    String proof_pic = "";
    String upToken;
    File tmpPic[] = new File[3];
    String uploadPic[] = new String[3];
    int picNum = 0;
    UploadManager uploadManager = new UploadManager();
    private int id;
    private int position;

    @Override
    protected void setListener() {
        b.btnCommit.setOnClickListener(this);
        b.imgbtnCameraPic.setOnClickListener(this);
        b.add.setOnClickListener(this);
        b.delete.setOnClickListener(this);
        b.imgDelete1.setOnClickListener(v -> {
            tmpPic[0] = null;
            uploadPic[0] = null;
            if (picNum >= 1) {
                for (char i = 1; i < picNum; i++) {
                    tmpPic[i - 1] = tmpPic[i];
                    uploadPic[i - 1] = uploadPic[i];
                }
                picNum--;
            }
            showPic();
        });
        b.imgDelete2.setOnClickListener(v -> {
            tmpPic[1] = null;
            uploadPic[1] = null;
            if (picNum >= 2) {
                for (char i = 2; i < picNum; i++) {
                    tmpPic[i - 1] = tmpPic[i];
                    uploadPic[i - 1] = uploadPic[i];
                }
                picNum--;
            }
        });
        b.imgDelete3.setOnClickListener(v -> {
            tmpPic[2] = null;
            uploadPic[2] = null;
            if (picNum >= 3) {
                for (char i = 3; i < picNum; i++) {
                    tmpPic[i - 1] = tmpPic[i];
                    uploadPic[i - 1] = uploadPic[i];
                }
                picNum--;
            }
        });

        b.etRefundInfo.setOnClickListener(this);
        b.etRefundReason.setOnTouchListener((v, event) -> {
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                chooseReason();
            }
            return false;
        });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        id = extras.getInt("id");
        position = extras.getInt("position");
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_apply_return_goods;
    }

    @Override
    protected void initData() {
        BaseApp.getTradeInteractor(this)
            .getOrderDetail(id, new ServiceResponse<OrderDetailBean>(mBaseActivity) {
                @Override
                public void onNext(OrderDetailBean orderDetailBean) {
                    goods_info = orderDetailBean.getOrders().get(position);
                    fillDataIntoView();
                }

                @Override
                public void onError(Throwable e) {
                    JUtils.Toast("加载失败");
                }
            });
    }

    private void fillDataIntoView() {
        fillDataToView(goods_info);
        getQiniuToken();
    }

    private void fillDataToView(AllOrdersBean.ResultsBean.OrdersBean goods) {
        if ((goods == null)) return;

        ViewUtils.loadImgToImgViewWithPlaceholder(this, b.imgGood, goods.getPic_path());

        b.txGoodName.setText(goods.getTitle());
        b.txGoodPrice.setText("￥" + goods.getTotal_fee());

        b.txGoodSize.setText(goods.getSku_name());
        b.txGoodNum.setText("×" + goods.getNum());

        num = goods.getNum();
        b.txRefundNum.setText(Integer.toString(num));
        b.txRefundfee.setText("￥" + goods.getPayment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                desc = b.etRefundInfo.getText().toString().trim();
                proof_pic = "";
                for (int i = 0; i < picNum; i++) {
                    if (uploadPic[i] != null) {
                        if (i != picNum - 1) {
                            proof_pic += BaseApi.QINIU_UPLOAD_URL_BASE + uploadPic[i] + ",";
                        } else {
                            proof_pic += BaseApi.QINIU_UPLOAD_URL_BASE + uploadPic[i];
                        }
                    }
                }
                if (reason.equals("")) {
                    JUtils.Toast("请选择退货原因！");
                } else {
                    commit_apply();
                }
                break;
            case R.id.et_refund_info:
                b.etRefundInfo.setCursorVisible(true);
                b.etRefundInfo.setFocusable(true);
                b.etRefundInfo.setFocusableInTouchMode(true);
                b.etRefundInfo.requestFocus();
                break;
            case R.id.add:
                if (goods_info.getNum() > num) {
                    num++;
                }
                b.txRefundNum.setText(Integer.toString(num));
                break;
            case R.id.delete:
                if (num > 1) {
                    num--;
                }
                b.txRefundNum.setText(Integer.toString(num));
                break;

            case R.id.imgbtn_camera_pic:
                CameraUtils.getSystemPicture(this);
                break;
        }
    }

    private void commit_apply() {
        BaseApp.getTradeInteractor(this)
            .refundCreate(goods_info.getId(), BaseConst.get_reason_num(reason), num,
                apply_fee, desc, proof_pic, new ServiceResponse<RefundMsgBean>(mBaseActivity) {
                    @Override
                    public void onNext(RefundMsgBean resp) {
                        new AlertDialog.Builder(ApplyReturnGoodsActivity.this)
                            .setMessage(resp.getInfo())
                            .setCancelable(false)
                            .setPositiveButton("确认", (dialog1, which) -> {
                                dialog1.dismiss();
                                finish();
                            })
                            .create()
                            .show();
                    }
                });
    }

    private void chooseReason() {
        new AlertDialog.Builder(this).setTitle("")
            .setItems(select_reason, (dialog, which) -> {
                reason = select_reason[which];
                b.etRefundReason.setText(reason);
                dialog.dismiss();
            })
            .setNegativeButton("取消", null)
            .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            File b;
            Uri uri = data.getData();
            String path = Utils.getPath(this, uri);
            if (path != null) {
                b = new File(path);
                if (picNum <= 2) {
                    tmpPic[picNum] = b;
                    uploadPic[picNum] = b.getName();
                    picNum++;
                } else {
                    tmpPic[picNum - 1] = b;
                    uploadPic[picNum - 1] = b.getName();
                }
                showPic();
                uploadFile(b);
            } else {
                JUtils.Toast("获取照片失败");
            }
        } else {
            JUtils.Toast("请重新上传");
        }
    }

    private void getQiniuToken() {
        BaseApp.getTradeInteractor(this)
            .getQiniuToken(new ServiceResponse<QiniuTokenBean>(mBaseActivity) {
                @Override
                public void onNext(QiniuTokenBean resp) {
                    upToken = resp.getUptoken();
                }
            });
    }

    private void uploadFile(File file) {
        byte[] file_byte;
        try {
            Bitmap b = CameraUtils.decodeFile(file);
            file_byte = compressImage(b);
        } catch (Exception e) {
            e.printStackTrace();
            JUtils.Toast("文件压缩失败,请重新选择文件!");
            return;
        }
        String key = file.getName();
        String token = upToken;
        uploadManager.put(file_byte, key, token, (key1, info, res) -> {
        }, new UploadOptions(null, null, false, (key12, percent) -> {
        }, null));
    }

    public byte[] compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 300) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 5;
        }
        return baos.toByteArray();
    }

    private void showPic() {
        for (int i = 0; i < picNum; i++) {
            Bitmap bitmap = CameraUtils.decodeFile(tmpPic[i]);
            bitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
            if (i == 0) {
                b.imgProofPic1.setImageBitmap(bitmap);
                b.rlProofPic1.setVisibility(View.VISIBLE);
            } else if (i == 1) {
                b.imgProofPic2.setImageBitmap(bitmap);
                b.rlProofPic2.setVisibility(View.VISIBLE);
            } else if (i == 2) {
                b.imgProofPic3.setImageBitmap(bitmap);
                b.rlProofPic3.setVisibility(View.VISIBLE);
            }
        }

        if (picNum < 3) {
            for (int i = picNum; i < 3; i++) {
                if (i == 0) {
                    b.rlProofPic1.setVisibility(View.INVISIBLE);
                } else if (i == 1) {
                    b.rlProofPic2.setVisibility(View.INVISIBLE);
                } else if (i == 2) {
                    b.rlProofPic3.setVisibility(View.INVISIBLE);
                }
            }
        }
    }
}
