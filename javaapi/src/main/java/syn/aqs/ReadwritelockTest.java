package syn.aqs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadwritelockTest {
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {
        readwritelock();
    }
    static void readwritelock() {
        ThreadFactory threadFactory = new ThreadFactory() {
            int count = 0;
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("线程号码：" + count);
                System.out.println("threadFactory打印，线程号：" + thread.getName());
                count++;
                return thread;
            }
        };

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(7, 7, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), threadFactory);

        threadPool.execute(()->{
            writeLock.lock();
            System.out.println("========write start1================");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("========write end1================");
            writeLock.unlock();
        });

        for(int i = 0; i<6; i++) {
            threadPool.execute(()->{
                readLock.lock();
                try {
                    long start = System.currentTimeMillis();
                    System.out.println("==============线程内部打印====="
                            +Thread.currentThread().getName()
                            +"=====current time:" + start);
                    Thread.sleep(2999);
                    long end = System.currentTimeMillis();
                    System.out.println("===========结束============="
                            +Thread.currentThread().getName()
                            +"=====current time:" + end + "=====" + String.valueOf(end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readLock.unlock();
                }
            });
        }

        threadPool.execute(()->{
            writeLock.lock();
            System.out.println("========write start2================");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("========write end2================");
            writeLock.unlock();
        });

    }
}
