package redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class LettuceDemo {

    @Test
    public void testLettuce() throws InterruptedException {
        RedisClient redisClient = RedisClient.create(RedisURI.create("localhost", 6379));
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();
        ThreadPoolExecutor t = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        t.submit(()->{
            String key1 =  syncCommands.set("key1","111");
            System.out.println(syncCommands.get("key1"));
        });
        t.submit(()->{
            String key2 =  syncCommands.set("key2", "Hello, Redis!");
            System.out.println(syncCommands.get("key2"));
        });
        Thread.sleep(2000);
        connection.close();
        redisClient.shutdown();
    }
}
