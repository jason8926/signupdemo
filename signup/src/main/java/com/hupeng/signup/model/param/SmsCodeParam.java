package com.hupeng.signup.model.param;

/**
 * Created by End User on 2015/7/31.
 */
public class SmsCodeParam {
    public SmsCodeParam(String phoneNumber, int type, String checkCode) {
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.checkCode = checkCode;
    }
    public  String phoneNumber;
    public int  type;
    public  String checkCode;
}
