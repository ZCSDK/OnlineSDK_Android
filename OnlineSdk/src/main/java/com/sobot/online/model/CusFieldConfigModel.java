package com.sobot.online.model;

import java.io.Serializable;
import java.util.List;

/**
 * 客户信息自定义字段实体类
 */

public class CusFieldConfigModel implements Serializable{

    private String companyId;
    private int computedUnit;//1 是相加，2是相乘
    private String createId;
    private int createTime;
    private String fieldId;
    private int fieldLevel;
    private String fieldName;
    private String fieldRemark;
    private int fieldStatus;
    private int fieldType;
    private String fieldVariable;
    private int numericalFlag;
    private int fillFlag;//是否必填
    private int openFlag = 1;
    private int operateType;
    private int optionFlag;
    private int queryFlag;
    private String operateUnitId1;//"e1db9834d1ad4e509139ee2d9d7d00a8"
    private String operateUnitId2;//"1ca6115e414b47bcb4014fa0571c568c"
    private int optionValueFlag;
    private int queryShowFlag;
    private int relatedFlag;
    private int sortNo;
    private String updateId;
    private int updateTime;
    private int workShowFlag;
    private int workSortNo;
    private String limitOptions;

    private String fieldDataValue;//name
    private String temFieldId;//id
    private String fieldValue;//value
    private String temDataId;//id
    private String summy;//最后合计

    private List<CombinFormFieldList> combinFormFieldList;
    private List<CusFieldDataInfoList> cusFieldDataInfoList;

    @Override
    public String toString() {
        return "CusFieldConfig{" +
                "companyId='" + companyId + '\'' +
                ", computedUnit=" + computedUnit +
                ", createId='" + createId + '\'' +
                ", createTime=" + createTime +
                ", fieldId='" + fieldId + '\'' +
                ", fieldLevel=" + fieldLevel +
                ", fieldName='" + fieldName + '\'' +
                ", fieldRemark='" + fieldRemark + '\'' +
                ", fieldStatus=" + fieldStatus +
                ", fieldType=" + fieldType +
                ", fieldVariable='" + fieldVariable + '\'' +
                ", numericalFlag=" + numericalFlag +
                ", fillFlag=" + fillFlag +
                ", openFlag=" + openFlag +
                ", operateType=" + operateType +
                ", optionFlag=" + optionFlag +
                ", queryFlag=" + queryFlag +
                ", operateUnitId1='" + operateUnitId1 + '\'' +
                ", operateUnitId2='" + operateUnitId2 + '\'' +
                ", optionValueFlag=" + optionValueFlag +
                ", queryShowFlag=" + queryShowFlag +
                ", relatedFlag=" + relatedFlag +
                ", sortNo=" + sortNo +
                ", updateId='" + updateId + '\'' +
                ", updateTime=" + updateTime +
                ", workShowFlag=" + workShowFlag +
                ", workSortNo=" + workSortNo +
                ", fieldDataValue='" + fieldDataValue + '\'' +
                ", temFieldId='" + temFieldId + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                ", temDataId='" + temDataId + '\'' +
                ", combinFormFieldList=" + combinFormFieldList +
                ", cusFieldDataInfoList=" + cusFieldDataInfoList +
                ", summy=" + summy +
                ", limitOptions=" + limitOptions +
                '}';
    }

    public String getLimitOptions() {
        return limitOptions;
    }

    public void setLimitOptions(String limitOptions) {
        this.limitOptions = limitOptions;
    }

    public String getSummy() {
        return summy;
    }

    public void setSummy(String summy) {
        this.summy = summy;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getComputedUnit() {
        return computedUnit;
    }

    public void setComputedUnit(int computedUnit) {
        this.computedUnit = computedUnit;
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

    public String getFieldRemark() {
        return fieldRemark;
    }

    public void setFieldRemark(String fieldRemark) {
        this.fieldRemark = fieldRemark;
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

    public int getNumericalFlag() {
        return numericalFlag;
    }

    public void setNumericalFlag(int numericalFlag) {
        this.numericalFlag = numericalFlag;
    }

    public int getFillFlag() {
        return fillFlag;
    }

    public void setFillFlag(int fillFlag) {
        this.fillFlag = fillFlag;
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

    public int getOptionFlag() {
        return optionFlag;
    }

    public void setOptionFlag(int optionFlag) {
        this.optionFlag = optionFlag;
    }

    public int getQueryFlag() {
        return queryFlag;
    }

    public void setQueryFlag(int queryFlag) {
        this.queryFlag = queryFlag;
    }

    public String getOperateUnitId1() {
        return operateUnitId1;
    }

    public void setOperateUnitId1(String operateUnitId1) {
        this.operateUnitId1 = operateUnitId1;
    }

    public String getOperateUnitId2() {
        return operateUnitId2;
    }

    public void setOperateUnitId2(String operateUnitId2) {
        this.operateUnitId2 = operateUnitId2;
    }

    public int getOptionValueFlag() {
        return optionValueFlag;
    }

    public void setOptionValueFlag(int optionValueFlag) {
        this.optionValueFlag = optionValueFlag;
    }

    public int getQueryShowFlag() {
        return queryShowFlag;
    }

    public void setQueryShowFlag(int queryShowFlag) {
        this.queryShowFlag = queryShowFlag;
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

    public String getFieldDataValue() {
        return fieldDataValue;
    }

    public void setFieldDataValue(String fieldDataValue) {
        this.fieldDataValue = fieldDataValue;
    }

    public String getTemFieldId() {
        return temFieldId;
    }

    public void setTemFieldId(String temFieldId) {
        this.temFieldId = temFieldId;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getTemDataId() {
        return temDataId;
    }

    public void setTemDataId(String temDataId) {
        this.temDataId = temDataId;
    }

    public List<CombinFormFieldList> getCombinFormFieldList() {
        return combinFormFieldList;
    }

    public void setCombinFormFieldList(List<CombinFormFieldList> combinFormFieldList) {
        this.combinFormFieldList = combinFormFieldList;
    }

    public List<CusFieldDataInfoList> getCusFieldDataInfoList() {
        return cusFieldDataInfoList;
    }

    public void setCusFieldDataInfoList(List<CusFieldDataInfoList> cusFieldDataInfoList) {
        this.cusFieldDataInfoList = cusFieldDataInfoList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CusFieldConfigModel) {
            if (((CusFieldConfigModel) obj).getFieldId().equals(this.getFieldId())) {
                return true;
            }
        }
        return false;
    }
}