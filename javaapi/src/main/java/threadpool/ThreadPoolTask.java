package threadpool;

public class ThreadPoolTask implements Runnable{

    int i;
    public ThreadPoolTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("这是第" + i + "个线程====start");
        try {
            Thread.sleep(30000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("这是第" + i + "个线程=======end");
    }
}
