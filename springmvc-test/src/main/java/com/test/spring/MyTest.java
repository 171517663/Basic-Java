package com.test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Hello hel = (Hello) context.getBean("hello");
        System.out.println(hel);

        //constructor-arg标签和name字段验证
        User user = (User)context.getBean("userAlias");
        System.out.println(user);

        User user2 = (User)context.getBean("userAlias2");
        System.out.println(user2);
    }
}
