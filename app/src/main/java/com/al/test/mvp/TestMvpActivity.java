package com.al.test.mvp;

import android.os.Bundle;

import com.al.test.R;
import com.al.test.mvp.base.BaseActivity;
import com.al.test.mvp.contract.TestContract;
import com.al.test.mvp.presenter.TestPresenter;

import org.al.common.utils.ComLog;

public class TestMvpActivity extends BaseActivity<TestPresenter> implements TestContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mvp);


        mPresenter.initLogin();


    }

    @Override
    public void onAlSuccessLogin() {

        ComLog.E("成功");

    }

    @Override
    public void onAlSuccessDealData() {

    }
}
