package com.example.mytest.presenter.main;

import com.example.mytest.app.App;
import com.example.mytest.base.RxPresenter;
import com.example.mytest.model.contract.main.SplashContract;
import com.example.mytest.model.DataManager;
import com.example.mytest.model.bean.LoginBean;
import com.example.mytest.util.RxUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */

public class SplashPresenter extends RxPresenter<SplashContract.View> implements SplashContract.Presenter {

    private DataManager dataManager;

    @Inject
    public SplashPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void checkVersion() {

    }

    @Override
    public void judgeStateAndCopyDB() {
        addSubscribe(Flowable.timer(1000, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(aLong -> judgeUserState()));
    }

    private void judgeUserState() {
        LoginBean loginInfo = App.getAppComponent().preferencesHelper().getLoginInfo();

    }
}
