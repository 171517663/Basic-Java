package thread;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();

        ThreadFactory threadFactory = new ThreadFactory() {
            AtomicInteger count = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("线程号：" + count.get() + "《》");
                count.getAndIncrement();
                return thread;
            }
        };

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(20,20,0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024),threadFactory);

        for(int i=0; i<200; i++) {
            int finalI = i;
            threadPool.execute(()->{
                System.out.println("========"+ finalI +"==========");
                System.out.println(DataUtil.parse("2019-05-01 23:58:56"));
            });
        }
        threadPool.shutdown();

    }

    static class DataUtil {
        private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        private static final ThreadLocal<DateFormat> THREAD_LOCAL =
                ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

        public static Date parse(String dataStr) {
            Date date = null;
            try {
                //date = sdf.parse(dataStr);
                date = THREAD_LOCAL.get().parse(dataStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
    }
}
