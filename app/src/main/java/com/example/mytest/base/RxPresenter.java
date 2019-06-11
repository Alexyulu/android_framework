package com.example.mytest.base;

import com.example.mytest.component.RxBus;
import com.example.mytest.model.http.response.BaseResponse;
import com.example.mytest.util.RxUtil;
import com.example.mytest.widget.CommonSubscriber;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : 基于Rx的Presenter封装,控制订阅的生命周期
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T mView;
    protected CompositeDisposable mCompositeDisposable;

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    protected <U> void addRxBusSubscribe(Class<U> eventType, Consumer<U> act) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(RxBus.getDefault().toDefaultFlowable(eventType, act));
    }

    public <R> void doDefault(Flowable<BaseResponse<R>> baseResponseFlowable, CommonSubscriber<R> subscriber) {
        addSubscribe(baseResponseFlowable.compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult())
                .subscribeWith(subscriber));
    }

    @Override
    public void attach(T view) {
        this.mView = view;
    }

    @Override
    public void detach() {
        this.mView = null;
        unSubscribe();
    }
}
