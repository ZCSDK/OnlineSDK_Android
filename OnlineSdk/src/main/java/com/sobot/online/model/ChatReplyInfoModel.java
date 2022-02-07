package com.sobot.online.model;

import java.io.Serializable;
import java.util.List;

/**
 * 快捷回复内容实体类
 */
public class ChatReplyInfoModel implements Serializable {
    private String id;
    private String value;
    private String groupId;
    private Long updateTime;
    private String userId;
    private String companyId;
    private int sortNo;
    private int adminFlag;
    private int searchCount;
    private String orderBy;//排序字段
    private String orderByType;//正序逆序
    private List<String> groupIds;//批量组id
    private List<String> replyIds;//批量回复id
    private String temReplyIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(Integer adminFlag) {
        this.adminFlag = adminFlag;
    }

    public Integer getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Integer searchCount) {
        this.searchCount = searchCount;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderByType() {
        return orderByType;
    }

    public void setOrderByType(String orderByType) {
        this.orderByType = orderByType;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }

    public List<String> getReplyIds() {
        return replyIds;
    }

    public void setReplyIds(List<String> replyIds) {
        this.replyIds = replyIds;
    }

    public String getTemReplyIds() {
        return temReplyIds;
    }

    public void setTemReplyIds(String temReplyIds) {
        this.temReplyIds = temReplyIds;
    }

    @Override
    public String toString() {
        return "ChatReplyInfoModel{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", groupId='" + groupId + '\'' +
                ", updateTime=" + updateTime +
                ", userId='" + userId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", sortNo=" + sortNo +
                ", adminFlag=" + adminFlag +
                ", searchCount=" + searchCount +
                ", orderBy='" + orderBy + '\'' +
                ", orderByType='" + orderByType + '\'' +
                ", groupIds=" + groupIds +
                ", replyIds=" + replyIds +
                ", temReplyIds='" + temReplyIds + '\'' +
                '}';
    }
}
