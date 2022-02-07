package com.sobot.online.model;

import java.io.Serializable;

/**
 * 用户会话信息实体类
 */

public class UserConversationModel implements Serializable{

    /**
     * visitLandPage : 访问着陆页
     * conLaunchPage : 会话发起页
     * os : Windows 7
     * groupName : 00000
     * remainTime : 排队时长
     * source : 0
     * terminal : Chrome浏览器
     * ip : 61.50.104.126
     * visitMsg : {"visitSourcePage":"访问来源页","searchEngine":"搜索引擎","utmCampaign":"广告名称","utmContent":"广告内容","utmMedium":"广告媒介","utmSource":"广告来源","utmTerm":"广告关键词","keyWord":"关键字"}
     */

    private String searchSource;
    private String visitLandPage;
    private String visitSourceType;
    private String conLaunchPage;
    private String os;
    private String groupName;
    private String remainTime;
    private int source;
    private String terminal;
    private String ip;
    private VisitMsgBean visitMsg;

    public String getSearchSource() { return searchSource; }

    public void setSearchSource(String searchSource) { this.searchSource = searchSource; }

    public String getVisitSourceType() { return visitSourceType; }

    public void setVisitSourceType(String visitSourceType) { this.visitSourceType = visitSourceType; }

    public String getVisitLandPage() {
        return visitLandPage;
    }

    public void setVisitLandPage(String visitLandPage) {
        this.visitLandPage = visitLandPage;
    }

    public String getConLaunchPage() {
        return conLaunchPage;
    }

    public void setConLaunchPage(String conLaunchPage) {
        this.conLaunchPage = conLaunchPage;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public VisitMsgBean getVisitMsg() {
        return visitMsg;
    }

    public void setVisitMsg(VisitMsgBean visitMsg) {
        this.visitMsg = visitMsg;
    }
}