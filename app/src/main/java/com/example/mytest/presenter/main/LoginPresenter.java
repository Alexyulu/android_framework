package com.example.mytest.presenter.main;

import com.example.mytest.base.RxPresenter;
import com.example.mytest.model.DataManager;
import com.example.mytest.model.bean.LoginBean;
import com.example.mytest.model.contract.main.LoginContract;
import com.example.mytest.util.RxUtil;
import com.example.mytest.widget.CommonSubscriber;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/19
 * Desc :
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    private DataManager dataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void login(String userPhone, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("userPhone", userPhone);
        map.put("password", password);
        //map.put("platForm", 1);

        addSubscribe(dataManager.fetchLoginInfo(map)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult())
                .subscribeWith(new CommonSubscriber<LoginBean>(mView) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.onLoginSuccess(loginBean);
                    }
                }));
    }
}
