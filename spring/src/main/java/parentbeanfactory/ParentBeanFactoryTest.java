package parentbeanfactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import parentbeanfactory.module1.Module1Config;
import parentbeanfactory.module1.Service1;
import parentbeanfactory.module2.Module2Config;
import parentbeanfactory.module2.Service2;

public class ParentBeanFactoryTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext parent = new AnnotationConfigApplicationContext();
        parent.register(Module1Config.class);
        parent.refresh();
        Service1 service1 = parent.getBean("service1", Service1.class);
        service1.hello();

        AnnotationConfigApplicationContext son = new AnnotationConfigApplicationContext();
        son.register(Module2Config.class);
        son.setParent(parent);
        son.refresh();

        Service2 service2 = son.getBean("service2", Service2.class);
        service2.medthod();
        System.out.println(son.getBean("service1"));//parentbeanfactory.module2.Service1
        System.out.println("=========================");

        //不设置父子关系，相同的beanid在一个容器中会出现异常
        AnnotationConfigApplicationContext err = new AnnotationConfigApplicationContext(Module1Config.class, Module2Config.class);
        err.getBean("service1");

    }
}
