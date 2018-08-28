package com.example.mytest.presenter.main;

import com.example.mytest.base.RxPresenter;
import com.example.mytest.base.contract.main.SplashContract;
import com.example.mytest.model.bean.LoginBean;
import com.example.mytest.util.L;

import javax.inject.Inject;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */

public class SplashPresenter extends RxPresenter<SplashContract.View> implements SplashContract.Presenter {

    @Inject
    public SplashPresenter() {

    }

    @Override
    public void judgeUserState(LoginBean body) {
        int roleType = body.getRoleType();
        switch (roleType) {
            case 0:
                L.i("本地有登录信息,且用户未选择身份");
                //getView().toSelectIdentity(loginInfo);
                mView.toSelectIdentity();
                break;
            case 1:
                L.d("用户有身份信息，且是普通用户");
                //getView().toEvaluate(loginInfo);
                mView.toEvaluate();
                break;
            case 2:
                L.d("用户有身份信息，且是大师用户");
                //getView().toMaster(loginInfo);
                mView.toMaster();
                break;
            default:
                //getView().toLogin();
                mView.toLogin();
                break;
        }

    }
}
