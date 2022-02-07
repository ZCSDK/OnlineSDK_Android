package com.sobot.online.model;

import java.io.Serializable;

public class CombinFormField implements Serializable {

    /**
     * checked : false
     * companyId : e6fb17f5af9f4487a8ec7f1f58c972ba
     * createId : 1b6d22ab52bc4db99c3c7b055abd3332
     * createTime : 1543475161
     * dataId : 9febfc1678bf4799a53113fcbd7010f6
     * fieldId : 13376ed6680d4e9a8d7d9cb225517bca
     * fieldLevel : 1
     * fieldName : 实时分类
     * fieldStatus : 1
     * fieldType : 6
     * fieldVariable : customField3
     * fillFlag : 0
     * isOpenFlag : 1
     * nodeFlag : 1
     * numericalFlag : 0
     * openFlag : 1
     * operateType : 32
     * optionValueFlag : 1
     * parentFieldId : 1df7de037c784ba1a2d68d44cbbb77ad
     * queryFlag : 1
     * queryShowFlag : 1
     * relatedField :
     * relatedFlag : 0
     * sortNo : 3
     * text : 鞋靴
     * title : 实时分类
     * updateId : 1b6d22ab52bc4db99c3c7b055abd3332
     * updateTime : 1544441525
     * value : 543475161958938
     * workShowFlag : 0
     * workSortNo : 1
     */

    private String tmpId;

    private boolean checked;
    private String companyId;
    private String createId;
    private int createTime;
    private String dataId;
    private String fieldId;
    private int fieldLevel;
    private String fieldName;
    private int fieldStatus;
    private int fieldType;
    private String fieldVariable;
    private int fillFlag;
    private int isOpenFlag;
    private int nodeFlag;
    private int numericalFlag;
    private int openFlag;
    private int operateType;
    private int optionValueFlag;
    private String parentFieldId;
    private int queryFlag;
    private int queryShowFlag;
    private String relatedField;
    private int relatedFlag;
    private int sortNo;
    private String text;
    private String title;
    private String updateId;
    private int updateTime;
    private String value;
    private int workShowFlag;
    private int workSortNo;

    private boolean lastItem =false;//组合字段中  一组数据的最后一个设置为1，用来标记删除按钮
    private String tmpValue;//如果是数值类型和文本类型，用来存放数据

    private String changeValue;//第一二三级字段的数据发生改变后，与之相关的那些字段的数据也要改变，
    private boolean clearValue = false;//当前等级的选项发生改变后，是否要清空与之有关系的那些字段的数据

    public boolean isClearValue() {
        return clearValue;
    }

    public void setClearValue(boolean clearValue) {
        this.clearValue = clearValue;
    }

    @Override
    public String toString() {
        return "CombinFormField{" +
                "tmpId='" + tmpId + '\'' +
                ", checked=" + checked +
                ", companyId='" + companyId + '\'' +
                ", createId='" + createId + '\'' +
                ", createTime=" + createTime +
                ", dataId='" + dataId + '\'' +
                ", fieldId='" + fieldId + '\'' +
                ", fieldLevel=" + fieldLevel +
                ", fieldName='" + fieldName + '\'' +
                ", fieldStatus=" + fieldStatus +
                ", fieldType=" + fieldType +
                ", fieldVariable='" + fieldVariable + '\'' +
                ", fillFlag=" + fillFlag +
                ", isOpenFlag=" + isOpenFlag +
                ", nodeFlag=" + nodeFlag +
                ", numericalFlag=" + numericalFlag +
                ", openFlag=" + openFlag +
                ", operateType=" + operateType +
                ", optionValueFlag=" + optionValueFlag +
                ", parentFieldId='" + parentFieldId + '\'' +
                ", queryFlag=" + queryFlag +
                ", queryShowFlag=" + queryShowFlag +
                ", relatedField='" + relatedField + '\'' +
                ", relatedFlag=" + relatedFlag +
                ", sortNo=" + sortNo +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", updateId='" + updateId + '\'' +
                ", updateTime=" + updateTime +
                ", value='" + value + '\'' +
                ", workShowFlag=" + workShowFlag +
                ", workSortNo=" + workSortNo +
                ", tmpValue='" + tmpValue + '\'' +
                ", lastItem='" + lastItem + '\'' +
                ", changeValue='" + changeValue + '\'' +
                '}';
    }

