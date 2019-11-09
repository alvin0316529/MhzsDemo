package com.qfh.utils;

import com.qfh.domain.User;

import java.util.Random;

public class MockData {
    public static User getUser(){
        User user = new User();
        int no = new Random().nextInt(20);
        user.setUserId("userid" + no);
        user.setUserName("username" + no);
        user.setRealName("realname" + no);
        user.setAge(new Random().nextInt(30));
        user.setCity("city" + new Random().nextInt(5));
        user.setProfessional("pro" + new Random().nextInt(3));
        return user;
    }
}
