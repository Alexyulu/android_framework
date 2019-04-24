package com.example.mytest.model.contract.main;

import com.example.mytest.base.BasePresenter;
import com.example.mytest.base.BaseView;
import com.example.mytest.model.bean.LoginBean;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/19
 * Desc :
 */

public interface LoginContract {

    interface Presenter extends BasePresenter<View> {

        void login(String userPhone, String passCode);
    }

    interface View extends BaseView{

        void onLoginSuccess(LoginBean loginBean);
    }
}
