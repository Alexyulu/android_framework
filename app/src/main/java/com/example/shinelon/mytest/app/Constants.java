package com.example.shinelon.mytest.app;

import android.os.Environment;

import java.io.File;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : 静态类
 */

public class Constants {
    //生产环境的base url     http://service.ershouchedashi.com/
    //测试环境的base url     http://test-service.ershouchedashi.com:8080/
    public static final String BASE_URL = "http://test-service.ershouchedashi.com:8080/";

    //生产环境的post url     tms.do   注意版本号是否更改了！！！
    //测试环境的post url     master-service/tms.do
    public static final String POST_URL = "master-service/tms.do";

    //================= 登陆 ====================
    //获取验证码
    public static final String GET_YZM = "C001";
    //登录
    public static final String LOGIN = "U001";
    //密码登录
    public static final String LOGIN_PWD = "U009";
    //忘记密码
    public static final String FORGET_PWD = "U010";

    //================= PATH ====================

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test" + File.separator + "MyTest";
}
