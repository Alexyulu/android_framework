package com.example.shinelon.mytest.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.RelativeLayout;

import com.example.shinelon.mytest.R;
import com.example.shinelon.mytest.app.App;
import com.example.shinelon.mytest.base.BaseActivity;
import com.example.shinelon.mytest.base.contract.main.SplashContract;
import com.example.shinelon.mytest.model.bean.LoginBean;
import com.example.shinelon.mytest.model.prefs.ImplPreferencesHelper;
import com.example.shinelon.mytest.presenter.main.SplashPresenter;
import com.example.shinelon.mytest.util.LogUtils;
import com.example.shinelon.mytest.widget.CircleTextProgressbar;
import com.jaeger.library.StatusBarUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : Splash页
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.ctp_pass)
    CircleTextProgressbar ctpPass;
    @BindView(R.id.rl_need_offset)
    RelativeLayout rlNeedOffset;

    ImplPreferencesHelper implPreferencesHelper;
    LoginBean loginInfo;

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
        StatusBarUtil.setTranslucent(this, 0);
        implPreferencesHelper = App.getAppComponent().preferencesHelper();

        ctpPass.setCustomTextColor(Color.BLACK)
               .setProgressColor(Color.parseColor("#AAC6C6C6"))
               .setInCircleColor(Color.DKGRAY)
               .setTimeMillis(3000)
               .setProgressType(CircleTextProgressbar.ProgressType.COUNT)
               .setCustomText("跳过")
               .setCountdownProgressListener(0, new CircleTextProgressbar.OnCountdownProgressListener() {

            @Override
            public void onProgress(int what, int progress) {
                if (what == 0) {
                    //当进度条走到100时，调至引导页
                    if (progress == 100) {
                        judgeLoginInfo();
                    }
                }
            }
        }).start();

        RxView.clicks(ctpPass)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ctpPass.stop();
                        judgeLoginInfo();
                    }
                });
    }

    private void judgeLoginInfo() {
        loginInfo = implPreferencesHelper.getLoginInfo();
        if (loginInfo == null || TextUtils.isEmpty(loginInfo.getToken())) {
            LogUtils.i("没有登录信息");
            toLogin();
        } else {
            mPresenter.judgeUserState(loginInfo);
        }
    }

    @Override
    public void toLogin() {
//        ToastUtil.shortShow("去登录页面！");
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void toSelectIdentity() {

    }

    @Override
    public void toEvaluate() {
        //Intent intent = new Intent(this, EvaluateActivity.class);
        //startActivity(intent);
        //finish();
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void toMaster() {
        //Intent intent = new Intent(this, MasterNewActivity.class);
        //startActivity(intent);
        //finish();
    }
}
