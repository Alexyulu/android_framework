package com.example.shinelon.mytest.base;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */

public interface BasePresenter<T extends BaseView> {
    void attach(T view);

    void detach();
}
