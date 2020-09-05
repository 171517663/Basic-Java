package thread;

public class SleepTest {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(0); // 直接进入就绪队列
        Thread.sleep(2); // 进入等待队列
    }
}
