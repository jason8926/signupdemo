package com.hupeng.signup.model.callback;

/**
 * Created by End User on 2015/8/3.
 */
public interface ObjectResultCallback<T> {
    void result(T result, int code);
}
