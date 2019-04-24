package com.example.mytest.model.bean;

import java.util.List;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/20
 * Desc :
 */

public class LoginBean {

    /**
     * nickName : string
     * platForm : 0
     * roles : [{"createId":0,"createTime":"2019-04-01T06:13:13.646Z","designation":"string","id":0,"ifUseful":0,"parentId":0,"platform":0,"roleName":"string"}]
     * sessionId : string
     * userId : 0
     * userPhone : string
     */

    private String nickName;
    private int platForm;
    private String sessionId;
    private int userId;
    private String userPhone;
    private String alia;
    private List<RolesBean> roles;

    public String getAlia() {
        return alia;
    }

    public void setAlia(String alia) {
        this.alia = alia;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getPlatForm() {
        return platForm;
    }

    public void setPlatForm(int platForm) {
        this.platForm = platForm;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public List<RolesBean> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesBean> roles) {
        this.roles = roles;
    }

    public static class RolesBean {
        /**
         * createId : 0
         * createTime : 2019-04-01T06:13:13.646Z
         * designation : string
         * id : 0
         * ifUseful : 0
         * parentId : 0
         * platform : 0
         * roleName : string
         */

        private int createId;
        private String createTime;
        private String designation;
        private int id;
        private int ifUseful;
        private int parentId;
        private int platform;
        private String roleName;

        public int getCreateId() {
            return createId;
        }

        public void setCreateId(int createId) {
            this.createId = createId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIfUseful() {
            return ifUseful;
        }

        public void setIfUseful(int ifUseful) {
            this.ifUseful = ifUseful;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getPlatform() {
            return platform;
        }

        public void setPlatform(int platform) {
            this.platform = platform;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }
}
