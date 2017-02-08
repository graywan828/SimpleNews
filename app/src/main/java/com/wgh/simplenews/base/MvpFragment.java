package com.wgh.simplenews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/2/8
 * @description
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {

    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mvpPresenter != null){
            mvpPresenter.detachView();
        }
    }
}
