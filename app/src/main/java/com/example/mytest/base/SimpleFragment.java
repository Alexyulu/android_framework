package com.example.mytest.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.mytest.R;
import com.example.mytest.util.ProgressDialogUtils;

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

    public CompositeDisposable baseCompositeDisposable;
    private ProgressDialogUtils progressInstance;

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
        progressInstance = ProgressDialogUtils.getInstance(mContext);
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
        progressInstance.show();
    }

    /**
     * 取消progressbar
     */
    public void dismissProgressbar() {
        progressInstance.dismiss();
    }

    @SuppressWarnings("ConstantConditions")
    protected void initToolBar(String title, Boolean backUpButtonEnabled) {
        View view = mView.findViewById(R.id.toolbar);
        TextView toolbarTitle = mView.findViewById(R.id.toolbar_title_tv);
        TextView btn_back=mView.findViewById(R.id.btn_back);
        if (view != null) {
            Toolbar toolbar = (Toolbar) view;
            toolbar.setTitle("");
            toolbarTitle.setText(title);
            ((AppCompatActivity)getActivity()).setActionBar(toolbar);
//            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(backUpButtonEnabled);
//            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(backUpButtonEnabled);
            btn_back.setVisibility(backUpButtonEnabled ? View.VISIBLE : View.GONE);
//            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onBackPressedSupport();
//                }
//            });
            btn_back.setOnClickListener(view1 -> onBackPressedSupport());
        }
    }
}
