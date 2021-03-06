package com.example.mytest.model.http.api;

import com.example.mytest.app.AppConfig;
import com.example.mytest.model.bean.LoginBean;
import com.example.mytest.model.http.response.BaseResponse;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author: luyu
 * EMail : luyu1235211@163.com
 * Date : 2017/5/27
 * Desc : 登录页网络请求接口
 */
public interface MainApi {
    @FormUrlEncoded
    @POST(AppConfig.APP_LOGIN)
    Flowable<BaseResponse<LoginBean>> loginReq(@FieldMap Map<String, Object> hashMap);
}
