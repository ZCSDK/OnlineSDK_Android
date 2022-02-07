package com.sobot.online.api;

import com.sobot.onlinecommon.gson.JsonObject;

public class OnlineBaseCodeThird<T> {


    public T item;
    public JsonObject[] items;
    public String retMsg;
    public String retCode;
    public int totalCount;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public JsonObject[] getItems() {
        return items;
    }

    public void setItems(JsonObject[] items) {
        this.items = items;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}