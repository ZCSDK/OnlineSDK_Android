package com.sobot.online.model;

import java.io.Serializable;

// 查看历史用户接口 返回数据  实体类
public class HistoryUserInfoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String staffId;//客服id，抢接的时候会用到
    private String id;/* 用户id */
    private int isblack;/* 是否黑名单 1 是 ，0 否*/
    private String lastCid;/* 最后会话id */
    private int ismark;/* 是否星标 1 是 ，0 否*/
    private String source;/* 用户 来源0 pc 1微信 2APP 3微博 4WAP 5融云 */
    private String ts;/* 最后会话时间 */
    private String uname;/* 用户名字 */
    private String face;
    private int chatType;//0正常创建   1转接
    private String lastMsg;//最后会话信息
    private boolean isOnline = true;//true  用户在线

    //自建属性  用来控制侧滑菜单的显现 0全部 1星标 2黑名单
    private int slidingMenuType = 0;

    //是否已总结
    private boolean isSummary = false;
    //是否显示已总结
    private boolean isShowSummary = false;

    public boolean isSummary() {
        return isSummary;
    }

    public void setSummary(boolean summary) {
        isSummary = summary;
    }

    public boolean isShowSummary() {
        return isShowSummary;
    }

    public void setShowSummary(boolean showSummary) {
        isShowSummary = showSummary;
    }

    public int getSlidingMenuType() {
        return slidingMenuType;
    }

    public void setSlidingMenuType(int slidingMenuType) {
        this.slidingMenuType = slidingMenuType;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsblack() {
        return isblack;
    }

    public void setIsblack(int isblack) {
        this.isblack = isblack;
    }

    public String getLastCid() {
        return lastCid;
    }

    public void setLastCid(String lastCid) {
        this.lastCid = lastCid;
    }

    public int getIsmark() {
        return ismark;
    }

    public void setIsmark(int ismark) {
        this.ismark = ismark;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    @Override
    public String toString() {
        return "HistoryUserInfoModel{" +
                "id='" + id + '\'' +
                ", isblack=" + isblack +
                ", lastCid='" + lastCid + '\'' +
                ", ismark=" + ismark +
                ", source='" + source + '\'' +
                ", ts='" + ts + '\'' +
                ", uname='" + uname + '\'' +
                ", chatType=" + chatType +
                ", lastMsg='" + lastMsg + '\'' +
                '}';
    }
}