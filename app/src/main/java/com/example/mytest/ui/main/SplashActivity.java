package com.example.mytest.ui.main;

import android.content.Intent;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.example.mytest.R;
import com.example.mytest.app.App;
import com.example.mytest.base.BaseActivity;
import com.example.mytest.base.contract.main.SplashContract;
import com.example.mytest.model.bean.LoginBean;
import com.example.mytest.model.prefs.ImplPreferencesHelper;
import com.example.mytest.presenter.main.SplashPresenter;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : Splash页
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    ImplPreferencesHelper implPreferencesHelper;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEventAndData() {
//        StatusBarUtil.setTranslucentForImageView(this, 225, rlNeedOffset);
//        StatusBarUtil.setTranslucent(this, 0);
        implPreferencesHelper = App.getAppComponent().preferencesHelper();

        //judgeLoginInfo();

        mPresenter.checkVersion();
        //延迟1000毫秒
        judgeLoginInfo();
    }

    private void judgeLoginInfo() {
        LoginBean loginInfo = implPreferencesHelper.getLoginInfo();
        if (loginInfo == null || TextUtils.isEmpty(loginInfo.getToken())) {
            LogUtils.i("没有登录信息");
            toLogin();
        } else {
            mPresenter.judgeStateAndCopyDB();
        }
    }

    @Override
    public void toLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
