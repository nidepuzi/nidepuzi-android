package com.danlai.library.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by itxuye(www.itxuye.com) on 2016/01/21.
 * <p>
 * Copyright 2015年 上海己美. All rights reserved.
 */
public class DataClearManager {
    private static final String newsCacheurl =
        Environment.getExternalStorageDirectory().getAbsolutePath() + "/xlmm";
    private static double dirSizeAll = 0;

    /**
     * 清除本应用内部缓存
     */
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    public static double getInternalCacheSize(Context context) {
        Log.d("getApplicationDataSize", context.getCacheDir() + "kkkkk");
        return getDirSize(context.getCacheDir());
    }

    /**
     * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /**
     * 计算外部cache大小
     */
    public static double getExternalCacheSize(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return getDirSize(context.getExternalCacheDir());
        }
        return 0;
    }

    /**
     * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
     */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    public static String getCustomCacheSize(String filePath) {
        double size = getDirSize(new File(filePath));
        return String.format("%.1f", size);
    }

    /**
     * 清除本应用所有的数据
     */
    public static void cleanApplicationData(Context context, String... filepath) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        //cleanSharedPreference(context);
        for (String filePath : filepath) {
            Log.d("DataClear", "filePath " + filePath);
            cleanCustomCache(filePath);
        }
    }

    public static void cleanApplicationData(Context context) {
        Log.d("cleanApplicationData", newsCacheurl);
        cleanApplicationData(context, newsCacheurl);
    }

    /**
     * 获得删除数据的大小，保存两位有效数字，并且返回字符串
     */
    public static String getApplicationDataSize(Context context, String... filepath) {
        double size = 0;
        size += getInternalCacheSize(context);
        dirSizeAll = 0;
        getExternalCacheSize(context);
        size += dirSizeAll;
        for (String file : filepath) {
            dirSizeAll = 0;
            getCustomCacheSize(file);
            size += dirSizeAll;
        }
        return String.format("%.1f", size) + "M";
    }

    //
    public static String getApplicationDataSize(Context context) {
        return getApplicationDataSize(context, newsCacheurl);
    }

    /**
     * 删除某文件夹下的文件，如果传入参数是个文件，酱不做处理
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                if (item.isFile()) {
                    item.delete();
                } else if (item.isDirectory()) {
                    deleteFilesByDirectory(item);
                }
            }
            //            directory.delete();
        }
    }

    /**
     * 计算文件夹大小
     *
     * @return Mb
     */
    public static double getDirSize(File dir) {
        if (dir == null || !dir.exists()) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        double dirSize = 0;
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile()) {
                    dirSize += file.length();
                } else if (file.isDirectory()) {
                    dirSize += getDirSize(file); // 如果遇到目录则通过递归调用继续统计
                }
            }
        }
        dirSizeAll += dirSize / (1024 * 1024);
        return dirSizeAll;
    }
}
