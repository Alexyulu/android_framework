package com.example.shinelon.mytest.model.http.api;

import com.example.shinelon.mytest.app.Constants;
import com.example.shinelon.mytest.model.bean.LoginBean;
import com.example.shinelon.mytest.model.bean.PassCodeBean;
import com.example.shinelon.mytest.model.http.response.BaseResponse;

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
    /*@FormUrlEncoded
    @POST(API.POST_URL)
    Call<ForgetPwdModel> forgetPwdReq(@FieldMap Map<String, Object> hashMap);

    @FormUrlEncoded
    @POST(API.POST_URL)
    Call<LoginModel> loginByPwdReq(@FieldMap Map<String, Object> hashMap);

    @FormUrlEncoded
    @POST(API.POST_URL)
    Observable<BaseModel<LoginNewModel>> loginRxPwdReq(@FieldMap Map<String, Object> hashMap);*/

    @FormUrlEncoded
    @POST(Constants.POST_URL)
    Flowable<BaseResponse<LoginBean>> loginReq(@FieldMap Map<String, Object> hashMap);

    @FormUrlEncoded
    @POST(Constants.POST_URL)
    Flowable<BaseResponse<PassCodeBean>> getPassReq(@FieldMap Map<String, Object> hashMap);
}
