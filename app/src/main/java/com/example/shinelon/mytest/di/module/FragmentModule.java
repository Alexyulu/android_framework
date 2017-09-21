package com.example.shinelon.mytest.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.shinelon.mytest.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : FragmentModule
 */
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
