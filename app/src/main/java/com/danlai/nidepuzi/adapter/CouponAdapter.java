package com.danlai.nidepuzi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemCouponBinding;
import com.danlai.nidepuzi.entity.CouponEntity;

/**
 * @author wisdom
 * @date 2017年04月15日 上午9:34
 */

public class CouponAdapter extends BaseRecyclerViewAdapter<ItemCouponBinding, CouponEntity> {
    private int mCouponTyp;
    private String mSelectCouponId;
    private boolean isUsable = false;

    public CouponAdapter(BaseActivity mActivity, int type) {
        super(mActivity);
        mCouponTyp = type;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemCouponBinding> holder, int position) {
        CouponEntity bean = data.get(position);
        if (mCouponTyp == BaseConst.UNUSED_COUPON) {
            holder.b.tvCouponValue.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
            holder.b.tvCouponMoney.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
            holder.b.name.setTextColor(mActivity.getResources().getColor(R.color.white));
            holder.b.rl.setBackgroundResource(R.drawable.img_coupon_unused);
            holder.b.imgFlag.setImageResource(R.drawable.icon_coupon_use);
        } else if (mCouponTyp == BaseConst.FREEZE_COUPON) {
            holder.b.imgFlag.setImageResource(R.drawable.icon_coupon_normal);
        } else if (mCouponTyp == BaseConst.PAST_COUPON) {
            holder.b.imgFlag.setImageResource(R.drawable.icon_coupon_past);
        } else if (mCouponTyp == BaseConst.USED_COUPON) {
            holder.b.imgFlag.setImageResource(R.drawable.icon_coupon_used);
        } else if (mCouponTyp == BaseConst.DISABLE_COUPON) {
            holder.b.imgFlag.setImageResource(R.drawable.icon_coupon_normal);
        }
        double coupon_value = bean.getCoupon_value();
        double format = Double.parseDouble(JUtils.formatDouble(coupon_value));
        if (Math.round(format * 100) % 100 == 0) {
            holder.b.tvCouponValue.setText("" + Math.round(format * 100) / 100);
        } else {
            holder.b.tvCouponValue.setText("" + format);
        }
        holder.b.title.setText(bean.getPros_desc());
        String deadline = bean.getDeadline();
        holder.b.tvCouponTime.setText("有效期至:  " + deadline.substring(0, 10));
        holder.b.useFee.setText(bean.getUse_fee_des());
        if ((mSelectCouponId != null) && (!mSelectCouponId.isEmpty())
            && mSelectCouponId.equals(Integer.toString(bean.getId()))) {
            holder.b.imgSelected.setVisibility(View.VISIBLE);
        } else {
            holder.b.imgSelected.setVisibility(View.INVISIBLE);
        }
        if (isUsable) {
            holder.itemView.setOnClickListener(v -> {
                String coupon_id = bean.getId() + "";
                Intent intent = new Intent();
                double value = bean.getCoupon_value();
                intent.putExtra("coupon_id", coupon_id);
                intent.putExtra("coupon_price", value);
                mActivity.setResult(Activity.RESULT_OK, intent);
                mActivity.finish();
            });
        }
    }

    public void setSelectCouponId(String selectCouponId) {
        mSelectCouponId = selectCouponId;
    }

    public void setInfo(boolean usable) {
        isUsable = usable;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_coupon;
    }
}
