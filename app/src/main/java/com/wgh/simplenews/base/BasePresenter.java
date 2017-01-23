package com.wgh.simplenews.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/1/23
 * @description
 */

public class BasePresenter<V>{

    public V mvpView;
    protected CompositeSubscription mCompositeSubscription;

    public void attachView(V mvpView){
        this.mvpView = mvpView;
    }

    public void detachView(){
        this.mvpView = null;
        unSubscribe();
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

}
