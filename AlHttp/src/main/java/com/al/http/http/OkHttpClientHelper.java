package com.al.http.http;


import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by li4236 on 2017/1/5.
 * okhttp操作：添加公司统一配置head
 */

public class OkHttpClientHelper {

    private OkHttpClient mClient;

    private final static long TIMEOUT = 60;  //超时时间

    private List<Interceptor> mInterceptors;

    private OkHttpClientHelper() {

    }

    private static OkHttpClientHelper clientHelper;

    public static OkHttpClientHelper getInstance() {
        if (clientHelper == null) {
            synchronized (OkHttpClientHelper.class) {
                if (clientHelper == null) {
                    clientHelper = new OkHttpClientHelper();
                }
            }
        }
        return clientHelper;
    }


    //获取OKHttpClicent对象
    public OkHttpClient getOkHttpClient() {

        if (mClient == null) {

            OkHttpClient.Builder builder
                    = new OkHttpClient.Builder();

            //设置超时时间
            builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
            //读取时间
            builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);

            builder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
            //添加日志输出
//                    .addInterceptor(new LogInterceptor())


            if (mInterceptors != null && mInterceptors.size()>0){
                for (Interceptor interceptor: mInterceptors) {
                    builder.addInterceptor(interceptor);
                }
            }


            mClient = builder.build();



        }
        return mClient;
    }


}
