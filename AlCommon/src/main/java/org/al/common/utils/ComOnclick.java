package org.al.common.utils;

/**
 * Created by li4236 on 16/5/21.
 * 邮箱: li4236@aliyun.com
 */
public class ComOnclick {


    private static long lastClickTime;

    /*
     * 防止用户重复点击
     */
    public static boolean isFastDoubleClick(long mLong) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < mLong) {

            lastClickTime = time;
            return true;
        } else {
            lastClickTime = time;
        }
        return false;

    }


}
