package com.example.shinelon.mytest.base;

import android.content.Intent;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shinelon.mytest.app.App;
import com.example.shinelon.mytest.di.component.ActivityComponent;
import com.example.shinelon.mytest.di.component.DaggerActivityComponent;
import com.example.shinelon.mytest.di.module.ActivityModule;
import com.example.shinelon.mytest.model.bean.LoginBean;
import com.example.shinelon.mytest.model.http.exception.CodeDetail;
import com.example.shinelon.mytest.ui.main.LoginActivity;
import com.example.shinelon.mytest.util.SnackbarUtil;

import javax.inject.Inject;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : MVP activity基类
 */

public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {
    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detach();
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void operateErrCode(String code) {
        if (!TextUtils.isEmpty(code)) {
            if (code.equals("ERR0013")) {
                Toast.makeText(App.getInstance(), "登录信息有误，请重新登录", Toast.LENGTH_SHORT).show();
                App.getAppComponent().preferencesHelper().setLoginInfo(new LoginBean());
                Intent intent = new Intent(App.getInstance(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                Toast.makeText(App.getInstance(), CodeDetail.judgeCode(code), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    protected abstract void initInject();
}
