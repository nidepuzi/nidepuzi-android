package com.danlai.nidepuzi.service;


import com.danlai.nidepuzi.base.BaseSubscriberContext;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by wisdom on 17/4/2.
 */
public class ServiceResponse<T> implements Observer<T> {
    private BaseSubscriberContext context;

    public ServiceResponse(BaseSubscriberContext context) {
        this.context = context;
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        context.addDisposable(d);
    }

    @Override
    public void onNext(T t) {

    }
}
