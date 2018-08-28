package com.example.mytest.model.http.exception;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : 自定义的exception
 */
public class ApiException extends Exception {

    private int code;
    private String errMsg;

   /* public ApiException(String msg) {
        super(msg);
    }*/

    public ApiException(String msg, int code) {
        this.code = code;
        this.errMsg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
