package com.test.component;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("annotation-beans.xml");
        User user = context.getBean("user", User.class);
        System.out.printf(user.toString());
    }
}
