package com.wgh.simplenews.utils;

import com.orhanobut.logger.Logger;

public class LogUtils {
    public static final boolean DEBUG = true;

    private static final String TAG = "defaultTag";

    public static void v(String message) {
        Logger.v( message);
    }

    public static void d(String message) {
        Logger.d( message);
    }

    public static void i(String message) {
        Logger.i( message);
    }

    public static void w(String message) {
        Logger.w(message);
    }

    public static void e(String message) {
        Logger.e(message);
    }
}
