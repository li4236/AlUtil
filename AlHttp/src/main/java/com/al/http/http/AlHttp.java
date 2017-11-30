package com.al.http.http;

import com.al.http.util.HttpEnum;
import com.al.http.impl.UploadProgressImpl;
import com.al.http.subscriber.AlSubscriber;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by li4236 on 2017/11/16.
 */

public class AlHttp {


    public static <T> void Get(String url, Map<String, Object> map, final AlSubscriber<T> callback) {

        AlExecute.execute(url, callback.getType(), map, HttpEnum.GET).subscribe((DisposableObserver) callback);
    }

    public static <T> void Post(String url, Map<String, Object> map, final AlSubscriber<T> callback) {


        AlExecute.execute(url, callback.getType(), map, HttpEnum.POST).subscribe((DisposableObserver) callback);
    }

    public static <T> void DownLoad(String url, String finleName, Map<String, Object> map, final AlSubscriber<T> callback) {


        AlExecute.executeDoanload(url, finleName, map).subscribe((DisposableObserver) callback);
    }

    public static <T> void UploadFile(String url, Map<String, Object> map, final UploadProgressImpl callback) {

        AlExecute.executeUpload(url, map, callback);

    }


}
