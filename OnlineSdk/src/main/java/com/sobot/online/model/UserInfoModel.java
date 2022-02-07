package com.sobot.online.model;

import java.io.Serializable;

/**
 * 用户信息实例
 */
public class UserInfoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String companyId;//公司id
    private int ismark;//是否是星标 0否1是
    private int isblack;//是否是黑名单 0否1是
    private String userId;	//用户id
    private String uname;	//用户名称
    private String qq;	//用户qq
    private String tel;	//用户手机
    private String email;	//用户邮箱
    private String remark;	//备注
    private String os;	//用户操作平台
    private String browser;	//用户浏览器
    private String age; //年龄
    private String partnerId;//对接id
    private String realname;//姓名
    private String sex;//性别
    private String weibo;//微博
    private String weixin;//微信
    private String params;
    private String customerId;//如果这个字段有值，说明他是一个客户，就可以去调用客户中心的查看客户和联络记录的接口了

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getIsmark() {
        return ismark;
    }

    public void setIsmark(int ismark) {
        this.ismark = ismark;
    }

    public int getIsblack() {
        return isblack;
    }

    public void setIsblack(int isblack) {
        this.isblack = isblack;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "UserInfoModel{" +
                "companyId='" + companyId + '\'' +
                ", ismark=" + ismark +
                ", isblack=" + isblack +
                ", userId='" + userId + '\'' +
                ", uname='" + uname + '\'' +
                ", qq='" + qq + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", remark='" + remark + '\'' +
                ", os='" + os + '\'' +
                ", browser='" + browser + '\'' +
                ", age='" + age + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", weibo='" + weibo + '\'' +
                ", weixin='" + weixin + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}