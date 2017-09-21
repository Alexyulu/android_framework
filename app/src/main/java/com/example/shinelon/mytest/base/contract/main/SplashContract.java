package com.example.shinelon.mytest.base.contract.main;

import com.example.shinelon.mytest.base.BasePresenter;
import com.example.shinelon.mytest.base.BaseView;
import com.example.shinelon.mytest.model.bean.LoginBean;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */

public interface SplashContract {
    interface View extends BaseView {

        void toLogin();

        void toSelectIdentity();

        void toEvaluate();

        void toMaster();
    }

    interface Presenter extends BasePresenter<View> {

        void judgeUserState(LoginBean bodyBean);
    }
}
