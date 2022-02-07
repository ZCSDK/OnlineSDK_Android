package com.sobot.online.model;

import java.io.Serializable;
import java.util.List;

/**
 * 快捷回复分组实体类
 */
public class ChatReplyGroupInfoModel implements Serializable {
    private String groupId;
    private String companyId;
    private String groupName;
    private String userId;
    private Long createTime;
    private int sortNo;
    private String parentGroupId;
    private int groupLevel;
    private List<ChatReplyGroupInfoModel> children;
    private List<ChatReplyInfoModel> quickreply;
    private int adminFlag; //是否为公用
    private int replyCount;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getParentGroupId() {
        return parentGroupId;
    }

    public void setParentGroupId(String parentGroupId) {
        this.parentGroupId = parentGroupId;
    }

    public int getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(int groupLevel) {
        this.groupLevel = groupLevel;
    }

    public List<ChatReplyGroupInfoModel> getChildren() {
        return children;
    }

    public void setChildren(List<ChatReplyGroupInfoModel> children) {
        this.children = children;
    }

    public List<ChatReplyInfoModel> getQuickreply() {
        return quickreply;
    }

    public void setQuickreply(List<ChatReplyInfoModel> quickreply) {
        this.quickreply = quickreply;
    }

    public Integer getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(Integer adminFlag) {
        this.adminFlag = adminFlag;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public String toString() {
        return "ChatReplyGroupInfoModel{" +
                "groupId='" + groupId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", sortNo=" + sortNo +
                ", parentGroupId='" + parentGroupId + '\'' +
                ", groupLevel=" + groupLevel +
                ", children=" + children +
                ", quickreply=" + quickreply +
                ", adminFlag=" + adminFlag +
                ", replyCount=" + replyCount +
                '}';
    }
}
