package com.sobot.online.model;


import com.sobot.onlinecommon.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 排队用户实体类
 */
public class QueueUserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int countPage;

    private List<QueueUser> list;

    private int waitSize;

    public int getCountPage() {
        return countPage;
    }

    public void setCountPage(int countPage) {
        this.countPage = countPage;
    }

    public List<QueueUser> getList() {
        return list;
    }

    public void setList(List<QueueUser> list) {
        this.list = list;
    }

    public int getWaitSize() {
        return waitSize;
    }

    public void setWaitSize(int waitSize) {
        this.waitSize = waitSize;
    }

    @Override
    public String toString() {
        return "QueueUserModel [countPage=" + countPage + ", list=" + list
                + ", waitSize=" + waitSize + "]";
    }

    public class QueueUser {

        private String groupId;// 技能组id

        @SerializedName("id")
        private String uid;// 用户id

        private String lastTime;// 最后请求时间

        @SerializedName("name")
        private String uname;// 用户名字

        private String remainTime;// 等待时间

        private int source;// 显示头像来源

        private String visitTitle;

        private String groupName;// 技能组名称

        private int state = 0;// 当前的状态 0正在排队 1已邀请 2已被他人邀请 3用户已离线
        
        private boolean isClickable;//是否可点击

        public boolean isClickable() {
            return isClickable;
        }

        public void setClickable(boolean isClickable) {
            this.isClickable = isClickable;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
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

        @Override
        public String toString() {
            return "QueueUser [groupId=" + groupId + ", uid=" + uid
                    + ", lastTime=" + lastTime + ", uname=" + uname
                    + ", remainTime=" + remainTime + ", source=" + source
                    + ", visitTitle=" + visitTitle + ", groupName=" + groupName
                    + "]";
        }

        public String getVisitTitle() {
            return visitTitle;
        }

        public void setVisitTitle(String visitTitle) {
            this.visitTitle = visitTitle;
        }
    }

}