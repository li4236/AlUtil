package com.al.http.http;

import com.al.http.HttpEnum;
import com.al.http.subscriber.AlSubscriber;
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
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by li4236 on 2017/11/16.
 */

public class AlHttp {


    public static <T> void Get(String url, Map<String, Object> map, final AlSubscriber<T> callback) {

        execute(url, callback.getType(), map, HttpEnum.GET).subscribe((DisposableObserver) callback);
    }

    public static <T> void Post(String url, Map<String, Object> map, final AlSubscriber<T> callback) {


        execute(url, callback.getType(), map, HttpEnum.POST).subscribe((DisposableObserver) callback);
    }

    public static <T> void DownLoad(String url, String finleName, Map<String, Object> map, final AlSubscriber<T> callback) {

        if (map == null) {  map = new HashMap<>();}

        executeDoanload(url, finleName, map).subscribe((DisposableObserver) callback);
    }

    public static <T> void UploadFile(String url, Map<String, Object> map, final UploadProgressImpl callback) {

        executeUpload(url, map, callback);
    }

    private static <T> Observable<T> execute(String url, Type type, Map<String, Object> map, HttpEnum httpEnum) {

        Observable<ResponseBody> apiService = getResponseBodyObservable(url, map, httpEnum, null);

        return (Observable<T>) apiService.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(new FunctionResult<T>(type));
    }

    private static <T> Observable<T> executeDoanload(String url, final String fileName, Map<String, Object> map) {

        Observable<ResponseBody> apiService = getResponseBodyObservable(url, map, HttpEnum.DOWNLOAD, null);

        return (Observable<T>) apiService.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .toFlowable(BackpressureStrategy.LATEST)
                .flatMap(new Function<ResponseBody, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(final ResponseBody responseBody) throws Exception {
                        return Flowable.create(new DownloadFlowable(responseBody, fileName), BackpressureStrategy.LATEST);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable();

    }

    private static <T> Observable<T> executeUpload(String url, Map<String, Object> map, UploadProgressImpl uploadProgress) {


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
                .getRetrofit(true).create(ApiService.class);

    }


}
