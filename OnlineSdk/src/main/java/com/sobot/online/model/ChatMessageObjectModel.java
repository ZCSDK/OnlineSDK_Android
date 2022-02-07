package com.sobot.online.model;


import java.io.Serializable;

/**
 * 对象消息实体类
 */
public class ChatMessageObjectModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Object msg;
    //   对象类型： 0-富文本 1-多伦会话 2-位置 3-小卡片 4-订单卡片
    private int type;

    public ChatMessageObjectModel(Object msg, int type) {
        this.msg = msg;
        this.type = type;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChatMessageObjectModel{" +
                "msg=" + msg +
                ", type=" + type +
                '}';
    }
}