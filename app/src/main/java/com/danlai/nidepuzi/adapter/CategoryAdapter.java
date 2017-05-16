package com.danlai.nidepuzi.adapter;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.danlai.library.utils.FileUtils;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.base.BaseActivity;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseRecyclerViewAdapter;
import com.danlai.nidepuzi.base.BaseViewHolder;
import com.danlai.nidepuzi.databinding.ItemCategoryBinding;
import com.danlai.nidepuzi.entity.CategoryBean;
import com.danlai.nidepuzi.ui.activity.product.CategoryProductActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.ArrayList;

import okhttp3.Call;

/**
 * @author wisdom
 * @date 2016年08月03日 上午11:14
 */
public class CategoryAdapter extends BaseRecyclerViewAdapter<ItemCategoryBinding, CategoryBean> {

    private String name;

    public CategoryAdapter(BaseActivity context, String name) {
        super(context);
        this.name = name;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_category;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemCategoryBinding> holder, int position) {
        CategoryBean bean = data.get(position);
        Glide.with(mActivity).load(R.drawable.place_holder).into(holder.b.img);
        holder.b.name.setText(bean.getName());
        String picAddress = BaseConst.BASE_DIR + "category/" + bean.getCid() + ".png";
        if (FileUtils.isFileExist(picAddress)) {
            Glide.with(mActivity).load(new File(picAddress)).crossFade().into(holder.b.img);
        } else {
            if (bean.getCat_pic() != null && !"".equals(bean.getCat_pic())) {
                OkHttpUtils.get().url(bean.getCat_pic()).build()
                    .execute(new FileCallBack(BaseConst.BASE_DIR + "category/", bean.getCid() + ".png") {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(File response, int id) {
                            if (!mActivity.isFinishing()) {
                                Glide.with(mActivity).load(new File(picAddress)).into(holder.b.img);
                            }
                        }
                    });
            }
        }
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, CategoryProductActivity.class);
            Bundle bundle = new Bundle();
            ArrayList<String> nameList = new ArrayList<>();
            ArrayList<String> cidList = new ArrayList<>();
            nameList.add(name);
            cidList.add(bean.getParent_cid());
            for (int i = 0; i < data.size(); i++) {
                nameList.add(data.get(i).getName());
                cidList.add(data.get(i).getCid());
            }
            bundle.putStringArrayList("name", nameList);
            bundle.putStringArrayList("cid", cidList);
            bundle.putInt("position", position + 1);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        });
    }
}
