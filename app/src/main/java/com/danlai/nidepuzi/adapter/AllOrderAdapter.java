package com.danlai.nidepuzi.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.ViewUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemOrderContentBinding;
import com.danlai.nidepuzi.databinding.ItemOrderFooterBinding;
import com.danlai.nidepuzi.databinding.ItemOrderHeadBinding;
import com.danlai.nidepuzi.entity.OrderContent;
import com.danlai.nidepuzi.entity.OrderFooter;
import com.danlai.nidepuzi.entity.OrderHead;
import com.danlai.nidepuzi.ui.activity.trade.OrderDetailActivity;

import java.util.ArrayList;
import java.util.List;

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

    public AllOrderAdapter(BaseActivity activity) {
        mActivity = activity;
        data = new ArrayList<>();
        isShow = false;
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
            ItemOrderHeadBinding b = DataBindingUtil.inflate(LayoutInflater.from(mActivity), R.layout.item_order_head, parent, false);
            return new HeadHolder(b);
        } else if (viewType == TYPE_FOOTER) {
            ItemOrderFooterBinding b = DataBindingUtil.inflate(LayoutInflater.from(mActivity), R.layout.item_order_footer, parent, false);
            return new FooterHolder(b);
        } else if (viewType == TYPE_CONTENT) {
            ItemOrderContentBinding b = DataBindingUtil.inflate(LayoutInflater.from(mActivity), R.layout.item_order_content, parent, false);
            return new ContentHolder(b);
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
        // TODO: 17/5/11 暂时隐藏
//        if (isShow) {
//            holder.b.ivSave.setVisibility(View.VISIBLE);
//            holder.b.tvSave.setVisibility(View.VISIBLE);
//        } else {
//            holder.b.ivSave.setVisibility(View.GONE);
//            holder.b.tvSave.setVisibility(View.GONE);
//        }
        holder.b.ivSave.setImageResource(R.drawable.icon_save);
        holder.b.tvName.setText(bean.getName());
        holder.b.tvSize.setText("规格:" + bean.getSize());
        holder.b.tvPrice.setText("单价:" + bean.getPrice() + "  x" + bean.getNum());
        ViewUtils.loadImgToImgViewWithPlaceholder(mActivity, holder.b.ivGood, bean.getUrl());
        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("order_id", bean.getOrderId());
            mActivity.readyGo(OrderDetailActivity.class, bundle);
        });
    }

    private void initFooter(FooterHolder holder, OrderFooter bean) {
        if (isShow) {
            holder.b.layoutFooter.setVisibility(View.VISIBLE);
            holder.b.footerLine.setVisibility(View.VISIBLE);
            holder.b.btnDetail.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt("order_id", bean.getOrderId());
                mActivity.readyGo(OrderDetailActivity.class, bundle);
            });
            switch (bean.getStatus()) {
                case BaseConst.ORDER_STATE_WAITPAY:
                    holder.b.btnDelete.setVisibility(View.VISIBLE);
                    holder.b.btnDelete.setText("去付款");
                    holder.b.btnDelete.setTextColor(Color.WHITE);
                    holder.b.btnDelete.setBackgroundResource(R.drawable.btn_common_default);
                    holder.b.btnDelete.setOnClickListener(v -> {
                        Bundle bundle = new Bundle();
                        bundle.putInt("order_id", bean.getOrderId());
                        mActivity.readyGo(OrderDetailActivity.class, bundle);
                    });
                    break;
                case BaseConst.ORDER_STATE_SENDED:
                    holder.b.btnDelete.setVisibility(View.GONE);
                    holder.b.btnDetail.setText("物流/详情");
                    break;
                case BaseConst.ORDER_STATE_TRADE_CLOSE:
                    // TODO: 17/5/11 暂时隐藏
                    holder.b.btnDelete.setVisibility(View.GONE);
                    holder.b.btnDelete.setText("删除");
                    holder.b.btnDelete.setTextColor(mActivity.getResources().getColor(R.color.color_33));
                    holder.b.btnDelete.setBackgroundResource(R.drawable.btn_common_black);
                    holder.b.btnDelete.setOnClickListener(v -> JUtils.Toast("暂不支持删除订单功能!"));
                    break;
                default:
                    holder.b.btnDelete.setVisibility(View.GONE);
                    break;
            }
        } else {
            holder.b.layoutFooter.setVisibility(View.GONE);
            holder.b.footerLine.setVisibility(View.GONE);
            holder.b.btnDetail.setOnClickListener(null);
        }
        holder.b.tvPayment.setText("￥" + JUtils.formatDouble(bean.getPayment()));
        holder.b.tvPost.setText("(运费:￥" + JUtils.formatDouble(bean.getPostFee()) + ")");
    }

    private void initHead(HeadHolder holder, OrderHead bean) {
        holder.b.tvTime.setText(bean.getTime().replaceAll("T", " ").substring(0, 19));
        holder.b.tvStatus.setText(bean.getStatus());
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

    private class HeadHolder extends BaseViewHolder<ItemOrderHeadBinding> {

        HeadHolder(ItemOrderHeadBinding itemOrderHeadBinding) {
            super(itemOrderHeadBinding);
        }
    }

    private class FooterHolder extends BaseViewHolder<ItemOrderFooterBinding> {

        FooterHolder(ItemOrderFooterBinding itemOrderFooterBinding) {
            super(itemOrderFooterBinding);
        }
    }

    private class ContentHolder extends BaseViewHolder<ItemOrderContentBinding> {

        ContentHolder(ItemOrderContentBinding itemOrderContentBinding) {
            super(itemOrderContentBinding);
        }
    }
}
