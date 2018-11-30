package com.example.mytest.app;

import android.annotation.SuppressLint;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : 静态类
 */

public interface Constants {
    /**
     * 是否 debug 版本，true 是调试版本；false 是正式版本
     */
    boolean IS_DEBUG = true;
    /**
     * 定义APP缓存SD卡上的根目录
     */
    String APP_CACHE_ROOT_FILE = "/rulai";


    String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    /**
     * initService action
     */
    String ACTION_INIT = "initApplication";
    /**
     * tag
     */
    String TAG = "zhiru";
    /**
     * DATE_FORMAT
     */
    @SuppressLint("SimpleDateFormat")
    DateFormat Y_M_D = new SimpleDateFormat("yyyy-MM-dd");

    @SuppressLint("SimpleDateFormat")
    DateFormat M_D = new SimpleDateFormat("MM-dd");

    //================= SP缓存 ====================
    /**
     * SharePreferences的name
     */
    String SP_NAME = "my_sp";

    /**
     * 登陆的SP的key
     */
    String KEY_ACCOUNT = "account";

    /**
     * 当前item的SP的key
     */
    String SP_CURRENT_ITEM = "current_item";

    /**
     * 当前的角色名称
     */
    String SP_CURRENT_ROLE = "current_role";

    /**
     * 当前用户是否是首次打开登陆的key
     */
    String SP_IS_FIRST_OPEN = "is_first_open";

    //================= SP缓存 ====================
}
