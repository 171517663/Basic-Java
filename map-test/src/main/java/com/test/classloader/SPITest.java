package com.test.classloader;

import java.util.ServiceLoader;

public class SPITest {

    public static void main(String[] args) {
        ServiceLoader classloader = ServiceLoader.load(ClassloaderTest.class);
    }
}
