package collection;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
        //queue.offer(5);
//        queue.put(8);
//        queue.put(3);
        Demo2 demo2 = new Demo2(queue);
        Demo1 demo1 = new Demo1(queue);
        Thread t1= new Thread(demo2);
        Thread t2= new Thread(demo1);
        t1.start();
        t2.start();
    }

}
/**
 * 模拟生产者
 * @author Administrator
 *
 */
class Demo1 implements Runnable{
    SynchronousQueue<Integer> queue = null;

    public Demo1(){

    }

    public Demo1(SynchronousQueue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        int rand = new Random().nextInt(1000);
        System.out.println(String.format("模拟生产者：%d",rand));
        try{
//            System.out.println("生产者sleep(3000)");
//            TimeUnit.SECONDS.sleep(3);
            System.out.println("生产者执行put方法，现在的时间：" +System.currentTimeMillis());
            queue.put(rand);
            System.out.println("生产者执行put方法结束，现在的时间：" +System.currentTimeMillis());

            System.out.println("生产者执行put方法，现在的时间：" +System.currentTimeMillis());
            queue.put(20);
            System.out.println("生产者执行put方法结束，现在的时间：" +System.currentTimeMillis());
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(queue.isEmpty());
    }

}
/**
 * 模拟消费者
 * @author Administrator
 *
 */
class Demo2 implements Runnable{
    SynchronousQueue<Integer> queue = null;

    public Demo2(){

    }

    public Demo2(SynchronousQueue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("消费者已经准备好接受元素了...");
        try{
            System.out.println("消费者sleep(3000)");
            TimeUnit.SECONDS.sleep(10);
            System.out.println(String.format("消费一个元素：%d", queue.take())+ "==现在的时间：" +System.currentTimeMillis());
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("------------------------------------------");
    }

}

