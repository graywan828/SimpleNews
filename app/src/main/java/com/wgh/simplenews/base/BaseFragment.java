package com.wgh.simplenews.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/1/18
 */

public abstract class BaseFragment extends Fragment{

    public Activity mContext;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        mContext = getActivity();

        initViews();
        registerListener();
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
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }

    public void onUnsubscribe(){
        if(mCompositeSubscription!=null)
            mCompositeSubscription.unsubscribe();
    }

    public void addSubscription(Subscription subscription){
        if(mCompositeSubscription == null){
            mCompositeSubscription = new CompositeSubscription();
        }

        mCompositeSubscription.add(subscription);
    }
}
