package nio;

import java.nio.ByteBuffer;

/**
 * 0 < mark < position < limit <capacity
 */
public class BufferTest {
    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println("==============");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        System.out.println("=====put===写状态=========");
        buf.put("abcd".getBytes());
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        buf.flip();
        System.out.println("======flip===切换到读状态=====");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        buf.get();
        System.out.println("=======get=======");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        buf.rewind();
        System.out.println("=======rewind=======");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        buf.mark();
        buf.reset();

    }
}