    public String getChangeValue() {
        return changeValue;
    }

    public void setChangeValue(String changeValue) {
        this.changeValue = changeValue;
    }

    public String getTmpId() {
        return tmpId;
    }

    public void setTmpId(String tmpId) {
        this.tmpId = tmpId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public int getFieldLevel() {
        return fieldLevel;
    }

    public void setFieldLevel(int fieldLevel) {
        this.fieldLevel = fieldLevel;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getFieldStatus() {
        return fieldStatus;
    }

    public void setFieldStatus(int fieldStatus) {
        this.fieldStatus = fieldStatus;
    }

    public int getFieldType() {
        return fieldType;
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldVariable() {
        return fieldVariable;
    }

    public void setFieldVariable(String fieldVariable) {
        this.fieldVariable = fieldVariable;
    }

    public int getFillFlag() {
        return fillFlag;
    }

    public void setFillFlag(int fillFlag) {
        this.fillFlag = fillFlag;
    }

    public int getIsOpenFlag() {
        return isOpenFlag;
    }

    public void setIsOpenFlag(int isOpenFlag) {
        this.isOpenFlag = isOpenFlag;
    }

    public int getNodeFlag() {
        return nodeFlag;
    }

    public void setNodeFlag(int nodeFlag) {
        this.nodeFlag = nodeFlag;
    }

    public int getNumericalFlag() {
        return numericalFlag;
    }

    public void setNumericalFlag(int numericalFlag) {
        this.numericalFlag = numericalFlag;
    }

    public int getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(int openFlag) {
        this.openFlag = openFlag;
    }

    public int getOperateType() {
        return operateType;
    }

    public void setOperateType(int operateType) {
        this.operateType = operateType;
    }

    public int getOptionValueFlag() {
        return optionValueFlag;
    }

    public void setOptionValueFlag(int optionValueFlag) {
        this.optionValueFlag = optionValueFlag;
    }

    public String getParentFieldId() {
        return parentFieldId;
    }

    public void setParentFieldId(String parentFieldId) {
        this.parentFieldId = parentFieldId;
    }

    public int getQueryFlag() {
        return queryFlag;
    }

    public void setQueryFlag(int queryFlag) {
        this.queryFlag = queryFlag;
    }

    public int getQueryShowFlag() {
        return queryShowFlag;
    }

    public void setQueryShowFlag(int queryShowFlag) {
        this.queryShowFlag = queryShowFlag;
    }

    public String getRelatedField() {
        return relatedField;
    }

    public void setRelatedField(String relatedField) {
        this.relatedField = relatedField;
    }

    public int getRelatedFlag() {
        return relatedFlag;
    }

    public void setRelatedFlag(int relatedFlag) {
        this.relatedFlag = relatedFlag;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getWorkShowFlag() {
        return workShowFlag;
    }

    public void setWorkShowFlag(int workShowFlag) {
        this.workShowFlag = workShowFlag;
    }

    public int getWorkSortNo() {
        return workSortNo;
    }

    public void setWorkSortNo(int workSortNo) {
        this.workSortNo = workSortNo;
    }

    public String getTmpValue() {
        return tmpValue;
    }

    public void setTmpValue(String tmpValue) {
        this.tmpValue = tmpValue;
    }

    public boolean isLastItem() {
        return lastItem;
    }

    public void setLastItem(boolean lastItem) {
        this.lastItem = lastItem;
    }
}