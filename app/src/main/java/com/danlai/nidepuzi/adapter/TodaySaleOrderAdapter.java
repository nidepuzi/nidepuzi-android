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
import com.danlai.nidepuzi.databinding.ItemSaleOrderBinding;
import com.danlai.nidepuzi.entity.OrderCarryBean.ResultsEntity;

import java.text.DecimalFormat;

/**
 * @author wisdom
 * @date 2017年05月23日 下午3:08
 */

public class TodaySaleOrderAdapter extends BaseRecyclerViewAdapter<ItemSaleOrderBinding, ResultsEntity> {

    public TodaySaleOrderAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_sale_order;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemSaleOrderBinding> holder, int position) {
        ResultsEntity resultsEntity = data.get(position);
        holder.b.tvGoodName.setText(resultsEntity.getSku_name());
//        holder.b.shopTime.setText(resultsEntity.getCreated().replaceAll("T", " ").substring(0, 19));
        holder.b.category.setVisibility(View.GONE);
        if (TextUtils.isEmpty(resultsEntity.getSku_img())) {
            Glide.with(mActivity)
                .load(R.drawable.place_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.b.picPath);
        } else {
            ViewUtils.loadImgToImgView(mActivity, holder.b.picPath, resultsEntity.getSku_img());
        }
        holder.b.getStatusDisplay.setText(resultsEntity.getStatus_display());
        holder.b.wxNick.setText(resultsEntity.getContributor_nick());
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
        holder.b.tvTime.setText(resultsEntity.getCreated().substring(11, 16));
        holder.b.tvIncome.setText("收益" + JUtils.formatDouble(resultsEntity.getCarry_num()));
    }
}
