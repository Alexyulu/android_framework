package com.example.shinelon.mytest.di.module;

import com.example.shinelon.mytest.app.App;
import com.example.shinelon.mytest.model.DataManager;
import com.example.shinelon.mytest.model.http.HttpHelper;
import com.example.shinelon.mytest.model.http.RetrofitHelper;
import com.example.shinelon.mytest.model.prefs.ImplPreferencesHelper;
import com.example.shinelon.mytest.model.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : Application级别的Module，通过注解的方式编译时注入dex文件中，以反射的方式提供回调实例
 */
@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, preferencesHelper);
    }
}
