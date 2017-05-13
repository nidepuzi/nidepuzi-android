package com.danlai.nidepuzi.adapter;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.danlai.library.entities.FilePara;
import com.danlai.library.utils.CameraUtils;
import com.danlai.library.utils.FileUtils;
import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.image.MultiImageView;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.entity.NinePicBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.service.okhttp3.FileParaCallback;
import com.danlai.nidepuzi.ui.activity.shop.ImagePagerActivity;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;

/**
 * @author wisdom
 * @date 16/6/7 上午09:45
 */
public class NinePicAdapter extends BaseAdapter {
    private BaseActivity mContext;
    private List<NinePicBean> mList;
    private List<Uri> mFiles;

    public NinePicAdapter(BaseActivity mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
        mFiles = new ArrayList<>();
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    public void update(List<NinePicBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NinePicBean ninePicBean = mList.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_ninepic, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String description = ninePicBean.getDescription();
        holder.titleTv.setText(ninePicBean.getTitle_content());
        holder.contentTv.setText(description);
        holder.timeTv.setText(ninePicBean.getStartTime().replace("T", " "));
        holder.likeTv.setText(ninePicBean.getSave_times() + "");
        holder.shareTv.setText(ninePicBean.getSave_times() + "");
        List<String> picArray = new ArrayList<>();
        final int nineId = ninePicBean.getId();
        if (ninePicBean.getPicArry() != null && ninePicBean.getPicArry().size() > 0) {
            for (int i = 0; i < ninePicBean.getPicArry().size(); i++) {
                String picUrl = ninePicBean.getPicArry().get(i);
                if (picUrl != null && !"".equals(picUrl)
                    && picUrl.length() > 20) {
                    picArray.add(picUrl);
                }
            }
            if (picArray.size() > 0) {
                holder.multiImageView.setVisibility(View.VISIBLE);
                holder.multiImageView.setList(picArray);
                holder.multiImageView.setOnItemClickListener((view, currentPosition) ->
                    ImagePagerActivity.startImagePagerActivity(mContext, picArray, currentPosition));
            } else {
                holder.multiImageView.setVisibility(View.GONE);
            }
        } else {
            holder.multiImageView.setVisibility(View.GONE);
        }
        holder.save.setOnClickListener(v -> {
            BaseApp.getVipInteractor(mContext)
                .saveTime(ninePicBean.getId(), 1, new ServiceResponse<>(mContext));
            if (mContext instanceof BaseMVVMActivity) {
                mContext.showIndeterminateProgressDialog(true);
            }
            if (JUtils.isPermission(mContext, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                mFiles.clear();
                JUtils.copyToClipboard(description);
                if (picArray.size() > 0) {
                    downloadNinepic(picArray, description, nineId);
                } else {
                    shareToWx(description);
                }
                JUtils.ToastLong("努力分享中,请稍等几秒钟...");
            } else {
                hideLoading();
                Intent localIntent = new Intent();
                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
                mContext.startActivity(localIntent);
                JUtils.Toast("你的铺子需要存储权限存储图片,请打开存储权限再次操作.");
            }
        });
        holder.copy.setOnClickListener(v -> {
            JUtils.copyToClipboard(description);
            JUtils.Toast("文案复制成功啦~可以发送给您的好友哦!");
        });
        return convertView;
    }

    private void downloadNinepic(List<String> picArry, String desc, int nineId) {
        new Thread(() -> {
            for (int i = 0; i < picArry.size(); i++) {
                String str = picArry.get(i);
                String fileName = "/nine_pic" + nineId + "_" + i;
                String newFileName = Environment.getExternalStorageDirectory() +
                    CameraUtils.NIDEPUZI_IMG_PATH + fileName + ".jpg";
                if (FileUtils.isFileExist(newFileName)) {
                    Uri uri;
                    uri = Uri.fromFile(new File(newFileName));
                    mFiles.add(uri);
                    if (mFiles.size() == picArry.size()) {
                        mContext.runOnUiThread(() -> shareToWx(desc));
                    }
                } else {
                    final int finalI = i;
                    OkHttpUtils.get()
                        .url(str)
                        .build()
                        .execute(new FileParaCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                hideLoading();
                                JUtils.Toast("分享失败,请重新分享!");
                            }

                            @Override
                            public void onResponse(FilePara response, int id) {
                                if (response != null) {
                                    try {
                                        String pic_indicate_name;
                                        pic_indicate_name = "/nine_pic" + nineId + "_" + finalI;
                                        String newName = Environment.getExternalStorageDirectory() +
                                            CameraUtils.NIDEPUZI_IMG_PATH + pic_indicate_name + ".jpg";
                                        if (FileUtils.isFileExist(newName)) {
                                            FileUtils.deleteFile(newName);
                                        }
                                        File file = new File(response.getFilePath());
                                        file.renameTo(new File(newName));
                                        Uri uri = Uri.fromFile(new File(newName));
                                        mFiles.add(uri);
                                        Intent scannerIntent =
                                            new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                                        scannerIntent.setData(uri);
                                        mContext.sendBroadcast(scannerIntent);
                                        if (mFiles.size() == picArry.size()) {
                                            shareToWx(desc);
                                        }
                                    } catch (Exception e) {
                                        hideLoading();
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                }
            }
        }).start();
    }

    private void hideLoading() {
        if (mContext != null) {
            mContext.hideIndeterminateProgressDialog();
        }
    }

    private void shareToWx(String desc) {
        hideLoading();
        ArrayList<Uri> imageUris = new ArrayList<>();
        Collections.sort(mFiles);
        for (int i1 = 0; i1 < mFiles.size(); i1++) {
            if (i1 < 9) {
                imageUris.add(mFiles.get(i1));
            }
        }
        try {
            if (imageUris.size() > 0) {
                Intent intent = new Intent();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                    intent.setType("image/*");
                    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                    mContext.startActivity(Intent.createChooser(intent, "分享"));
                } else {
                    ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
                    intent.setComponent(comp);
                    intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                    intent.setType("image/*");
                    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                    intent.putExtra("Kdescription", desc);
                    mContext.startActivity(intent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof ActivityNotFoundException) {
                JUtils.Toast("您手机没有安装微信客户端,图片已保存到本地,请手动分享!");
            } else {
                JUtils.Toast("分享失败,文案已复制，图片已保存，可以手动分享到朋友圈!");
            }
        }
    }

    class ViewHolder {
        MultiImageView multiImageView;
        TextView timeTv, contentTv, titleTv, likeTv, shareTv;
        Button save, copy;

        public ViewHolder(View itemView) {
            timeTv = (TextView) itemView.findViewById(R.id.time_tv);
            contentTv = (TextView) itemView.findViewById(R.id.contentTv);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            likeTv = (TextView) itemView.findViewById(R.id.like_tv);
            shareTv = (TextView) itemView.findViewById(R.id.share_tv);
            save = (Button) itemView.findViewById(R.id.save);
            copy = (Button) itemView.findViewById(R.id.copy);
            ViewStub linkOrImgViewStub = (ViewStub) itemView.findViewById(R.id.view_stub);
            linkOrImgViewStub.setLayoutResource(R.layout.viewstub_img_body);
            linkOrImgViewStub.inflate();
            multiImageView = (MultiImageView) itemView.findViewById(R.id.multi_image);
        }
    }
}
