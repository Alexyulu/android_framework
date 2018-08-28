package com.example.mytest.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.mytest.R;
import com.example.mytest.app.App;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */

public abstract class SimpleActivity extends SupportActivity{
    public static final int MESSAGE_WHAT_TIMMING_CANCEL = 1;  // 定时取消缓存打圈的msg what
    public static int POST_DELAY_TIME = 60 * 1000;             // 获取数据最长加载时间

    protected Activity mContext;
    private Unbinder mUnBinder;
    public CompositeDisposable baseCompositeDisposable;
    public View mProgressBarView;// 缓存打圈布局


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mContext = this;
        mUnBinder = ButterKnife.bind(this);
        onViewCreated();
        App.getInstance().addActivity(this);
        if (baseCompositeDisposable == null) {baseCompositeDisposable = new CompositeDisposable();}
        initEventAndData();
    }

    @SuppressWarnings("ConstantConditions")
    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(view -> onBackPressedSupport());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnBinder.unbind();
        if (baseCompositeDisposable != null) {
            baseCompositeDisposable.clear();
        }
    }

    /**
     * 初始化获取数据打圈
     */
    @SuppressLint("InflateParams")
    public void createProgressBar() {
        if (mProgressBarView == null) {
            mProgressBarView = LayoutInflater.from(this).inflate(R.layout.progressbar_custom, null);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.gravity = Gravity.CENTER;
            //是否显示进度值
            //TextView textView = (TextView) mProgressBarView.findViewById(R.id.tv_progress);
            //判断是否显示遮罩
            //TextView mask = (TextView) mProgressBarView.findViewById(R.id.tv_mask);
            this.addContentView(mProgressBarView, params);
        }
        simpleHandler.sendEmptyMessageDelayed(MESSAGE_WHAT_TIMMING_CANCEL, POST_DELAY_TIME);//超时发送通知取消打圈
        /*if (maskShow) { //遮罩
            mask.setVisibility(View.VISIBLE);
        }*/
       /* if (progressShow) {//百分比
            tv_progress.setVisibility(View.VISIBLE);
        }*/
    }

    /**
     * 取消progressbar
     */
    public void dismissProgressbar() {
        if (mProgressBarView != null && mProgressBarView.getVisibility() == View.VISIBLE) {
            mProgressBarView.setVisibility(View.GONE);
            //判断handler中是否有打圈的消息，如果存在清除掉
            if (simpleHandler.hasMessages(MESSAGE_WHAT_TIMMING_CANCEL)) {
                simpleHandler.removeMessages(MESSAGE_WHAT_TIMMING_CANCEL);
            }
        }
    }

    private final simpleHandler simpleHandler = new simpleHandler(this);

    private static class simpleHandler extends Handler {
        private final WeakReference<SimpleActivity> mActivity;

        private simpleHandler(SimpleActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SimpleActivity simpleActivity = mActivity.get();
            if (simpleActivity != null) {
                switch (msg.what) {
                    //定时取消打圈
                    case MESSAGE_WHAT_TIMMING_CANCEL:
//                        ToastUtils.showToast(simpleActivity, simpleActivity.getString(R.string.connect_timeout));
                        if (simpleActivity.mProgressBarView != null && simpleActivity.mProgressBarView.getVisibility() == View.VISIBLE) {
                            simpleActivity.mProgressBarView.setVisibility(View.GONE);
                            //判断handler中是否有打圈的消息，如果存在清除掉
                            if (this.hasMessages(MESSAGE_WHAT_TIMMING_CANCEL)) {
                                this.removeMessages(MESSAGE_WHAT_TIMMING_CANCEL);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    protected void onViewCreated() {

    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();
}
