package org.al.common.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by li4236 on 16/3/16.
 * 文件工具类
 */
public class ComFileUtil {

    /**
     * 判断文件是否存在， true表示存在，false表示
     * @param fileName 文件名
     * @return
     */
    public static boolean isFileExits(String fileName){
        File file = new File(fileName);
        if(file.exists()){
            return true;
        }
        return false;
    }

    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {

        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    public static String mkdirs(String path) {
        String sdcard = getSDPath();
        if (path.indexOf(getSDPath()) == -1) {
            path = sdcard + (path.indexOf("/") == 0 ? "" : "/") + path;
        }
        File destDir = new File(path);
        if (!destDir.exists()) {
            path = makedir(path);
            if (path == null) {
                return null;
            }
        }
        return path;
    }

    private static String makedir(String path) {
        String sdPath = getSDPath();
        String[] dirs = path.replace(sdPath, "").split("/");
        StringBuffer filePath = new StringBuffer(sdPath);
        for (String dir : dirs) {
            if (!"".equals(dir) && !dir.equals(sdPath)) {
                filePath.append("/").append(dir);
                File destDir = new File(filePath.toString());
                if (!destDir.exists()) {
                    boolean b = destDir.mkdirs();
                    if (!b) {
                        return null;
                    }
                }
            }
        }
        return filePath.toString();
    }

    public static String getSDPath() {
        File sdDir = null;
        // 判断sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            // 获取跟目录
            sdDir = Environment.getExternalStorageDirectory();
        }
        return sdDir.toString();
    }
}
