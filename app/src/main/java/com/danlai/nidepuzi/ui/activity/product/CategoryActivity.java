package com.danlai.nidepuzi.ui.activity.product;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;

import com.danlai.library.utils.FileUtils;
import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.SpaceItemDecoration;
import com.danlai.nidepuzi.BaseApp;
import com.danlai.nidepuzi.R;
import com.danlai.nidepuzi.adapter.CategoryItemAdapter;
import com.danlai.nidepuzi.adapter.CategoryNameListAdapter;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityCategoryBinding;
import com.danlai.nidepuzi.entity.CategoryBean;
import com.danlai.nidepuzi.entity.CategoryDownBean;
import com.danlai.nidepuzi.service.ServiceResponse;
import com.danlai.nidepuzi.task.CategoryListTask;
import com.danlai.nidepuzi.task.CategoryTask;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * @author wisdom
 * @date 2017年04月22日 下午5:47
 */

public class CategoryActivity extends BaseMVVMActivity<ActivityCategoryBinding> implements AdapterView.OnItemClickListener, View.OnClickListener {
    private CategoryItemAdapter adapter;
    private CategoryNameListAdapter mCategoryNameListAdapter;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_category;
    }

    @Override
    public View getLoadingView() {
        return b.layout;
    }

    @Override
    protected void initViews() {
        mCategoryNameListAdapter = new CategoryNameListAdapter(mBaseActivity);
        b.lv.setAdapter(mCategoryNameListAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(mBaseActivity, LinearLayoutManager.VERTICAL, false);
        b.xrv.setLayoutManager(manager);
        b.xrv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        b.xrv.addItemDecoration(new SpaceItemDecoration(10));
        b.xrv.setPullRefreshEnabled(false);
        b.xrv.setLoadingMoreEnabled(false);

        adapter = new CategoryItemAdapter(mBaseActivity);
        b.xrv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        showIndeterminateProgressDialog(false);
        downloadJson("");
    }

    private void downloadJson(String cid) {
        BaseApp.getMainInteractor(mBaseActivity)
            .getCategoryDown(new ServiceResponse<CategoryDownBean>(mBaseActivity) {
                @Override
                public void onNext(CategoryDownBean categoryDownBean) {
                    if (categoryDownBean != null) {
                        String downloadUrl = categoryDownBean.getDownload_url();
                        String sha1 = categoryDownBean.getSha1();
                        if (!FileUtils.isCategorySame(mBaseActivity, sha1) ||
                            !FileUtils.isFileExist(BaseConst.CATEGORY_JSON)) {
                            if (FileUtils.isFolderExist(BaseConst.CATEGORY_JSON)) {
                                FileUtils.deleteFile(BaseConst.CATEGORY_JSON);
                            }
                            if (downloadUrl != null) {
                                OkHttpUtils.get().url(downloadUrl).build()
                                    .execute(new FileCallBack(BaseConst.BASE_DIR, "category.json") {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            hideIndeterminateProgressDialog();
                                        }

                                        @Override
                                        public void onResponse(File response, int id) {
                                            FileUtils.saveCategoryFile(mBaseActivity, sha1);
                                            new CategoryListTask(mCategoryNameListAdapter, cid).execute();
                                            new CategoryTask(adapter, b.emptyLayout, mBaseActivity).execute(cid);
                                        }
                                    });
                            } else {
                                JUtils.Toast("暂无分类类目!");
                                hideIndeterminateProgressDialog();
                            }
                        } else {
                            new CategoryListTask(mCategoryNameListAdapter, "").execute();
                            new CategoryTask(adapter, b.emptyLayout, mBaseActivity).execute("");
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    hideIndeterminateProgressDialog();
                    mVaryViewHelperController.showNetworkError(view -> {
                        refreshView();
                        showNetworkError();
                    });
                }
            });
    }

    @Override
    public void setListener() {
        b.lv.setOnItemClickListener(this);
        b.searchLayout.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showIndeterminateProgressDialog(false);
        b.emptyLayout.setVisibility(View.GONE);
        String cid = ((CategoryBean) mCategoryNameListAdapter.getItem(position)).getCid();
        mCategoryNameListAdapter.setCid(cid);
        if (!FileUtils.isFileExist(BaseConst.CATEGORY_JSON)) {
            downloadJson(cid);
        } else {
            mCategoryNameListAdapter.setCid(cid);
            new CategoryTask(adapter, b.emptyLayout, mBaseActivity).execute(cid);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_layout:
                Pair<View, String> searchPair = new Pair<>(b.searchLayout, "search");//nameTv是名字控件
                Intent intent = new Intent(this, SearchActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,
                        searchPair).toBundle());
                } else {
                    startActivity(intent);
                }
                break;
        }
    }
}
