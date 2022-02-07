package com.sobot.online.model;

import java.io.Serializable;

// 查看历史会话户接口 返回数据  实体类
public class HistoryChatModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private String uname;//昵称
    private Long chatStartTime;//会话开始时间
    private Long convEndDateTime;//会话结束时间
    private int sessionHumanNotRecep;//未接待会话
    private int invalidSession;//总结状态  0,1表示已总结，-1或者不存在表示未总结
    private int questionStatus;//处理状态
    private int isEvaluated;//人工是否参评
    private int score;//星级
    private String partnerId;//对接id
    private String visitorId;//访客id
    private String cid;//会话id
    private String staffMsgText;//客服消息
    private String visitorMsgText;//访客消息
    private String lastStaffId;//最后接待客服
    private String staffIds;//接待客服列表
    private String companyId;//公司id
    private String groupId;//技能组id
    private String platformId;//平台id
    private int markStatus;//星标
    private int ticketFlag;//已创建工单标识  需要加生产消费者
    private int isBlack;//是否拉黑
    private int source;//来源
    private String lastMsg;//最后一条消息

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Long getChatStartTime() {
        return chatStartTime;
    }

    public void setChatStartTime(Long chatStartTime) {
        this.chatStartTime = chatStartTime;
    }

    public Long getConvEndDateTime() {
        return convEndDateTime;
    }

    public void setConvEndDateTime(Long convEndDateTime) {
        this.convEndDateTime = convEndDateTime;
    }

    public int getSessionHumanNotRecep() {
        return sessionHumanNotRecep;
    }

    public void setSessionHumanNotRecep(int sessionHumanNotRecep) {
        this.sessionHumanNotRecep = sessionHumanNotRecep;
    }

    public int getInvalidSession() {
        return invalidSession;
    }

    public void setInvalidSession(int invalidSession) {
        this.invalidSession = invalidSession;
    }

    public int getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(int questionStatus) {
        this.questionStatus = questionStatus;
    }

    public int getIsEvaluated() {
        return isEvaluated;
    }

    public void setIsEvaluated(int isEvaluated) {
        this.isEvaluated = isEvaluated;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getStaffMsgText() {
        return staffMsgText;
    }

    public void setStaffMsgText(String staffMsgText) {
        this.staffMsgText = staffMsgText;
    }

    public String getVisitorMsgText() {
        return visitorMsgText;
    }

    public void setVisitorMsgText(String visitorMsgText) {
        this.visitorMsgText = visitorMsgText;
    }

    public String getLastStaffId() {
        return lastStaffId;
    }

    public void setLastStaffId(String lastStaffId) {
        this.lastStaffId = lastStaffId;
    }

    public String getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(String staffIds) {
        this.staffIds = staffIds;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public int getMarkStatus() {
        return markStatus;
    }

    public void setMarkStatus(int markStatus) {
        this.markStatus = markStatus;
    }

    public int getTicketFlag() {
        return ticketFlag;
    }

    public void setTicketFlag(int ticketFlag) {
        this.ticketFlag = ticketFlag;
    }

    public int getIsBlack() {
        return isBlack;
    }

    public void setIsBlack(int isBlack) {
        this.isBlack = isBlack;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    @Override
    public String toString() {
        return "HistoryChatModel{" +
                "uname='" + uname + '\'' +
                ", chatStartTime=" + chatStartTime +
                ", convEndDateTime=" + convEndDateTime +
                ", sessionHumanNotRecep=" + sessionHumanNotRecep +
                ", invalidSession=" + invalidSession +
                ", questionStatus=" + questionStatus +
                ", isEvaluated=" + isEvaluated +
                ", score=" + score +
                ", partnerId='" + partnerId + '\'' +
                ", visitorId='" + visitorId + '\'' +
                ", cid='" + cid + '\'' +
                ", staffMsgText='" + staffMsgText + '\'' +
                ", visitorMsgText='" + visitorMsgText + '\'' +
                ", lastStaffId='" + lastStaffId + '\'' +
                ", staffIds='" + staffIds + '\'' +
                ", companyId='" + companyId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", platformId='" + platformId + '\'' +
                ", markStatus=" + markStatus +
                ", ticketFlag=" + ticketFlag +
                ", isBlack=" + isBlack +
                ", source=" + source +
                ", lastMsg='" + lastMsg + '\'' +
                '}';
    }
}