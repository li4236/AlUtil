package com.al.http.subscriber;

import com.al.http.bean.AlInfo;
import com.al.http.impl.IType;

public abstract class AlSubscriber<T> extends IType<T> {


    public AlSubscriber() {
    }

    @Override
    public void onError(Throwable e) {
        onAlError(1000, e.getMessage().toString());
    }

    @Override
    public void onNext(AlInfo<T> tAlResponse) {


        onAlSuccess(tAlResponse);
    }


    @Override
    public void onComplete() {
    }


    protected abstract void onAlSuccess(AlInfo<T> alInfo);

    protected abstract void onAlError(int code, String message);

}
