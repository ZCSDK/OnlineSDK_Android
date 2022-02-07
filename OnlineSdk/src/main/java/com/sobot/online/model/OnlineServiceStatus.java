package com.sobot.online.model;

import java.io.Serializable;

public class OnlineServiceStatus implements Serializable {
    /**
     * dictId : 10292
     * dictValue : 4
     * dictName : 培训
     * dictCode : 1029
     * dictStatus : 1
     * dictRemark : 客服工作台自定义状态
     */

    private String dictId;
    private String dictValue;
    private String dictName;
    private String dictCode;
    private String dictStatus;
    private String dictRemark;
    private boolean isChecked;

    public OnlineServiceStatus(String dictValue, String dictName) {
        this.dictValue = dictValue;
        this.dictName = dictName;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictStatus() {
        return dictStatus;
    }

    public void setDictStatus(String dictStatus) {
        this.dictStatus = dictStatus;
    }

    public String getDictRemark() {
        return dictRemark;
    }

    public void setDictRemark(String dictRemark) {
        this.dictRemark = dictRemark;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "OnlineServiceStatus{" +
                "dictId='" + dictId + '\'' +
                ", dictValue='" + dictValue + '\'' +
                ", dictName='" + dictName + '\'' +
                ", dictCode='" + dictCode + '\'' +
                ", dictStatus='" + dictStatus + '\'' +
                ", dictRemark='" + dictRemark + '\'' +
                ", isChecked='" + isChecked + '\'' +
                '}';
    }
}