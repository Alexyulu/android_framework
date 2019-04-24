package com.example.mytest.ui.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.mytest.R;
import com.example.mytest.app.App;
import com.example.mytest.app.Constants;
import com.example.mytest.base.BaseActivity;
import com.example.mytest.model.bean.LoginBean;
import com.example.mytest.model.contract.main.LoginContract;
import com.example.mytest.presenter.main.LoginPresenter;
import com.example.mytest.util.Utils;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/19
 * Desc : 登陆页
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_user_pass)
    EditText etUserPass;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        //每当重新打开登陆的时候置空
        App.getAppComponent().preferencesHelper().setLoginInfo(new LoginBean());
        SPUtils.getInstance().put("sessionid", "");

        baseCompositeDisposable.add(RxView.clicks(btnLogin)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .subscribe(o -> onLoginClick()));

        //检查用户是否时首次打开登陆，如果是，则提示去设置打开所有通知权限
        boolean aBoolean = SPUtils.getInstance().getBoolean(Constants.SP_IS_FIRST_OPEN, true);

        if (aBoolean) {
            openApplicationSettings();
        }

    }

    private void onLoginClick() {
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("网络不可用");
            return;
        }

        String userPhone = Utils.getText(etUser);
        String passCode = Utils.getText(etUserPass);

        if (TextUtils.isEmpty(userPhone)) {
            ToastUtils.showShort("账号不能为空");
            Utils.requestFocus(etUser);
        } else if (TextUtils.isEmpty(passCode)) {
            ToastUtils.showShort("密码不能为空");
            Utils.requestFocus(etUserPass);
        } else {
            //用户登录
            stateLoading();
            mPresenter.login(userPhone, passCode);
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        stateMain();

        App.getAppComponent().preferencesHelper().setLoginInfo(loginBean);
        //将token放出sp中
        SPUtils.getInstance().put("sessionid", loginBean.getSessionId());

        SPUtils.getInstance().put("isLogin", true);

        LogUtils.d("sessionid = " + loginBean.getSessionId());
        //LogUtils.d("tag = " + loginBean.getTag());

        ToastUtils.showShort("登陆成功");

        //设置alias别名
        //setAlias(loginBean.getAlia());
//        setTag(loginBean.getTag());

        //登陆成功  去首页
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void openApplicationSettings() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("通知权限申请")
                .setMessage("请点击设置，在 应用详情 → 通知管理 中打开 <所有通知权限>，以便更好的使用本应用")
                .setPositiveButton("设置", (dialog, which) -> {
                    SPUtils.getInstance().put(Constants.SP_IS_FIRST_OPEN, false);
                    //去允许通知的设置页
                    goToNotificationSetting();
                    /*Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + LoginActivity.this.getPackageName()));
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    startActivityForResult(intent, SETTINGS_REQUEST_CODE);*/
                })
                .setNegativeButton("取消", (dialog, which) -> {
                    SPUtils.getInstance().put(Constants.SP_IS_FIRST_OPEN, false);
                    dialog.dismiss();
                    //ToastUtils.showShort("您的通知功能可能会收到影响，请在设置→应用管理→智如下将所有通知权限开启");
                });
        builder.setCancelable(false);
        builder.show();
    }

    private void goToNotificationSetting() {
        Intent intent = new Intent();
        /*if (Build.VERSION.SDK_INT >= 26) {
            // android 8.0引导
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", this.getPackageName());
        } else if (Build.VERSION.SDK_INT >= 21) {
            // android 5.0-7.0
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", this.getPackageName());
            intent.putExtra("app_uid", this.getApplicationInfo().uid);
        } else {
            // 其他
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", this.getPackageName(), null));
        }*/
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this.getPackageName(), null));

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
