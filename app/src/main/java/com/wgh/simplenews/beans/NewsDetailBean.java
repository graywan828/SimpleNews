package com.wgh.simplenews.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NewsDetailBean implements Serializable {
    /**
     * docid
     */
    private String docid;
    /**
     * title
     */
    private String title;
    /**
     * source
     */
    private String source;
    /**
     * body
     */
    private String body;
    /**
     * ptime
     */
    private String ptime;
    /**
     * cover
     */
    private String cover;

    @SerializedName("replyCount")
    private int replyCount;

    @SerializedName("dkeys")
    private String dkeys;

    @SerializedName("picnews")
    private boolean picnews;

    @SerializedName("spinfo")
    private List<SpinfoBean> spinfo;

    @SerializedName("img")
    private List<ImgBean> imgList;

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getDkeys() {
        return dkeys;
    }

    public void setDkeys(String dkeys) {
        this.dkeys = dkeys;
    }

    public boolean isPicnews() {
        return picnews;
    }

    public void setPicnews(boolean picnews) {
        this.picnews = picnews;
    }

    public List<SpinfoBean> getSpinfo() {
        return spinfo;
    }

    public void setSpinfo(List<SpinfoBean> spinfo) {
        this.spinfo = spinfo;
    }

    public List<ImgBean> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImgBean> imgList) {
        this.imgList = imgList;
    }

    public static class SpinfoBean {
        @SerializedName("ref")
        private String ref;
        @SerializedName("spcontent")
        private String spcontent;
        @SerializedName("sptype")
        private String sptype;

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public String getSpcontent() {
            return spcontent;
        }

        public void setSpcontent(String spcontent) {
            this.spcontent = spcontent;
        }

        public String getSptype() {
            return sptype;
        }

        public void setSptype(String sptype) {
            this.sptype = sptype;
        }
    }

    public static class ImgBean {
        @SerializedName("ref")
        private String ref;
        @SerializedName("src")
        private String src;

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }
}
