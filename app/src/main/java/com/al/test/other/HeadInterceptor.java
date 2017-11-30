package com.al.test.other;


import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;

/**
 * Created by li4236 on 2017/1/6.
 * 网络统一头部添加处理
 */

public class HeadInterceptor implements Interceptor {

    public static String TAG = "LogInterceptor";

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

         //添加公司统一参数配置
        Request.Builder requestBuilder = original.newBuilder();

//                .addHeader("key", "")
//                .addHeader("clueid", "");


        HashMap<String, Object> hashMap = new HashMap<>();

        Request request = null;

        if ("POST".equals(original.method())) {


            //请求体定制：统一添加sign参数
            if (original.body() instanceof FormBody) {
                FormBody.Builder newFormBody = new FormBody.Builder();
//                FormBody oidFormBody = (FormBody) original.body();
//
//                for (int i = 0; i < oidFormBody.size(); i++) {
//                    String name = oidFormBody.name(i);
//                    String value = oidFormBody.value(i);
//                    hashMap.put(name, value);
//                    newFormBody.addEncoded(name, value);
//                }
//
//                newFormBody.add("sign","键值加密统一");

                requestBuilder.method(original.method(), newFormBody.build());
                request = requestBuilder.build();
            } else if (original.body() instanceof MultipartBody) {

//
                request = requestBuilder.build();
            }
        } else if ("GET".equals(original.method())) {

            Iterator<String> iterator = original.url().queryParameterNames().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = original.url().queryParameter(key);
                hashMap.put(key, value);
            }

            HttpUrl.Builder builder = original.url().newBuilder();
//
//
//            builder.addQueryParameter("imei","");
//
//
//            builder.addQueryParameter("sign","键值加密统一");

            HttpUrl accessSign = builder.build();

            request = requestBuilder.url(accessSign).build();
        }

        return chain.proceed(request);
    }
}
