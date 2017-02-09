package com.wgh.simplenews.news.presenter;

import com.wgh.simplenews.base.BasePresenter;
import com.wgh.simplenews.beans.NewsBean;
import com.wgh.simplenews.network.OkHttpUtils;
import com.wgh.simplenews.network.Urls;
import com.wgh.simplenews.news.NewsResult2ItemsMapper;
import com.wgh.simplenews.news.view.NewsView;
import com.wgh.simplenews.news.widget.NewsFragment;
import com.wgh.simplenews.utils.LogUtils;

import java.util.List;

import rx.Subscriber;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/1/22
 * @description
 */

public class NewsPresenter extends BasePresenter<NewsView>{

    private int type;

    public NewsPresenter(NewsView newsView,int type) {
        attachView(newsView);
        this.type = type;
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

//        loadNews(newsPath, newsId, 20);
        loadNews(newsPath, newsId, pageIndex);

    }

    private void loadNews(String newsPath, String newsId, int pageIndex) {

        LogUtils.e(newsPath +  " ... " + newsId+" ... "+ pageIndex);

        addSubscription(OkHttpUtils.getInstance().getNewsApi()
                .getNews(newsPath,newsId,pageIndex)
                .map(new NewsResult2ItemsMapper(type))
                ,new Subscriber<List<NewsBean>>() {
                    @Override
                    public void onCompleted() {
                        mvpView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpView.hideProgress();
                    }

                    @Override
                    public void onNext(List<NewsBean> newsBeanList) {

                        mvpView.addNews(newsBeanList);
                    }
                });

    }

}
