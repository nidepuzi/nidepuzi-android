package com.danlai.nidepuzi.adapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseListViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemCartsPayBinding;
import com.danlai.nidepuzi.entity.CartsPayInfoBean.CartListEntity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by wisdom on 17/3/21.
 */

public class CartPayInfoAdapter extends BaseListViewAdapter<ItemCartsPayBinding, CartListEntity> {

    public CartPayInfoAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_carts_pay;
    }

    @Override
    protected void fillData(CartListEntity cartListEntity, BaseViewHolder<ItemCartsPayBinding> holder, int position) {
        String picPath = cartListEntity.getPicPath();
        String head_img;
        Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
        Matcher m = p.matcher(picPath);
        if (m.find()) {
            String group = m.group();
            String[] temp = picPath.split(group + "/");
            if (temp.length > 1) {
                try {
                    head_img = "http://" + group + "/"
                        + URLEncoder.encode(temp[1], "utf-8")
                        + "?imageMogr2/format/jpg/size-limit/30k/thumbnail/289/quality/90";
                    Glide.with(mActivity)
                        .load(head_img)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .into(holder.b.cartImage);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Glide.with(mActivity)
                .load(picPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.b.cartImage);
        }
        holder.b.title.setText(cartListEntity.getTitle());
        holder.b.txGoodSize.setText("尺码:" + cartListEntity.getSkuName());
        holder.b.tvNum.setText("x" + cartListEntity.getNum());
        holder.b.priceTv.setText("¥" + JUtils.formatDouble(cartListEntity.getPrice()));
    }
}
