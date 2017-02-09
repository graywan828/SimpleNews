package com.wgh.simplenews.base;

import android.os.Bundle;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/2/8
 * @description
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mvpPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mvpPresenter!=null){
            mvpPresenter.detachView();
        }
    }
}
