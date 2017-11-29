package com.al.test.other;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by li4236 on 2017/11/1.
 */

public class TestImageLoade extends ImageLoade<TestInfo> {

    public TestImageLoade(List<TestInfo> hotSearchBean) {
        super.ImageLoade(hotSearchBean);
    }
    @Override
    public void getInitView(Context context, TestInfo data, ImageView imageView) {
        Glide.with(context).load(data.getAlPath()).into(imageView);

    }
}
