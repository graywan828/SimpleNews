package com.wgh.simplenews.news.view;

import com.wgh.simplenews.beans.NewsBean;

import java.util.List;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/1/19
 */

public interface NewsView {

    void addNews(List<NewsBean> newsList);

    void showProgress();

    void hideProgress();

    void showLoadFailMsg();

}
