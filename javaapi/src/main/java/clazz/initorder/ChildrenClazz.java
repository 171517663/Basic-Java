package clazz.initorder;

public class ChildrenClazz extends ParentClazz{
    static {
        System.out.println("ChildrenClazz静态代码块。。。");
    }

    public ChildrenClazz() {
        System.out.println("ChildrenClazz构造器。。。");
    }
}
