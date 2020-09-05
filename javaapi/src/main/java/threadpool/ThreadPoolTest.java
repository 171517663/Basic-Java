package threadpool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3,
//                300, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(6));
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3,
                300, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        //ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        for (int count = 0;count<9;count++) {
            pool.execute(new ThreadPoolTask(count));
        }
        pool.shutdown();
    }

    public static class ThreadTest {

        public static void main(String[] args) throws InterruptedException {

            Thread thread = new Thread(() -> yieldTest());
            thread.start();

            //用户线程不存在时，守护线程会自动销毁
            Thread thread2 = new Thread(() -> nonstop());
            thread2.setDaemon(true);//设为守护线程
            thread2.start();
            System.out.println("main结束========================================================================================================");
        }

        public static void yieldTest() {
            long start = System.currentTimeMillis();

            int count = 0;
            for (int i = 0; i<100000 ; i ++) {
                Thread.yield();
                count++;
            }

            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }

        public static void nonstop() {
            while (true) {
                System.out.println(System.currentTimeMillis());
            }
        }
    }
}
