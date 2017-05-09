package com.danlai.nidepuzi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseWebViewActivity;
import com.danlai.nidepuzi.entity.EduBean;
import com.danlai.nidepuzi.util.JumpUtils;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author wisdom
 * @date 2017年05月09日 下午4:26
 */

public class EduAdapter extends RecyclerView.Adapter<EduAdapter.ViewHolder> {
    private BaseActivity mActivity;
    private List<EduBean.ResultsBean> data;

    public EduAdapter(BaseActivity activity) {
        mActivity = activity;
        data = new ArrayList<>();
    }

    public void updateWithClear(List<EduBean.ResultsBean> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void update(List<EduBean.ResultsBean> list) {
        data.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_edu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EduBean.ResultsBean bean = data.get(position);
        holder.title.setText(bean.getTitle());
        holder.desc.setText(bean.getDescription());
        holder.num.setText("参与人数" + bean.getNum_attender());
        Glide.with(mActivity).load(bean.getCover_image()).into(holder.img);
        holder.itemView.setOnClickListener(v -> JumpUtils.jumpToWebViewWithCookies(mActivity, bean.getContent_link(), -1, BaseWebViewActivity.class,
            bean.getTitle()+"测试测试测试测试测试测试测试测试测试测试测试测试测试测试", false));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.desc)
        TextView desc;
        @Bind(R.id.img)
        ImageView img;
        @Bind(R.id.num)
        TextView num;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
