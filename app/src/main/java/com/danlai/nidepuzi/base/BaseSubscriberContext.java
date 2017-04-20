package com.danlai.nidepuzi.base;

import io.reactivex.disposables.Disposable;

/**
 * @author wisdom
 * @date 2017年04月02日 上午9:11
 */

public interface BaseSubscriberContext {
    void addDisposable(Disposable d);
}
