package com.danlai.nidepuzi.adapter;

import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemOrderAchieveBinding;
import com.danlai.nidepuzi.entity.OrderCarryBean.ResultsEntity;

import java.text.DecimalFormat;

/**
 * @author wisdom
 * @date 2017年05月26日 下午4:31
 */

public class OrderAchieveAdapter extends BaseRecyclerViewAdapter<ItemOrderAchieveBinding, ResultsEntity> {

    public OrderAchieveAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_order_achieve;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemOrderAchieveBinding> holder, int position) {
        holder.b.flag.setVisibility(View.GONE);
        ResultsEntity resultsEntity = data.get(position);
        try {
            if (position == 0) {
                showCategory(holder);
            } else {
                boolean theCategoryOfLastEqualsToThis = data.get(position - 1)
                    .getDate_field()
                    .equals(data.get(position).getDate_field());
                if (!theCategoryOfLastEqualsToThis) {
                    showCategory(holder);
                } else {
                    hideCategory(holder);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        holder.b.tvGoodName.setText(resultsEntity.getSku_name());
        holder.b.shopTime.setText(resultsEntity.getDate_field());
        if (TextUtils.isEmpty(resultsEntity.getSku_img())) {
            Glide.with(mActivity)
                .load(R.drawable.place_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.b.picPath);
        } else {
            ViewUtils.loadImgToImgView(mActivity, holder.b.picPath, resultsEntity.getSku_img());
        }
        holder.b.wxNick.setText(resultsEntity.getContributor_nick());
        holder.b.tvTime.setText(resultsEntity.getCreated().substring(11, 16));
        holder.b.totalMoney.setText("实付" + new DecimalFormat("0.0").format(resultsEntity.getOrder_value()));
        if (resultsEntity.getCarry_type() == 2) {
            holder.b.flagTv.setText("APP");
            holder.b.flag.setVisibility(View.VISIBLE);
        } else if (resultsEntity.getCarry_type() == 3 || resultsEntity.getCarry_type() == 4) {
            holder.b.flagTv.setText("下属订单");
            holder.b.flag.setVisibility(View.VISIBLE);
        } else {
            holder.b.flag.setVisibility(View.GONE);
        }
        holder.b.tvIncome.setText("收益" + JUtils.formatDouble(resultsEntity.getCarry_num()));
        holder.b.totalCash.setText("总收益 " + JUtils.formatDouble(resultsEntity.getToday_carry()));
    }

    private void showCategory(BaseViewHolder<ItemOrderAchieveBinding> holder) {
        if (!isVisibleOf(holder.b.category)) holder.b.category.setVisibility(View.VISIBLE);
    }

    private void hideCategory(BaseViewHolder<ItemOrderAchieveBinding> holder) {
        if (isVisibleOf(holder.b.category)) holder.b.category.setVisibility(View.GONE);
    }

    private boolean isVisibleOf(View view) {
        return view.getVisibility() == View.VISIBLE;
    }
}
