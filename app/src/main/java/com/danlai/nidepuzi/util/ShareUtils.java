package com.danlai.nidepuzi.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.ActivityBean;
import com.danlai.nidepuzi.entity.ShareModelBean;

import java.text.DecimalFormat;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by wisdom on 17/3/9.
 */

public class ShareUtils {

    public static void shareWithModel(ShareModelBean shareModel, Activity activity) {
        View view = activity.getLayoutInflater().inflate(R.layout.share_product_layout, null);
        Dialog dialog = new Dialog(activity, R.style.CustomDialog);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        TextView price = (TextView) view.findViewById(R.id.price);
        TextView desc = (TextView) view.findViewById(R.id.desc);
        String min = new DecimalFormat("0.00").format(shareModel.getProfit().getMin());
        String max = new DecimalFormat("0.00").format(shareModel.getProfit().getMax());
        String priceStr = "赚" + min + "~" + max;
        price.setText(priceStr);
        String descStr = "只要你的好友通过你的链接购买此商品,你就能得到至少" + min + "元的利润哦~";
        SpannableStringBuilder spannable = new SpannableStringBuilder(descStr);
        spannable.setSpan(new ForegroundColorSpan(activity.getResources().getColor(R.color.colorAccent)),
            25, 25 + min.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        desc.setText(spannable);
        view.findViewById(R.id.qq_layout).setOnClickListener(v -> {
            dialog.dismiss();
            Platform plat = ShareSDK.getPlatform(QQ.NAME);
            showShare(plat.getName(), activity, shareModel);
        });
        view.findViewById(R.id.wx_layout).setOnClickListener(v -> {
            dialog.dismiss();
            Platform plat = ShareSDK.getPlatform(Wechat.NAME);
            showShare(plat.getName(), activity, shareModel);
        });
        view.findViewById(R.id.moment_layout).setOnClickListener(v -> {
            dialog.dismiss();
            Platform plat = ShareSDK.getPlatform(WechatMoments.NAME);
            showShare(plat.getName(), activity, shareModel);
        });
        view.findViewById(R.id.zone_layout).setOnClickListener(v -> {
            dialog.dismiss();
            Platform plat = ShareSDK.getPlatform(QZone.NAME);
            showShare(plat.getName(), activity, shareModel);
        });
        view.findViewById(R.id.sina_layout).setOnClickListener(v -> {
            dialog.dismiss();
            Platform plat = ShareSDK.getPlatform(SinaWeibo.NAME);
            showShare(plat.getName(), activity, shareModel);
        });
        view.findViewById(R.id.copy_layout).setOnClickListener(v -> {
            dialog.dismiss();
            JUtils.copyToClipboard(shareModel.getShare_link());
            JUtils.Toast("已复制链接");
        });
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        window.setWindowAnimations(R.style.BottomDialogAnim);
        dialog.show();
    }

    private static void showShare(String platform, Context context, ShareModelBean shareModel) {
        OnekeyShare oks = new OnekeyShare();
        oks.setPlatform(platform);
        oks.disableSSOWhenAuthorize();
        oks.setTitle(shareModel.getTitle());
        oks.setTitleUrl(shareModel.getShare_link());
        oks.setText(shareModel.getDesc());
        oks.setImageUrl(shareModel.getShare_img());
        oks.setUrl(shareModel.getShare_link());
        oks.setSite("小鹿美美");
        oks.setSiteUrl("http://m.nidepuzi.com/mall/");
        oks.show(context);
    }

    public static void shareActivity(ActivityBean partyShareInfo, Activity activity){
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(partyShareInfo.getTitle());
        oks.setTitleUrl(partyShareInfo.getShareLink());
        oks.setText(partyShareInfo.getActiveDec());
        oks.setImageUrl(partyShareInfo.getShareIcon());
        oks.setUrl(partyShareInfo.getShareLink());
        Bitmap enableLogo =
            BitmapFactory.decodeResource(activity.getResources(), R.drawable.ssdk_oks_logo_copy);
        View.OnClickListener listener = v -> {
            JUtils.copyToClipboard(partyShareInfo.getShareLink() + "");
            JUtils.Toast("已复制链接");
        };
        oks.setCustomerLogo(enableLogo, "复制链接", listener);
        oks.show(activity);
    }
}
