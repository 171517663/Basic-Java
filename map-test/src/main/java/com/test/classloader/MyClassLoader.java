package com.test.classloader;

/**
 *  *     class NetworkClassLoader extends ClassLoader {
 *  *         String host;
 *  *         int port;
 *  *
 *  *         public Class findClass(String name) {
 *  *             byte[] b = loadClassData(name);
 *  *             return defineClass(name, b, 0, b.length);
 *  *         }
 *  *
 *  *         private byte[] loadClassData(String name) {
 *  *             // load the class data from the connection
 *  *             &nbsp;.&nbsp;.&nbsp;.
 *  *         }
 *  *     }
 */
public class MyClassLoader extends ClassLoader{

    //指定父加载器
    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    //默认appclassloader为父加载器
    public MyClassLoader() {
        super();
    }

    public Class findClass(String name) {
    byte[] b = loadClassData(name);
    return defineClass(name, b, 0, b.length);
    }

    //加载class文件为二进制数组
    private byte[] loadClassData(String name) {
    // load the class data from the connection
    //&nbsp;.&nbsp;.&nbsp;.
        return new byte[2];
    }
}
