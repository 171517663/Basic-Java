package com.spring.aop2;

public class MyPonitCut {
    String str;

    public String getStr() {
        return str;
    }

    public MyPonitCut setStr(String str) {
        this.str = str;
        return this;
    }

    @Override
    public String toString() {
        System.out.println("tostring");
        return "MyPonitCut{" +
                "str='" + str + '\'' +
                '}';
    }
}
