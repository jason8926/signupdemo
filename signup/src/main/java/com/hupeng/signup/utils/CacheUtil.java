package com.hupeng.signup.utils;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hupeng.signup.model.callback.ArrayListResultCallback;
import com.hupeng.signup.model.callback.ObjectResultCallback;
import com.hupeng.signup.model.subclass.BaseClass;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by End User on 2015/8/3.
 */
public class CacheUtil {
    private static CacheUtil instance = null;
    private ACache mCache = null;
    public static CacheUtil getInstance(Context ctx) {
        if (instance == null) {
            synchronized (CacheUtil.class) {
                if (instance == null) {
                    instance = new CacheUtil(ctx);
                }
            }
        }
        return instance;
    }

    private CacheUtil(Context ctx) {
        mCache = ACache.get(ctx);
    }

    private String makeCacheKey(final String type ,final String objectId) {
        return  type + "_" + objectId;
    }

    public void putCache(final String type, final BaseClass baseClass, final boolean force) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String key = makeCacheKey(type, baseClass.getObjectId());
                if(force || !mCache.isExist(key)) {
                    mCache.put(key, new Gson().toJson(baseClass));
                }
            }
        }).start();
    }

    public void getCache(final String type, final String objectId, final ObjectResultCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = mCache.getAsString(makeCacheKey(type, objectId));
                if(callback != null) {
                    callback.result(new Gson().fromJson(json, BaseClass.class), 0);
                }
            }
        }).start();
    }

    public void putCache(final String type, final ArrayList<BaseClass> baseClassArrayList, final boolean force) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> idArrylist = new ArrayList<String>();
                for(BaseClass baseClass : baseClassArrayList) {
                    idArrylist.add(baseClass.getObjectId());
                    String key = makeCacheKey(type, baseClass.getObjectId());
                    if(force || !mCache.isExist(key)) {
                        mCache.put(makeCacheKey(type, baseClass.getObjectId()), new Gson().toJson(baseClass));
                    }
                }
                mCache.put(type, new Gson().toJson(idArrylist));
            }
        }).start();
    }

    public  void getCache(final String type, final ArrayListResultCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                String jsonStr = mCache.getAsString(type);
                Type collectionType = new TypeToken<List<String>>() {
                }.getType();
                ArrayList<BaseClass> baseClassArrayList = new ArrayList<BaseClass>();
                List<String> idArrylist = gson.fromJson(jsonStr, collectionType);
                for (String objectId : idArrylist) {
                    baseClassArrayList.add(gson.fromJson(makeCacheKey(type, objectId), BaseClass.class));
                }
                if(callback != null) {
                    callback.result(baseClassArrayList, 0);
                }
            }
        }).start();
    }
}
