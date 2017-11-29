package com.al.http.http;


import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by li4236 on 2017/1/5.
 */

public class RetrofitHelper {

    private final OkHttpClient mClient;

    private Retrofit mRetrofit, mRetrofits;

    private RetrofitHelper() {


        mClient = OkHttpClientHelper.getInstance().getOkHttpClient();
    }

    private static RetrofitHelper helper;

    //单例 保证对象唯一
    public static RetrofitHelper getInstance() {
        if (helper == null) {
            synchronized (RetrofitHelper.class) {
                if (helper == null) {
                    helper = new RetrofitHelper();
                }
            }
        }
        return helper;
    }

    /**
     * @param m true = https false = http
     * @return
     */
    //获取Retrofit对象
    public Retrofit getRetrofit(boolean m) {


        if (mRetrofits == null) {
            mRetrofits = getBuild("http://v.juhe.cn/toutiao/");
        }

//        String bseUrl = m ? YLifeUrl.HTTPS : YLifeUrl.HTTP;
//
//        if (m){
//            if (mRetrofits == null) {
//                mRetrofits = getBuild(bseUrl);
//            }
//            return mRetrofits;
//        }else {
//
//            if (mRetrofit == null) {
//                mRetrofit = getBuild(bseUrl);
//            }
//
//            return mRetrofit;
//        }

        return mRetrofits;


    }

    @NonNull
    private Retrofit getBuild(String bseUrl) {
        return new Retrofit.Builder()
                .baseUrl(bseUrl)

                .addConverterFactory(GsonConverterFactory.create())  //添加Gson支持
//                    .addConverterFactory(new ToStringConverterFactory())  //定义字符串形式支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //添加RxJava支持
                .client(mClient)                                            //关联okhttp
                .build();
    }


}
