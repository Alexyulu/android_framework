package com.example.shinelon.mytest.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.example.shinelon.mytest.component.InitializeService;
import com.example.shinelon.mytest.di.component.AppComponent;
import com.example.shinelon.mytest.di.component.DaggerAppComponent;
import com.example.shinelon.mytest.di.module.AppModule;
import com.example.shinelon.mytest.di.module.HttpModule;

import java.util.HashSet;
import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : 自定义Application
 */

public class App extends Application {
    private static App instance;
    public static AppComponent appComponent;
    private Set<Activity> allActivities;
    private CompositeDisposable baseCompositeDisposable;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //获取屏幕宽高
        getScreenSize();

        //在其他线程完成初始化操作
        InitializeService.start(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    public void addActivity(Activity act) {
        if (allActivities == null) allActivities = new HashSet<>();
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (App.class) {
                for (Activity allActivity : allActivities) {
                    allActivity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        defaultDisplay.getMetrics(displayMetrics);

        DIMEN_RATE = displayMetrics.density / 1.0F;
        DIMEN_DPI = displayMetrics.densityDpi;
        SCREEN_WIDTH = displayMetrics.widthPixels;
        SCREEN_HEIGHT = displayMetrics.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
           appComponent = DaggerAppComponent.builder()
                   .appModule(new AppModule(instance))
                   .httpModule(new HttpModule())
                   .build();
        }
        return appComponent;
    }
}
