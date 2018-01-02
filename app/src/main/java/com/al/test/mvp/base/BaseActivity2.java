package com.al.test.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.al.common.utils.ComTUtil;

/**
 * Created by li4236 on 2017/12/31.
 */

public class BaseActivity2<V ,P extends BasePresenter<V>> extends AppCompatActivity {

    public P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //获取presenter
        mPresenter = ComTUtil.getT(this, 1);

        if (mPresenter != null)
            //绑定view
            mPresenter.bindView((V)this);
    }
}
