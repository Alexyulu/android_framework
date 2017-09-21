package com.example.shinelon.mytest.di.component;

import android.app.Activity;

import com.example.shinelon.mytest.di.module.ActivityModule;
import com.example.shinelon.mytest.di.scope.ActivityScope;
import com.example.shinelon.mytest.ui.main.LoginActivity;
import com.example.shinelon.mytest.ui.main.SplashActivity;

import dagger.Component;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(SplashActivity splashActivity);

    void inject(LoginActivity activity);
}
