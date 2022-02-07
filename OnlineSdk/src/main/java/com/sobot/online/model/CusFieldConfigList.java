package com.sobot.online.model;

import java.io.Serializable;
import java.util.List;

public class CusFieldConfigList implements Serializable {

    private List<CusFieldConfigModel> cusFieldConfigList;

    public List<CusFieldConfigModel> getListCusFieldConfig() {
        return cusFieldConfigList;
    }

    public void setListCusFieldConfig(List<CusFieldConfigModel> cusFieldConfigList) {
        cusFieldConfigList = cusFieldConfigList;
    }
}