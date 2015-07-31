package com.hupeng.signup.model.response;


/**
 * Created by End User on 2015/7/30.
 */
public class BaseResponse {
    FailResponse   fail;

    public static class FailResponse {
        public int code;
        public String errmsg;
    }
}
