package com.wgh.simplenews.network;

import com.wgh.simplenews.network.api.ImageApi;
import com.wgh.simplenews.network.api.NewsApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/1/19
 */
public class OkHttpUtils {

    private static final String TAG = "OkHttpUtils";

    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private static Converter.Factory scalarsConverterFactory = ScalarsConverterFactory.create();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static NewsApi newsApi;
    private static ImageApi imageApi;

    private OkHttpUtils(){
        mOkHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new HttpInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .build();
    }

    public synchronized static OkHttpUtils getInstance() {
        if(mInstance == null){
            mInstance = new OkHttpUtils();
        }
        return mInstance;
    }

    public NewsApi getNewsApi(){
        if (newsApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl(Urls.HOST)
                    .addConverterFactory(scalarsConverterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            newsApi = retrofit.create(NewsApi.class);
        }
        return newsApi;
    }

    public ImageApi getImageApi(){
        if (imageApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl(Urls.IMAGES_URL_HOST)
                    .addConverterFactory(scalarsConverterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            imageApi = retrofit.create(ImageApi.class);
        }
        return imageApi;
    }

}
