package clazz.initorder;

public class ParentClazz {
    private ClassTest classTest= new ClassTest();
    //private static ClassTest classTest2= new ClassTest();
    //ChildrenClazz childrenClazz = new ChildrenClazz();

    static {
        System.out.println("ParentClazz静态代码块。。。");
    }

    public ParentClazz() {
        System.out.println("ParentClazz构造器。。。");
    }
}
