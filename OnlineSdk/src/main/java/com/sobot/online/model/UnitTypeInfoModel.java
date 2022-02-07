package com.sobot.online.model;

import java.io.Serializable;

/**
 * 服务总结 业务类型实体类
 */
public class UnitTypeInfoModel implements Serializable, Comparable<UnitTypeInfoModel> {

    private String companyId;
    private String createId;
    private String createTime;
    private String level;
    private String nodeFlag;
    private String parentId;
    private String sortFlag;
    private String typeId;
    private String typeName;
    private String typeStatus;
    private String typeStr;
    private String unitId;
    private String updateId;
    private String updateTime;

    private boolean checked;
    private boolean clickable = true;
    private boolean childrenChecked;

    public UnitTypeInfoModel(String typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNodeFlag() {
        return nodeFlag;
    }

    public void setNodeFlag(String nodeFlag) {
        this.nodeFlag = nodeFlag;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(String sortFlag) {
        this.sortFlag = sortFlag;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(String typeStatus) {
        this.typeStatus = typeStatus;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public boolean isChildrenChecked() {
        return childrenChecked;
    }

    public void setChildrenChecked(boolean childrenChecked) {
        this.childrenChecked = childrenChecked;
    }

    @Override
    public String toString() {
        return "UnitTypeInfoModel{" +
                "companyId='" + companyId + '\'' +
                ", createId='" + createId + '\'' +
                ", createTime=" + createTime +
                ", level=" + level +
                ", nodeFlag=" + nodeFlag +
                ", parentId='" + parentId + '\'' +
                ", sortFlag=" + sortFlag +
                ", typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                ", typeStatus=" + typeStatus +
                ", typeStr='" + typeStr + '\'' +
                ", unitId='" + unitId + '\'' +
                ", updateId='" + updateId + '\'' +
                ", updateTime=" + updateTime +
                ", checked=" + checked +
                ", clickable=" + clickable +
                ", childrenChecked=" + childrenChecked +
                '}';
    }

    @Override
    public int compareTo(UnitTypeInfoModel another) {
        long tmp = another.getTypeName().length() - this.getTypeName().length();
        if (tmp > 0) {
            return 1;
        } else if (tmp == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
