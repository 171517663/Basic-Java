package nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * java.nio.channels.Channel
 *     本地文件channel  |--FileChannel
 *     网络channel     |--SocketChannel
 *                    |--ServerSocketChannel
 *                    |--DatagramChannel
 *
 * 获取通道的方法
 *      1.java通过支持io的类提供了getChannel()方法
 *         1.本地io
 *             FileInputStream/FileOutputStream
 *             RandomAccessFile
 *         2.网络io
 *             Socket
 *             ServerSocket
 *             DatagramSocket
 *
 *      2.各个Channel提供了静态方法open()
 *
 *      3.Files.newByteChannel()
 *
 *
 */
public class ChannelTest {

    public static void main(String[] args) throws IOException {

        test2();
        test3();
    }

    //直接缓冲区
    public static void test1() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("D:\\code\\javaapi\\src\\main\\resources\\1.png"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\code\\javaapi\\src\\main\\resources\\3.png"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMapBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0 ,inChannel.size());
        MappedByteBuffer outMapBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0 ,inChannel.size());

        //直接对缓冲区进行数据读写
        byte[] dst = new byte[inMapBuffer.limit()];
        inMapBuffer.get(dst);
        outMapBuffer.put(dst);
    }

    //非直接缓冲器
    public static void test2() throws IOException {

        FileInputStream in = new FileInputStream("D:\\code\\javaapi\\src\\main\\resources\\1.png");
        FileOutputStream out = new FileOutputStream("D:\\code\\javaapi\\src\\main\\resources\\2.png");

        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        long begin = System.currentTimeMillis();
        while (inChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
        inChannel.close();
        outChannel.close();

        in.close();
        out.close();

    }

    //非通道方式
    public static void test3() {
            FileInputStream fis;
            FileOutputStream fos;
            try {
                fis = new FileInputStream("D:\\code\\javaapi\\src\\main\\resources\\1.png");
                fos = new FileOutputStream("D:\\code\\javaapi\\src\\main\\resources\\4.png");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
            // 使用byte数组读取方式，缓存1MB数据
            byte[] readed = new byte[1024];

            try {
                long begin = System.currentTimeMillis();
                while (fis.read(readed) != -1) {
                    fos.write(readed);
                }
                long end = System.currentTimeMillis();
                System.out.println(end - begin);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


}
