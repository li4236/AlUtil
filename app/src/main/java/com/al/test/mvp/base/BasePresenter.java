package com.al.test.mvp.base;

/**
 * Created by li4236 on 2017/12/31.
 */

public abstract class BasePresenter<V> {

    public V mView;

    public void bindView(V view){

        this.mView = view;
    }
}
