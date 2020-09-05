package syn.aqs;
import java.util.concurrent.locks.ReentrantLock;
public class LockTest {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                lockTest(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }).start();
        Thread thread2 = new Thread(() -> {
            try {
                lockTest(1000000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        });

        //thread1.start();
        thread2.start();
    }

    static void lockTest(int time) throws InterruptedException {
        System.out.println("=======开始======");
        lock.lock();
        System.out.println("进入锁");
        Thread.sleep(time);
        lock.unlock();
        System.out.println("=========结束========");
    }

}
