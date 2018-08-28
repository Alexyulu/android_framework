package com.example.mytest.model.bean;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/19
 * Desc :
 */

public class PassCodeBean {
    /**
     * verifyCode : 5466
     * userPhone : 13032135595
     */
    String verifyCode;
    String userPhone;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "BodyBean{" +
                "verifyCode='" + verifyCode + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
