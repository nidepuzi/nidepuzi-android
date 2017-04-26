package com.danlai.nidepuzi.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.CartsPayInfoBean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wisdom on 17/3/21.
 */

public class CartPayInfoAdapter extends BaseAdapter {
    private List<CartsPayInfoBean.CartListEntity> mList;
    private Activity activity;

    public CartPayInfoAdapter(Activity activity) {
        this.activity = activity;
        mList = new ArrayList<>();
    }

    public void update(List<CartsPayInfoBean.CartListEntity> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void updateWithClear(List<CartsPayInfoBean.CartListEntity> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_carts_pay, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CartsPayInfoBean.CartListEntity cartListEntity = mList.get(position);
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
                    Glide.with(activity)
                        .load(head_img)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .into(holder.cartImage);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Glide.with(activity)
                .load(picPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.cartImage);
        }
        holder.title.setText(cartListEntity.getTitle());
        holder.goodSize.setText("尺码:" + cartListEntity.getSkuName());
        holder.num.setText("x" + cartListEntity.getNum());
        holder.price.setText("¥" + new DecimalFormat("0.00").format(cartListEntity.getPrice()));
        return convertView;
    }

    private class ViewHolder {

        ImageView cartImage;
        TextView title, goodSize, price, num;

        public ViewHolder(View itemView) {
            cartImage = ((ImageView) itemView.findViewById(R.id.cart_image));
            title = (TextView) itemView.findViewById(R.id.title);
            goodSize = (TextView) itemView.findViewById(R.id.tx_good_size);
            price = (TextView) itemView.findViewById(R.id.price_tv);
            num = (TextView) itemView.findViewById(R.id.tv_num);
        }
    }
}
