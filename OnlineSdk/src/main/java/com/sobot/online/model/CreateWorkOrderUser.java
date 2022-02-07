package com.sobot.online.model;

import java.io.Serializable;
import java.util.List;

public class CreateWorkOrderUser implements Serializable {

    /**
     * createServiceId : 1b6d22ab52bc4db99c3c7b055abd3332
     * createServiceName : 超级管理员
     * createTime : 1547540029
     * departmentId : e6fb17f5af9f4487a8ec7f1f58c972ba|1426386263294b57bbdf2d7f8fa25829
     * email : 122666666@qq.com
     * exFields : {}
     * id : a1dd118d52874b95a4d2450972e9bc17
     * img : https://img.sobot.com/console/common/face/user.png
     * isVip : 0
     * nick : 添加用户一
     * passwd : 94c8df207cfe3b4e38f2203ae4274244
     * pid : e6fb17f5af9f4487a8ec7f1f58c972ba
     * resultList : []
     * salt : 9bbd4c1d1fd34c6cbf377b635c71a419
     * source : 0
     * status : 8
     * tel : 13613440999
     * type : 0
     * updateTime : 1547540029
     * updateUserId : 1b6d22ab52bc4db99c3c7b055abd3332
     * updateUserName : 超级管理员
     */

    private String createServiceId;
    private String createServiceName;
    private int createTime;
    private String departmentId;
    private String email;
    private ExFieldsBean exFields;
    private String id;
    private String img;
    private String isVip;
    private String nick;
    private String passwd;
    private String pid;
    private String salt;
    private int source;
    private int status;
    private String tel;
    private int type;
    private int updateTime;
    private String updateUserId;
    private String updateUserName;
    private List<?> resultList;
    private String visitorIds;

    public String getVisitorIds() {
        return visitorIds;
    }

    public void setVisitorIds(String visitorIds) {
        this.visitorIds = visitorIds;
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

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ExFieldsBean getExFields() {
        return exFields;
    }

    public void setExFields(ExFieldsBean exFields) {
        this.exFields = exFields;
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

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
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

    public List<?> getResultList() {
        return resultList;
    }

    public void setResultList(List<?> resultList) {
        this.resultList = resultList;
    }

    public static class ExFieldsBean {
    }
}