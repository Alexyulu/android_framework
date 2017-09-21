package com.example.shinelon.mytest.model.bean;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/20
 * Desc :
 */

public class LoginBean {
    String uId;
    String dId;
    int levelPresent;
    int roleType;
    String userId;
    int userCarCount;
    long companyId;
    String token;
    String userPhone;
    String upToken;
    int masterTDCount;
    long HBMoney;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public int getLevelPresent() {
        return levelPresent;
    }

    public void setLevelPresent(int levelPresent) {
        this.levelPresent = levelPresent;
    }

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserCarCount() {
        return userCarCount;
    }

    public void setUserCarCount(int userCarCount) {
        this.userCarCount = userCarCount;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUpToken() {
        return upToken;
    }

    public void setUpToken(String upToken) {
        this.upToken = upToken;
    }

    public int getMasterTDCount() {
        return masterTDCount;
    }

    public void setMasterTDCount(int masterTDCount) {
        this.masterTDCount = masterTDCount;
    }

    public long getHBMoney() {
        return HBMoney;
    }

    public void setHBMoney(long HBMoney) {
        this.HBMoney = HBMoney;
    }

    @Override
    public String toString() {
        return "BodyBean{" +
                "uId='" + uId + '\'' +
                ", dId='" + dId + '\'' +
                ", levelPresent=" + levelPresent +
                ", roleType=" + roleType +
                ", userId='" + userId + '\'' +
                ", userCarCount='" + userCarCount + '\'' +
                ", companyId=" + companyId +
                ", token='" + token + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", upToken='" + upToken + '\'' +
                ", masterTDCount='" + masterTDCount + '\'' +
                ", HBMoney='" + HBMoney + '\'' +
                '}';
    }
}
