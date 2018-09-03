package com.example.mytest.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.example.mytest.app.App;
import com.example.mytest.di.component.DaggerFragmentComponent;
import com.example.mytest.di.component.FragmentComponent;
import com.example.mytest.di.module.FragmentModule;
import com.example.mytest.util.SnackbarUtil;

import javax.inject.Inject;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : BaseFragment
 */

public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {
    @Inject
    protected T mPresenter;

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attach(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detach();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        dismissProgressbar();
        FragmentActivity activity = getActivity();
        try {
            if (activity != null)
                SnackbarUtil.show(((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0), msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void operateErrCode(int code, String errMsg) {
        dismissProgressbar();
        ToastUtils.showShort(errMsg);

        //token验证失效
        /*if (code == 1038 || code == 1037) {
            //关闭所有页面 重新打开登陆页面
            //MyApp.getInstance().finishAllActivty();
            Intent intent = new Intent(mActivity, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
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
