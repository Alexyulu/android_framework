package com.example.mytest.model;

import com.example.mytest.model.bean.LoginBean;
import com.example.mytest.model.http.HttpHelper;
import com.example.mytest.model.http.response.BaseResponse;
import com.example.mytest.model.prefs.PreferencesHelper;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */

public class DataManager implements HttpHelper, PreferencesHelper{
    private HttpHelper httpHelper;
    private PreferencesHelper preferencesHelper;

    public DataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
        this.httpHelper = httpHelper;
        this.preferencesHelper = preferencesHelper;
    }

    public Flowable<BaseResponse<LoginBean>> fetchLoginInfo(Map<String, Object> map) {
        return httpHelper.fetchLoginInfo(map);
    }
}
