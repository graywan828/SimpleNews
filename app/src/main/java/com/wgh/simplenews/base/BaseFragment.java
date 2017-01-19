package com.wgh.simplenews.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import rx.Subscription;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/1/18
 */

public abstract class BaseFragment extends Fragment{

    protected Subscription subscription;
    public Context mContext;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getActivity();

        registerListener();
        initViews();
        initData();
    }

    /**
     * 绑定监听器
     */
    protected abstract void registerListener();

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}