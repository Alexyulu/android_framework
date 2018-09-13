package com.example.mytest.base.contract.main;

import com.example.mytest.base.BasePresenter;
import com.example.mytest.base.BaseView;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */

public interface SplashContract {
    interface View extends BaseView {

        void toLogin();

    }

    interface Presenter extends BasePresenter<View> {

        void checkVersion();

        void judgeStateAndCopyDB();
    }
}
