package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.HashMap;

public class JedisTest {

    Jedis jedis = new Jedis("127.0.0.1", 6379);

    @Test
    public void dealHashMap() {
        HashMap map = new HashMap();
        map.put("1", "haha");
        map.put("2", "xixi");
        jedis.hmset("hashmap", map);
        System.out.println(jedis.hget("hashmap", "1"));
    }

    @Test
    public void testTX() {
        Transaction multi = jedis.multi();//开启事务;
        try {
            jedis.watch("name1");       //在执行事务期间name1属性发生变化，则会抛出异常。
            multi.set("name1", "xiaohuang");
            multi.set("name2", "xiaowan");
            multi.exec();//执行事务
        } catch (Exception e) {
            multi.discard();//放弃事务。如果事务执行失败，放弃事务则全部执行失败。
        }
        finally {
            System.out.println(jedis.get("name1"));
            jedis.close();
        }

    }
}
