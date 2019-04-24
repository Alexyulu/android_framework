package com.example.mytest.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.mytest.R;
import com.example.mytest.app.App;
import com.example.mytest.util.ProgressDialogUtils;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */

public abstract class SimpleActivity extends SupportActivity{
    protected Activity mContext;
    private Unbinder mUnBinder;
    public CompositeDisposable baseCompositeDisposable;
    private ProgressDialogUtils instance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarColor();
        setContentView(getLayout());
        mContext = this;
        mUnBinder = ButterKnife.bind(this);
        onViewCreated();
        App.getInstance().addActivity(this);
        if (baseCompositeDisposable == null) {baseCompositeDisposable = new CompositeDisposable();}

        instance = ProgressDialogUtils.getInstance(this);
        statusBarTextColor();
        initEventAndData();
    }

    @SuppressWarnings("ConstantConditions")
    protected void initToolBar(String title, Boolean backUpButtonEnabled) {
        View view = findViewById(R.id.toolbar);
        TextView toolbarTitle = findViewById(R.id.toolbar_title_tv);
        TextView btn_back = findViewById(R.id.btn_back);
        if (view != null) {
            Toolbar toolbar = (Toolbar) view;
            toolbar.setTitle("");
            toolbarTitle.setText(title);
            setSupportActionBar(toolbar);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(backUpButtonEnabled);
            getSupportActionBar().setDisplayShowHomeEnabled(backUpButtonEnabled);
            btn_back.setVisibility(backUpButtonEnabled ? View.VISIBLE : View.GONE);
            btn_back.setOnClickListener(view12 -> onBackPressedSupport());
        }
    }

    public void statusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(getWindow().getDecorView(), Color.TRANSPARENT);  //改为透明
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void statusBarTextColor() {
        //状态栏中的文字颜色和图标颜色，需要android系统6.0以上，而且目前只有一种可以修改（一种是深色，一种是浅色即白色）
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnBinder.unbind();
        if (baseCompositeDisposable != null) {
            baseCompositeDisposable.clear();
        }
    }

    /**
     * 初始化获取数据打圈
     */
    @SuppressLint("InflateParams")
    public void createProgressBar() {
        instance.show();
    }

    /**
     * 取消progressbar
     */
    public void dismissProgressbar() {
        instance.dismiss();
    }

    protected void onViewCreated() {

    }

    protected abstract void initEventAndData();

    protected abstract int getLayout();
}
