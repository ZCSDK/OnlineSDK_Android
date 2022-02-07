package com.sobot.online.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 多轮会话  model
 */
public class OnlineMultiDiaRespInfo implements Serializable {
    /**
     * remindQuestion : 机器人提示问题
     * interfaceRetList : 接口返回列表
     * outPutParamList : 接口实际用到的返回参数列表，例aa#bb
     * level : 多轮对话中进行到第几个
     * conversationId : 多轮会话id
     * retCode : 接口返回值：000000-成功，100001-失败
     * retErrorMsg : 失败时返回的数据
     * endFlag : 多轮对话是否结束，true-结束
     * answerStrip : 机器人多轮回答 最终答案提示
     * template : 模板
     * clickFlag : 接口类型是否允许多次点击 0:不允许 1:允许
     */

    private String remindQuestion;
    private List<OnlineInterfaceRetInfo> interfaceRetList;
    private String inputContentList;//逗号分割的数组，需单独处理
    private String level;
    private String conversationId;
    private String retCode;
    private String retErrorMsg;
    private boolean endFlag;
    private String answerStrip;
    private String template;
    private String answer;
    private int clickFlag;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    //以下为自有属性
    //表示当前显示的页数
    private int pageNum = 1;
    //ZhiChiReplyAnswer msgType 为1511 特殊处理的字段  其它类型都为null
    private List<Map<String, String>> icLists;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getRemindQuestion() {
        return remindQuestion;
    }

    public void setRemindQuestion(String remindQuestion) {
        this.remindQuestion = remindQuestion;
    }

    public List<OnlineInterfaceRetInfo> getInterfaceRetList() {
        return interfaceRetList;
    }

    public void setInterfaceRetList(List<OnlineInterfaceRetInfo> interfaceRetList) {
        this.interfaceRetList = interfaceRetList;
    }

    public String getInputContentList() {
        return inputContentList;
    }

    public void setInputContentList(String inputContentList) {
        this.inputContentList = inputContentList;
    }

    public List<Map<String, String>> getIcLists() {
        return icLists;
    }

    public void setIcLists(List<Map<String, String>> icLists) {
        this.icLists = icLists;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetErrorMsg() {
        return retErrorMsg;
    }

    public void setRetErrorMsg(String retErrorMsg) {
        this.retErrorMsg = retErrorMsg;
    }

    public boolean getEndFlag() {
        return endFlag;
    }

    public void setEndFlag(boolean endFlag) {
        this.endFlag = endFlag;
    }

    public String getAnswerStrip() {
        return answerStrip;
    }

    public void setAnswerStrip(String answerStrip) {
        this.answerStrip = answerStrip;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getClickFlag() {
        return clickFlag;
    }

    public void setClickFlag(int clickFlag) {
        this.clickFlag = clickFlag;
    }

    @Override
    public String toString() {
        return "SobotMultiDiaRespInfo{" +
                "remindQuestion='" + remindQuestion + '\'' +
                ", interfaceRetList=" + interfaceRetList +
                ", inputContentList=" + inputContentList +
                ", level='" + level + '\'' +
                ", conversationId='" + conversationId + '\'' +
                ", retCode='" + retCode + '\'' +
                ", retErrorMsg='" + retErrorMsg + '\'' +
                ", endFlag=" + endFlag +
                ", answerStrip='" + answerStrip + '\'' +
                ", template='" + template + '\'' +
                ", answer='" + answer + '\'' +
                ", clickFlag=" + clickFlag +
                ", pageNum=" + pageNum +
                ", icLists=" + icLists +
                '}';
    }
}