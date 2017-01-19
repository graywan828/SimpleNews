package com.wgh.simplenews.utils;

import com.orhanobut.logger.Logger;

public class LogUtils {
    public static final boolean DEBUG = true;

    private static final String TAG = "defaultTag";

    public static void v(String message) {
        LogUtils.v(TAG, message);
    }

    public static void d(String message) {
        LogUtils.d(TAG, message);
    }

    public static void i(String message) {
        LogUtils.i(TAG, message);
    }

    public static void w(String message) {
        LogUtils.w(TAG, message);
    }

    public static void e(String message) {
        LogUtils.e(TAG, message);
    }

    public static void v(String tag, String message) {
        if(DEBUG) {
            Logger.v(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if(DEBUG) {
            Logger.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if(DEBUG) {
            Logger.i(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if(DEBUG) {
            Logger.w(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if(DEBUG) {
            Logger.e(tag, message);
        }
    }

    public static void e(String tag, String message, Exception e) {
        if(DEBUG) {
            Logger.e(tag, message, e);
        }
    }
}
