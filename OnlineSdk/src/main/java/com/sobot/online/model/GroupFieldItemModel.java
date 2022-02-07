package com.sobot.online.model;

import java.io.Serializable;

public class GroupFieldItemModel implements Serializable {
    /**
     * dataId : 104276675dbb4d5bb10b629c0c62eaf8
     * id : 1
     * pId : 0
     * text : 男
     * numerical :
     * serial : 98af6b3e-bad5-41b0-8a92-88c15c569c5b
     */

    private String tmpData;////获取本地时间，作为标识
    private String dataId;
    private String id;
    private String pId;
    private String text;
    private String numerical;
    private String serial;
    private boolean isChecked = false;

    @Override
    public String toString() {
        return "GroupFieldItemModel{" +
                "dataId='" + dataId + '\'' +
                ", id='" + id + '\'' +
                ", pId='" + pId + '\'' +
                ", text='" + text + '\'' +
                ", numerical='" + numerical + '\'' +
                ", serial='" + serial + '\'' +
                ", isChecked='" + isChecked + '\'' +
                ", tmpData='" + tmpData + '\'' +
                '}';
    }

    public String getTmpData() {
        return tmpData;
    }

    public void setTmpData(String tmpData) {
        this.tmpData = tmpData;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNumerical() {
        return numerical;
    }

    public void setNumerical(String numerical) {
        this.numerical = numerical;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}