package com.al.test.http;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.al.http.bean.AlInfo;
import com.al.http.bean.DownLoadProgress;
import com.al.http.config.AlConfig;
import com.al.http.http.AlHttp;
import com.al.http.subscriber.AlSubscriber;
import com.al.test.R;
import com.al.test.bean.NewsInfo;
import com.al.test.other.HeadInterceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;

public class HTTPActivity extends AppCompatActivity {

    private TextView mTextView;

    Map<String, Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        mTextView = findViewById(R.id.testmessage);

        //内置sd卡路径
        String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();

        List<Interceptor> interceptors = new ArrayList<>();
        //比如日志拦截输出
//        interceptors.add(new LogInterceptor());
//        网络统一头部添加处理
        interceptors.add(new HeadInterceptor());

       //初始化
        AlConfig.init()
                //初始化基础api
                .baseUrl("http://v.juhe.cn/toutiao/")
                //初始化下载文件保存路径
                .downlaodFath(sdcardPath+"/AlDownload")
                .interceptors(interceptors);

        map.put("type", "top");

        map.put("key", "50b1d3ed18ea0ef9a82d28c7229c0773");
    }

    public void download(View view) {//下载示例
        String saveName = "weixin.apk";
        String url = "http://dldir1.qq.com/weixin/android/weixin6330android920.apk";
//        http://openbox.mobilem.360.cn/url/r/k/std_1511770750

        AlHttp.DownLoad(url, saveName, null, new AlSubscriber<DownLoadProgress>() {

            @Override
            protected void onAlSuccess(AlInfo<DownLoadProgress> alInfo) {
                Log.e("li4236", alInfo.getResult().getProgressBar() + "");
                Log.e("li4236", alInfo.getResult().toString());

                mTextView.setText(alInfo.getResult().getProgressBar() + "======" + alInfo.getResult().toString());

                if (alInfo.getResult().isDownComplete()) {
                    Log.e("li4236", "下载成功");
                }
            }

            @Override
            protected void onAlError(int code, String message) {
                Log.e("li4236", message);
            }
        });

    }

    public void Uploading(View view) {
    }

    public void POSTStirng(View view) {

        AlHttp.Post("http://v.juhe.cn/toutiao/index?", map, new AlSubscriber<String>() {

            @Override
            protected void onAlSuccess(AlInfo<String> alInfo) {
                Log.e("PostString==", alInfo.getResult());
                mTextView.setText(alInfo.getResult());
            }

            @Override
            protected void onAlError(int code, String message) {

            }
        });
    }

    public void GETString(View view) {

        AlHttp.Get("index?", map, new AlSubscriber<String>() {

            @Override
            protected void onAlSuccess(AlInfo<String> alInfo) {
                Log.e("GetString===", alInfo.getResult());
                mTextView.setText(alInfo.getResult());
            }

            @Override
            protected void onAlError(int code, String message) {

            }
        });
    }

    public void POST(View view) {


        AlHttp.Post("index?", map, new AlSubscriber<NewsInfo>() {
            @Override
            protected void onAlSuccess(AlInfo<NewsInfo> t) {

                Log.e("Post", t.toString());
                mTextView.setText( "请求成功"+t.toString());
            }

            @Override
            protected void onAlError(int code, String message) {

            }
        });
    }

    public void GET(View view) {

        AlHttp.Get("index?", map, new AlSubscriber<NewsInfo>() {
            @Override
            protected void onAlSuccess(AlInfo<NewsInfo> t) {

                Log.e("Get", t.toString());
                mTextView.setText( "请求成功"+t.toString());

            }

            @Override
            protected void onAlError(int code, String message) {

            }
        });
    }

    public void list(View view) {

        //没有这种类型的api，不过我自己模拟成功的
//
//        {
//            "reason": "成功的返回",
//            "error_code": 0
//            "result": [
//            {
//                "uniquekey": "ce4fb337ca171e90b02514762bba91dd",
//                    "title": "肱二头肌训练时，常被人忽视的误区，教你正确的进行锻炼",
//                    "date": "2017-11-29 10:54",
//                    "category": "头条",
//                    "author_name": " 运动发骚客",
//                    "url": "http://mini.eastday.com/mobile/171129105445698.html",
//                    "thumbnail_pic_s": "http://05.imgmini.eastday.com/mobile/20171129/20171129_c823c75e962280dc5f76eef619560dd2_cover_mwpm_03200403.jpg",
//                    "thumbnail_pic_s02": "http://05.imgmini.eastday.com/mobile/20171129/20171129_fce53c207ab377fdd83afe32eb797fd5_cover_mwpm_03200403.jpg",
//                    "thumbnail_pic_s03": "http://05.imgmini.eastday.com/mobile/20171129/20171129_c8ee262b16b89cf286df06e2a8fba94c_cover_mwpm_03200403.jpg"
//            },
//            {
//                "uniquekey": "d01b624318d5ebe819a935bc1582d02d",
//                    "title": "国台办谈李明哲案：是否危害大陆 应由大陆法律判定",
//                    "date": "2017-11-29 11:21",
//                    "category": "头条",
//                    "author_name": "中国新闻网",
//                    "url": "http://mini.eastday.com/mobile/171129112135134.html",
//                    "thumbnail_pic_s": "http://08.imgmini.eastday.com/mobile/20171129/20171129112135_45b5cdbe4420df02223d9d8418b72c64_1_mwpm_03200403.jpg"
//            }
//
//    ],
//
//        }


        AlHttp.Post("index?", map, new AlSubscriber<List<NewsInfo>>() {

            @Override
            protected void onAlSuccess(AlInfo<List<NewsInfo>> alInfo) {
                Log.e("list", alInfo.toString());
            }

            @Override
            protected void onAlError(int code, String message) {
                Log.e("list", message);
            }
        });
    }
}
