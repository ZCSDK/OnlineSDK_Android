package com.sobot.online.model;

import java.io.Serializable;

//转接客服组
public class OnLineGroupModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String groupId;
    private String groupName;
    private int onlineNum;
    private int freeNum;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(int onlineNum) {
        this.onlineNum = onlineNum;
    }

    public int getFreeNum() {
        return freeNum;
    }

    public void setFreeNum(int freeNum) {
        this.freeNum = freeNum;
    }
}