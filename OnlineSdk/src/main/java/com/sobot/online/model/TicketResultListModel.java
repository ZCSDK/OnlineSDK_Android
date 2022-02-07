package com.sobot.online.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */

public class TicketResultListModel implements Serializable{

    private boolean checked;
    private String fieldId;
    private int fieldType;
    private String title;
    private String value;
    private String text;
    private int isOpenFlag = 1;//自定义字段是否开启    0 是关闭  1开启
    private String summary;
    private int numericalFlag;
    private List<?> items;
    private List<List<CombinFormField>> combinFormFieldList;

    public TicketResultListModel(boolean checked, String fieldId, int fieldType, String title, String value, String text, int isOpenFlag, String summary) {
        this.checked = checked;
        this.fieldId = fieldId;
        this.fieldType = fieldType;
        this.title = title;
        this.value = value;
        this.text = text;
        this.isOpenFlag = isOpenFlag;
        this.summary = summary;
    }

    public int getNumericalFlag() {
        return numericalFlag;
    }

    public void setNumericalFlag(int numericalFlag) {
        this.numericalFlag = numericalFlag;
    }

    public List<List<CombinFormField>> getCombinFormFieldList() {
        return combinFormFieldList;
    }

    public void setCombinFormFieldList(List<List<CombinFormField>> combinFormFieldList) {
        this.combinFormFieldList = combinFormFieldList;
    }

    @Override
    public String toString() {
        return "TicketResultListModel{" +
                "checked=" + checked +
                ", fieldId='" + fieldId + '\'' +
                ", fieldType=" + fieldType +
                ", title='" + title + '\'' +
                ", value='" + value + '\'' +
                ", text='" + text + '\'' +
                ", isOpenFlag='" + isOpenFlag + '\'' +
                ", items=" + items +
                ", summary=" + summary +
                ", combinFormFieldList=" + combinFormFieldList +
                '}';
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getIsOpenFlag() {
        return isOpenFlag;
    }

    public void setIsOpenFlag(int isOpenFlag) {
        this.isOpenFlag = isOpenFlag;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public int getFieldType() {
        return fieldType;
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }
}