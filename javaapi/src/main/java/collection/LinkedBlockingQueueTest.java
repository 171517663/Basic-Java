package collection;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {
    @Test
    public void test() throws InterruptedException {
        LinkedBlockingQueue queue = new LinkedBlockingQueue(3);
        queue.put("222");
        queue.put("222");
        queue.put("222");
        System.out.println("111============");
        queue.put("222");
        System.out.println("222============");
    }
}
