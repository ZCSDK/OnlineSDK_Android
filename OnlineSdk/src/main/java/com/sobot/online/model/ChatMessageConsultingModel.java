package com.sobot.online.model;


import java.io.Serializable;

/**
 * 小卡片消息实体类
 */
public class ChatMessageConsultingModel implements Serializable {

//       {title:sdfs
//        url:sfsdfs
//        description:描述
//        label:标签
//        thumbnail:缩略图}

    private static final long serialVersionUID = 1L;

    private String title;
    private String url;
    private String description;
    private String label;
    private String thumbnail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "ChatMessageConsultingModel{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", label='" + label + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}