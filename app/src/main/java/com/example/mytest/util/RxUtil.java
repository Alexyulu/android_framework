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
     * 统一返回结果处理
     * @param <T>
     * @return
     *//*
    public static <T> FlowableTransformer<WXHttpResponse<T>, T> handleWXResult() {   //compose判断结果
        return new FlowableTransformer<WXHttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<WXHttpResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<WXHttpResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(WXHttpResponse<T> tWXHttpResponse) {
                        if(tWXHttpResponse.getCode() == 200) {
                            return createData(tWXHttpResponse.getNewslist());
                        } else {
                            return Flowable.error(new ApiException(tWXHttpResponse.getMsg(), tWXHttpResponse.getCode()));
                        }
                    }
                });
            }
        };
    }*/

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     *//*
    public static <T> FlowableTransformer<MyHttpResponse<T>, T> handleMyResult() {   //compose判断结果
        return new FlowableTransformer<MyHttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<MyHttpResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<MyHttpResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(MyHttpResponse<T> tMyHttpResponse) {
                        if(tMyHttpResponse.getCode() == 200) {
                            return createData(tMyHttpResponse.getData());
                        } else {
                            return Flowable.error(new ApiException(tMyHttpResponse.getMessage(), tMyHttpResponse.getCode()));
                        }
                    }
                });
            }
        };
    }*/

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     *//*
    public static <T> FlowableTransformer<GoldHttpResponse<T>, T> handleGoldResult() {   //compose判断结果
        return new FlowableTransformer<GoldHttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<GoldHttpResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<GoldHttpResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(GoldHttpResponse<T> tGoldHttpResponse) {
                        if(tGoldHttpResponse.getResults() != null) {
                            return createData(tGoldHttpResponse.getResults());
                        } else {
                            return Flowable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }*/

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
