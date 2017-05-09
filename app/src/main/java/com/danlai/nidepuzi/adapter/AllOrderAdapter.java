package com.danlai.nidepuzi.adapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.entity.OrderContent;
import com.danlai.nidepuzi.entity.OrderFooter;
import com.danlai.nidepuzi.entity.OrderHead;
import com.danlai.nidepuzi.ui.activity.trade.OrderDetailActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author wisdom
 * @date 2017年05月05日 上午11:22
 */

public class AllOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEAD = 221;
    private static final int TYPE_CONTENT = 222;
    private static final int TYPE_FOOTER = 223;
    private BaseActivity mActivity;
    private List<Object> data;
    private boolean isShow;
    private boolean isAchievement;
    private boolean isShare;

    public AllOrderAdapter(BaseActivity activity) {
        mActivity = activity;
        data = new ArrayList<>();
        isShow = false;
        isShare = false;
        isAchievement = false;
    }

    public void updateWithClear(List<Object> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void update(List<Object> list) {
        data.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_order_head, parent, false);
            return new HeadHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_order_footer, parent, false);
            return new FooterHolder(view);
        } else if (viewType == TYPE_CONTENT) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_order_content, parent, false);
            return new ContentHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object o = data.get(position);
        if (holder instanceof HeadHolder) {
            initHead((HeadHolder) holder, (OrderHead) o);
        } else if (holder instanceof FooterHolder) {
            initFooter((FooterHolder) holder, (OrderFooter) o);
        } else if (holder instanceof ContentHolder) {
            initContent((ContentHolder) holder, (OrderContent) o);
        }
    }

    private void initContent(ContentHolder holder, OrderContent bean) {
        if (isShow) {
            holder.ivSave.setVisibility(View.VISIBLE);
            holder.tvSave.setVisibility(View.VISIBLE);
        } else {
            holder.ivSave.setVisibility(View.GONE);
            holder.tvSave.setVisibility(View.GONE);
        }
        if (isShare) {
            holder.ivSave.setImageResource(R.drawable.icon_earn);
        } else {
            holder.ivSave.setImageResource(R.drawable.icon_save);
        }
        holder.tvName.setText(bean.getName());
        holder.tvSize.setText("规格:" + bean.getSize());
        holder.tvPrice.setText("单价:" + bean.getPrice() + "  x" + bean.getNum());
        ViewUtils.loadImgToImgViewWithPlaceholder(mActivity, holder.ivGood, bean.getUrl());
        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("order_id", bean.getOrderId());
            mActivity.readyGo(OrderDetailActivity.class, bundle);
        });
    }

    private void initFooter(FooterHolder holder, OrderFooter bean) {
        if (isShow && !isAchievement) {
            holder.layoutFooter.setVisibility(View.VISIBLE);
            holder.footerLine.setVisibility(View.VISIBLE);
            holder.btnDetail.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt("order_id", bean.getOrderId());
                mActivity.readyGo(OrderDetailActivity.class, bundle);
            });
            switch (bean.getStatus()) {
                case BaseConst.ORDER_STATE_WAITPAY:
                    holder.btnDelete.setVisibility(View.VISIBLE);
                    holder.btnDelete.setText("去付款");
                    holder.btnDelete.setTextColor(Color.WHITE);
                    holder.btnDelete.setBackgroundResource(R.drawable.btn_common_default);
                    holder.btnDelete.setOnClickListener(v -> {
                        Bundle bundle = new Bundle();
                        bundle.putInt("order_id", bean.getOrderId());
                        mActivity.readyGo(OrderDetailActivity.class, bundle);
                    });
                    break;
                case BaseConst.ORDER_STATE_SENDED:
                    holder.btnDelete.setVisibility(View.GONE);
                    holder.btnDetail.setText("物流/详情");
                    break;
                case BaseConst.ORDER_STATE_TRADE_CLOSE:
                    holder.btnDelete.setVisibility(View.VISIBLE);
                    holder.btnDelete.setText("删除");
                    holder.btnDelete.setTextColor(mActivity.getResources().getColor(R.color.color_33));
                    holder.btnDelete.setBackgroundResource(R.drawable.btn_common_black);
                    holder.btnDelete.setOnClickListener(v -> JUtils.Toast("暂不支持删除订单功能!"));
                    break;
                default:
                    holder.btnDelete.setVisibility(View.GONE);
                    break;
            }
        } else {
            holder.layoutFooter.setVisibility(View.GONE);
            holder.footerLine.setVisibility(View.GONE);
            holder.btnDetail.setOnClickListener(null);
        }
        holder.tvPayment.setText("￥" + JUtils.formatDouble(bean.getPayment()));
        holder.tvPost.setText("(运费:￥" + JUtils.formatDouble(bean.getPostFee()) + ")");
    }

    private void initHead(HeadHolder holder, OrderHead bean) {
        holder.tvTime.setText(bean.getTime().replaceAll("T", " ").substring(0, 19));
        holder.tvStatus.setText(bean.getStatus());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object o = data.get(position);
        if (o instanceof OrderHead) {
            return TYPE_HEAD;
        } else if (o instanceof OrderFooter) {
            return TYPE_FOOTER;
        } else if (o instanceof OrderContent) {
            return TYPE_CONTENT;
        } else {
            return super.getItemViewType(position);
        }
    }

    public void setShow(boolean show) {
        isShow = show;
        notifyDataSetChanged();
    }

    public void setShare(boolean share) {
        isShare = share;
        notifyDataSetChanged();
    }

    public void setAchievement(boolean achievement) {
        isAchievement = achievement;
        notifyDataSetChanged();
    }

    class HeadHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_status)
        TextView tvStatus;

        HeadHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_payment)
        TextView tvPayment;
        @Bind(R.id.tv_post)
        TextView tvPost;
        @Bind(R.id.footer_line)
        View footerLine;
        @Bind(R.id.layout_footer)
        LinearLayout layoutFooter;
        @Bind(R.id.btn_delete)
        TextView btnDelete;
        @Bind(R.id.btn_detail)
        TextView btnDetail;

        FooterHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ContentHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_good)
        ImageView ivGood;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_size)
        TextView tvSize;
        @Bind(R.id.tv_price)
        TextView tvPrice;
        @Bind(R.id.iv_save)
        ImageView ivSave;
        @Bind(R.id.tv_save)
        TextView tvSave;

        ContentHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
