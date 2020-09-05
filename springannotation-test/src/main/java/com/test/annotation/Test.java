package com.test.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public class Test {
    //Autowired按名字自动装配，字段名=xml中id名
    //@Autowired(required = false),为false的时候可以传入空值，为true的时候必须传入值
    @Autowired(required = false)
    User user;
    @Autowired
    @Qualifier("user2222")
    User2 user2;
    //先按名字装配byname，找不到，再按类型装配butype(bytype时要保证类型唯一)
    @Resource(name="user333")
    User3 user3;


    public User getUser() {
        return user;
    }

    public Test setUser(User user) {
        this.user = user;
        return this;
    }

    public User2 getUser2() {
        return user2;
    }

    public Test setUser2(User2 user2) {
        this.user2 = user2;
        return this;
    }

    public static void main(String[] args) {
        System.out.println(new Test().user2);
    }
}
