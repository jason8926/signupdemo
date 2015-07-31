package com.hupeng.signup.model;

import com.hupeng.signup.model.param.SmsCodeParam;
import com.hupeng.signup.model.response.UserResponse;
import com.hupeng.signup.utils.LiteHttpUtil;
import com.litesuits.android.log.Log;
import com.litesuits.http.data.NameValuePair;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.Request;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.request.content.SerializableBody;
import com.litesuits.http.request.content.UrlEncodedFormBody;
import com.litesuits.http.request.param.HttpMethod;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by End User on 2015/7/30.
 */
public class UserModel {
    private static UserModel mInstance = null;
    private static String baseUrl  = "http://10.0.0.17";

    private UserModel() {
    }
    public static UserModel getInstance() {
        if (mInstance == null)
            mInstance = new UserModel();
        return mInstance;
    }

    public static void getUserInfo() {
        String url = baseUrl + "/" + "userInfo";
        LiteHttpUtil.getInstance().getAsyncExcutor().execute(new Request(url), new HttpModelHandler<UserResponse>() {
            @Override
            protected void onSuccess(UserResponse data, Response res) {
                Log.i("data: " + data);
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                Log.i("e: " + e);
            }

        });
    }

    public static void getSmscode(final SmsCodeParam param, final HttpModelHandler callback) {
        String url = baseUrl + "/" + "getSmscode";
        Request req = new Request(url);
        req.setMethod(HttpMethod.Post);
        req.setHttpBody(new JsonBody(param));
        LiteHttpUtil.getInstance().getAsyncExcutor().execute(req, callback);
    }
}
