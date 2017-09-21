package com.example.shinelon.mytest.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/20
 * Desc :
 */

public class StringUtils {

    /**
     * 你懂的 phone
     *
     * @param str str
     * @return boolean
     */
    public static boolean isPhone(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        // 我们只验证第1位。 20150831
        String reg = "^1\\d{10}$";// "(((13[0-9])|(14[5,7])|(15[^4,\\D])|(18[0-3,5-9]))\\d{8}$))|(^((170[0,5,9]))\\d{7}$))";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        return mat.matches();
    }
}
