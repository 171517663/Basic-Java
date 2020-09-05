package com.test.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe类绕过JVM直接操作底层
 */
public class CASUtil {
    public static Unsafe reflectGetUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return (Unsafe) field.get(null);
    }

}
