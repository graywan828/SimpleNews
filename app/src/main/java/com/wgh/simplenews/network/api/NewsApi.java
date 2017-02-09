package com.wgh.simplenews.network.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/1/19
 */
public interface NewsApi {

    @GET("{path}{id}/{index}-20.html")
    Observable<String> getNews(@Path("path")String path,@Path("id")String urlId,@Path("index")int pageIndex);

    //nc/article/CCQVBPAV0001899O/full.html
    @GET("nc/article/{docId}/full.html")
    Observable<String> getNewsDetail(@Path("docId")String docId);

}
