package com.al.test.mvp.contract;

import com.al.test.mvp.base.BasePresenter;
import com.al.test.mvp.base.BaseView;

/**
 * Created by li4236 on 2017/12/31.
 * 模拟请求数据
 */

public interface TestContract {

    interface View extends BaseView {

        void onAlSuccessLogin();//登录成功之后

        void onAlSuccessDealData();//数据处理成功之后

    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void initLogin();//模拟登录

        public abstract void dealData();//处理数据


    }


}
