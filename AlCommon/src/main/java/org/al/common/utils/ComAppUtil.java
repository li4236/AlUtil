package org.al.common.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by li4236 on 16/2/22.
 * 获取APP有关信息
 */
public class ComAppUtil {



    /**
     * 获取软件版本号
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            String pkgname = context.getPackageName();
            versionCode = context.getPackageManager()
                    .getPackageInfo(pkgname, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    // 获取版本名
    public static String getVersionName(Context context) {
        String verName = "";
        String pkgname = context.getPackageName();
        try {
            verName = context.getPackageManager().getPackageInfo(pkgname, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    /**
     * 获取APP的包名
     */
    public static String getPackageName(Context context) {

        return context.getPackageName();
    }
}
