package threadpool;

import java.util.concurrent.Executors;

public class ExecutorsTest {
    public static void main(String[] args) {

        Executors.newCachedThreadPool();        //没有核心线程，队列不存放，全部直接提交最大线程任务
        Executors.newFixedThreadPool(5);        //只有核心线程，其他任务交给阻塞队列
        Executors.newScheduledThreadPool(5);        //
        Executors.newSingleThreadExecutor();        //只有1个核心线程，其他任务交给阻塞队列
        Executors.newWorkStealingPool();            //

    }
}
