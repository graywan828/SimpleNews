package com.wgh.simplenews.main.presenter;

import com.wgh.simplenews.R;
import com.wgh.simplenews.main.view.MainView;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/1/18
 */

public class MainPresenter {

    private MainView mMainView;

    public MainPresenter(MainView mainView) {
        mMainView = mainView;
    }

    public void switchNavigation(int id) {
        switch (id) {
            case R.id.navigation_item_news:
                mMainView.switch2News();
                break;
            case R.id.navigation_item_images:
                mMainView.switch2Images();
                break;
            case R.id.navigation_item_weather:
                mMainView.switch2Weather();
                break;
            case R.id.navigation_item_about:
                mMainView.switch2About();
                break;
            default:
                mMainView.switch2News();
                break;
        }
    }
}
