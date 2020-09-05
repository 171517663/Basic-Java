package com.test.spi;

import sun.misc.Launcher;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 双亲委派的实际顺序
 * findLoadedClass
 * 委托parent加载器加载（这里注意bootstrap加载器的parent为null)
 * 自行加载
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        ServiceLoader<Fly> fly = ServiceLoader.load(Fly.class);

        Iterator<Fly> flyIterator = fly.iterator();
        flyIterator.hasNext();
        flyIterator.next().flying();


        Class cl= Launcher.class;
        DriverManager.getConnection("");
    }
}
