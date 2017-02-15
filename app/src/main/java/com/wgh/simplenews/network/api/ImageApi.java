package com.wgh.simplenews.network.api;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/2/14
 * @description
 */

public interface ImageApi {

    @GET("open/tupian.json")
    Observable<String> getImages();

}
