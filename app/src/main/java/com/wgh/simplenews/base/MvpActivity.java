package com.wgh.simplenews.base;

import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/2/8
 * @description
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mvpPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState, persistentState);
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
