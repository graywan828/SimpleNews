package com.wgh.simplenews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import rx.Subscription;

/**
 * @version V9.0.0
 * @author: guanghui_wan
 * @date: 2017/1/18
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Subscription subscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

    }

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }


    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
