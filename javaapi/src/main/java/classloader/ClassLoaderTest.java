package classloader;

import java.util.HashMap;

public class ClassLoaderTest {
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(object.getClass().getClassLoader());
        System.out.println("=============================");

        System.out.println(ClassLoaderTest.class.getClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent());
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent().getParent());
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}
