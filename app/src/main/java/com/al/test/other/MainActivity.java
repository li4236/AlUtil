package com.al.test.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.al.test.R;
import com.al.test.http.HTTPActivity;

import org.al.common.view.AdvertBannerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    AdvertBannerView mAdvertBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        mAdvertBannerView = findViewById(R.id.test_banner);


        final List<TestInfo> infos = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            TestInfo a = new TestInfo();
//            a.setAlPath("http://pic2.ooopic.com/12/22/94/37bOOOPICc7_1024.jpg");
            switch (i) {
                case 0:
                    a.setAlPath("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509600945294&di=c718ecc40c3d3db6a215af213c91821f&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F12%2F43%2F49%2F70bOOOPIC1e_1024.jpg");

                    break;
                case 1:
                    a.setAlPath("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509600945295&di=1ea20f0ae58022787ec85331eb6aa3e6&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F85%2F29%2F50t58PICuba_1024.jpg");
                    break;
                case 2:
                    a.setAlPath("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509600945295&di=dbf20326f95407936d27074921e52e0e&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F79%2F14%2F42k58PICNhr_1024.jpg");
                    break;
            }
            infos.add(a);
        }

        mAdvertBannerView.addCircleDatas( new TestImageLoade(infos));


        findViewById(R.id.http).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), HTTPActivity.class));
            }
        });









    }
}
