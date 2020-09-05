package com.test.spi;

public class Bird implements Fly {
    @Override
    public void flying() {
        String str = "111";
        String str2 = "222";
        str.equals(str2);
        System.out.println("bird flying......");
    }
}
