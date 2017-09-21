package com.example.shinelon.mytest.model;

import com.example.shinelon.mytest.model.bean.LoginBean;
import com.example.shinelon.mytest.model.bean.PassCodeBean;
import com.example.shinelon.mytest.model.http.HttpHelper;
import com.example.shinelon.mytest.model.prefs.PreferencesHelper;
import com.example.shinelon.mytest.model.http.response.BaseResponse;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */

public class DataManager implements HttpHelper, PreferencesHelper{
    HttpHelper httpHelper;
    PreferencesHelper preferencesHelper;

    public DataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
        this.httpHelper = httpHelper;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public Flowable<BaseResponse<PassCodeBean>> fetchPassCodeInfo(Map<String, Object> map) {
        return httpHelper.fetchPassCodeInfo(map);
    }

    public Flowable<BaseResponse<LoginBean>> fetchLoginInfo(Map<String, Object> map) {
        return httpHelper.fetchLoginInfo(map);
    }
}
