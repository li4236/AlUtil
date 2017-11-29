package com.al.test.other;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import org.al.common.impl.AlImageLoader;

import java.util.List;

/**
 * Created by li4236 on 2017/11/1.
 */

public abstract class ImageLoade<T> implements AlImageLoader {

    private List<T> mLists;

    public void ImageLoade(List<T> lists) {

        if (lists.size() > 1) {

            //头部添加一个
            lists.add(0, lists.get(lists.size() - 1));
//            //尾部添加一个
            lists.add(lists.get(1));

        }


        this.mLists = lists;

    }


    @Override
    public void initView(Context context, int position, ImageView imageView) {
        getInitView(context, mLists.get(position), imageView);
    }

    @Override
    public void onClick(Context context, View view) {

    }

    @Override
    public int getBannerSize() {

        return mLists.size();

    }

    public abstract void getInitView(Context context, T data, ImageView imageView);
}
