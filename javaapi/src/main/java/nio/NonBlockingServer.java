package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NonBlockingServer {

    public static void main(String[] args) throws IOException {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress( 1155));
            serverSocketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            //selector.select()是阻塞方法，直到监听到事件返回
            //selector.select(longtime)阻塞时间longtime，未监听到事件责返回
            //selector.wakeup()阻塞在select()方法的selector返回
            //selector.selectNow()立刻返回
            //selector.selectedKeys().iterator()获取到监听到事件的selectedkeys
            //SelectionKey.channel()获取到对应的channel
            while (selector.select() > 0) {
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();

                while (it.hasNext()) {
                    SelectionKey sk = it.next();

                    if(sk.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);

                    } else if(sk.isReadable()) {

                        SocketChannel socketChannel = (SocketChannel)sk.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int len = 0;
                        while ((len = socketChannel.read(byteBuffer)) > 0) {
                            byteBuffer.flip();
                            System.out.println(new String(byteBuffer.array(), 0 ,len));
                            byteBuffer.clear();
                        }

                    }
                    it.remove();
                }

            }
    }
}
