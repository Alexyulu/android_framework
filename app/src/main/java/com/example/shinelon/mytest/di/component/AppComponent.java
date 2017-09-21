package com.example.shinelon.mytest.di.component;

import com.example.shinelon.mytest.app.App;
import com.example.shinelon.mytest.di.module.AppModule;
import com.example.shinelon.mytest.di.module.HttpModule;
import com.example.shinelon.mytest.model.DataManager;
import com.example.shinelon.mytest.model.http.RetrofitHelper;
import com.example.shinelon.mytest.model.prefs.ImplPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}
