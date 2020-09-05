package lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 顺序↓
 * @PostConstruct
 * InitializingBean====afterPropertiesSet
 * @Bean(initMethod = "initMedthod")======initMethod
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("====================");
        System.out.println(context.getBean("getA"));
    }
}
