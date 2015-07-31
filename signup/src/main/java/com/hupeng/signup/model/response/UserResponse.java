package com.hupeng.signup.model.response;

import com.hupeng.signup.model.subclass.User;

/**
 * Created by End User on 2015/7/30.
 */
public class UserResponse extends BaseResponse {
    SuccessResponse success;

    public static class SuccessResponse {
        User data;
    }
}
