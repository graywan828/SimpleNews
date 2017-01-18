package com.wgh.simplenews;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * @version V9.0.0
 * @author: guanghui_wan
 * @date: 2017/1/18
 */

public class App extends Application {

    private static final String TAG = "App";
    private static App instance;

    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Logger.init(TAG);
    }
}
