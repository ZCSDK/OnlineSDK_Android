package com.sobot.online.model;

import com.sobot.online.api.OnlineBaseCode;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 服务总结查询实体类
 */
public class SummaryInfoModelResult extends OnlineBaseCode<SummaryInfoModelResult.SummaryInfoModel> {

    public class SummaryInfoModel implements Serializable {
        private String questionRemark;
        private String questionStatus = "-1";
        private UnitInfoModel unitInfo;
        private long updateTime;
        private String aname;

        public String getAname() {
            return aname;
        }

        public void setAname(String aname) {
            this.aname = aname;
        }

        private ArrayList<UnitTypeInfoModel> unitTypeInfo;

        public String getQuestionRemark() {
            return questionRemark;
        }

        public void setQuestionRemark(String questionRemark) {
            this.questionRemark = questionRemark;
        }

        public String getQuestionStatus() {
            return questionStatus;
        }

        public void setQuestionStatus(String questionStatus) {
            this.questionStatus = questionStatus;
        }

        public UnitInfoModel getUnitInfo() {
            return unitInfo;
        }

        public void setUnitInfo(UnitInfoModel unitInfo) {
            this.unitInfo = unitInfo;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public ArrayList<UnitTypeInfoModel> getUnitTypeInfo() {
            return unitTypeInfo;
        }

        public void setUnitTypeInfo(ArrayList<UnitTypeInfoModel> unitTypeInfo) {
            this.unitTypeInfo = unitTypeInfo;
        }

        @Override
        public String toString() {
            return "SummaryInfoModel{" +
                    "questionRemark='" + questionRemark + '\'' +
                    ", questionStatus=" + questionStatus +
                    ", unitInfo=" + unitInfo +
                    ", updateTime=" + updateTime +
                    ", unitTypeInfo=" + unitTypeInfo +
                    '}';
        }

    }
}
