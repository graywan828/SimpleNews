package com.wgh.simplenews.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @version V9.0.0
 * @author: guanghui_wan
 * @date: 2017/1/18
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Activity mActivity;
    protected Subscription subscription;
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        ButterKnife.bind(this);
        mActivity = this;
        initView();
        registerListener();
        initData();
    }



    protected abstract void setContentView();

    protected abstract void initView();

    protected abstract void registerListener();

    protected abstract void initData();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }

    public void addSubscription(Observable observable, Subscriber subscriber){
        if(mCompositeSubscription == null){
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber));
    }

    protected void unsubscribe() {
        if(mCompositeSubscription!=null && mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }
}
