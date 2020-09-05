package autowrite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoWriterTest {

    //自动装配cat、dog到对象中。<bean id="person" class="autowrite.Person" autowire="byName"/>
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Person person = context.getBean("person", Person.class);
        System.out.println(person.toString());
    }
}
