package com.sobot.online.model;

import java.io.Serializable;
import java.util.List;

/**
 * 内部知识库实体类
 */
public class RebotInsideAnswerModel implements Serializable {
    private Integer robotFlag;
    private String robotName;
    private List<RobotSmartAnswerVo> sugguestions;

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

    public List<RobotSmartAnswerVo> getSugguestions() {
        return sugguestions;
    }

    public void setSugguestions(List<RobotSmartAnswerVo> sugguestions) {
        this.sugguestions = sugguestions;
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
         * 机器人标识
         */
        private Integer robotFlag;

        /**
         * 机器人名字
         */
        private String robotName;

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
    }
}
