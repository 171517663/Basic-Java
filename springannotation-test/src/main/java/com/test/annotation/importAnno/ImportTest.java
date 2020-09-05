package com.test.annotation.importAnno;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//该类展示@Import的使用方式
//@Import把类注册到ico容器中
@Component
public class ImportTest {
    @Autowired
    ObjNormal objNormal;

    @Autowired
    ObjConfigrationAnno objConfigrationAnno;

    @Autowired
    TestA testA;

    @Autowired
    TestB testB;

    @Test
    void test() {
        System.out.println(objNormal);
        System.out.println(objConfigrationAnno);
        System.out.println(testA);
        System.out.println(testB);
    }

    @Override
    public String toString() {
        return "ImportTest{" +
                "objNormal=" + objNormal +
                ", objConfigrationAnno=" + objConfigrationAnno +
                ", testA=" + testA +
                ", testB=" + testB +
                '}';
    }
}
