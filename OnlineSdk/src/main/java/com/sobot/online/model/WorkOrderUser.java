package com.sobot.online.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */

public class WorkOrderUser implements Serializable {

    private String partnerId;
    private String areaName = "";// : "襄垣县"
    private String areaId;// : "140423"
    private String cityId;//  : "140400"
    private String cityName = "";//  : "长治市"
    private String createServiceId;//   : "99a81432a5c2490e8cbcb0aaaf34be2e"
    private String createServiceName;//   : "杨光"
    private String createTime;//   : 1501767241
    private String email;
    private String enterpriseId;//  : "83130c11b7e94715b6a6b0f19a5bba6b"
    private String enterpriseName;//  : "Airbnb"
    private String id;
    //用户头像
    private String img;
    //用户昵称
    private String nick;
    //是否为vip用户
    private boolean isVip;
    //用户vip等级
    private String vipLevelName;
    private String vipLevel;
    //访客id
    private String visitorIds;
    private String tmpNick;//搜索的时候，要高亮，用来临时存放高亮的昵称
    private String passwd;// : "c39a3384a774ba6afd62a739a44200a8"
    private String pid;//  : "f582fe8814a94ad1b6a210bdf794cfe4"
    private String proviceId;//  : "140000"
    private String proviceName = "";//  : "山西省"
    private String qq;
    private String remark;// : "备注，备注，备注"
    private String salt;// : "c77533a4e12c472a9f199615d2bce16b"
    private String source;//用户来源  2是APP
    private String status;
    private String tel;
    private String type;
    private String uname;// : "杨光"
    private String updateTime;// : 1502250180
    private String updateUserId;// : "bd2a71805d8a4493844d34698829c111"
    private String updateUserName;// : "张学壮"
    private List<TicketResultListModel> resultList;
    private String customFields;
    private int userStatus;

    @Override
    public String toString() {
        return "WorkOrderUser{" +
                "partnerId='" + partnerId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", areaId='" + areaId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", createServiceId='" + createServiceId + '\'' +
                ", createServiceName='" + createServiceName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", email='" + email + '\'' +
                ", enterpriseId='" + enterpriseId + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", id='" + id + '\'' +
                ", img='" + img + '\'' +
                ", nick='" + nick + '\'' +
                ", passwd='" + passwd + '\'' +
                ", resultList='" + resultList + '\'' +
                ", pid='" + pid + '\'' +
                ", proviceId='" + proviceId + '\'' +
                ", proviceName='" + proviceName + '\'' +
                ", qq='" + qq + '\'' +
                ", remark='" + remark + '\'' +
                ", salt='" + salt + '\'' +
                ", source=" + source +
                ", status='" + status + '\'' +
                ", tel='" + tel + '\'' +
                ", type='" + type + '\'' +
                ", uname='" + uname + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateUserName='" + updateUserName + '\'' +
                ", vipLevelName='" + vipLevelName + '\'' +
                ", vipLevel='" + vipLevel + '\'' +
                ", customFields='" + customFields + '\'' +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getCustomFields() {
        return customFields;
    }

    public void setCustomFields(String customFields) {
        this.customFields = customFields;
    }

    public List<TicketResultListModel> getResultList() {
        return resultList;
    }

    public void setResultList(List<TicketResultListModel> resultList) {
        this.resultList = resultList;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCreateServiceId() {
        return createServiceId;
    }

    public void setCreateServiceId(String createServiceId) {
        this.createServiceId = createServiceId;
    }

    public String getCreateServiceName() {
        return createServiceName;
    }

    public void setCreateServiceName(String createServiceName) {
        this.createServiceName = createServiceName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProviceId() {
        return proviceId;
    }

    public void setProviceId(String proviceId) {
        this.proviceId = proviceId;
    }

    public String getProviceName() {
        return proviceName;
    }

    public void setProviceName(String proviceName) {
        this.proviceName = proviceName;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getTmpNick() {
        return tmpNick;
    }

    public void setTmpNick(String tmpNick) {
        this.tmpNick = tmpNick;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public String getVipLevelName() {
        return vipLevelName;
    }

    public void setVipLevelName(String vipLevelName) {
        this.vipLevelName = vipLevelName;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getVisitorIds() {
        return visitorIds;
    }

    public void setVisitorIds(String visitorIds) {
        this.visitorIds = visitorIds;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
}