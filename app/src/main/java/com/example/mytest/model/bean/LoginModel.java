package com.example.mytest.model.bean;

/**
 * Created by luyu on 2017/3/14
 */

public class LoginModel {

    /**
     * header : {"status":"ok","msg":"用户登录成功！"}
     * body : {"uId":"U000001","levelPresent":5,"roleType":1,
     * "userId":"1","money":0,"token":"AC23E22E3B5B7FEF6014CFE31F9F55E9"}
     */
    HeaderBean header;
    BodyBean body;

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * status : ok
         * msg : 用户登录成功！
         */
        String status;
        String code;
        String msg;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "HeaderBean{" +
                    "status='" + status + '\'' +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    public static class BodyBean {
        /**
         * uId : U000001
         * levelPresent : 5
         * roleType : 1
         * userId : 1
         * money : 0.0
         * token : AC23E22E3B5B7FEF6014CFE31F9F55E9
         */
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

    @Override
    public String toString() {
        return "LoginModel{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
