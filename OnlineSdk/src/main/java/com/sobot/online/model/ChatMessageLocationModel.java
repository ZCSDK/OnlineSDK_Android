package com.sobot.online.model;


import java.io.Serializable;

/**
 * 位置消息实体类
 */
public class ChatMessageLocationModel implements Serializable {

//    {url:位置链接地址
////        picUrl:展示图片地址
////        title:位置标题
////        desc:描述
////        lng:经度
////        lat:维度}

    private static final long serialVersionUID = 1L;

    private String url;
    private String picUrl;
    private String title;
    private String desc;
    private String lng;
    private String lat;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "ChatMessageLocationModel{" +
                "url='" + url + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}