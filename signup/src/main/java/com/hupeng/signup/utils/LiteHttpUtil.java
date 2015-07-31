package com.hupeng.signup.utils;

import com.litesuits.http.LiteHttpClient;
import com.litesuits.http.async.HttpAsyncExecutor;

/**
 * Created by End User on 2015/7/30.
 */
public class LiteHttpUtil {
    private static LiteHttpUtil instance = null;
    private LiteHttpClient client;
    private HttpAsyncExecutor asyncExcutor;

    public static LiteHttpUtil getInstance() {
        if (instance == null) {
            synchronized (LiteHttpUtil.class) {
                if (instance == null) {
                    instance = new LiteHttpUtil();
                }
            }
        }
        return instance;
    }

    private LiteHttpUtil() {
        client = LiteHttpClient.newApacheHttpClient(null);
        asyncExcutor = HttpAsyncExecutor.newInstance(client);
    }

    public HttpAsyncExecutor getAsyncExcutor() {
        return asyncExcutor;
    }
}
