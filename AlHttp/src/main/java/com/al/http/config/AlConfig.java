package com.al.http.config;

import com.al.http.http.RetrofitHelper;
import com.al.http.util.FileUtil;

import java.util.List;

import okhttp3.Interceptor;

/**
 * author: li4236 on 2017/11/29
 */
public class AlConfig {

    public static AlConfig alConfig;

    private String baseUrl;

    private String downlaodFath;

    private List<Interceptor> mInterceptors;

    public AlConfig() {

    }

    //单例 保证对象唯一
    public static AlConfig init() {
        if (alConfig == null) {
            synchronized (RetrofitHelper.class) {
                if (alConfig == null) {
                    alConfig = new AlConfig();
                }
            }
        }
        return alConfig;
    }

    public String getBaseUrl() {
        return init().baseUrl;
    }

    public String getDownlaodFath() {
        return init().downlaodFath;
    }

    public AlConfig baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public AlConfig downlaodFath(String downlaodFath) {
        this.downlaodFath = downlaodFath;

        FileUtil.mkdirs(downlaodFath);
        return this;
    }

    public List<Interceptor> getInterceptors() {
        return mInterceptors;
    }

    public AlConfig interceptors(List<Interceptor> interceptors) {
        mInterceptors = interceptors;
        return this;
    }
}
