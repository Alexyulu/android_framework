package com.example.shinelon.mytest.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.shinelon.mytest.app.App;
import com.example.shinelon.mytest.model.bean.LoginBean;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */

public class ImplPreferencesHelper implements PreferencesHelper{
    private static final String SHAREDPREFERENCES_NAME = "my_sp";

    private static final String KEY_ACCOUNT = "account";

    private final SharedPreferences mSPrefs;

    @Inject
    public ImplPreferencesHelper() {
        mSPrefs = App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public LoginBean getLoginInfo() {
        String accountJson = mSPrefs.getString(KEY_ACCOUNT, null);
        if (!TextUtils.isEmpty(accountJson)) {
           return new GsonBuilder().create().fromJson(accountJson, LoginBean.class);
        } else {
            return null;
        }
    }

    public void setLoginInfo(LoginBean loginBean) {
        mSPrefs.edit().putString(KEY_ACCOUNT, new GsonBuilder().create().toJson(loginBean)).apply();
    }
}
