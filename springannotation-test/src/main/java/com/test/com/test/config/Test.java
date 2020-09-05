package com.test.com.test.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        User user = context.getBean("togetUser", User.class);
        System.out.println(user);

        User user2 = context.getBean("getUser", User.class);
        System.out.println(user2);

        User user3 = context.getBean("getUserWithPet", User.class);
        System.out.println(user3);
    }
}
