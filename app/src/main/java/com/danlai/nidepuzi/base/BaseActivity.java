package com.danlai.nidepuzi.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;

import com.danlai.library.utils.JUtils;
import com.danlai.library.utils.StatusBarUtil;
import com.danlai.library.utils.ViewUtils;
import com.danlai.library.widget.loading.VaryViewHelperController;
import com.danlai.library.widget.loading.WisdomLoading;
import com.danlai.library.widget.swipeback.SwipeBackActivityHelper;
import com.danlai.nidepuzi.R;
import com.umeng.analytics.MobclickAgent;
import com.zhy.autolayout.AutoLayoutActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * @author wisdom
 * @date 2016年10月14日 下午1:15
 */

public abstract class BaseActivity extends AutoLayoutActivity implements BaseSubscriberContext {
    protected BaseActivity mBaseActivity = null;
    protected WisdomLoading wisdomLoading;
    public VaryViewHelperController mVaryViewHelperController;
    private SwipeBackActivityHelper mHelper;
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        Uri uri = getIntent().getData();
        if (extras != null) {
            getBundleExtras(extras);
        } else if (uri != null) {
            getIntentUrl(uri);
        }
        mBaseActivity = this;
        BaseAppManager.getInstance().addActivity(this);
        if (getContentViewLayoutID() != 0) {
            initContentView();
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        initViews();
        if (mVaryViewHelperController == null) {
            mVaryViewHelperController = new VaryViewHelperController(getLoadingView());
        }
        if (isNeedShow()) {
            refreshView();
            showNetworkError();
        } else {
            initData();
        }
        setListener();
    }

    public abstract void initContentView();

    public void getIntentUrl(Uri uri) {

    }

    protected void setListener() {

    }

    protected void initData() {

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null) return mHelper.findViewById(id);
        return v;
    }

    public void setSwipeBackEnable(boolean enable) {
        mHelper.getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
        MobclickAgent.onPause(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        if (isAllWindow()) {
            ViewUtils.setWindowStatus(this);
        } else {
            StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent), 0);
        }

    }

    protected void getBundleExtras(Bundle extras) {

    }

    protected abstract int getContentViewLayoutID();

    protected void initViews() {

    }

    public void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    public void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    public void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    public boolean isAllWindow() {
        return false;
    }

    public void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void showIndeterminateProgressDialog(boolean cancelable) {
        if (wisdomLoading == null) {
            wisdomLoading = WisdomLoading.createDialog(this)
                .setCanCancel(cancelable)
                .start();
        }
    }

    public void setDialogContent(String content) {
        if (wisdomLoading != null) {
            wisdomLoading.setContent(content);
        }
    }

    public void hideIndeterminateProgressDialog() {
        try {
            if (wisdomLoading != null) {
                wisdomLoading.dismiss();
                wisdomLoading = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addDisposable(Disposable d) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(d);
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideIndeterminateProgressDialog();
        try {
            if (this.mCompositeDisposable != null) {
                this.mCompositeDisposable.clear();
                this.mCompositeDisposable = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMsg(String title, String msg1, String msg2) {
        showMsgAndFinish(title, msg1, msg2, false);
    }

    public void showMsgAndFinish(String title, String msg1, String msg2, boolean isfinish) {
        String str = title;
        if (null != msg1 && msg1.length() != 0) {
            str += "\n" + msg1;
        }
        if (null != msg2 && msg2.length() != 0) {
            str += "\n" + msg2;
        }
        if (title.equals("fail")) {
            str = "支付失败，请重试！";
        } else if (title.equals("invalid")) {
            str = "支付失败，支付软件未安装完整！";
        }
        new AlertDialog.Builder(this)
            .setMessage(str)
            .setTitle("提示")
            .setPositiveButton("OK", (dialog1, which) -> {
                dialog1.dismiss();
                if (isfinish) {
                    finish();
                }
            })
            .create()
            .show();
    }

    public void showNetworkError() {
        if (!JUtils.isNetWorkAvilable()) {
            hideIndeterminateProgressDialog();
            if (mVaryViewHelperController == null) {
                throw new IllegalStateException("no ViewHelperController");
            }
            mVaryViewHelperController.showNetworkError(view -> {
                refreshView();
                showNetworkError();
            });
        } else {
            initData();
        }
    }

    @Override
    public void finish() {
        super.finish();
        BaseAppManager.getInstance().removeActivity(this);
    }

    public void refreshView() {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.restore();
    }

    public View getLoadingView() {
        return null;
    }

    public boolean isNeedShow() {
        return true;
    }
}
