package com.danlai.nidepuzi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.entity.CouponEntity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author wisdom
 * @date 2017年04月15日 上午9:34
 */

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {
    private BaseActivity mContext;
    private List<CouponEntity> mList;
    private int mCouponTyp;
    private String mSelectCouponId;
    private boolean isUsable = false;

    public CouponAdapter(BaseActivity context, int type) {
        mContext = context;
        mCouponTyp = type;
        mList = new ArrayList<>();
    }

    public void updateWithClear(List<CouponEntity> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void update(List<CouponEntity> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_coupon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CouponEntity bean = mList.get(position);
        if (mCouponTyp == BaseConst.UNUSED_COUPON) {
            holder.mCouponValue.setTextColor(Color.parseColor("#F05050"));
            holder.rl.setBackgroundResource(R.drawable.bg_img_coupon);
        } else if (mCouponTyp == BaseConst.PAST_COUPON) {
            holder.mCouponValue.setTextColor(Color.parseColor("#B4B4B4"));
            holder.mTitle.setTextColor(Color.parseColor("#D2D2D2"));
            holder.mTime.setTextColor(Color.parseColor("#D2D2D2"));
            holder.mDesc.setTextColor(Color.parseColor("#D2D2D2"));
            holder.mUseFee.setTextColor(Color.parseColor("#D2D2D2"));
            holder.rl.setBackgroundResource(R.drawable.bg_img_pastcoupon);
        } else if (mCouponTyp == BaseConst.USED_COUPON) {
            holder.mCouponValue.setTextColor(Color.parseColor("#646464"));
            holder.rl.setBackgroundResource(R.drawable.bg_img_usedcoupon);
        } else {
            holder.mCouponValue.setTextColor(Color.parseColor("#646464"));
            holder.rl.setBackgroundResource(R.drawable.bg_img_dcoupon);
        }
        double coupon_value = bean.getCoupon_value();
        double format = Double.parseDouble(JUtils.formatDouble(coupon_value));
        if (Math.round(format * 100) % 100 == 0) {
            holder.mCouponValue.setText("￥" + Math.round(format * 100) / 100);
        } else {
            holder.mCouponValue.setText("￥" + format);
        }
        holder.mTitle.setText(bean.getTitle());
        holder.mDesc.setText(bean.getPros_desc());
        String start_time = bean.getStart_time();
        String deadline = bean.getDeadline();
        String created = bean.getCreated();
        if (start_time != null) {
            holder.mTime.setText("期限  " + start_time.replaceAll("T", " ")
                + "  至  " + deadline.replaceAll("T", " "));
        } else {
            holder.mTime.setText("期限  " + created.replaceAll("T", " ")
                + "  至  " + deadline.replaceAll("T", " "));
        }
        holder.mUseFee.setText(bean.getUse_fee_des());
        if ((mSelectCouponId != null) && (!mSelectCouponId.isEmpty())
            && mSelectCouponId.equals(Integer.toString(bean.getId()))) {
            holder.mImageSelect.setVisibility(View.VISIBLE);
        } else {
            holder.mImageSelect.setVisibility(View.INVISIBLE);
        }
        if (isUsable) {
            holder.itemView.setOnClickListener(v -> {
                String coupon_id = bean.getId() + "";
                Intent intent = new Intent();
                double value = bean.getCoupon_value();
                intent.putExtra("coupon_id", coupon_id);
                intent.putExtra("coupon_price", value);
                mContext.setResult(Activity.RESULT_OK, intent);
                mContext.finish();
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setSelectCouponId(String selectCouponId) {
        mSelectCouponId = selectCouponId;
    }

    public void setInfo(boolean usable) {
        isUsable = usable;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.rl)
        RelativeLayout rl;
        @Bind(R.id.tv_coupon_value)
        TextView mCouponValue;
        @Bind(R.id.title)
        TextView mDesc;
        @Bind(R.id.use_fee)
        TextView mUseFee;
        @Bind(R.id.img_selected)
        ImageView mImageSelect;
        @Bind(R.id.tv_coupon_info)
        TextView mTitle;
        @Bind(R.id.tv_coupon_crttime)
        TextView mTime;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
