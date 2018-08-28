package com.example.mytest.base.contract.main;

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

        void getPass(String userPhone);

        void login(String userPhone, String passCode);
    }

    interface View extends BaseView{

        void onGetPassSuccess(String verifyCode);

        void onLoginSuccess(LoginBean loginBean);
    }
}
