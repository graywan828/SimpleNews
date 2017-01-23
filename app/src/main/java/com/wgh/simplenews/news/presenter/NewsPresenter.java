package com.wgh.simplenews.news.presenter;

import com.wgh.simplenews.beans.NewsBean;
import com.wgh.simplenews.network.Urls;
import com.wgh.simplenews.news.NewsFragment;
import com.wgh.simplenews.news.model.NewsModel;
import com.wgh.simplenews.news.view.NewsView;
import com.wgh.simplenews.utils.LogUtils;

import java.util.List;

import rx.Observer;
import rx.Subscription;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/1/22
 * @description
 */

public class NewsPresenter {

    private NewsView mNewsView;
    private NewsModel mNewsModel;
    private int type;

    public NewsPresenter(int type, NewsView newsView, Subscription subscription) {
        this.type = type;
        mNewsView = newsView;
        //TODO
//        mNewsModel = new NewsModel(type);
    }

    public void loadNews(int pageIndex) {

        String newsId = Urls.TOP_ID;
        String newsPath = Urls.TOP_PATH;
        switch (type) {

            case NewsFragment.NEWS_TYPE_TOP:
                newsId = Urls.TOP_ID;
                newsPath = Urls.TOP_PATH;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                newsId = Urls.NBA_ID;
                newsPath = Urls.COMMON_PATH;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                newsId = Urls.CAR_ID;
                newsPath = Urls.COMMON_PATH;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                newsId = Urls.JOKE_ID;
                newsPath = Urls.COMMON_PATH;
                break;

        }

        mNewsModel.loadNews(newsPath, newsId, pageIndex,mObserver);

    }

    Observer<List<NewsBean>> mObserver = new Observer<List<NewsBean>>() {
        @Override
        public void onCompleted() {
            mNewsView.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            mNewsView.hideProgress();
            LogUtils.e("onError  "  + e.getMessage());
        }

        @Override
        public void onNext(List<NewsBean> newsBeanList) {

            mNewsView.addNews(newsBeanList);

        }

    };
}
