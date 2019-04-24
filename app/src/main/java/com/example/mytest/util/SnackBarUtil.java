package com.example.mytest.util;

import android.view.View;

import com.blankj.utilcode.util.SnackbarUtils;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */

public class SnackBarUtil {

    public static void show(View view, String msg) {
        SnackbarUtils.with(view).setMessage(msg).setDuration(SnackbarUtils.LENGTH_LONG).show();
    }

    public static void showShort(View view, String msg) {
        SnackbarUtils.with(view).setMessage(msg).setDuration(SnackbarUtils.LENGTH_SHORT).show();
    }
}
