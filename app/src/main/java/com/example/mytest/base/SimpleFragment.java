package com.example.mytest.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.mytest.R;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */

public abstract class SimpleFragment extends SupportFragment {
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;
    protected boolean isInited = false;

    public static final int MESSAGE_WHAT_TIMMING_CANCEL = 1;// 定时取消缓存打圈的msg what
    public static int POST_DELAY_TIME = 60 * 1000;// 获取数据最长加载时间
    public View mProgressBarView;// 缓存打圈布局
    public CompositeDisposable baseCompositeDisposable;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        if (baseCompositeDisposable == null) {
            baseCompositeDisposable = new CompositeDisposable();
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        isInited = true;
        initEventAndData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        if (baseCompositeDisposable != null) {
            baseCompositeDisposable.clear();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();

    /**
     * 初始化获取数据打圈
     */
    public void createProgressBar() {
        if (mProgressBarView == null) {
            mProgressBarView = LayoutInflater.from(getContext()).inflate(R.layout.progressbar_custom, null);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.gravity = Gravity.CENTER;
            //是否显示进度值
            //TextView textView = (TextView) mProgressBarView.findViewById(R.id.tv_progress);
            //判断是否显示遮罩
            //TextView mask = (TextView) mProgressBarView.findViewById(R.id.tv_mask);
            FragmentActivity activity = getActivity();
            try {
                if (activity != null)
                    activity.addContentView(mProgressBarView, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        simpleHandler.sendEmptyMessageDelayed(MESSAGE_WHAT_TIMMING_CANCEL, POST_DELAY_TIME);//超时发送通知取消打圈
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

    private final SimpleFragment.simpleHandler simpleHandler = new SimpleFragment.simpleHandler(this);

    private static class simpleHandler extends Handler {
        private final WeakReference<SimpleFragment> mFragment;

        private simpleHandler(SimpleFragment simpleFragment) {
            mFragment = new WeakReference<>(simpleFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            SimpleFragment simpleFragment = mFragment.get();
            if (simpleFragment != null) {
                switch (msg.what) {
                    //定时取消打圈
                    case MESSAGE_WHAT_TIMMING_CANCEL:
                        if (simpleFragment.mProgressBarView != null && simpleFragment.mProgressBarView.getVisibility() == View.VISIBLE) {
                            simpleFragment.mProgressBarView.setVisibility(View.GONE);
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
}
