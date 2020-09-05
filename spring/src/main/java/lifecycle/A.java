package lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class A implements InitializingBean, ApplicationContextAware, FactoryBean {

    String field;

    @Value("属性注入field2")
    String field2;

    ApplicationContext applicationContext;

    public A() {
        System.out.println("执行A类构造方法constructor()");
    }


    public String getField() {
        return field;
    }

    @Value("属性注入field")
    public void setField(String field) {
        System.out.println("执行set属性注入");
        this.field = field;
    }

    @PostConstruct
    void init() {
        System.out.println("执行注解@PostConstruct标注的方法");
    }

    @PreDestroy
    void destory() {
        System.out.println("@PreDestroy");
    }

    void initMedthod() {
        System.out.println("执行@Bean(initMethod = \"initMedthod\", destroyMethod = \"destroyMethod\")指定的方法====initMethod");
    }

    void destroyMethod() {
        System.out.println("destroyMethod");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行InitializingBean接口的方法：====afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("ApplicationContextAware");
    }

    //context.getBean("a")的时候返回getObject()的值。
    @Override
    public Object getObject() throws Exception {
        return "hahahaha";
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }

}
