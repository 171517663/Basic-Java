package com.spring.aop2;

import com.spring.aop.DaoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans2.xml");
        MyPonitCut ponit = context.getBean("pointCut", MyPonitCut.class);
        System.out.println(ponit.toString());
    }
}
