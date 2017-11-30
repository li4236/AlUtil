package com.al.test.other;


import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by li4236 on 2017/1/6.
 * 日志输入 retrofit 日志拦截
 */

public class LogInterceptor implements Interceptor {

    public static String TAG = "LogInterceptor";

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();


        long startTime = System.currentTimeMillis();
        okhttp3.Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        okhttp3.MediaType mediaType = response.body().contentType();
//        String content = response.body().string();

        Log.e(TAG, "\n");
        Log.e(TAG, "----------Start 参数打印----------------");
        Log.e(TAG, request.toString());
        String method = request.method();

//        if (ComLog.LOG) {
        if ("POST".equals(method)) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + " == " + body.encodedValue(i) + "\n");

                }
                sb.delete(sb.length() - 1, sb.length());
                Log.e(TAG, "请求参数:【\n" + sb.toString() + " \n】");
            }
        }
        Log.e(TAG, "----------End:" + duration + "毫秒----------");
//        }
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, response.body().string()))
                .build();
    }
}
