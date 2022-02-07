package com.sobot.online.model;


import java.io.Serializable;

/**
 * 音频消息实体类
 */
public class ChatMessageAudioModel implements Serializable {

    private static final long serialVersionUID = 1L;
    //时长
    private String duration;
    //语音地址
    private String url;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}