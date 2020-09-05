package initbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        A a1 = context.getBean(A.class);
        A a2 = context.getBean(A.class);
        System.out.println("a1 = a2 :" + (a1 == a2));

        System.out.println(new D().toString());
        System.out.println(context.getBean("d"));
        System.out.println(context.getBean(C.class));
        System.out.println(context.getBean("b"));
        System.out.println(context.getBean(B.class));
    }
}
