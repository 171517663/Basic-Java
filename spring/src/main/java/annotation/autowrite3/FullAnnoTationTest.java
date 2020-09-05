package annotation.autowrite3;

import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FullAnnoTationTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);

        Dog dog = context.getBean("dog", Dog.class);
        System.out.println(dog);

        Cat cat = context.getBean("cat", Cat.class);
        System.out.println(cat);

        Bird bird = context.getBean("getBird", Bird.class);
        System.out.println(bird);
    }
}
