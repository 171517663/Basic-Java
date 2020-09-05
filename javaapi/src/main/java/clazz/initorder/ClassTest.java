package clazz.initorder;

public class ClassTest {
    static {
        System.out.println("ClassTest静态块....");
    }
    public ClassTest() {
        System.out.println("ClassTest构造器...");
    }
}
