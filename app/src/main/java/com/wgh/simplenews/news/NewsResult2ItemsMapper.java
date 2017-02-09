package com.wgh.simplenews.news;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.wgh.simplenews.beans.NewsBean;
import com.wgh.simplenews.network.Urls;
import com.wgh.simplenews.news.widget.NewsFragment;
import com.wgh.simplenews.utils.JsonUtils;

import java.util.List;

import rx.functions.Func1;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/1/22
 * @description
 */

public class NewsResult2ItemsMapper implements Func1<String,List<NewsBean>> {

    private int mType = NewsFragment.NEWS_TYPE_TOP;

    public NewsResult2ItemsMapper(int type) {
        mType = type;
    }

    @Override
    public List<NewsBean> call(String s) {

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(s).getAsJsonObject();
        String key=Urls.TOP_ID;
        switch (mType){
            case NewsFragment.NEWS_TYPE_TOP:
                key = Urls.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                key = Urls.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                key = Urls.CAR_ID;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                key = Urls.JOKE_ID;
                break;
        }

        JsonElement jsonElement = jsonObject.get(key);
        if(jsonElement==null){
            return null;
        }
        List<NewsBean> items = JsonUtils.deserialize(jsonElement,new TypeToken<List<NewsBean>>(){}.getType());
        return items;
    }
}
