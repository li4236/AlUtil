package org.al.common.utils;

import android.util.Log;

/**
 * Created by li4236 on 16/5/30.
 * 邮箱: li4236@aliyun.com
 */
public class ComLog {

    public static boolean LOG = false;

    public static void E(String charSequence) {

        if (ComToast.Control) {
            Log.e("CommonLibrary", charSequence+"");
        }

    }

    public static void E(String type, String charSequence) {
        if (ComToast.Control) {
            Log.e(type+"", charSequence+"");
        }
    }

    public static void I(String type, String charSequence) {
        if (ComToast.Control) {
            Log.i(type, charSequence+"");
        }
    }

    public static void I(String charSequence) {

        if (ComToast.Control) {
            Log.i("CommonLibrary", charSequence+"");
        }

    }

    public static void D(String charSequence) {

        if (ComToast.Control) {
            Log.d("CommonLibrary", charSequence+"");
        }

    }

    public static void println(String charSequence) {

        Log.e("CommonLibrary", "打印调试键值信息:"+charSequence);

    }
}
