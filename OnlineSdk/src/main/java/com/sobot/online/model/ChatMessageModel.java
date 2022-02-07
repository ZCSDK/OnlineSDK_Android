package com.sobot.online.model;


import com.sobot.onlinecommon.socket.module.ChatMessageMsgModel;

import java.io.Serializable;

/**
 * 聊天的消息列表的信息
 */
public class ChatMessageModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userNoSeeFlag;//为1时用户不可见，说明是敏感词，历史记录中有
    private boolean isSensitive = false;/* 是否是敏感词 */
    private int status;//status = 2 是敏感词
    private String id;
    private String cid;
    private int action;
    private String sender;
    private String senderName;
    //发送,0，客户，1机器人，2客服
    private int senderType;
    private String senderFace;
    private String t;
    private String ts;
    private int msgType;
    private String msg;
    private boolean isListend = false;/* 语音是否已经听取 */
    private String receiver;
    private String receiverName;
    private String offlineType;
    private int isSendOk = 1;/* 是否发送成功 1 成功 0 发送失败 2 发送中*/
    private int textFailTimes = 0;/*失败的次数*/
    private int isHistory = 0;/*1历史记录消息 0当前会话消息*/
    private boolean isInputIng = false;/*用户正在输入*/
    private int revokeFlag;//消息撤回，历史记录里撤回的消息有个标记位，revokeFlag字段=1表示撤回消息
    private String msgId;
    private boolean voideIsPlaying = false;

    private ChatMessageMsgModel message;

    private String sdkMsg;
    private Object miniPage;

    private String adminSendContent;//客服发送的内容，临时保存，用于重发
    private String adminSendFilePath;//客服发送的文件地址，临时保存，用于重发
    private String title;//action=25 时的提示语

    //多伦会话 当前显示的页码
    private int currentPageNum;

    public int getUserNoSeeFlag() {
        return userNoSeeFlag;
    }

    public void setUserNoSeeFlag(int userNoSeeFlag) {
        this.userNoSeeFlag = userNoSeeFlag;
    }

    public boolean isSensitive() {
        return isSensitive;
    }

    public void setSensitive(boolean sensitive) {
        isSensitive = sensitive;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getSenderType() {
        return senderType;
    }

    public void setSenderType(int senderType) {
        this.senderType = senderType;
    }

    public String getSenderFace() {
        return senderFace;
    }

    public void setSenderFace(String senderFace) {
        this.senderFace = senderFace;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isListend() {
        return isListend;
    }

    public void setListend(boolean listend) {
        isListend = listend;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getOfflineType() {
        return offlineType;
    }

    public void setOfflineType(String offlineType) {
        this.offlineType = offlineType;
    }

    public int getIsSendOk() {
        return isSendOk;
    }

    public void setIsSendOk(int isSendOk) {
        this.isSendOk = isSendOk;
    }

    public int getTextFailTimes() {
        return textFailTimes;
    }

    public void setTextFailTimes(int textFailTimes) {
        this.textFailTimes = textFailTimes;
    }

    public int getIsHistory() {
        return isHistory;
    }

    public void setIsHistory(int isHistory) {
        this.isHistory = isHistory;
    }

    public boolean isInputIng() {
        return isInputIng;
    }

    public void setInputIng(boolean inputIng) {
        isInputIng = inputIng;
    }

    public boolean isRevokeFlag() {
        return revokeFlag == 1 ? true : false;
    }

    public void setRevokeFlag(int revokeFlag) {
        this.revokeFlag = revokeFlag;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public ChatMessageMsgModel getMessage() {
        return message;
    }

    public void setMessage(ChatMessageMsgModel message) {
        this.message = message;
    }

    public boolean isVoideIsPlaying() {
        return voideIsPlaying;
    }

    public void setVoideIsPlaying(boolean voideIsPlaying) {
        this.voideIsPlaying = voideIsPlaying;
    }

    public String getSdkMsg() {
        return sdkMsg;
    }

    public void setSdkMsg(String sdkMsg) {
        this.sdkMsg = sdkMsg;
    }

    public Object getMiniPage() {
        return miniPage;
    }

    public void setMiniPage(Object miniPage) {
        this.miniPage = miniPage;
    }

    public String getAdminSendContent() {
        return adminSendContent;
    }

    public void setAdminSendContent(String adminSendContent) {
        this.adminSendContent = adminSendContent;
    }

    public String getAdminSendFilePath() {
        return adminSendFilePath;
    }

    public void setAdminSendFilePath(String adminSendFilePath) {
        this.adminSendFilePath = adminSendFilePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }
}
