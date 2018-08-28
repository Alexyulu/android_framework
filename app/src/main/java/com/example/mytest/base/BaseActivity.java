package com.example.mytest.base;

import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.example.mytest.app.App;
import com.example.mytest.di.component.ActivityComponent;
import com.example.mytest.di.component.DaggerActivityComponent;
import com.example.mytest.di.module.ActivityModule;
import com.example.mytest.util.SnackbarUtil;

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
        dismissProgressbar();
        SnackbarUtil.show(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void operateErrCode(int code, String errMsg) {
        dismissProgressbar();
        ToastUtils.showShort(errMsg);

        /*if (!TextUtils.isEmpty(code)) {
            if (code.equals("ERR0013")) {
                Toast.makeText(App.getInstance(), "登录信息有误，请重新登录", Toast.LENGTH_SHORT).show();
                App.getAppComponent().preferencesHelper().setLoginInfo(new LoginBean());
                Intent intent = new Intent(App.getInstance(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                Toast.makeText(App.getInstance(), CodeDetail.judgeCode(code), Toast.LENGTH_SHORT).show();
            }
        }*/
    }

    @Override
    public void stateError() {
        dismissProgressbar();
    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {
        createProgressBar();
    }

    @Override
    public void stateMain() {
        dismissProgressbar();
    }

    protected abstract void initInject();
}
