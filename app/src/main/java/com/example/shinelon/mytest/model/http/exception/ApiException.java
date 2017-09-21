package com.example.shinelon.mytest.model.http.exception;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : 自定义的exception
 */
public class ApiException extends Exception {

    private String code;

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(String msg, String code) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
