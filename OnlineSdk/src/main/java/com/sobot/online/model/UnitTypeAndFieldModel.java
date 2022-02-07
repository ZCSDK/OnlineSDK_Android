package com.sobot.online.model;

import java.util.ArrayList;

/**
 * 服务总结 业务类型和自定义组合实体类
 */
public class UnitTypeAndFieldModel {
    private ArrayList<UnitTypeInfoModel> typeList;
    private ArrayList<CombinFormField> fieldList;

    public ArrayList<UnitTypeInfoModel> getTypeList() {
        return typeList;
    }

    public void setTypeList(ArrayList<UnitTypeInfoModel> typeList) {
        this.typeList = typeList;
    }

    public ArrayList<CombinFormField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(ArrayList<CombinFormField> fieldList) {
        this.fieldList = fieldList;
    }
}
