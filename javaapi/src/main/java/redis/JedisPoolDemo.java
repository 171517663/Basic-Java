package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 	jedis多线程操作
 * 		jedis本身不是多线程安全的，这并不是jedis的bug，而是jedis的设计与redis本身就是单线程相关，jedis实例抽象的是发送命令相关，
 * 	一个jedis实例使用一个线程与使用100个线程去发送命令没有本质上的区别，所以没必要设置为线程安全的。但是如果需要用多线程方式
 * 	访问redis服务器怎么做呢？那就使用多个jedis实例，每个线程对应一个jedis实例，而不是一个jedis实例多个线程共享。一个jedis关
 * 	联一个Client，相当于一个客户端，Client继承了Connection，Connection维护了Socket连接，对于Socket这种昂贵的连接，一般都会做
 * 	池化，jedis提供了JedisPool。
 */
public class JedisPoolDemo {
    private static final ExecutorService pool = Executors.newCachedThreadPool();

    private static final CountDownLatch latch = new CountDownLatch(20);

    private static final JedisPool jPool = new JedisPool("127.0.0.1", 6379);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for(int i=0;i<20;i++){
            pool.execute(new RedisTest());
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);
        pool.shutdownNow();

    }

    static class RedisTest implements Runnable{

        @Override
        public void run() {
            Jedis jedis = jPool.getResource();
            int i = 1000;
            try{
                while(i-->0){
                    jedis.set("hello", "world");
                }
            }finally{
                jedis.close();
                latch.countDown();
            }
        }

    }

}