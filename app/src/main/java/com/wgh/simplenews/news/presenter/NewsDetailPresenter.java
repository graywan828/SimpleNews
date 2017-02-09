package com.wgh.simplenews.news.presenter;

import com.wgh.simplenews.base.BasePresenter;
import com.wgh.simplenews.beans.NewsDetailBean;
import com.wgh.simplenews.network.OkHttpUtils;
import com.wgh.simplenews.news.NewsDetailResult2BeanMapper;
import com.wgh.simplenews.news.view.NewsDetailView;

import rx.Subscriber;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/2/9
 * @description
 */

public class NewsDetailPresenter extends BasePresenter<NewsDetailView>{

    public NewsDetailPresenter(NewsDetailView newsDetailView) {
        attachView(newsDetailView);
    }

    public void loadNewsDetail(String docId){
        mvpView.showProgress();
        addSubscription(OkHttpUtils.getInstance().getNewsApi()
                        .getNewsDetail(docId)
                .map(new NewsDetailResult2BeanMapper(docId)),
                new Subscriber<NewsDetailBean>() {
                    @Override
                    public void onCompleted() {
                        mvpView.hideProgress();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsDetailBean newsDetailBean) {
                        mvpView.showNewsDetailContent(newsDetailBean.getBody());
                    }
                });
    }
}
