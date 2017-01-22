package com.wgh.simplenews.network;

import com.wgh.simplenews.utils.LogUtils;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/1/22
 * @description 定义http拦截器，用于设置http协议和日志调试
 */

public class HttpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request().newBuilder()
                .addHeader("Content-Type","")
                .build();

        Headers headers = request.headers();

        String requestUrl = request.url().toString();
        String methodStr = request.method();
        RequestBody body = request.body();
        String bodyStr = body==null?"":body.toString();

        LogUtils.e(methodStr +"\n"+requestUrl+"\n"+bodyStr);

        Response response = chain.proceed(request);
        return response;
    }
}
