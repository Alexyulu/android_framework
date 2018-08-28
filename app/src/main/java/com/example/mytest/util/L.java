package com.example.mytest.util;

import com.blankj.utilcode.util.LogUtils;
import com.example.mytest.BuildConfig;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : Log统一处理类
 */
public class L {

    public static boolean isDebug = BuildConfig.DEBUG;
    private static final String TAG = "Test";

    public static void e(String tag, Object o) {
        LogUtils.e(tag, o);
    }

    public static void e(Object o) {
        LogUtils.e(TAG, o);
    }

    public static void w(String tag, Object o) {
        LogUtils.w(tag, o);
    }

    public static void w(Object o) {
        LogUtils.w(TAG, o);
    }

    public static void d(String msg) {
        LogUtils.d(msg);
    }

    public static void i(String msg) {
        LogUtils.i(msg);
    }
}
