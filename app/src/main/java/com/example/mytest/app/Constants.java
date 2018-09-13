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

    //================= TYPE ====================
    int TYPE_MAIN = 0;

    int TYPE_INVENTORY = 1;

    int TYPE_CUSTOMER = 2;

    int TYPE_MY = 3;
    /**
     * 首页通用接口type分类
     *
     *  1:潜客跟进 2:潜客邀约 3:订单跟进 4:今日交车 5:后续服务
     */
    int TYPE_POTENTIAL_FOLLOW = 1;
    int TYPE_ORDER_FOLLOW = 2;

    int TYPE_POTENTIAL_INVITE = 1;
    int TYPE_DELIVERY_TODAY = 2;
    int TYPE_AFTER_SERVICE = 3;

    /**
     * 客户模块接口分类
     *
     * 修改为  modify  1,潜客2，订单3，交车 4，休眠5，战败
     */
    int TYPE_POTENTIAL = 1;
    int TYPE_ORDER = 2;
    int TYPE_DELIVERY = 3;
    int TYPE_DORMANT = 4;
    int TYPE_DEFEAT = 5;

    /**
     * 审批分类
     *
     * 0.客户审批   1.订单审批
     */
    int APPROVE_CUSTOMER = 0;
    int APPROVE_ORDER = 1;

    int EDIT_FILTER = 1;
    int RIGHT_FILTER = 2;

    int ADAPTER_LEVEL_FILTER = 1;
    int ADAPTER_MODEL_FILTER = 2;
    int ADAPTER_CONFIGURATION_FILTER = 3;

    int FRAGMENT_POTENTIAL = 0;
    int FRAGMENT_ORDER = 1;
    int FRAGMENT_DELIVERY = 2;
    int FRAGMENT_DORMANT = 3;
    int FRAGMENT_DEFEAT = 4;

    //================= TYPE ====================

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
