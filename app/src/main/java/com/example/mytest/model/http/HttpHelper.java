package com.example.mytest.model.http;

import com.example.mytest.model.bean.LoginBean;
import com.example.mytest.model.http.response.BaseResponse;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : 与API调取有关的接口类
 */

public interface HttpHelper {

    Flowable<BaseResponse<LoginBean>> fetchLoginInfo(Map<String, Object> map);
}
