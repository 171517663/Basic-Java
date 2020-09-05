package cycledependence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Test {
    public static void main(String[] args) {
//        //设置关闭循环依赖
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(Config.class);
//        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)context.getBeanFactory();
//        beanFactory.setAllowCircularReferences(false);
//        context.refresh();

        //默认开启循环依赖
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        A a = context.getBean("a", A.class);
        System.out.println(a);

    }
}
