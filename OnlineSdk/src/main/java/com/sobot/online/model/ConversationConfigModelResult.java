package com.sobot.online.model;

import com.sobot.online.api.OnlineBaseCode;

import java.io.Serializable;

/**
 * 登录客服的相关配置
 */
public class ConversationConfigModelResult extends OnlineBaseCode<ConversationConfigModelResult.ConversationConfigModel> {
    public class ConversationConfigModel implements Serializable {

        //浏览轨迹开关
        private int scanPathFlag;
        //是否打开浏览网站用户邀请功能
        private int isInvite;

        //咨询总结开关
        private int openSummaryFlag;
        //咨询总结业务单元开关  1-开启0-关闭 以下咨询总结相关同此设置
        private int summaryOperationFlag;
        //咨询总结业务单元必填开关
        private int summaryOperationInputFlag;
        //咨询总结问题类型开关
        private int summaryTypeFlag;
        //咨询总结处理状态开关
        private int summaryStatusFlag;
        //咨询总结处理状态必填开关
        private int summaryStatusInputFlag;
        //备注是否显示
        private int qDescribeShowFlag;

        public int getIsInvite() {
            return isInvite;
        }

        public void setIsInvite(int isInvite) {
            this.isInvite = isInvite;
        }

        public int getQDescribeShowFlag() {
            return qDescribeShowFlag;
        }

        public void setQDescribeShowFlag(int qDescribeShowFlag) {
            this.qDescribeShowFlag = qDescribeShowFlag;
        }

        public int getScanPathFlag() {
            return scanPathFlag;
        }

        public void setScanPathFlag(int scanPathFlag) {
            this.scanPathFlag = scanPathFlag;
        }

        public int getOpenSummaryFlag() {
            return openSummaryFlag;
        }

        public void setOpenSummaryFlag(int openSummaryFlag) {
            this.openSummaryFlag = openSummaryFlag;
        }

        public int getSummaryOperationFlag() {
            return summaryOperationFlag;
        }

        public void setSummaryOperationFlag(int summaryOperationFlag) {
            this.summaryOperationFlag = summaryOperationFlag;
        }

        public int getSummaryOperationInputFlag() {
            return summaryOperationInputFlag;
        }

        public void setSummaryOperationInputFlag(int summaryOperationInputFlag) {
            this.summaryOperationInputFlag = summaryOperationInputFlag;
        }

        public int getSummaryTypeFlag() {
            return summaryTypeFlag;
        }

        public void setSummaryTypeFlag(int summaryTypeFlag) {
            this.summaryTypeFlag = summaryTypeFlag;
        }

        public int getSummaryStatusInputFlag() {
            return summaryStatusInputFlag;
        }

        public void setSummaryStatusInputFlag(int summaryStatusInputFlag) {
            this.summaryStatusInputFlag = summaryStatusInputFlag;
        }

        public int getSummaryStatusFlag() {
            return summaryStatusFlag;
        }

        public void setSummaryStatusFlag(int summaryStatusFlag) {
            this.summaryStatusFlag = summaryStatusFlag;
        }

        public int getqDescribeShowFlag() {
            return qDescribeShowFlag;
        }

        public void setqDescribeShowFlag(int qDescribeShowFlag) {
            this.qDescribeShowFlag = qDescribeShowFlag;
        }

        @Override
        public String toString() {
            return "ConversationConfigModel{" +
                    "qDescribeShowFlag=" + qDescribeShowFlag +
                    ", scanPathFlag=" + scanPathFlag +
                    ", openSummaryFlag=" + openSummaryFlag +
                    '}';
        }
    }
}
