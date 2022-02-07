package com.sobot.online.model;

import java.io.Serializable;
import java.util.List;

/**
 * 机器人知识库实体类
 */
public class RebotSmartAnswerModel implements Serializable {
    private Integer robotFlag;
    private String robotName;
    private List<RobotSmartAnswerVo> infos;

    public Integer getRobotFlag() {
        return robotFlag;
    }

    public void setRobotFlag(Integer robotFlag) {
        this.robotFlag = robotFlag;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public List<RobotSmartAnswerVo> getInfos() {
        return infos;
    }

    public void setInfos(List<RobotSmartAnswerVo> infos) {
        this.infos = infos;
    }

    public class RobotSmartAnswerVo {

        /**
         * 问题
         */
        private String question;

        /**
         * 机器人查询的答案
         */
        private String answer;

        /**
         * 答案的纯文本,没有标签
         */
        private String answerTxt;

        /**
         * 答案的纯文本,没有标签
         */
        private List<ChatMessageRichTextModel.ChatMessageRichListModel> richList;
        /**
         * 文档id
         */
        private String docId;

        /**
         * 机器人标识
         */
        private Integer robotFlag;

        /**
         * 机器人名字
         */
        private String robotName;

        /**
         * 答案的分数
         */
        private double score;

        /**
         * 前端展示时是否展示答案 1:展示答案; 2:不展示
         */
        private int level;

        /**
         * 是否是关联问题 1:是关联问题; 0:不是关联问题
         */
        private int relationFlag;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getAnswerTxt() {
            return answerTxt;
        }

        public void setAnswerTxt(String answerTxt) {
            this.answerTxt = answerTxt;
        }

        public String getDocId() {
            return docId;
        }

        public void setDocId(String docId) {
            this.docId = docId;
        }

        public Integer getRobotFlag() {
            return robotFlag;
        }

        public void setRobotFlag(Integer robotFlag) {
            this.robotFlag = robotFlag;
        }

        public String getRobotName() {
            return robotName;
        }

        public void setRobotName(String robotName) {
            this.robotName = robotName;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getRelationFlag() {
            return relationFlag;
        }

        public void setRelationFlag(int relationFlag) {
            this.relationFlag = relationFlag;
        }

        public List<ChatMessageRichTextModel.ChatMessageRichListModel> getRichList() {
            return richList;
        }

        public void setRichList(List<ChatMessageRichTextModel.ChatMessageRichListModel> richList) {
            this.richList = richList;
        }
    }
}
