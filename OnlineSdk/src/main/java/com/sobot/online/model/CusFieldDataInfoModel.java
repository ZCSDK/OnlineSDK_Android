package com.sobot.online.model;

import java.io.Serializable;


public class CusFieldDataInfoModel implements Serializable {

    private String tmpData;//获取本地时间，作为标识

    private String companyId;//e6fb17f5af9f4487a8ec7f1f58c972ba
    private String createId;//1b6d22ab52bc4db99c3c7b055abd3332
    private String createTime;//150071054
    private String dataId;//aa1974e833814089a3016df7fc1ecbf7
    private String dataName;//66666666
    private int dataStatus;// 1
    private String dataValue;//500710546694836
    private String fieldId;// 0ab01b8393a7472b9aa4c6a8698cbdb4
    private String fieldVariable;//customField8
    private String parentDataId;//  "0"
    private String updateId;//1b6d22ab52bc4db99c3c7b055abd3332
    private String updateTime;//1500710546
    private String optionValue;

    private boolean isChecked = false;
    private String tmpDataName;//

    private String id;
    private String valueDataId;
    private String numerical;
    private String text;

    @Override
    public String toString() {
        return "CusFieldDataInfoModel{" +
                "companyId='" + companyId + '\'' +
                ", createId='" + createId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", dataId='" + dataId + '\'' +
                ", dataName='" + dataName + '\'' +
                ", dataStatus=" + dataStatus +
                ", dataValue='" + dataValue + '\'' +
                ", fieldId='" + fieldId + '\'' +
                ", fieldVariable='" + fieldVariable + '\'' +
                ", parentDataId='" + parentDataId + '\'' +
                ", updateId='" + updateId + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", isChecked='" + isChecked + '\'' +
                ", tmpDataName='" + tmpDataName + '\'' +
                ", id='" + id + '\'' +
                ", valueDataId='" + valueDataId + '\'' +
                ", numerical='" + numerical + '\'' +
                ", text='" + text + '\'' +
                ", tmpData='" + tmpData + '\'' +
                ", optionValue='" + optionValue + '\'' +
                '}';
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldVariable() {
        return fieldVariable;
    }

    public void setFieldVariable(String fieldVariable) {
        this.fieldVariable = fieldVariable;
    }

    public String getParentDataId() {
        return parentDataId;
    }

    public void setParentDataId(String parentDataId) {
        this.parentDataId = parentDataId;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTmpDataName() {
        return tmpDataName;
    }

    public void setTmpDataName(String tmpDataName) {
        this.tmpDataName = tmpDataName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValueDataId() {
        return valueDataId;
    }

    public void setValueDataId(String valueDataId) {
        this.valueDataId = valueDataId;
    }

    public String getNumerical() {
        return numerical;
    }

    public void setNumerical(String numerical) {
        this.numerical = numerical;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTmpData() {
        return tmpData;
    }

    public void setTmpData(String tmpData) {
        this.tmpData = tmpData;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }
}