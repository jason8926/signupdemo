package com.hupeng.signup.model.callback;

import java.util.ArrayList;

/**
 * Created by End User on 2015/8/3.
 */
public interface ArrayListResultCallback<T> {
    void result(ArrayList<T> result, int code);
}
