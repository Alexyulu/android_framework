package com.example.shinelon.mytest.presenter.main;

import com.example.shinelon.mytest.app.Constants;
import com.example.shinelon.mytest.base.RxPresenter;
import com.example.shinelon.mytest.base.contract.main.LoginContract;
import com.example.shinelon.mytest.model.DataManager;
import com.example.shinelon.mytest.model.bean.LoginBean;
import com.example.shinelon.mytest.model.bean.PassCodeBean;
import com.example.shinelon.mytest.model.http.response.BaseResponse;
import com.example.shinelon.mytest.util.RxUtil;
import com.example.shinelon.mytest.widget.CommonSubscriber;

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
    public void getPass(String userPhone) {
        Map<String, Object> map = new HashMap<>();
        map.put("tranCode", Constants.GET_YZM);
        map.put("userPhone", userPhone);

        addSubscribe(dataManager.fetchPassCodeInfo(map)
                    .compose(RxUtil.<BaseResponse<PassCodeBean>>rxSchedulerHelper())
                    .compose(RxUtil.<PassCodeBean>handleResult())
                    .subscribeWith(new CommonSubscriber<PassCodeBean>(mView) {
                        @Override
                        public void onNext(PassCodeBean passCodeBean) {
                            mView.onGetPassSuccess(passCodeBean.getVerifyCode());
                        }
                    })
        );
    }

    @Override
    public void login(String userPhone, String passCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("tranCode", Constants.LOGIN);
        map.put("userPhone", userPhone);
        map.put("code", passCode);

        addSubscribe(dataManager.fetchLoginInfo(map)
                    .compose(RxUtil.<BaseResponse<LoginBean>>rxSchedulerHelper())
                    .compose(RxUtil.<LoginBean>handleResult())
                    .subscribeWith(new CommonSubscriber<LoginBean>(mView) {
                        @Override
                        public void onNext(LoginBean loginBean) {
                            mView.onLoginSuccess(loginBean);
                        }
                    }));
    }
}
