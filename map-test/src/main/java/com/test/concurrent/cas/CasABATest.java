package com.test.concurrent.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过test得知：ABA不感知
 */
public class CasABATest {
    public static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int expect = i.get();
                int update = expect + 1;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean b = i.compareAndSet(expect, update);
                System.out.println(b);
            }
        }, "主线程").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int expect = i.get();
                int update = expect + 1;
                i.compareAndSet(expect, update);
                i.compareAndSet(update, expect);
            }
        }, "干扰线程ABA").start();
    }
}
