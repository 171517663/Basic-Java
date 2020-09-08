package syn.myjuc;

/**
 * 可见性演示实例
 */
public class VolatileTest {
    // private volatile boolean isContinueRun=true;
    private boolean isContinueRun=true;

    public void runMethod(){
        while (isContinueRun == true){}
        System.out.println("停止下来");
    }
    public void stopMethod(){
        isContinueRun=false;
    }
    public static void main(String[] args) throws InterruptedException {
        VolatileTest service=new VolatileTest();
        ThreadA threadA=new ThreadA(service);
        threadA.start();
        Thread.sleep(1000);
        ThreadB threadB=new ThreadB(service);
        threadB.start();
        System.out.println("命令已经停止");
    }

}


// 我们设置两个线程
class ThreadA extends Thread {
    private VolatileTest service;

    public ThreadA(VolatileTest service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.runMethod();
    }
}
class ThreadB extends Thread {
    private VolatileTest service;

    public ThreadB(VolatileTest service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.stopMethod();
    }
}
