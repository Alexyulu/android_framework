package com.example.shinelon.mytest.util;

import com.example.shinelon.mytest.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : Log统一处理类
 */
public class LogUtils {

    public static boolean isDebug = BuildConfig.DEBUG;
    private static final String TAG = "CarMaster";

    public static void e(String tag, Object o) {
        Logger.e(tag, o);
    }

    public static void e(Object o) {
        LogUtils.e(TAG, o);
    }

    public static void w(String tag, Object o) {
        Logger.w(tag, o);
    }

    public static void w(Object o) {
        LogUtils.w(TAG, o);
    }

    public static void d(String msg) {
        Logger.d(msg);
    }

    public static void i(String msg) {
        Logger.i(msg);
    }
}
