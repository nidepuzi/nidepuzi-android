package com.danlai.nidepuzi.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.danlai.nidepuzi.R;


/**
 * @author wisdom
 * @date 2016年07月26日 下午2:12
 */
public abstract class BaseMVVMActivity<T extends ViewDataBinding> extends BaseActivity {
    protected T b;

    @Override
    public void initContentView() {
        b = DataBindingUtil.setContentView(this, getContentViewLayoutID());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

}
