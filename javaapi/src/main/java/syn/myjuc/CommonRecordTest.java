package syn.myjuc;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * 指令重排演示
 */
public class CommonRecordTest {
    int a = 0;
    boolean flag = false;

    public void writer(){
        a =1;
        flag = true;
        ArrayList<String> test = new ArrayList<>();
    }

    public void reader(){
        if(flag){
//            System.out.println("执行中 ");
            if(a == 0) {
                System.out.println("==============发生了指令重排，此时a的值是：" + a);
            }
        }
    }

    public static void main(String[] args){
        Semaphore windows = new Semaphore(100);//声明100个窗口,这里声明默认的是非公平锁，防止资源耗尽
        for(;;){
            CommonRecordTest disorderTest = new CommonRecordTest();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        windows.acquire();//占用窗口
                        disorderTest.reader();
                        windows.release();//释放窗口，这里释放以后，第后面的线程才能继续获取
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        windows.acquire();//占用窗口
                        disorderTest.writer();
                        windows.release();//释放窗口
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
