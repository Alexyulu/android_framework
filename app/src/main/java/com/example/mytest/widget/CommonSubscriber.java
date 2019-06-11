package com.example.mytest.widget;

import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.example.mytest.base.BaseView;
import com.example.mytest.model.http.exception.ApiException;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : 抽象观察者对异常做统一调度
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;
    private String mErrorMsg;
    private boolean isShowErrorState = true;

    protected CommonSubscriber(BaseView view){
        this.mView = view;
    }

    protected CommonSubscriber(BaseView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected CommonSubscriber(BaseView view, boolean isShowErrorState){
        this.mView = view;
        this.isShowErrorState = isShowErrorState;
    }

    protected CommonSubscriber(BaseView view, String errorMsg, boolean isShowErrorState){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowErrorState = isShowErrorState;
    }

    @Override
    public void onComplete() {
        mView.stateMain();
    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        mView.stateError();
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showErrorMsg(mErrorMsg);
        } else if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            mView.operateErrCode(apiException.getCode(), apiException.getErrMsg());
        } else if (e instanceof HttpException) {
            mView.showErrorMsg("数据加载失败");
        } else if (e instanceof ConnectException) {
            mView.showErrorMsg("连接服务器失败");
        } else if (e instanceof SocketTimeoutException) {
            mView.showErrorMsg("连接服务器超时");
        } else if (e instanceof NumberFormatException) {
            mView.showErrorMsg("数据转换异常");
            e.printStackTrace();
        } else if (e instanceof JsonSyntaxException) {
            mView.showErrorMsg("数据解析错误");
            e.printStackTrace();
        } else if (e instanceof NullPointerException) {
            mView.showErrorMsg("无数据");
            e.printStackTrace();
        } else {
            mView.showErrorMsg("未知错误");
            e.printStackTrace();
            LogUtils.d(e.toString());
        }
        LogUtils.e(e);
        if (isShowErrorState) {
            mView.stateError();
        }
    }

    @Override
    public void onNext(T t) {
        mView.stateMain();
        successReturn(t);
    }

    public abstract void successReturn(T data);
}
