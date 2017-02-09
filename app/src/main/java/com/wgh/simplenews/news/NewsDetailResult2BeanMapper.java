package com.wgh.simplenews.news;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wgh.simplenews.beans.NewsDetailBean;
import com.wgh.simplenews.utils.JsonUtils;

import rx.functions.Func1;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/2/9
 * @description
 */
public class NewsDetailResult2BeanMapper implements Func1<String,NewsDetailBean> {

    private String docId;

    public NewsDetailResult2BeanMapper(String docId) {
        this.docId = docId;
    }

    @Override
    public NewsDetailBean call(String s) {

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(s).getAsJsonObject();

        JsonElement jsonElement = jsonObject.get(docId);
        if(jsonElement==null){
            return null;
        }
        NewsDetailBean bean = JsonUtils.deserialize(jsonElement,NewsDetailBean.class);
        return bean;
    }
}
