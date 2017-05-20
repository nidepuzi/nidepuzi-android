package com.danlai.nidepuzi.adapter;

import android.app.Activity;
import android.content.Intent;

import com.bumptech.glide.Glide;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemBankListBinding;
import com.danlai.nidepuzi.entity.BankListEntity.BanksBean;

/**
 * @author wisdom
 * @date 2017年05月19日 下午3:44
 */

public class BankListAdapter extends BaseRecyclerViewAdapter<ItemBankListBinding, BanksBean> {
    public BankListAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_bank_list;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemBankListBinding> holder, int position) {
        BanksBean bean = data.get(position);
        Glide.with(mActivity).load(bean.getBank_img()).into(holder.b.img);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("name", bean.getBank_name());
            intent.putExtra("img", bean.getBank_img());
            mActivity.setResult(Activity.RESULT_OK, intent);
            mActivity.finish();
        });
    }
}
