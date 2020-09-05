package com.test.classloader;

import sun.misc.Launcher;

public class ClassloaderTest {




    public static void main(String[] args) {
        ClassloaderTest.class.getClassLoader();
        Launcher.getLauncher();
        System.out.println(System.getProperty("java.security.manager"));

        new ClassloaderTest().hello("nihao");
    }

    void hello(String ha) {
        System.out.println("hi" + ha);
        return;
    }

    public  static  void nihao(String hi) {
        int t=0;
        System.out.println("haha"+hi+t);
    }
}
