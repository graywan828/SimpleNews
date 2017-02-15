package com.wgh.simplenews.beans;

import com.google.gson.annotations.SerializedName;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/2/14
 * @description
 */

public class ImageBean {

    @SerializedName("title")
    private String title;
    @SerializedName("thumburl")
    private String thumburl;
    @SerializedName("sourceurl")
    private String sourceurl;
    @SerializedName("height")
    private int height;
    @SerializedName("width")
    private int width;
    @SerializedName("class")
    private String classX;
    @SerializedName("url")
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
