package com.danlai.nidepuzi.base;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danlai.library.utils.JUtils;
import com.danlai.library.widget.loading.VaryViewHelperController;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * @author wisdom
 * @date 2016年11月01日 下午3:11
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment implements BaseSubscriberContext {
    public BaseActivity mActivity;
    public BaseFragment mFragment;
    private boolean mIsHidden = true;
    private static final String FRAGMENT_STORE = "STORE";
    private boolean isVisible = false;
    public boolean isInitView = false;
    private boolean isFirstLoad = true;
    public VaryViewHelperController mVaryViewHelperController;
    private CompositeDisposable mCompositeDisposable;
    protected T b;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getContentViewId() != 0) {
            b = DataBindingUtil.inflate(inflater, getContentViewId(), container, false);
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        if (mVaryViewHelperController == null) {
            mVaryViewHelperController = new VaryViewHelperController(getLoadingView());
        }
        initViews();
        isInitView = true;
        lazyLoadData();
        setListener();
        return b.getRoot();
    }

    public void setListener() {

    }

    public abstract View getLoadingView();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragment = this;
        if (savedInstanceState != null) {
            mIsHidden = savedInstanceState.getBoolean(FRAGMENT_STORE);
        }
        if (restoreInstanceState()) {
            processRestoreInstanceState(savedInstanceState);
        }
    }

    private void processRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden()) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoadData();
        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }


    public boolean isSupportHidden() {
        return mIsHidden;
    }

    protected boolean restoreInstanceState() {
        return true;
    }

    public void lazyLoadData() {
        if (!isFirstLoad || !isVisible || !isInitView) {
            return;
        }
        refreshView();
        showNetworkError();
        isFirstLoad = false;
    }

    public abstract void initData();

    protected abstract void initViews();

    protected abstract int getContentViewId();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FRAGMENT_STORE, isHidden());
    }

    @Override
    public void onDestroyView() {
        try {
            if (this.mCompositeDisposable != null) {
                this.mCompositeDisposable.clear();
                this.mCompositeDisposable = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroyView();
    }

    @Override
    public void addDisposable(Disposable d) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(d);
    }

    public void showIndeterminateProgressDialog(boolean cancelable) {
        mActivity.showIndeterminateProgressDialog(cancelable);
    }

    public void hideIndeterminateProgressDialog() {
        mActivity.hideIndeterminateProgressDialog();
    }

    public String getTitle() {
        if (getArguments() != null) {
            return getArguments().getString("title");
        }
        return "";
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
            showIndeterminateProgressDialog(true);
            initData();
        }
    }

    public void refreshView() {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.restore();
    }

    public void initDataError() {
        mVaryViewHelperController.showNetworkError(view -> {
            refreshView();
            showNetworkError();
        });
    }

    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(mActivity, clazz);
        startActivity(intent);
    }

    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(mActivity, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(mActivity, clazz);
        startActivityForResult(intent, requestCode);
    }

    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(mActivity, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}
