package com.wgh.simplenews.news.model;

import com.wgh.simplenews.beans.NewsBean;
import com.wgh.simplenews.network.OkHttpUtils;
import com.wgh.simplenews.news.NewsResult2ItemsMapper;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/1/22
 * @description
 */

public class NewsModel {

    private int type;

    public NewsModel(int type) {
        this.type = type;
    }

    public void loadNews(String newsPath, String newsId, int pageIndex, Observer<List<NewsBean>> observer){

        Subscription subscription = OkHttpUtils.getInstance().getNewsApi()
                .getNews(newsPath,newsId,pageIndex)
                .map(new NewsResult2ItemsMapper(type))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
