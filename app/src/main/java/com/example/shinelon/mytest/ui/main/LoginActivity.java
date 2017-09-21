package com.example.shinelon.mytest.ui.main;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.shinelon.mytest.R;
import com.example.shinelon.mytest.app.App;
import com.example.shinelon.mytest.base.BaseActivity;
import com.example.shinelon.mytest.base.contract.main.LoginContract;
import com.example.shinelon.mytest.model.bean.LoginBean;
import com.example.shinelon.mytest.presenter.main.LoginPresenter;
import com.example.shinelon.mytest.util.LogUtils;
import com.example.shinelon.mytest.util.StringUtils;
import com.example.shinelon.mytest.util.SystemUtil;
import com.example.shinelon.mytest.util.ToastUtil;
import com.example.shinelon.mytest.util.Utils;
import com.jaeger.library.StatusBarUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import cn.iwgang.countdownview.CountdownView;
import io.reactivex.functions.Consumer;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/19
 * Desc : 登陆页
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    private static final int REQUEST_CALL_PERMISSION = 1;

    @BindView(R.id.iv_user_avatar)
    ImageView ivUserAvatar;

    @BindView(R.id.rb_yzm)
    RadioButton rbYzm;
    @BindView(R.id.v_yzm)
    View vYzm;
    @BindView(R.id.rb_pwd)
    RadioButton rbPwd;
    @BindView(R.id.v_pwd)
    View vPwd;

    @BindView(R.id.et_userPhone)
    EditText etUserPhone;
    @BindView(R.id.et_user_pwd)
    EditText etUserPwd;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.ll_pwd)
    LinearLayout llPwd;
    @BindView(R.id.ll_yzm)
    LinearLayout llYZM;

    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_user_pass)
    EditText etUserPass;
    @BindView(R.id.tv_get_pass)
    TextView tvGetPass;
    @BindView(R.id.cdv_login)
    CountdownView cdvLogin;
    @BindView(R.id.ll_count_down)
    LinearLayout llCountDown;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.register_rules)
    TextView registerRules;


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        StatusBarUtil.setTranslucent(this, 0);

        baseCompositeDisposable.addAll(
                RxView.clicks(tvGetPass)
                .throttleFirst(5000, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        onGetPassCodeClick();
                    }
                }),  RxView.clicks(btnLogin)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        onLoginClick();
                    }
                }));
    }

    private void onLoginClick() {
        if (!SystemUtil.isNetworkConnected()) {
            //onShowToast("网络不可用");
            ToastUtil.shortShow("网络不可用");
            return;
        }

        String userPhone = Utils.getText(etUser);
        String passCode = Utils.getText(etUserPass);

        if (TextUtils.isEmpty(userPhone)) {
            ToastUtil.shortShow("手机号不能为空");
            Utils.requestFocus(etUser);
        } else if (TextUtils.isEmpty(passCode)) {
            ToastUtil.shortShow("验证码不能为空");
            Utils.requestFocus(etUserPass);
        } else {
            //用户登录
            if (StringUtils.isPhone(userPhone)) {
                mPresenter.login(userPhone, passCode);
            } else {
                ToastUtil.shortShow("您输入的手机号有误");
            }
        }
    }

    private void onGetPassCodeClick() {
        if (!SystemUtil.isNetworkConnected()) {
            ToastUtil.shortShow("网络不可用");
            return;
        }

        String userPhone = Utils.getText(etUser);

        if (TextUtils.isEmpty(userPhone)) {
            ToastUtil.shortShow("手机号不能为空");
            Utils.requestFocus(etUser);
        } else {
            tvGetPass.setEnabled(false);
            mPresenter.getPass(userPhone);
            etUser.clearFocus();
            Utils.requestFocus(etUserPass);
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onGetPassSuccess(String verifyCode) {
        LogUtils.d(verifyCode);

        tvGetPass.setTextColor(Color.parseColor("#00FFFFFF"));
        tvGetPass.setEnabled(false);
        tvGetPass.setClickable(false);
        llCountDown.setVisibility(View.VISIBLE);
        cdvLogin.start(60000L);
        cdvLogin.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                llCountDown.setVisibility(View.GONE);
                tvGetPass.setTextColor(Color.parseColor("#57585f"));
                tvGetPass.setEnabled(true);
                tvGetPass.setClickable(true);
            }
        });
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        LogUtils.d(loginBean.toString());
        App.getAppComponent().preferencesHelper().setLoginInfo(loginBean);

        LoginBean loginInfo = App.getAppComponent().preferencesHelper().getLoginInfo();
        LogUtils.d(loginInfo.toString() + " ===================");
    }
}
