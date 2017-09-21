package com.example.shinelon.mytest.util;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/19
 * Desc :
 */

public class Utils {

    /**
     * 获取EditText中的值，若EditText为null则返回空
     * @param editText EditText
     * @return String
     */
    public static String getText(EditText editText) {
        return editText == null ? "" : TextUtils.isEmpty(editText.getText()) ? "" : editText.getText().toString().trim();
    }

    /**
     * view获取焦点
     * @param view View
     */
    public static void requestFocus(View view) {
        requestFocus(view, -1);
    }

    private static void requestFocus(final View view, long delay) {
        if (view == null) {
            return;
        }
        delay = delay <= -1 ? 0 : delay;
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setFocusableInTouchMode(true);
                view.setFocusable(true);
                view.requestFocus();
            }
        }, delay);
    }
}
