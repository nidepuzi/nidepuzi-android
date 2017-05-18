package com.danlai.nidepuzi.ui.activity.product;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
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
import com.danlai.nidepuzi.adapter.CategoryNameAdapter;
import com.danlai.nidepuzi.base.BaseConst;
import com.danlai.nidepuzi.base.BaseMVVMActivity;
import com.danlai.nidepuzi.databinding.ActivityCategoryBinding;
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
    private CategoryNameAdapter mCategoryNameAdapter;


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
        mCategoryNameAdapter = new CategoryNameAdapter(mBaseActivity);
        b.lv.setAdapter(mCategoryNameAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(mBaseActivity);
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
        if (JUtils.isPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            showIndeterminateProgressDialog(false);
            downloadJson("");
        } else {
            new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("应用包含缓存节省流量功能,需要打开存储权限,应用才能正常使用。")
                .setPositiveButton("确认", (dialog, which) -> {
                    dialog.dismiss();
                    getAppDetailSettingIntent();
                })
                .setNegativeButton("取消", (dialog, which) -> {
                    dialog.dismiss();
                    JUtils.Toast("拒绝授权，类目查看失败!");
                }).show();
        }
    }

    private void getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivity(localIntent);
        finish();
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
                                            new CategoryListTask(mCategoryNameAdapter, cid).execute();
                                            new CategoryTask(adapter, b.emptyLayout, mBaseActivity).execute(cid);
                                        }
                                    });
                            } else {
                                JUtils.Toast("暂无分类类目!");
                                hideIndeterminateProgressDialog();
                            }
                        } else {
                            new CategoryListTask(mCategoryNameAdapter, "").execute();
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
        b.finishLayout.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showIndeterminateProgressDialog(false);
        b.emptyLayout.setVisibility(View.GONE);
        String cid = mCategoryNameAdapter.getItem(position).getCid();
        mCategoryNameAdapter.setCid(cid);
        if (!FileUtils.isFileExist(BaseConst.CATEGORY_JSON)) {
            downloadJson(cid);
        } else {
            mCategoryNameAdapter.setCid(cid);
            new CategoryTask(adapter, b.emptyLayout, mBaseActivity).execute(cid);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_layout:
                Intent intent = new Intent(this, SearchActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Pair<View, String> searchPair = new Pair<>(b.searchLayout, "search");
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,
                        searchPair).toBundle());
                } else {
                    startActivity(intent);
                }
                break;
            case R.id.finish_layout:
                finish();
                break;
        }
    }
}
