package com.sobot.online.model;


import com.sobot.onlinecommon.frame.http.model.SobotProgress;

import java.io.Serializable;

/**
 * 文件类型model
 *
 * @author Created by jinxl on 2018/1/11.
 */
public class SobotCacheFile implements Serializable {
    //msgId
    private String msgId;
    //本地文件地址
    private String filePath;
    //文件名
    private String fileName;
    //文件类型
    private int fileType;
    //进度
    private int progress;
    //文件网络地址
    private String url;
    //文件大小
    private String fileSize;
    //视频缩略图
    private String snapshot;

    //是否已下载
    private boolean isCache;
    private int status = SobotProgress.NONE;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
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

    public boolean isCache() {
        return isCache;
    }

    public void setCache(boolean cache) {
        isCache = cache;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    @Override
    public String toString() {
        return "SobotCacheFile{" +
                "msgId='" + msgId + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType=" + fileType +
                ", progress=" + progress +
                ", url='" + url + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", isCache=" + isCache +
                ", status=" + status +
                '}';
    }
}
