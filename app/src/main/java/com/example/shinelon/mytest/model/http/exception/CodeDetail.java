package com.example.shinelon.mytest.model.http.exception;

/**
 * Created by luyu on 2017/3/20
 */

public class CodeDetail {
    public static String judgeCode(String code) {
        switch (code) {
            case "ERR0000":
                return "服务异常";
            case "ERR0001":
                return "uptoken已经存在";
            case "ERR0002":
                return "用户名格式错误";
            case "ERR0003":
                return "验证码已过期或尚未获取,请重新获取验证码";
            case "ERR0004":
                return "该账户名已被使用,请使用其他账户";
            case "ERR0005":
                return "用户名错误";
            case "ERR0006":
                return "密码错误";
            case "ERR0007":
                return "参数错误";
            case "ERR0008":
                return "缺少必要的请求参数";
            case "ERR0009":
                return "该账号暂时不允许登录";
            case "ERR0010":
                return "请等待审核";
            case "ERR0011":
                return "验证码输入有误";
            case "ERR0012":
                return "验证码获取异常";
            case "ERR0013":
                return "登录信息验证有误";
            case "ERR0014":
                return "用户已经注册未选择身份，请选择身份";
            case "ERR0015":
                return "用户提现金额异常";
            case "ERR0016":
                return "定价时间已经结束";
            case "ERR0017":
                return "支付失败";
            case "ERR0018":
                return "对不起，二手车大师还没开放注册，只有获得邀请的大师才能使用，如有需要，请联系客服。";
            case "ERR0023":
                return "该账号还未设置登录密码";
            case "ERR0025":
                return "该车辆已经上传成功，不允许重新上传";
            default:
                return "查询异常";
        }
    }
}
