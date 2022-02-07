package com.sobot.online.model;

import java.io.Serializable;

public class OnlineCustomPopModel implements Serializable {
    private String sValue;//显示内容
    private int sPosition;// 位置索引

    public OnlineCustomPopModel(String sValue, int sPosition) {
        this.sValue = sValue;
        this.sPosition = sPosition;
    }

    public String getsValue() {
        return sValue;
    }

    public void setsValue(String sValue) {
        this.sValue = sValue;
    }

    public int getsPosition() {
        return sPosition;
    }

    public void setsPosition(int sPosition) {
        this.sPosition = sPosition;
    }
}
