package com.al.test.mvp.presenter;

import com.al.test.mvp.contract.TestContract;

import org.al.common.utils.ComLog;

/**
 * Created by li4236 on 2017/12/31.
 */

public class TestPresenter extends TestContract.Presenter {
    @Override
    public void initLogin() {

        ComLog.E("模拟网络请求数据");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }

        mView.onAlSuccessLogin();

    }

    @Override
    public void dealData() {

    }
}
