package jdk18;

import java.util.*;
import java.util.stream.Stream;

/**
 * Stream注意事项：
 * 1）一个Stream流只能调用一次方法
 * 2）Stream流调用返回的是新的Stream流
 * 3）Stream流必须调用终结方法,否则不执行
 *      终结方法：返回值类型不再是 Stream 接口自身类型的方法，因此不再支持类似StringBuilder 那样的链式调用。
 *              本小节中，终结方法包括 count 和 forEach 方法。
 *      非终结方法（函数拼接方法）：返回值类型仍然是 Stream 接口自身类型的方法，因此支持链式调用。
 *              （除了终结方法外，其余方法均为非终结方法。）
 */
public class StreamTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强", "张三丰");
        list.stream().filter(s -> s.startsWith("张")).filter(s -> s.length() == 3)
                .forEach(s -> System.out.println(s));
    }

    void dealList() {
        List<String> list = new ArrayList<>();
        // ...
        Stream<String> stream1 = list.stream();
        Set<String> set = new HashSet<>();
        // ...
        Stream<String> stream2 = set.stream();
        Vector<String> vector = new Vector<>();
        // ...
        Stream<String> stream3 = vector.stream();
    }

    void dealMap() {
        Map<String, String> map = new HashMap<>();
        // ...
        Stream<String> keyStream = map.keySet().stream();
        Stream<String> valueStream = map.values().stream();
        Stream<Map.Entry<String, String>> entryStream =
                map.entrySet().stream();
    }

    void dealArry() {
        String[] array = { "张无忌", "张翠山", "张三丰", "张一元" };
        Stream<String> stream = Stream.of(array);//基本数据类型不行
    }
}
