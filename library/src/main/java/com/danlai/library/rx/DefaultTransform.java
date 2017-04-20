package com.danlai.library.rx;


import com.danlai.library.utils.JUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * @author wisdom
 * @date 2016年12月20日 下午3:18
 */
public class DefaultTransform<T> implements ObservableTransformer<T, T> {
    private int count = 0;

    @Override
    public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        return upstream
            .doOnError(throwable -> JUtils.Log(throwable.getLocalizedMessage()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread(), true)
            .retryWhen(throwableObservable ->
                throwableObservable.flatMap((Function<Throwable, ObservableSource<?>>) throwable -> {
                    if (count++ <= 2 && !JUtils.isNetWorkAvilable()) {
                        return Observable.timer(2000, TimeUnit.MILLISECONDS);
                    }
                    return Observable.error(throwable);
                }));
    }
}
