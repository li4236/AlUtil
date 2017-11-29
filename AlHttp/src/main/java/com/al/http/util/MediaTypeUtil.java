package com.al.http.util;

import com.al.http.impl.UploadProgressImpl;
import com.al.http.subscriber.UploadProgressRequestBody;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by li4236 on 2017/11/28.
 */

public class MediaTypeUtil {


    public static Map<String, RequestBody> getRequestBody(Map<String, Object> map, UploadProgressImpl uploadProgress) {

        Map<String, RequestBody> partMap = new HashMap<>();

        Iterator iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry entry = (Map.Entry) iterator.next();
            Object o = entry.getValue();
            if (o instanceof String) {

                RequestBody nickNameBody = RequestBody.create(MediaType.parse("text/plain"), (String) entry.getValue());
                partMap.put((String) entry.getKey(), nickNameBody);

            } else if (o instanceof File) {
                RequestBody fileBody = RequestBody.create(getMediaType(((File) o).getName()), (File) entry.getValue());
//
//                RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), (File) entry.getValue());
//                partMap.put((String) entry.getKey() + "\"; filename=\"" + ((File) entry.getValue()).getName() + "\"", fileBody);

                if (uploadProgress != null) {

                    UploadProgressRequestBody uploadProgressRequestBody = new UploadProgressRequestBody(fileBody, uploadProgress);

                    partMap.put((String) entry.getKey(), uploadProgressRequestBody);

                } else {

                    partMap.put((String) entry.getKey(), fileBody);

                }


            }

        }

        return partMap;
    }


    public static MediaType getMediaType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        path = path.replace("#", "");   //解决文件名中含有#号异常的问题
        String contentType = fileNameMap.getContentTypeFor(path);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return MediaType.parse(contentType);
    }
}
