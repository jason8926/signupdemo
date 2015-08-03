package com.hupeng.signup.model.subclass;

import java.util.ArrayList;

/**
 * Created by End User on 2015/7/30.
 */
public class UserClass extends BaseClass {
    private  String            name;
    private  int               age;
    private  ArrayList<String> girl_friends;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<String> getGirl_friends() {
        return girl_friends;
    }

    public void setGirl_friends(ArrayList<String> girl_friends) {
        this.girl_friends = girl_friends;
    }
}
