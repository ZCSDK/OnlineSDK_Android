package com.sobot.online.model;

import java.util.List;


public class CusFieldDataInfoListResult  {

    private String retCode;
    private List<CusFieldDataInfoModel> items;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<CusFieldDataInfoModel> getItems() {
        return items;
    }

    public void setItems(List<CusFieldDataInfoModel> items) {
        this.items = items;
    }
}