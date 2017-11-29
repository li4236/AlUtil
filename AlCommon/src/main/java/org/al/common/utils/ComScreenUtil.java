package org.al.common.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by li4236 on 2016/12/7.
 */

public class ComScreenUtil {


    public static int getScreenHeight(Context context) {

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

//        int width = wm.getDefaultDisplay().getWidth();
//        int height = wm.getDefaultDisplay().getHeight();
        return wm.getDefaultDisplay().getHeight();
    }

    public static int getScreenWidth(Context context) {

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        return wm.getDefaultDisplay().getWidth();
    }
}
