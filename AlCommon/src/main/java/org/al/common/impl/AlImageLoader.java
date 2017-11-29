package org.al.common.impl;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by li4236 on 2017/11/1.
 */

public interface AlImageLoader {

    /**
     * @param context
     * @param position
     * @param imageView
     */
     void initView(Context context,int position, ImageView imageView);
     void onClick(Context context, View view);


     int getBannerSize();
}
