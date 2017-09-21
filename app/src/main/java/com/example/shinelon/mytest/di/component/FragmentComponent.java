package com.example.shinelon.mytest.di.component;

import android.app.Activity;

import com.example.shinelon.mytest.di.module.FragmentModule;
import com.example.shinelon.mytest.di.scope.FragmentScope;

import dagger.Component;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
}
