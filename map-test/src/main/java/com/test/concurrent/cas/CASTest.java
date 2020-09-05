package com.test.concurrent.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * java实现简单的CAS
 * C-compare;S-赋值
 *
 *
 * CountDownLatch类的使用---放在finally块中
 */
public class CASTest {
    public static volatile int count = 0;

    public static synchronized boolean compareAndSwap(int var1, int var2){
        if(getI() == var1) {
            count = var2;
            System.out.println(count);
            return true;
        }
        return false;
    }

    public static int getI() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i=0; i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        TimeUnit.MILLISECONDS.sleep(5);
                        int expectcount;
                        while (!compareAndSwap(expectcount = getI(), expectcount+1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            }).start();

        }
        latch.await();
        System.out.println("count="+count);
    }
}
