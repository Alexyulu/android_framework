package com.example.mytest.model.http;

import com.example.mytest.model.bean.LoginBean;
import com.example.mytest.model.bean.PassCodeBean;
import com.example.mytest.model.http.api.MainApi;
import com.example.mytest.model.http.response.BaseResponse;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : HttpHelper的api实现类，编译时注入api接口
 */

public class RetrofitHelper implements HttpHelper {

    private MainApi mMainApi;

    @Inject
    public RetrofitHelper(MainApi mainApi) {
        mMainApi = mainApi;
    }

    @Override
    public Flowable<BaseResponse<PassCodeBean>> fetchPassCodeInfo(Map<String, Object> map) {
        return mMainApi.getPassReq(map);
    }

    @Override
    public Flowable<BaseResponse<LoginBean>> fetchLoginInfo(Map<String, Object> map) {
        return mMainApi.loginReq(map);
    }
}
