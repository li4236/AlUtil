package com.al.http.http;

import com.al.http.util.HttpEnum;
import com.al.http.api.ApiService;
import com.al.http.api.FunctionResult;
import com.al.http.impl.UploadProgressImpl;
import com.al.http.subscriber.DownloadFlowable;
import com.al.http.util.MediaTypeUtil;

import org.reactivestreams.Publisher;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * author: li4236 on 2017/11/30
 */
public class AlExecute {


    public static <T> Observable<T> execute(String url, Type type, Map<String, Object> map, HttpEnum httpEnum) {
        if (map == null) {  map = new HashMap<>();}

        Observable<ResponseBody> apiService = getResponseBodyObservable(url, map, httpEnum, null);

        return (Observable<T>) apiService.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(new FunctionResult<T>(type));
    }

    public static <T> Observable<T> executeDoanload(String url, final String fileName, Map<String, Object> map) {

        if (map == null) {  map = new HashMap<>();}

        Observable<ResponseBody> apiService = getResponseBodyObservable(url, map, HttpEnum.DOWNLOAD, null);

        return (Observable<T>) apiService.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .toFlowable(BackpressureStrategy.LATEST)
                .flatMap(new Function<ResponseBody, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(final ResponseBody responseBody) throws Exception {
                        return Flowable.create(
                                new DownloadFlowable(responseBody, fileName), BackpressureStrategy.LATEST);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable();

    }

    public static <T> Observable<T> executeUpload(String url, Map<String, Object> map, UploadProgressImpl uploadProgress) {
        if (map == null) {  map = new HashMap<>();}

        Observable<ResponseBody> apiService =
                getResponseBodyObservable(url, map, HttpEnum.UPLOADING, uploadProgress);

        return (Observable<T>) apiService.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


    private static Observable<ResponseBody> getResponseBodyObservable(String url, Map<String, Object> map, HttpEnum httpEnum, UploadProgressImpl uploadProgress) {

        Observable<ResponseBody> apiService = null;

        switch (httpEnum) {
            case GET:
                apiService = getApiService().get(url, map);
                break;
            case POST:
                apiService = getApiService().post(url, map);
                break;
            case DOWNLOAD:
                apiService = getApiService().downFile(url, map);
                break;
            case UPLOADING:
                apiService = getApiService()
                        .uploadFiles(url, MediaTypeUtil.getRequestBody(map, uploadProgress));
                break;
        }

        return apiService;
    }


    private static ApiService getApiService() {

        return RetrofitHelper.getInstance()
                .getRetrofit().create(ApiService.class);

    }

}
