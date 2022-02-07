package com.sobot.online.model;

import java.io.Serializable;
import java.util.List;

public class CombinFormFieldList implements Serializable {

    private CombinFormField combinFormField;
    private List<CusFieldDataInfoList> cusFieldDataInfoList;
    private List<GroupFieldItemModel> groupFieldItemModels;

    @Override
    public String toString() {
        return "CombinFormFieldList{" +
                "combinFormField=" + combinFormField +
                ", cusFieldDataInfoList=" + cusFieldDataInfoList +
                ", groupFieldItemModels=" + groupFieldItemModels +
                '}';
    }

    public List<GroupFieldItemModel> getGroupFieldItemModels() {
        return groupFieldItemModels;
    }

    public void setGroupFieldItemModels(List<GroupFieldItemModel> groupFieldItemModels) {
        this.groupFieldItemModels = groupFieldItemModels;
    }

    public CombinFormField getCombinFormField() {
        return combinFormField;
    }

    public void setCombinFormField(CombinFormField combinFormField) {
        this.combinFormField = combinFormField;
    }

    public List<CusFieldDataInfoList> getCusFieldDataInfoList() {
        return cusFieldDataInfoList;
    }

    public void setCusFieldDataInfoList(List<CusFieldDataInfoList> cusFieldDataInfoList) {
        this.cusFieldDataInfoList = cusFieldDataInfoList;
    }
}