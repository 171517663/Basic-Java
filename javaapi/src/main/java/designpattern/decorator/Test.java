package designpattern.decorator;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

//允许向一个现有的对象增强功能，同时又不改变其结构。（增强功能）
public class Test {

    public static void main(String[] args) {
        Sourcable source = new Source();

        // 装饰类对象
        Sourcable obj = new Decorator1(new Decorator2(new Decorator3(source)));
        obj.operation();
    }

}
