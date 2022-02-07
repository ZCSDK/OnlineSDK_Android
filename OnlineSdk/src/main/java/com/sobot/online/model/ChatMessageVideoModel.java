package com.sobot.online.model;


import java.io.Serializable;

/**
 * 视频消息实体类
 */
public class ChatMessageVideoModel implements Serializable {
//
//    fileName: "智齿电商版功能清单--20200710.xlsx"
//    fileSize: "1M"
//    snapshot: "fdsfdds"
//    url: "https://img4.sobot.com/chatres/f9c48b6234944997bdedfef23f1deeb3/msg/20200826/f5e1c0b4816c48af97ace1502985eb25/2d6e409b72494585a2d08da6f9f4fed9.xlsx"

    private static final long serialVersionUID = 1L;

    private String fileName;
    private String fileSize;
    //简介
    private String snapshot;
    private String url;
    private String serviceUrl;//服务端播放地址

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }
}