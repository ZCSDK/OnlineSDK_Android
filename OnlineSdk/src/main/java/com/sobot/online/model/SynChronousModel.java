package com.sobot.online.model;

import com.sobot.onlinecommon.socket.module.PushMessageModel;

import java.util.List;


/**
 * 同步实体例
 */
public class SynChronousModel {

    //排队用户数量
    private int waitSize;
    private List<PushMessageModel> userList;

    public int getWaitSize() {
        return waitSize;
    }

    public void setWaitSize(int waitSize) {
        this.waitSize = waitSize;
    }

    public List<PushMessageModel> getUserList() {
        return userList;
    }

    public void setUserList(List<PushMessageModel> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "SynChronousModel [waitSize=" + waitSize + ", userList="
                + userList + "]";
    }
}