package com.test.annotation.importAnno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 打印结果
 * ImportTest{
 * objNormal=classname:class com.test.annotation.importAnno.ObjNormal,
 * objConfigrationAnno=classname:class com.test.annotation.importAnno.ObjConfigrationAnno$$EnhancerBySpringCGLIB$$588a95a9,
 * testA=classname:class com.test.annotation.importAnno.TestA,
 * testB=classname:class com.test.annotation.importAnno.TestB}
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ImportConfig.class);
        ImportTest importTest = context.getBean("importTest", ImportTest.class);
        System.out.println(importTest);
    }
}
