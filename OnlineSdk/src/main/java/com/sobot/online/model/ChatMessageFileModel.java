package com.sobot.online.model;


import java.io.Serializable;

/**
 * 文件消息实体类
 */
public class ChatMessageFileModel implements Serializable {

//    fileName: "智齿电商版功能清单--20200710.xlsx"
//    size: 11201
//    type: 2 //文件类型
//    url: "https://img4.sobot.com/chatres/f9c48b6234944997bdedfef23f1deeb3/msg/20200826/f5e1c0b4816c48af97ace1502985eb25/2d6e409b72494585a2d08da6f9f4fed9.xlsx"

    private static final long serialVersionUID = 1L;

    private String fileName;
    private String fileSize;
    private String size;
    //文件类型
    private int type;
    //文件url
    private String url;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "ChatMessageFileModel{" +
                "fileName='" + fileName + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", size='" + size + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                '}';
    }
}