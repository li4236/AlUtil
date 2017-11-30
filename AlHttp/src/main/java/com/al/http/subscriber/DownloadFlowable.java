package com.al.http.subscriber;

import com.al.http.bean.AlInfo;
import com.al.http.bean.DownLoadProgress;
import com.al.http.config.AlConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import okhttp3.ResponseBody;

/**
 * author: li4236 on 2017/11/28
 */
public class DownloadFlowable implements FlowableOnSubscribe<AlInfo<DownLoadProgress>> {

    private ResponseBody responseBody;

    private String fileName;

    public DownloadFlowable(ResponseBody responseBody,String fileName){
        this.responseBody = responseBody;
        this.fileName = fileName;
    }

    @Override
    public void subscribe(FlowableEmitter<AlInfo<DownLoadProgress>> subscriber) throws Exception {


        File file = new File(AlConfig.init().getDownlaodFath() + File.separator + fileName);
        saveFile(subscriber, file, responseBody);
    }


    private  void saveFile(final FlowableEmitter<? super AlInfo<DownLoadProgress>> sub, File saveFile, ResponseBody resp) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            try {
                int readLen;
                long downloadSize = 0;
                byte[] buffer = new byte[4096];

                AlInfo<DownLoadProgress> alInfo = new AlInfo<>();

                final DownLoadProgress downProgress = new DownLoadProgress();

                alInfo.setResult(downProgress);

                inputStream = resp.byteStream();

                outputStream = new FileOutputStream(saveFile);

                long contentLength = resp.contentLength();

                downProgress.setTotalSize(contentLength);

                while ((readLen = inputStream.read(buffer)) != -1) {

                    outputStream.write(buffer, 0, readLen);

                    downloadSize += readLen;

                    downProgress.setDownloadSize(downloadSize);


                    sub.onNext(alInfo);

                }

                outputStream.flush();

                sub.onComplete();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (resp != null) {
                    resp.close();
                }
            }
        } catch (IOException e) {
            sub.onError(e);
        }
    }

}
