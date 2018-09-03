package com.example.mytest.util;

import com.example.mytest.model.http.exception.ApiException;
import com.example.mytest.model.http.response.BaseResponse;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */
public class RxUtil {

    /**
     * 统一线程处理
     * @param <T> T
     * @return FlowableTransformer
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一返回结果处理
     * @param <T> T
     * @return FlowableTransformer
     */
    public static <T> FlowableTransformer<BaseResponse<T>, T> handleResult() {   //compose判断结果
        return httpResponseFlowable -> httpResponseFlowable.flatMap((Function<BaseResponse<T>, Flowable<T>>) tBaseResponse -> {
            int code = tBaseResponse.getCode();
            String msg = tBaseResponse.getMessage();
            if (code == 200) {
                return createData(tBaseResponse.getData());
            } else {
                return Flowable.error(new ApiException(msg, code));
            }
        });
    }

    /**
     * 生成Flowable
     * @param <T> T
     * @return Flowable<T>
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        }, BackpressureStrategy.BUFFER);
    }
}
