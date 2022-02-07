package com.sobot.online.model;

import java.io.Serializable;

/**
 * 用来提交咨询总结的model  跟服务器返回的bean不一样
 */
public class SummaryModel implements Serializable {
    private String operationId;//业务id
    private String operationName;//业务名称
    private String reqTypeId;//问题类型id（格式：xxx，xxx,xxx）
    private String questionDescribe;//问题描述
    private int questionStatus;//问题状态   0：未解决 1：已解决
    private String uid;//用户id
    private String cid;//会话id
    private boolean isInvalidSession = false;//是否为无效会话 默认为有效会话

    public boolean isInvalidSession() {
        return isInvalidSession;
    }

    public void setInvalidSession(boolean invalidSession) {
        isInvalidSession = invalidSession;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getReqTypeId() {
        return reqTypeId;
    }

    public void setReqTypeId(String reqTypeId) {
        this.reqTypeId = reqTypeId;
    }

    public String getQuestionDescribe() {
        return questionDescribe;
    }

    public void setQuestionDescribe(String questionDescribe) {
        this.questionDescribe = questionDescribe;
    }

    public int getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(int questionStatus) {
        this.questionStatus = questionStatus;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "SummaryModel{" +
                "operationId='" + operationId + '\'' +
                ", operationName='" + operationName + '\'' +
                ", reqTypeId='" + reqTypeId + '\'' +
                ", questionDescribe='" + questionDescribe + '\'' +
                ", questionStatus=" + questionStatus +
                ", uid='" + uid + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}
