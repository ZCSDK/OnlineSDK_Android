package com.sobot.online.model;

import java.io.Serializable;
import java.util.List;

/**
 * 服务总结 咨询业务实体类
 */
public class UnitInfoModel implements Serializable {

    private String companyId;
    private String createId;
    private int createTime;
    private int sortFlag;
    private String unitDoc;
    //业务单元id
    private String unitId;
    //业务名称
    private String unitName;
    private int unitStatus;
    private String updateId;
    private int updateTime;

    private List<UnitTypeInfoModel> types;

    private boolean isChecked;

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

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(int sortFlag) {
        this.sortFlag = sortFlag;
    }

    public String getUnitDoc() {
        return unitDoc;
    }

    public void setUnitDoc(String unitDoc) {
        this.unitDoc = unitDoc;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(int unitStatus) {
        this.unitStatus = unitStatus;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public List<UnitTypeInfoModel> getTypes() {
        return types;
    }

    public void setTypes(List<UnitTypeInfoModel> types) {
        this.types = types;
    }
}
