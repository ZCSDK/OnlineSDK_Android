package com.sobot.online.model;

import java.io.Serializable;

/**
 * @Description: 多轮会话  model 接口返回列表
 * @Author: znw
 * @Date: 2020/12/10 下午5:43
 * @Version: 1.0
 */
public class OnlineInterfaceRetInfo implements Serializable {
    private String summary;
    private String thumbnail;
    private String anchor;
    private String movieId;
    private String tag;
    private String label;
    private String title;
    private String tempStr;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTempStr() {
        return tempStr;
    }

    public void setTempStr(String tempStr) {
        this.tempStr = tempStr;
    }
}