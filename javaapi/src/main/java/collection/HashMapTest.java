package collection;

import java.util.*;

public class HashMapTest {
    public static class T1 extends Thread
    {
        private Map map;

        public T1(Map map)
        {
            this.map = map;
        }

        public void run()
        {
            Set set = map.entrySet();
            for (Object i : set)
            {
            }
        }
    }

    public static class T2 extends Thread
    {
        private Map map;

        public T2(Map map)
        {
            this.map = map;
        }

        public void run()
        {
            for (int i = 0; i < map.size(); i++)
            {
                map.remove(i);
            }
        }
    }
    public static void main(String[] args)
    {
        Map map = new HashMap();

        for (int i = 0; i < 10000; i++)
        {
            map.put(i,i);
        }
        if (map.containsKey(1)) {}

        T1 t1 = new T1(map);
        T2 t2 = new T2(map);
        t1.start();
        t2.start();
    }
}
