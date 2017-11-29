package org.al.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by li4236 on 16/4/16.
 * Apk相关工具类
 */
public class ComApkUtil {


    /**
     * @param context
     * @param apkPath
     * @return获取本地是否有APK
     */

    public static boolean isApkLocal(Context context, String apkPath) {

        PackageInfo mPackageInfo = context.getPackageManager().getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES);

        boolean isVersion = mPackageInfo != null
                && mPackageInfo.packageName.equals(context.getPackageName());

        if (isVersion) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

                String versionName = mPackageInfo.versionName;

                int compare = versionName.compareTo(packageInfo.versionName);

                if (compare > 0) {

                    return true;

                } else {

                    return false;
                }

            } catch (PackageManager.NameNotFoundException e) {

                e.printStackTrace();

                return false;
            }

        } else {
//            if (mPackageInfo == null) {
            File f = new File(apkPath);

            if (f != null && f.exists()) {

                f.delete();
            }
            ComLog.E("删除apk");
//            }
            return false;
        }

    }


    /**
     * 安装apk
     */
    public static boolean InstallApk(String mPath, Context mContext) {
        if (!JudgeFileExist(mPath, mContext)) {
            return false;
        }
        // 4.本地apk是正常的，正确安装
        Uri uri = Uri.parse("file://" + mPath);

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addCategory("android.intent.category.DEFAULT");

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.setDataAndType(uri, "application/vnd.android.package-archive");

        mContext.startActivity(intent);

        return true;
    }

    /**
     * 判断apk是否可以安装
     */
    public static Boolean JudgeFileExist(String mPath, Context mContext) {
        if (TextUtils.isEmpty(mPath)) {
            return false;
        }
        // 1.先判断本地的文件是否存在
        File f = new File(mPath.toString());
        if (!f.exists()) {
            return false;
        }
        // 2.存在文件获取安装包的apk的信息判断是否正常可以用
        ApplicationInfo appInfo = null;

        PackageManager pm = mContext.getPackageManager();

        PackageInfo info = pm.getPackageArchiveInfo(mPath, PackageManager.GET_ACTIVITIES);

        if (info != null) {

            appInfo = info.applicationInfo;
        }

        // 3.表示本地的apk不可以用，直接删掉当前文件里面的apk
        if (info == null && appInfo == null) {

            File f1 = new File(mPath.toString());

            if (f1.exists()) {
                f1.delete();
            }
            return false;
        }

        return true;
    }


    /**
     * 判断点击打开apk时手机里面是否有安装了apk
     */
    public static Boolean isApkExist(String packagename, Context mContext) {
        PackageManager localPackageManager = mContext.getPackageManager();
        try {
            @SuppressWarnings("unused")
            PackageInfo localPackageInfo = localPackageManager.getPackageInfo(packagename,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
            // 表示手机里面没有安装过
            return false;
        }
    }


    /**
     * 打开apk
     */
    public static Boolean openAPK(String packagename, Context mContext, String apkId) {
        // 打开的时候在一次从手机获取是否已经安装了！
        if (!isApkExist(packagename, mContext)) {
            return false;
        }
        // 真正打开
        PackageManager packageManager = mContext.getPackageManager();
        Intent intent = new Intent();
        intent = packageManager.getLaunchIntentForPackage(packagename);
        mContext.startActivity(intent);
        return true;
    }

}
