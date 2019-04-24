package com.example.mytest.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 **/
public class ProgressDialogUtils {
    private ProgressDialog mProgressDialog;

    private ProgressDialogUtils(Context mContext) {
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("加载中");
    }


    public static synchronized ProgressDialogUtils getInstance(Context mContext) {
        return new ProgressDialogUtils(mContext);
    }

    public ProgressDialogUtils setMessage(String context) {
        mProgressDialog.setMessage(context);
        return this;
    }


    public ProgressDialogUtils show() {
        mProgressDialog.show();
        return this;
    }

    public ProgressDialogUtils dismiss() {
        mProgressDialog.dismiss();
        return this;
    }
}
