package annotation.autowrite1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoWriteTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("annotation-beans1.xml");
        Person person = context.getBean("person", Person.class);
        System.out.println(person);
    }
}
