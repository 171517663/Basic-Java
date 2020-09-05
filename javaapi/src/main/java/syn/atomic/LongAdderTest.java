package syn.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Under low update contention, the two classes have similar
 * characteristics. But under high contention, expected throughput of
 * this class(LongAdder) is significantly higher, at the expense of higher space
 * consumption.
 */

public class LongAdderTest {
    @Test
    public void test() {
        LongAdder longAdder = new LongAdder();
        AtomicLong atomicLong = new AtomicLong();

        longAdder.add(2);
        longAdder.add(3);
        longAdder.add(4);

        atomicLong.addAndGet(2);
    }
}
