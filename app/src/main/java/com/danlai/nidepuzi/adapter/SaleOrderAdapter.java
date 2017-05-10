package com.danlai.nidepuzi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.entity.OderCarryBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author wisdom-sun
 * @date 2017年05月10日 下午3:32
 */
public class SaleOrderAdapter extends RecyclerView.Adapter<SaleOrderAdapter.ShoppingListVH> {

    private List<OderCarryBean.ResultsEntity> mList;
    private Context mContext;

    public SaleOrderAdapter(Context context) {
        this.mContext = context;
        mList = new ArrayList<>();
    }

    public void updateWithClear(List<OderCarryBean.ResultsEntity> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void update(List<OderCarryBean.ResultsEntity> list) {

        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ShoppingListVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_sale_order, parent, false);
        return new ShoppingListVH(v);
    }

    @Override
    public void onBindViewHolder(ShoppingListVH holder, int position) {
        holder.flagRl.setVisibility(View.GONE);
        OderCarryBean.ResultsEntity resultsEntity = mList.get(position);
        try {
            if (position == 0) {
                showCategory(holder);
            } else {
                boolean theCategoryOfLastEqualsToThis = mList.get(position - 1)
                    .getDate_field()
                    .equals(mList.get(position).getDate_field());
                if (!theCategoryOfLastEqualsToThis) {
                    showCategory(holder);
                } else {
                    hideCategory(holder);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        holder.goodName.setText(resultsEntity.getSku_name());
        holder.shop_time.setText(resultsEntity.getDate_field());
        if (TextUtils.isEmpty(resultsEntity.getSku_img())) {
            Glide.with(mContext)
                .load(R.drawable.place_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.picPath);
        } else {
            ViewUtils.loadImgToImgView(mContext, holder.picPath, resultsEntity.getSku_img());
        }
        holder.getStatusDisplay.setText(resultsEntity.getStatus_display());
        holder.wx_nick.setText(resultsEntity.getContributor_nick());
        holder.timeDisplay.setText(resultsEntity.getCreated().substring(11, 16));
        holder.totalMoneyTv.setText("实付" + new DecimalFormat("0.0").format(resultsEntity.getOrder_value()));
        if (resultsEntity.getCarry_type() == 2) {
            holder.flagTv.setText("APP");
            holder.flagRl.setVisibility(View.VISIBLE);
        } else if (resultsEntity.getCarry_type() == 3 || resultsEntity.getCarry_type() == 4) {
            holder.flagTv.setText("下属订单");
            holder.flagRl.setVisibility(View.VISIBLE);
        } else {
            holder.flagRl.setVisibility(View.GONE);
        }
        holder.tvIncome.setText("收益" + JUtils.formatDouble(resultsEntity.getCarry_num()));
        holder.totalCash.setText("总收益 " + JUtils.formatDouble(resultsEntity.getToday_carry()));
    }

    private void showCategory(ShoppingListVH holder) {
        if (!isVisibleOf(holder.category)) holder.category.setVisibility(View.VISIBLE);
    }

    private void hideCategory(ShoppingListVH holder) {
        if (isVisibleOf(holder.category)) holder.category.setVisibility(View.GONE);
    }

    private boolean isVisibleOf(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ShoppingListVH extends RecyclerView.ViewHolder {
        @Bind(R.id.shop_time)
        TextView shop_time;
        @Bind(R.id.total_cash)
        TextView totalCash;
        @Bind(R.id.pic_path)
        ImageView picPath;
        @Bind(R.id.tv_time)
        TextView timeDisplay;
        @Bind(R.id.get_status_display)
        TextView getStatusDisplay;
        @Bind(R.id.wx_nick)
        TextView wx_nick;
        @Bind(R.id.tv_income)
        TextView tvIncome;
        @Bind(R.id.category)
        LinearLayout category;
        @Bind(R.id.content)
        LinearLayout content;
        @Bind(R.id.total_money)
        TextView totalMoneyTv;
        @Bind(R.id.flag)
        RelativeLayout flagRl;
        @Bind(R.id.flag_tv)
        TextView flagTv;
        @Bind(R.id.tv_good_name)
        TextView goodName;

        public ShoppingListVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
