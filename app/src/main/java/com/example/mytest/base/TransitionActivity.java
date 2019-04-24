package com.example.mytest.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import com.blankj.utilcode.util.SPUtils;
import com.example.mytest.R;
import com.example.mytest.ui.main.LoginActivity;
import com.example.mytest.ui.main.MainActivity;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/3/25
 * Desc : 解决启动白屏问题
 */
public class TransitionActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 后台返回时可能启动这个页面 http://blog.csdn.net/jianiuqi/article/details/54091181
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        new Handler().postDelayed(() -> {
            boolean isLogin = SPUtils.getInstance().getBoolean("isLogin");
            if (isLogin) {
                Intent intent = new Intent(TransitionActivity.this, MainActivity.class);
                intent.putExtra("from", getIntent().getIntExtra("from", 0));
                startActivity(intent);
                overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
                finish();
            } else{
                startActivity(new Intent(TransitionActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
                finish();
            }
        }, 50);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
